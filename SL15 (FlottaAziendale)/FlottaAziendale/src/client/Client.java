package client;
import client.controller.ClientInterfaceController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rilevatore.PacchettoVeicoloRilevato;

public class Client extends Application{
    private Stage primaryStage;
    private ObservableList<PacchettoVeicoloRilevato> vehicleList = FXCollections.observableArrayList();
    public ObservableList<PacchettoVeicoloRilevato> getVehicleList(){
        return vehicleList;
    }
    @Override
    public void start(Stage primaryStage){
	this.primaryStage = primaryStage;
	this.primaryStage.setTitle("FlottaAziendale");
        this.primaryStage.setResizable(false);
	initTableView();
    }
    private void initTableView(){
	try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("view/ClientInterface.fxml"));
            AnchorPane vehiclesOverview = (AnchorPane)loader.load();
            ClientInterfaceController controller = loader.getController();
            primaryStage.setScene(new Scene(vehiclesOverview));
            controller.setMainApp(this);
            primaryStage.show();
	}catch(IOException ex){
            ex.printStackTrace();
	}
    }
    public static void main(String[] args){
        launch(args);
    }
}