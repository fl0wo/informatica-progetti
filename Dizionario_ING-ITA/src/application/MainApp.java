package application;
import application.controller.DictionaryInterfaceController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class MainApp extends Application{
    private Stage primaryStage;
    private ObservableList<String> dictionary = FXCollections.observableArrayList();
    public void setDictionary(ObservableList<String> dictionary){
        this.dictionary = dictionary;
    }
    public ObservableList<String> getDictionary(){
        return dictionary;
    }
    @Override
    public void start(Stage primaryStage){
	this.primaryStage = primaryStage;
	this.primaryStage.setTitle("Dictionary");
        this.primaryStage.setResizable(false);
	initTableView();
    }
    private void initTableView(){
	try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/DictionaryInterface.fxml"));
            AnchorPane dictionary = (AnchorPane)loader.load();
            DictionaryInterfaceController controller = loader.getController();
            primaryStage.setScene(new Scene(dictionary));
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