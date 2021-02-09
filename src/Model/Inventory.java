/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author Myque
 */
public class Inventory {

    Stage stage;
    Parent scene;

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProduct = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProduct = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredPart = FXCollections.observableArrayList();

    //Adds passed in parts to the allParts array
    public static void addPart(Part part) {

        allParts.add(part);

    }
    //Adds passed in products to the allProducts array

    public static void addProduct(Product product) {

        allProduct.add(product);

    }

    public static Part lookupPart(int partId) {

        if (partId > allParts.size() || partId < 1) {
            System.out.println("Invaled Part ID");
            return null;
        } else {
            System.out.println("Part Found!");
            return allParts.get(partId - 1);
        }

    }

    public static Product lookupProduct(int ProductId) {

        if (ProductId > allProduct.size() || ProductId < 1) {
            System.out.println("Invaled Product ID");
            return null;
        } else {
            System.out.println("Product Found!");
            return allProduct.get(ProductId - 1);
        }
    }

    public static ObservableList<Part> lookupPart(String partName) {

        if (!(Inventory.filteredPart().isEmpty())) {
            Inventory.filteredPart().clear();
        }

        for (Part part : Inventory.filteredPart()) {

            if (part.getName().contains(partName)) {
                Inventory.filteredPart().add(part);
            }

        }

        if (Inventory.filteredPart().isEmpty()) {
            return Inventory.filteredPart();
        } else {
            return Inventory.filteredPart();
        }

    }

    public static ObservableList<Product> lookupProduct(String productName) {

        if (!(Inventory.filteredProduct().isEmpty())) {
            Inventory.filteredProduct().clear();
        }

        for (Product product : Inventory.getAllProducts()) {

            if (product.getName().contains(productName)) {
                Inventory.filteredProduct().add(product);
            }

        }

        if (Inventory.filteredProduct().isEmpty()) {
            return Inventory.getAllProducts();
        } else {
            return Inventory.filteredProduct();
        }

    }

    public static void updatePart(int index, Part selectedPart) {

        int aryNum = -1;

        for (Part part : Inventory.getAllParts()) {

            aryNum++;

            if (part.getID() == index) {

                Inventory.getAllParts().set(aryNum, selectedPart);
            }
        }

    }

    public static void updateProduct(int index, Product selectedProduct) {

        /* int aryNum = -1;
        
        for(Product product : Inventory.getAllProducts()){
            
            aryNum++;
            
            if (product.getID() == index){ */
        allProduct.set(index, selectedProduct);
        //}    
        //}     
    }

    public static void deletePart(Part selectedPart) {

        //Deletes a part for an ID
        for (Part part : Inventory.getAllParts()) {

            if (part.getID() == selectedPart.getID()) {
                Inventory.getAllParts().remove(part);
            }

        }
    }

    public static void deleteProduct(Product selectedProduct) {

        //Deletes a product for an ID
        for (Product product : Inventory.getAllProducts()) {

            if (product.getID() == selectedProduct.getID()) {
                Inventory.getAllProducts().remove(product);
            }

        }

    }

    public static ObservableList<Part> getAllParts() {

        return allParts;

    }

    public static ObservableList<Product> getAllProducts() {

        return allProduct;

    }

    public static ObservableList<Product> filteredProduct() {

        return filteredProduct;

    }

    public static ObservableList<Part> filteredPart() {

        return filteredPart;

    }

    public static Part selectPart(int id) {

        for (Part part : getAllParts()) {

            if (part.getID() == id) {
                return part;
            }
        }
        return null;
    }

    public static Product selectProduct(int id) {

        for (Product product : getAllProducts()) {

            if (product.getID() == id) {
                return product;
            }
        }
        return null;
    }

    public static Part selectPartPrice(Double price) {

        for (Part part : getAllParts()) {

            if (part.getPrice() == price) {
                return part;
            }
        }
        return null;
    }

    public static Product selectProductPrice(Double price) {

        for (Product product : getAllProducts()) {

            if (product.getPrice() == price) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> filterParts(String Name) {

        if (!(Inventory.filteredPart().isEmpty())) {
            Inventory.filteredPart().clear();
        }

        for (Part part : Inventory.getAllParts()) {

            if (part.getName().contains(Name)) {
                Inventory.filteredPart().add(part);
            }

        }

        if (Inventory.filteredPart().isEmpty()) {
            return Inventory.getAllParts();
        } else {
            return Inventory.filteredPart();
        }
    }

    public static ObservableList<Product> filterProducts(String Name) {

        if (!(Inventory.filteredProduct().isEmpty())) {
            Inventory.filteredProduct().clear();
        }

        for (Product product : Inventory.getAllProducts()) {

            if (product.getName().contains(Name)) {
                Inventory.filteredProduct().add(product);
            }

        }

        if (Inventory.filteredProduct().isEmpty()) {
            return Inventory.getAllProducts();
        } else {
            return Inventory.filteredProduct();
        }
    }

    public static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isNegative(int inventory, Double price, int min, int max) {

        if (inventory < 0 || price < 0 || min < 0 || max < 0) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Illegal Argument");
            alert.setContentText("Negative number are not allowed.");
            alert.showAndWait();
            return true;

        } else {
            return false;
        }

    }

    public static boolean inventoryCheck(int inventory, int min, int max) {

        if (inventory <= max && inventory >= min) {
            return true;
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Illegal Argument");
            alert.setContentText("The Stock: " + inventory + " must be between the"
                    + " Min stock amount: " + min + " and the Max stock amount: " + max + "!");
            alert.showAndWait();
            return false;

        }

    }

    public static void deleteAlert() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Null Pointer Exception");
        alert.setContentText("Please select an Item to delete from the Table.");
        alert.showAndWait();

    }

}
