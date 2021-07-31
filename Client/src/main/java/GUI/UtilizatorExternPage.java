package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.BUG;
import model.User;
import services.ObserverInterface;
import services.ServerInterface;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class UtilizatorExternPage extends UnicastRemoteObject implements Initializable, ObserverInterface {
    private ServerInterface server;
    private User loggedUser;
    private Stage primaryStage;
    private UtilizatorExternPage currentController;

    @FXML
    TextField denumireTextField;
    @FXML
    TextArea descriereTextArea;
    @FXML
    Button raportButton;

    public UtilizatorExternPage() throws RemoteException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private Stage createWindow(BorderPane rootLayout){
        Stage primaryStage = new Stage();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        return primaryStage;
    }

    public void setEnvironment(){ }

    public void setServer(ServerInterface server){
        this.server = server;
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setLoggedUser(User user){
        this.loggedUser = user;
    }

    public void setCurrentController(UtilizatorExternPage utilizatorController){
        this.currentController = utilizatorController;
    }
    private void showErrorMessage(String s){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Eroare");
        message.setContentText(s);
        message.showAndWait();
    }

    public void handleRaportButton(ActionEvent actionEvent) {
        String nume=denumireTextField.getText();
        String descriere= descriereTextArea.getText();
        if(nume.equals("")||descriere.equals("")) {
            showErrorMessage("Introduceti denumirea si descrierea BUG-ului!");
        }
        else {
            server.addBUG(new BUG(nume,descriere,"INREGISTRAT", loggedUser.getId(), 0));
            denumireTextField.setText("");
            descriereTextArea.setText("");
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Succes");
            message.setContentText("Ati adaugat BUG-ul cu succes!");
            message.showAndWait();
        }
    }

    public void handleLogoutButton(ActionEvent actionEvent) {
        this.primaryStage.hide();
        server.logout(this.loggedUser);
    }
}
