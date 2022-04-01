import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

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

    public void displayIncomeDashboard(String string) {
        incomeId.setText(string);
    }
    public void displayOrdersDashboard(String string) {
        orderId.setText(string);
    }
    public void displayItemsDashboard(String string) {
        itemId.setText(string);
    }
    public void displayDateDashboard() {
        dateId.setText("Date : "+LocalDate.now());
    }
    
}
