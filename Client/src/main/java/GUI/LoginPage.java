package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import services.MonitorizareException;
import services.ServerInterface;

import java.io.IOException;

public class LoginPage {
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    Button loginButton;
    @FXML
    Button exitButton;

    Stage primaryStage;
    private ServerInterface server;

    public void setServer(ServerInterface server){
        this.server = server;
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    private Stage createWindow(BorderPane rootLayout){
        Stage primaryStage = new Stage();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        return primaryStage;
    }

    private void showErrorMessage(String s){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Eroare");
        message.setContentText(s);
        message.showAndWait();
    }

    public void loginButtonHandler(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String parola = passwordTextField.getText();
        try{
            User user = new User();
            user.setUsername(username);
            user.setParola(parola);
            try{
                passwordTextField.setText("");
                FXMLLoader loaderProgramator = new FXMLLoader(getClass().getClassLoader().getResource("AppFX/fereastraProgramator.fxml"));
                BorderPane rootLayoutProgramator = loaderProgramator.load();
                ProgramatorPage programatorPage = loaderProgramator.getController();

                FXMLLoader loaderVerificator = new FXMLLoader(getClass().getClassLoader().getResource("AppFX/fereastraVerificator.fxml"));
                BorderPane rootLayoutVerificator = loaderVerificator.load();
                VerificatorPage verificatorPage = loaderVerificator.getController();

                FXMLLoader loaderUtilizator = new FXMLLoader(getClass().getClassLoader().getResource("AppFX/fereastraUtilizatorExtern.fxml"));
                BorderPane rootLayoutUtilizator = loaderUtilizator.load();
                UtilizatorExternPage utilizatorPage = loaderUtilizator.getController();

                User loggedUser = server.login(user, programatorPage, verificatorPage, utilizatorPage);
                if(loggedUser.getClass() == Programator.class){
                    programatorPage.setServer(server);
                    programatorPage.setLoggedUser((Programator)loggedUser);
                    programatorPage.setEnvironment();
                    Stage primaryStageProgramatorPage = createWindow(rootLayoutProgramator);
                    programatorPage.setPrimaryStage(primaryStageProgramatorPage);
                    primaryStageProgramatorPage.show();
                } else if(loggedUser.getClass() == Verificator.class){
                    verificatorPage.setServer(server);
                    verificatorPage.setLoggedUser((Verificator)loggedUser);
                    verificatorPage.setEnvironment();
                    Stage primaryStageVerificatorPage = createWindow(rootLayoutVerificator);
                    verificatorPage.setPrimaryStage(primaryStageVerificatorPage);
                    primaryStageVerificatorPage.show();
                } else if(loggedUser.getClass() == UtilizatorExtern.class){
                    utilizatorPage.setServer(server);
                    utilizatorPage.setLoggedUser((UtilizatorExtern)loggedUser);
                    utilizatorPage.setEnvironment();
                    Stage primaryStageUtilizatorPage = createWindow(rootLayoutUtilizator);
                    utilizatorPage.setPrimaryStage(primaryStageUtilizatorPage);
                    primaryStageUtilizatorPage.show();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }catch (MonitorizareException me){
            if(me.getMessage().equals("null")) {
                showErrorMessage("Numele de utilizator/parola gresite...");
            } else {
                showErrorMessage("Userul este deja logat!");
            }
        }
    }

    public void exitButtonHandler(ActionEvent actionEvent) {
        this.primaryStage.close();
    }
}
