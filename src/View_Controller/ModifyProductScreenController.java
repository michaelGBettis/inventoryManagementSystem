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
public class ModifyProductScreenController implements Initializable {

    Stage stage;
    Parent scene;
    Product modifyProduct; 
    int productIndex;
    @FXML
    private TextField productNameBox;
    @FXML
    private TextField invetoryBox;
    @FXML
    private TextField priceCostBox;
    @FXML
    private TextField maxProductBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private AnchorPane modifyProductScreenPane;
    @FXML
    private Button modifySearchButton;
    @FXML
    private TextField modifySearchBar;
    @FXML
    private TableView<Part> modifyToAddTableView;
    @FXML
    private TableColumn<Part, Integer> modifyToAddID;
    @FXML
    private TableColumn<Part, String> modifyToAddName;
    @FXML
    private TableColumn<Part, Integer> modifyToAddStock;
    @FXML
    private TableColumn<Part, Double> modifyToAddPrice;
    @FXML
    private TableView<Part> modifyToDeleteTableView;
    @FXML
    private TableColumn<Part, Integer> modifyToDeleteID;
    @FXML
    private TableColumn<Part, String> modifyToDeleteName;
    @FXML
    private TableColumn<Part, Integer> modifyToDeleteStock;
    @FXML
    private TableColumn<Part, Double> modifyToDeletePrice;
    @FXML
    private TextField productIDBox;
    @FXML
    private TextField minProductBox;
    
    public void recieveProduct(Product product){
                
         //Fills the modifyToDeleteTableView with data from getAssociatedParts
        modifyToDeleteTableView.setItems(product.getAssociatedParts());
        modifyToDeleteID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        modifyToDeleteName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        modifyToDeletePrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        modifyToDeleteStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        
        
        
        this.modifyProduct = product;
        productIDBox.setText(String.valueOf(product.getID()));
        productNameBox.setText(product.getName());
        invetoryBox.setText(String.valueOf(product.getStock()));
        priceCostBox.setText(String.valueOf(product.getPrice())); 
        maxProductBox.setText(String.valueOf(product.getMax()));
        minProductBox.setText(String.valueOf(product.getMin()));
           
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Fills the modifyToDeleteTableView with data from getAllProducts
        modifyToAddTableView.setItems(Inventory.getAllParts());             
        modifyToAddID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        modifyToAddName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        modifyToAddPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        modifyToAddStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        
        
       
       
       
        
        
        
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
    private void saveButtonHandler(ActionEvent event) throws IOException{
               
         try{
            
            //Converting Textbox text to strings and passing
            //them to variables for the Product object
            int ID = Integer.parseInt(productIDBox.getText());
            String Name = productNameBox.getText();
            Double Price = Double.parseDouble(priceCostBox.getText());
            int Stock = Integer.parseInt(invetoryBox.getText());
            int Min = Integer.parseInt(minProductBox.getText());
            int Max = Integer.parseInt(maxProductBox.getText());       
            
            
            if(Inventory.inventoryCheck(Stock, Min, Max)== true && 
                    Inventory.isNegative(Stock, Price, Min, Max) == false){
                
                modifyProduct.setID(ID);
                modifyProduct.setName(Name);
                modifyProduct.setPrice(Price);
                modifyProduct.setMin(Min);
                modifyProduct.setMax(Max);

                
                
            }
            else
                throw new IllegalArgumentException();
                     
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
         
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
                
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
        
        if(modifyToAddTableView.getSelectionModel().getSelectedItem() == null){
            
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

                Part delete = modifyToDeleteTableView.getSelectionModel().getSelectedItem();
                modifyProduct.deleteAssociatedPart(delete);

                }catch(java.lang.NullPointerException e){



                    Inventory.deleteAlert();

                }catch(java.util.ConcurrentModificationException e2){           
                }       

            }
        }        
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
        
        if(modifyToAddTableView.getSelectionModel().getSelectedItem() == null){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Part to add from the Part Table.");
            alert.showAndWait();
            
        }
        
        else{
            
            //selects the part
            Part add = modifyToAddTableView.getSelectionModel().getSelectedItem(); 
            //adds it to the product being modified
            modifyProduct.addAssociatedPart(add);
            
        }        
        
        
                  
    }

    @FXML
    private void modifySearchButtonHandler(ActionEvent event) {
        
        String searchStr = modifySearchBar.getText();       
        
        if(Inventory.isStringInteger(searchStr) == true ){
            
            int id = Integer.parseInt(searchStr);
            
            Inventory.lookupProduct(id);
            modifyToAddTableView.getSelectionModel().select(Inventory.selectPart(id));
            modifyToDeleteTableView.getSelectionModel().select(Inventory.selectPart(id));
            
        }
        else{

            modifyToAddTableView.setItems(Inventory.filterParts(modifySearchBar.getText()));
            modifyToDeleteTableView.setItems(Inventory.filterParts(modifySearchBar.getText()));
            
        }
        
        modifySearchBar.setText("");
        
    }

    @FXML
    private void productIDBoxHandler(ActionEvent event) {
    }

    @FXML
    private void productNameBoxHandler(ActionEvent event) {
    }

    @FXML
    private void minProductBoxHandler(ActionEvent event) {
    }
   
}

