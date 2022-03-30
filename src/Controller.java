import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
// import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;


public class Controller implements Initializable {

    private ObservableList<Item> itemList;
    private Item orderItem;

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Item> tbData;

    @FXML
    public TableColumn<Item, String> item;

    @FXML
    public TableColumn<Item, String> category;

    @FXML
    public TableColumn<Item, Double> price;

    @FXML
    private Label message;

    @FXML
    void openLoginPage(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        ObservableList<Item> singleItem = tbData.getSelectionModel().getSelectedItems();
        
        if(singleItem.size() > 0) {

            orderItem   = singleItem.get(0);
            Order order = new Order(orderItem);
            Observer.setObservableOrderList(order);

            System.out.println (order.generateString());
            message.setText("Order Placed, Thank You !");
            message.setTextFill(Color.BLUE);

            try {
                Observer.updateObservableOrderList();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            message.setText("Please Select an item");
            message.setTextFill(Color.color(1, 0, 0));
        }

    }

    @FXML
    void openOrderPage(ActionEvent event) throws IOException {
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

    // private ObservableList<Item> itemList = FXCollections.observableArrayList(
            // new Item("COFFEE", "Baverage", 1.69),
            // new Item("TEA", "Baverage", 1.88)
    // );

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

}
