import java.io.IOException;
import java.util.ArrayList;

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

public class AdminController {

    private Stage stage;
    private Scene scene;
    private ObservableList<Admin> adminList;

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
                    Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
            stage       = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene       = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
