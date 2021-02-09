/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package michaelbettis_inventorysystem;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Myque
 */
public class MichaelBettis_InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainScreen.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Product product1 = new Product(1, "Bike", 900.00, 10, 3, 10);
        Product product2 = new Product(2, "E-Bike", 250.00, 10, 3, 15);
        Product product3 = new Product(3, "Skate board", 450.00, 10, 3, 15);
        Product product4 = new Product(4, "Paddle board", 100.00, 10, 3, 20);
        
        
        
        Outsourced outsourced1 = new Outsourced(2, "Wheel OS", 900.00, 10, 3, 10, false, "Wheel Inc");
        Outsourced outsourced2 = new Outsourced(4, "Seat OS", 250.00, 10, 3, 15, false, "Seat Co");
        Outsourced outsourced3 = new Outsourced(6, "Handle bars OS", 450.00, 10, 3, 15, false, "HandleBarsToGo");
        Outsourced outsourced4 = new Outsourced(8, "Teather Coard OS", 100.00, 10, 3, 20, false, "Cordage Inc.");
        Outsourced outsourced5 = new Outsourced(10, "Paddle OS", 150.00, 10, 3, 25, false, "PaddleIt");
        
        InHouse inHouse1 = new InHouse(1, "Frame IH", 900.00, 10, 3, 10, true, 10101);
        InHouse inHouse2 = new InHouse(3, "Board IH", 250.00, 10, 3, 15, true, 10102);
        InHouse inHouse3 = new InHouse(5, "Lights IH", 450.00, 10, 3, 15, true, 10103);
        InHouse inHouse4 = new InHouse(7, "Motor IH", 100.00, 10, 3, 20, true, 10104);
        InHouse inHouse5 = new InHouse(9, "Grip Tape IH", 150.00, 10, 3, 25, true, 10105);
        
        product1.addAssociatedPart(outsourced1);
        product1.addAssociatedPart(outsourced2);
        product1.addAssociatedPart(inHouse5);
                
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);
        Inventory.addPart(outsourced4);
        Inventory.addPart(outsourced5);
        
        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(inHouse3);
        Inventory.addPart(inHouse4);
        Inventory.addPart(inHouse5);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
                
        launch(args);
    }
    
}
