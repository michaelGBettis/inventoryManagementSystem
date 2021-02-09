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
import java.time.ZoneId;
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
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button searchPartButton;
    @FXML
    private TextField searchPartBar;
    @FXML
    private Button modifyPart;
    @FXML
    private Button searchProductButton;
    @FXML
    private TextField searchProductBar;
    @FXML
    private Button modifyProduct;
    @FXML
    private Button addPart;
    @FXML
    private Button deletePart;
    @FXML
    private Button addProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button exit;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productID;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, Double> productPrice;

    @FXML
    private TableColumn<Product, Integer> productStock;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Double> partPrice;

    @FXML
    private TableColumn<Part, Integer> partStock;
    @FXML
    private AnchorPane mainScreenPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Tells partTableView where to populate its data from 
        partTableView.setItems(Inventory.getAllParts());

        partID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        productTableView.setItems(Inventory.getAllProducts());

        productID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        
    }

    @FXML
    private void searchPartButtonHandler(ActionEvent event) {

        String searchStr = searchPartBar.getText();

        if (Inventory.isStringInteger(searchStr) == true) {

            int id = Integer.parseInt(searchStr);

            Inventory.lookupPart(id);
            partTableView.getSelectionModel().select(Inventory.selectPart(id));

        } else if (Inventory.isStringInteger(searchStr) == false) {

            partTableView.setItems(Inventory.filterParts(searchPartBar.getText()));

        } else {

            partTableView.getSelectionModel().select(Inventory.selectPartPrice(Double.parseDouble(searchStr)));

        }

        searchPartBar.setText("");

    }

    @FXML
    private void addPartHandler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addInhousePartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    private void modifyPartHandler(ActionEvent event) throws IOException {

        Part InHouseCheck = partTableView.getSelectionModel().getSelectedItem();

        try {

            if (InHouseCheck.isIsInHouse() == true) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modifyInhousePartScreen.fxml"));
                loader.load();

                ModifyInhousePartScreenController MIPSController = loader.getController();
                MIPSController.recieveIHP(partTableView.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            } else {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("modifyOutsourcedPartScreen.fxml"));
                loader.load();

                ModifyOutsourcedPartScreenController MOPSController = loader.getController();
                MOPSController.recieveOSP(partTableView.getSelectionModel().getSelectedItem());

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }

        } catch (java.lang.NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Part to modify from the Part Table.");
            alert.showAndWait();

        }

    }

    @FXML
    private void deletePartHandler(ActionEvent event) {

        if (partTableView.getSelectionModel().getSelectedItem() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Part to delete from the Part Table.");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "This will delete the selected Part, do you want to continue? ");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                try {

                    Part delete = partTableView.getSelectionModel().getSelectedItem();
                    Inventory.deletePart(delete);

                } catch (java.lang.NullPointerException e) {

                    Inventory.deleteAlert();

                } catch (java.util.ConcurrentModificationException e2) {
                }

            }
        }
    }

    @FXML
    private void searchProductButtonHandler(ActionEvent event) {

        String searchStr = searchProductBar.getText();

        if (Inventory.isStringInteger(searchStr) == true) {

            int id = Integer.parseInt(searchStr);

            Inventory.lookupProduct(id);
            productTableView.getSelectionModel().select(Inventory.selectProduct(id));

        } else {

            productTableView.setItems(Inventory.filterProducts(searchStr));

        }

        searchProductBar.setText("");

    }

    @FXML
    private void addProductHandler(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    private void modifyProductHandler(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyProductScreen.fxml"));
            loader.load();

            ModifyProductScreenController MPSController = loader.getController();
            MPSController.recieveProduct(productTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (java.lang.NullPointerException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Product to modify from the Product Table.");
            alert.showAndWait();

        }

    }

    @FXML
    private void deleteProductHandler(ActionEvent event) {

        if (productTableView.getSelectionModel().getSelectedItem() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null Pointer Exception");
            alert.setContentText("Please select a Product to delete from the Product Table.");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "This will delete the selected Product, do you want to continue? ");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                try {

                    Product delete = productTableView.getSelectionModel().getSelectedItem();
                    Inventory.deleteProduct(delete);

                } catch (java.lang.NullPointerException e) {

                    Inventory.deleteAlert();

                } catch (java.util.ConcurrentModificationException e2) {
                }

            }
        }

    }

    @FXML
    private void exitBottonHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "This will exit the program, do you want to continue? ");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            System.exit(0);

        }

    }

}
