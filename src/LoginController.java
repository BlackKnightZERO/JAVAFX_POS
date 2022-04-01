import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.scene.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.collections.ObservableList;

public class LoginController {

    private ObservableList<Admin> adminList;

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField inputEmailorUsername;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Label output;

    @FXML
    void handleLoginSubmit(ActionEvent event) throws IOException {
        try {

            adminList                  = Observer.getObservableAdminList();

            String emailOrUserName     = inputEmailorUsername.getText();
            String password            = inputPassword.getText();

            for(Admin a : adminList) {
                if( a.getEmail().equals(emailOrUserName) && a.getPassword().equals(password) ) {
                    output.setTextFill(Color.BLACK);
                    output.setText("");

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

}
