import GUI.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.ServerInterface;

public class StartRpcClientFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            ServerInterface server = (ServerInterface) factory.getBean("monitorizareService");

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AppFX/loginAplicatie.fxml"));
            Parent root = loader.load();

            LoginPage loginController = loader.getController();
            loginController.setServer(server);
            primaryStage.setScene(new Scene(root));
            loginController.setStage(primaryStage);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
