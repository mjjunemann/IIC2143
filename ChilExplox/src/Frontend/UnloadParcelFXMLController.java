/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.ArchiveType;
import Backend.ChilExplox;
import Backend.Mailbox;
import Backend.Message;
import Backend.MessageType;
import Backend.Order;
import Backend.Parcel;
import Backend.Record;
import Backend.Truck;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class UnloadParcelFXMLController implements Initializable, iController {

    ChilExploxApp main;
    Truck truck;
    ImageView selectedParcel;
    private Map<ImageView,ParcelView> trucksParcelsImgs = new HashMap();
    
    @FXML
    public TilePane parcelTile;
    @FXML
    public Button sendTruckBackButton;
    @FXML
    public Button sendTruckBackErrorButton;
    @FXML
    public Label plateTruckLabel;
    @FXML
    public Label typeTruckLabel;
    @FXML
    public Label stateTruckLabel;
    @FXML
    public Label originTruckLabel;
    @FXML
    public Label nParcelsTruckLabel;
    @FXML
    public Label maxSpaceLabel;
    @FXML
    public Label idParcelLabel;
    @FXML
    public Label priorityParcelLabel;
    @FXML
    public Label destinationParcelLabel;
    @FXML
    public Label stateParcelLabel;
    @FXML
    public Label typeParcelLabel;
    @FXML
    public Label weightParcelLabel;
    @FXML
    public Label volumeParcelLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @Override
    public void setChilExploxApp(ChilExploxApp main){
        this.main = main;
    }
    public void initTableView()
    {
        parcelTile.setHgap(5);
        parcelTile.setVgap(5);
        selectedParcel = null;
        sendTruckBackErrorButton.setVisible(false);
        ParcelView pv;
        for(Parcel p: this.truck.getParcels()){
            pv = new ParcelView(p);
            pv.setMouseevent(this);
            trucksParcelsImgs.put(pv.view, pv);
            parcelTile.getChildren().add(pv.button);
        }
        
    }
    
    public void setTruck(Truck truck){
        this.truck = truck;
        plateTruckLabel.setText(truck.getPlate());
        typeTruckLabel.setText(truck.getType().toString());
        stateTruckLabel.setText(truck.getAvaibility().toString());
        originTruckLabel.setText(truck.getDestinyString());
        nParcelsTruckLabel.setText(String.valueOf(truck.getParcels().size()));
        maxSpaceLabel.setText(String.valueOf(this.truck.getMaxParcels()));
        initTableView();
    }
    @FXML
    private void returnScene(ActionEvent event){
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }  
    @FXML
    private void unloadParcel(ActionEvent event){
        if (selectedParcel != null) {
            truck.unloadArrived(trucksParcelsImgs.get(selectedParcel).parcel);
            parcelTile.getChildren().remove(trucksParcelsImgs.get(selectedParcel).button);
            stateTruckLabel.setText(truck.getAvaibility().toString());
            originTruckLabel.setText(truck.getDestinyString());
            nParcelsTruckLabel.setText(String.valueOf(truck.getParcels().size()));
            selectedParcel = null;
            idParcelLabel.setText("-");
            priorityParcelLabel.setText("-");
            destinationParcelLabel.setText("-");
            stateParcelLabel.setText("-");
            typeParcelLabel.setText("-");
            weightParcelLabel.setText("-");
            volumeParcelLabel.setText("-");
        }
    }
    @FXML 
    private void sendTruckBack(ActionEvent event){
        if (truck.getParcels().size()==0) {
            main.getChilExplox().getCurrentSubsidiary().sendBack(truck);
            this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
        }
    }
    @FXML 
    private void sendTruckBackError(ActionEvent event){
        main.getChilExplox().getCurrentSubsidiary().sendBackError(truck);
        this.main.changeScene("WatchTrucksListFXML.fxml", WatchTrucksListFXMLController.class);
    }
    @FXML 
    private void reportError(ActionEvent event){
        if (selectedParcel != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("ChilExplox");
            dialog.setHeaderText("Report Error to Sender:");
            dialog.setContentText("Message:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                ChilExplox chilexplox = main.getChilExplox();
                Mailbox origin = chilexplox.getCurrentSubsidiary().getMailbox();
                Mailbox destiny = 
                        main.getChilExplox().getSubsidiary(truck.getHomeAddress())
                                .getMailbox();
                String subject = String.format("Error in sent parcel: %s",
                        trucksParcelsImgs.get(selectedParcel).parcel.getId());
                String content = result.get();
                Message mail = new Message(origin,destiny, subject, content, 
                                        MessageType.Error);
                Parcel p = trucksParcelsImgs.get(selectedParcel).parcel;
                Record r = new Record(ArchiveType.Error, subject+" "+content,
                            p.getResposable(),
                            p);
                p.updateStatusToError();
                main.getChilExplox().getSubsidiary(truck.getHomeAddress()).addRecord(r);
                if (mail.getDestinyMailbox() != null){
                    main.getChilExplox().getCurrentSubsidiary().getMailbox().
                        sendMessage(mail);
                    sendTruckBackErrorButton.setVisible(true);
                    sendTruckBackButton.setVisible(false);
                }
            }
        }
    }
}
