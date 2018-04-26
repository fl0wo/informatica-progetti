package client.controller;
import Server.Server;
import client.Client;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rilevatore.FlottaAziendale;
import rilevatore.PacchettoVeicoloRilevato;
public class ClientInterfaceController implements Initializable{
    private Client mainApp;
    private Server server;
    @FXML
    private TableView<PacchettoVeicoloRilevato> vehicleTable;
    @FXML
    private TableColumn<PacchettoVeicoloRilevato, String> vehicleColumn;
    @FXML
    private Label targa;
    @FXML
    private Label marca;
    @FXML
    private Label latitudine;
    @FXML
    private Label longitudine;
    @FXML
    private Label data;
    @FXML
    private Label ora;
    @FXML
    private Label nearestStop;
    public ClientInterfaceController(){
        server = new Server();
        server.start();
    }
    @FXML
    private void handleNewVehicleAction(ActionEvent event){
	PacchettoVeicoloRilevato tempVehicle = FlottaAziendale.newVehicle();
        mainApp.getVehicleList().add(tempVehicle);
    }
    @FXML
    private void handleUpdateVehicleAction(ActionEvent event){
        showVehicleDetails(vehicleTable.getSelectionModel().selectedItemProperty().getValue());
    }
    @FXML
    private void handleRemoveVehicleAction(ActionEvent event){
        int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0)
            vehicleTable.getItems().remove(selectedIndex);
        else
            showVehicleDetails(null);
    }
    public void setMainApp(Client mainApp){
        this.mainApp = mainApp;
        vehicleTable.setItems(mainApp.getVehicleList());
        vehicleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
	vehicleColumn.setCellValueFactory(cellData -> cellData.getValue().getVehicleMainInfo());
        vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {showVehicleDetails(newValue);});
    }
    private void showVehicleDetails(PacchettoVeicoloRilevato vehicle){
        try{
            ArrayList<PacchettoVeicoloRilevato> tempArray = server.rilevazioni;
            boolean found = false;
            PacchettoVeicoloRilevato tempPacket = null;
            if(vehicle != null)
                for(int i=0;i<tempArray.size();i++){
                    if(tempArray.get(i).getTarga().equals(vehicle.getTarga())){
                        tempPacket = tempArray.get(i);
                        found = true;
                        break;
                    }
                }
            targa.setText(tempPacket != null && found ? tempPacket.getTarga() : "");
            marca.setText(tempPacket != null && found ? tempPacket.getMarca() : "");
            latitudine.setText(tempPacket != null && found ? Double.toString(tempPacket.getLatitudine()) : "");
            longitudine.setText(tempPacket != null && found ? Double.toString(tempPacket.getLongitudine()) : "");
            data.setText(tempPacket != null && found ? tempPacket.getGiorno()+"/"+ 
                    tempPacket.getMese()+"/"+tempPacket.getAnno() : "");
            ora.setText(tempPacket != null && found ? tempPacket.getOra()+":"+ 
                    tempPacket.getMinuto()+":"+tempPacket.getSecondo() : "");
            nearestStop.setText(tempPacket != null && found ? server.getPosizione(tempPacket).stop_name : "");
            }catch(NullPointerException ex){}
    }
}