/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Myque
 */
public class AddProductScreenController implements Initializable {

    Stage stage;
    Parent scene;
    Product newProduct; 

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private AnchorPane addProductScreenPane;
    @FXML
    private Button addSearchButton;
    @FXML
    private TextField addSearchBar;
    @FXML
    private TableView<Part> addToAddTableView;
    @FXML
    private TableColumn<Part, Integer> addToAddID;
    @FXML
    private TableColumn<Part, String> addToAddName;
    @FXML
    private TableColumn<Part, Integer> addToAddStock;
    @FXML
    private TableColumn<Part, Double> addToAddPrice;
    @FXML
    private TableView<Part> addToDeleteTableView;
    @FXML
    private TableColumn<Part, Integer> addToDeleteID;
    @FXML
    private TableColumn<Part, String> addToDeleteName;
    @FXML
    private TableColumn<Part, Integer> addToDeleteStock;
    @FXML
    private TableColumn<Part, Double> addToDeletePrice;
    @FXML
    private TextField minProductBox;
    @FXML
    private TextField productIDBox;
    @FXML
    private TextField productNameBox;
    @FXML
    private TextField invetoryBox;
    @FXML
    private TextField priceCostBox;
    @FXML
    private TextField maxProductBox;
    
   

    /**
     *
     */
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addToAddTableView.setItems(Inventory.getAllParts());
        addToAddID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        addToAddName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addToAddPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        addToAddStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));  
        
        newProduct = new Product(0,null,0,0,0,0);      
        
        addToDeleteTableView.setItems(newProduct.getAssociatedParts());
        addToDeleteID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        addToDeleteName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addToDeletePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        addToDeleteStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
    }    
     



    @FXML
    private void invetoryBoxHandeler(ActionEvent event) {
    }

    @FXML
    private void priceCostBoxHandler(ActionEvent event) {
    }

    @FXML
    private void maxProductBoxHandler(ActionEvent event) {
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        
        try{
        
            //Converting Textbox text to strings and passing
            //them to variables for the Product object
            int ID = Inventory.getAllProducts().size() + 1;
            String Name = productNameBox.getText();
            Double Price = Double.parseDouble(priceCostBox.getText());
            int Stock = Integer.parseInt(invetoryBox.getText());
            int Min = Integer.parseInt(minProductBox.getText());
            int Max = Integer.parseInt(maxProductBox.getText());
            
            //adds the values in the text boxes to the newProduct object
            newProduct.setID(ID);
            newProduct.setPrice(Price);
            newProduct.setName(Name);
            newProduct.setStock(Stock);
            newProduct.setMin(Min);
            newProduct.setMax(Max);
            
            //checks the stock level to make sure hey are in range of min and max
            //and that the values for min, max, price and stock aren't negative
            //then adds the newProduct to that addProduct list
            if(Inventory.inventoryCheck(Stock, Min, Max)== true && 
                    Inventory.isNegative(Stock, Price, Min, Max) == false){
                    
                Inventory.addProduct(newProduct);
                
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }
            else{
                throw new IllegalArgumentException(); }       
        
        }catch(java.lang.NumberFormatException e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Number Format Exception");
            alert.setContentText("Please enter a valid value in the text field.");
            alert.showAndWait();
            
        }catch(java.lang.IllegalArgumentException e2){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Number Format Exception");
            alert.setContentText("Please enter a valid value in the text field.");
            alert.showAndWait();
            
        }      

    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                        "This will clear the text fields, do you want to continue? ");   
                
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK){
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
        }
        
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
        
        if(addToDeleteTableView.getSelectionModel().getSelectedItem() == null){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Part to delete from the Part Table.");
            alert.showAndWait();
        }
        
        else{
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
                            "This will delete the selected Part, do you want to continue? ");   

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){

                try{

                Part delete = addToDeleteTableView.getSelectionModel().getSelectedItem();
                newProduct.deleteAssociatedPart(delete);

                }catch(java.lang.NullPointerException e){



                    Inventory.deleteAlert();

                }catch(java.util.ConcurrentModificationException e2){           
                }       

            }
        }
        
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
    
        if(addToAddTableView.getSelectionModel().getSelectedItem() == null){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Part to add from the Part Table.");
            alert.showAndWait();
            
        }
        
        else{
            
            Part add = addToAddTableView.getSelectionModel().getSelectedItem();        
            newProduct.addAssociatedPart(add);
            
        }        
        
    }
    
    @FXML
    private void searchButtonHandler(ActionEvent event) {
        
        String searchStr = addSearchBar.getText();       
        
        if(Inventory.isStringInteger(searchStr) == true ){
            
            int id = Integer.parseInt(searchStr);
            
            Inventory.lookupProduct(id);
            addToAddTableView.getSelectionModel().select(Inventory.selectPart(id));
            addToDeleteTableView.getSelectionModel().select(Inventory.selectPart(id));
            
        }
        else{

            addToAddTableView.setItems(Inventory.filterParts(addSearchBar.getText()));
            addToDeleteTableView.setItems(Inventory.filterParts(addSearchBar.getText()));
            
        }
        
        addSearchBar.setText("");
                
    }


    @FXML
    private void productNameBoxHandler(ActionEvent event) {
    }

    @FXML
    private void minProductBoxHandler(ActionEvent event) {
    }

    @FXML
    private void productIDBoxHandler(ActionEvent event) {
    }
    
}
