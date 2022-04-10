import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class InventoryController implements Initializable {

    private ObservableList<Item> itemList;
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Item> tbData;

    @FXML
    private TableColumn<Item, String> item;

    @FXML
    private TableColumn<Item, String> price;

    @FXML
    private TableColumn<Item, Double> category;


    @FXML
    private Label errorCategory;

    @FXML
    private Label errorName;

    @FXML
    private Label errorPrice;

    @FXML
    private TextField inputCategory;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPrice;

    @FXML
    private Label inventoryMsg;

    @FXML
    void handleAddItem(ActionEvent event) throws IOException {

        if( !inputName.getText().isEmpty() ) {
            if( !inputCategory.getText().isEmpty() ) {
                if( !inputPrice.getText().isEmpty() ) {
                    

                    try {

                        Item item= new Item( inputName.getText(), inputCategory.getText(), Double.parseDouble(inputPrice.getText()) );
                    
                        Observer.addItemToInventory(item);
                        
                        tbData.getItems().add(item);
                
                        inputName.clear();
                        inputCategory.clear();
                        inputPrice.clear();
                    
                    } catch ( NumberFormatException nex ) {
                        errorPrice.setText("Input is Invalid");
                        errorPrice.setTextFill(Color.RED);
                        nex.printStackTrace();
                    }  catch ( Exception ex ) {
                        ex.printStackTrace();
                    }


                } else {
                    this.showAlert("SPECIFY THE PRICE");
                } 
            } else {
                this.showAlert("SPECIFY THE CATOGERY");
            }
        } else {
            this.showAlert("SPECIFY THE ITEM");
        }

    }

    @FXML
    void handleDeleteItem(ActionEvent event) {

        int index = tbData.getSelectionModel().getSelectedIndex();

        if( index > -1 ) {
            try {
                Observer.deleteItemFromInventory(index);
                tbData.getItems().remove(index);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    @FXML
    void openDashboardPage(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
            Parent root = loader.load();
            DashboardController dashboardController = loader.getController();

            dashboardController.displayIncomeDashboard(Computed.getTotalIncome());
            dashboardController.displayOrdersDashboard(Computed.getTotalOrders());
            dashboardController.displayItemsDashboard(Computed.getTotalItems());
            dashboardController.displayDateDashboard();

            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    void openOrdersPage(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/orders.fxml"));
            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void openInventoryPage(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/inventory.fxml"));
            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/customer_view.fxml"));
            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item.setCellValueFactory(new PropertyValueFactory<>("Name"));
        category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        // tbData.setItems(itemList);
        try{
            itemList = Observer.getObservableItemList();
            tbData.setItems(itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String errorMessage) {
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setTitle("Error");
        alert.setContentText(errorMessage);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();
    }
    
}
