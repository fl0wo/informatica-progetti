package application.controller;
import application.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import loader.Loader;
import tabella.Tabella;
public class DictionaryInterfaceController implements Initializable{
    private MainApp mainApp;
    @FXML
    private Button translationMode;
    @FXML
    private TableView<String> dictionaryTable;
    @FXML
    private TableColumn<String, String> dictionaryColumn;
    @FXML
    private TextArea translation;
    private ArrayList<String> dictionary;
    private Tabella table;
    private boolean language = true;
    private String toTranslate = new String();
    private String previous = new String();
    @FXML
    private void handleTranslationModeAction(ActionEvent event){
        translationMode.setText(translationMode.getText().equals("INGLESE -> ITALIANO") ? "ITALIANO -> INGLESE" : "INGLESE -> ITALIANO");
        language = !language;
        mainApp.setDictionary(FXCollections.observableArrayList(dictionaryMode()));
        dictionaryTable.setItems(mainApp.getDictionary());
        translation.setText("");
    }
    @FXML
    private void handleTranslateAction(ActionEvent event){
        if(toTranslate != null && (previous == null || !previous.equals(toTranslate))){
            int pos = 0;
            if(toTranslate.length() > 90){
                toTranslate = toTranslate.substring(0, 90) + toTranslate.substring(91);
                if(toTranslate.substring(85).length() > 90){
                    toTranslate = toTranslate.substring(0, 179) + toTranslate.substring(180);
                }
            }
            for(int i=0;i<table.size();i++){
                if(table.getTable().get(i).getValue().equals(toTranslate))
                    pos = i;
            }
            long startTime = System.nanoTime();
            String result = table.get(table.getTable().get(pos).getKey());
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("Translation:\t" + result);
            System.out.println("HASH TABLE time: " + estimatedTime/1000 + " us\t");
            startTime = System.nanoTime();
            result = search(dictionary, dictionary.get(pos));
            estimatedTime = System.nanoTime() - startTime;
            System.out.println("LINEAR SEARCH time: " + estimatedTime/1000 + " us\t");
            translation.setText(result);
            previous = toTranslate;
        }
    }
    public void setMainApp(MainApp mainApp) throws IOException{
        this.mainApp = mainApp;
        dictionary = Loader.load("inglese_italiano.txt");
        table = new Tabella();
        for(int i=0;i<dictionary.size();i++)
            table.insert(table.hash(dictionary.get(i)), dictionary.get(i));
        for(int i=0;i<dictionary.size();i++){
            if(dictionary.get(i).length() > 90){
                dictionary.set(i, dictionary.get(i).substring(0, 90) + "\n" + dictionary.get(i).substring(90));
                if(dictionary.get(i).substring(90).length() > 90)
                    dictionary.set(i, dictionary.get(i).substring(0, 180) + "\n" + dictionary.get(i).substring(180));
            }
        }
        mainApp.setDictionary(FXCollections.observableArrayList(dictionaryMode()));
        dictionaryTable.setItems(mainApp.getDictionary());
        dictionaryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
	dictionaryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        dictionaryTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {toTranslate = newValue;});
    }
    private ArrayList<String> dictionaryMode(){
        ArrayList<String> tempDictionary = new ArrayList();
        if(language){
            for(int i=0;i<dictionary.size();i++)
                tempDictionary.add(dictionary.get(i++));
        }else
            for(int i=0;i<dictionary.size();i++)
                tempDictionary.add(dictionary.get(++i));
        previous = toTranslate = null;
        return tempDictionary;
    }
    private static String search(ArrayList<String> dictionary, String value){
        int language = dictionary.indexOf(value);
        for(int i=0;;i++){
            try{
                if(dictionary.get(i).equals(value))
                    return language%2 == 0 ? dictionary.get(i+1) : dictionary.get(i-1);
            }catch(IndexOutOfBoundsException ex){
                return null;
            }
        }
    }
}