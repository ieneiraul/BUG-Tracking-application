package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BUG;
import model.StatusBug;
import model.User;
import services.ObserverInterface;
import services.ServerInterface;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class VerificatorPage extends UnicastRemoteObject implements Initializable, ObserverInterface {
    private ServerInterface server;
    private User loggedUser;
    private Stage primaryStage;
    private VerificatorPage currentController;
    private ObservableList<BUG> buguri = FXCollections.observableArrayList();

    @FXML
    TableView<BUG> denumireTable;
    @FXML
    private TableColumn<BUG, String> denumireColumn;
    @FXML
    TextArea descriereTextArea;
    @FXML
    Button raportButton;
    @FXML
    TextField inregistrareDenumireTextField;
    @FXML
    TextArea inregistrareDescriereTextArea;


    public VerificatorPage() throws RemoteException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        denumireColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
    }
    private Stage createWindow(AnchorPane rootLayout){
        Stage primaryStage = new Stage();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        return primaryStage;
    }
    public void loadTable(){
        descriereTextArea.setText("");
        List<BUG> buguri = server.getAllBugsByStatus(StatusBug.INREGISTRAT);
        this.buguri.setAll(buguri);
        denumireTable.setItems(this.buguri);
    }

    public void setEnvironment(){
        denumireTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                String text= denumireTable.getSelectionModel().getSelectedItem().getDescriere();
                descriereTextArea.setText(text);
            }
        });
        loadTable();
    }

    private void showErrorMessage(String s){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Eroare");
        message.setContentText(s);
        message.showAndWait();
    }
    public void setServer(ServerInterface server){
        this.server = server;
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setLoggedUser(User user){
        this.loggedUser = user;
    }

    public void setCurrentController(VerificatorPage verificatorController){
        this.currentController = verificatorController;
    }

    public void handleLogoutButton(ActionEvent actionEvent) {
        this.primaryStage.hide();
        server.logout(this.loggedUser);
    }

    public void handleRaportButton(ActionEvent actionEvent) {
        String nume=inregistrareDenumireTextField.getText();
        String descriere= inregistrareDescriereTextArea.getText();
        if(nume.equals("")||descriere.equals("")) {
            showErrorMessage("Introduceti denumirea si descrierea BUG-ului!");
        }
        else {
            server.addBUG(new BUG(nume,descriere,"VERIFICAT", loggedUser.getId(), 0));
            inregistrareDenumireTextField.setText("");
            inregistrareDescriereTextArea.setText("");
            Alert message = new Alert(Alert.AlertType.INFORMATION);
            message.setTitle("Succes");
            message.setContentText("Ati adaugat BUG-ul cu succes!");
            message.showAndWait();
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) {
        loadTable();
    }

    public void handleRaportUtilizatorButton(ActionEvent actionEvent) {
        BUG bug=denumireTable.getSelectionModel().getSelectedItem();
        if(bug!=null) server.modificareStatusBUG(bug,StatusBug.VERIFICAT,bug.getId_creator());
        else showErrorMessage("Selectati un BUG!");
        loadTable();
    }
}
