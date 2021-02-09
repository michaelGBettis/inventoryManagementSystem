/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Myque
 */
public class ModifyOutsourcedPartScreenController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private RadioButton modifyInhouseRadioButton;
    @FXML
    private ToggleGroup partSource;
    @FXML
    private RadioButton modifyOutsourcedRadioButton;
    @FXML
    private TextField minProductbox;
    @FXML
    private TextField partNameBox;
    @FXML
    private TextField invetoryBox;
    @FXML
    private TextField priceCostBox;
    @FXML
    private TextField maxProductBox;
    @FXML
    private TextField companyNameBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane modifyOutsourcedPartScreenPane;
    @FXML
    private TextField partIDBox;
    
    public void recieveOSP(Part part){
        
        partIDBox.setText(String.valueOf(part.getID()));
        partNameBox.setText(part.getName());
        invetoryBox.setText(String.valueOf(part.getStock()));
        priceCostBox.setText(String.valueOf(part.getPrice())); 
        maxProductBox.setText(String.valueOf(part.getMax()));
        minProductbox.setText(String.valueOf(part.getMin()));
        if(part.isIsInHouse())
            System.out.println("InHouse");
        else
            System.out.println("Outsourced");
        if(part instanceof Outsourced)
            companyNameBox.setText(((Outsourced) part).getCompanyName());
        
                
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifyInhouseRadioButtonHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("modifyInhousePartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @FXML
    private void modifyOutsourcedRadioButtonHandler(ActionEvent event) {
    }

    @FXML
    private void minProductboxHandler(ActionEvent event) {
    }

    @FXML
    private void partNameBoxHandler(ActionEvent event) {
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
    private void companyNameBoxHandler(ActionEvent event) {
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        
        try{
            
            //Converting Textbox text to strings and passing
            //them to variables for the Product object
            int ID = Integer.parseInt(partIDBox.getText());
            String Name = partNameBox.getText();
            Double Price = Double.parseDouble(priceCostBox.getText());
            int Stock = Integer.parseInt(invetoryBox.getText());
            int Min = Integer.parseInt(minProductbox.getText());
            int Max = Integer.parseInt(maxProductBox.getText());
            boolean isInHouse = false;
            String companyName = companyNameBox.getText();
            
            if(Inventory.inventoryCheck(Stock, Min, Max)== true && 
                Inventory.isNegative(Stock, Price, Min, Max) == false){

                //Creating a new Product object and calling the  
                //Inventory.addProduct method 
                Inventory.updatePart(ID, new Outsourced(ID,Name,Price,Stock,Min,Max,isInHouse,companyName));
                

            }
            else
                throw new IllegalArgumentException();    

            //Clearing the values in the Textboxes on save botton click
            partIDBox.setText("");
            priceCostBox.setText("");
            invetoryBox.setText("");
            minProductbox.setText("");
            maxProductBox.setText("");

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show(); 

            System.out.println("Save Sucessful");
            
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
    private void partIDBoxHandler(ActionEvent event) {
    }
    
}
