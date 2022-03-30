import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class AdminController {

    private Stage stage;
    private Scene scene;
    private ObservableList<Admin> adminList;

    // loginform 
    @FXML
    private Label errorEmail;

    @FXML
    private Label errorPassword;

    @FXML
    private TextField inputEmail;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Label output; 

    // dashboard 
    @FXML
    private Label incomeId;

    @FXML
    private Label itemId;

    @FXML
    private Label orderId;

    @FXML
    private Label dateId;

    //inventory
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
    void handleLoginCancel(ActionEvent event) throws IOException {
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

    @FXML
    void handleLoginSubmit(ActionEvent event) throws IOException {
        try {

            adminList       = Observer.getObservableAdminList();

            String email    = inputEmail.getText();
            String password = inputPassword.getText();

            for(Admin a : adminList) {
                if( a.getEmail().equals(email) && a.getPassword().equals(password) ) {
                    output.setTextFill(Color.BLACK);
                    output.setText("");

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
                    Parent root = loader.load();
                    AdminController adminController = loader.getController();

                    adminController.displayIncomeDashboard(Computed.getTotalIncome());
                    adminController.displayOrdersDashboard(Computed.getTotalOrders());
                    adminController.displayItemsDashboard(Computed.getTotalItems());
                    adminController.displayDateDashboard();

                    stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene       = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    output.setTextFill(Color.RED);
                    output.setText("Invalid Login Credentials!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handleAddItem(ActionEvent event) {

    }

    @FXML
    void handleDeleteItem(ActionEvent event) {

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
    void openDashboardPage(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
            Parent root = loader.load();

            AdminController adminController = loader.getController();
            adminController.displayIncomeDashboard(Computed.getTotalIncome());
            adminController.displayOrdersDashboard(Computed.getTotalOrders());
            adminController.displayItemsDashboard(Computed.getTotalItems());
            adminController.displayDateDashboard();

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
