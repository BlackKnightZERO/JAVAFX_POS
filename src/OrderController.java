import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderController implements Initializable {

    private ObservableList<OrderView> orderList;

    private Stage stage;
    private Scene scene;

    @FXML
    private Label incomeId;

    @FXML
    private Label itemId;

    @FXML
    private Label orderId;

    @FXML
    private Label dateId;

    @FXML
    private TableColumn<OrderView, String> time;

    @FXML
    private TableColumn<OrderView, String> item;

    @FXML
    private TableColumn<OrderView, String> category;

    @FXML
    private TableColumn<OrderView, String> price;

    @FXML
    private TableView<OrderView> tbData;

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
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        item.setCellValueFactory(  new PropertyValueFactory<>("Name") );
        category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        //tbData.setItems(itemList);
        
        try{
            orderList = Observer.getObservableOrderViewList();
            tbData.setItems(orderList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
