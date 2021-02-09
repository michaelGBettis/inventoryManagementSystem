 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHouse;
import Model.Inventory;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Myque
 */
public class AddInhousePartScreenController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
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
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ToggleGroup addPartSource;
    @FXML
    private TextField machineIDBox;
    
    
       
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        
    }    
    
    @FXML
    private void inhouseRadioButtonHandler(ActionEvent event) {
    }

    @FXML
    private void outsourcedRadioButtonHandler(ActionEvent event) throws IOException {
        
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addOutsourcedPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
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
    private void saveButtonHandler(ActionEvent event) throws IOException {
        
        try{
            
            //Converting Textbox text to strings and passing
            //them to variables for the Product object
            int ID = Inventory.getAllParts().size() + 1;
            String Name = partNameBox.getText();
            Double Price = Double.parseDouble(priceCostBox.getText());
            int Stock = Integer.parseInt(invetoryBox.getText());
            int Min = Integer.parseInt(minProductbox.getText());
            int Max = Integer.parseInt(maxProductBox.getText());
            int machienId = Integer.parseInt(machineIDBox.getText());
            boolean isInHouse = true;    

            if(Inventory.inventoryCheck(Stock, Min, Max)== true && 
                    Inventory.isNegative(Stock, Price, Min, Max) == false){

                //Creating a new Product object and calling the  
                //Inventory.addProduct method 
                Inventory.addPart(new InHouse(ID,Name,Price,Stock,Min,Max,isInHouse, machienId));

            }
            else throw new IllegalArgumentException();

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
            //Clearing the values in the Textboxes on save botton click
            partNameBox.setText("");
            invetoryBox.setText("");
            priceCostBox.setText("");
            maxProductBox.setText("");
            minProductbox.setText("");
            minProductbox.setText("");

            System.out.println("Part Added Sucessfully!");
            
        }catch(java.lang.NumberFormatException e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Number Format Exception1");
            alert.setContentText("Please enter a valid value in the text field.");
            alert.showAndWait();
            
        }catch(java.lang.IllegalArgumentException e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);            
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Number Format Exception2");
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
    private void machineIDBoxHandler(ActionEvent event) {
    }
    
}
