/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Myque
 */
public class ModifyInhousePartScreenController implements Initializable {

    @FXML
    private ToggleGroup partSource;
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
    private TextField machineIDBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private RadioButton modifyInhouseRadioButton;
    @FXML
    private RadioButton modifyOutsourcedRadioButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void machineIDBoxHandler(ActionEvent event) {
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) saveButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "mainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) cancelButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "mainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyInhouseRadioButtonHandler(ActionEvent event) {
    }

    @FXML
    private void modifyOutsourcedRadioButtonHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) modifyOutsourcedRadioButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "modifyOutsourcedPartScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
