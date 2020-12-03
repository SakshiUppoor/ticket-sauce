/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //loadData();
    }    

    
    
    @FXML
    private void addMovie(ActionEvent event) throws IOException {
         Parent movieformView = FXMLLoader.load(getClass().getResource("FXML/Movieform.fxml"));
        Scene movieformScene = new Scene(movieformView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(movieformScene);
        window.show();
    }

    @FXML
    private void addShow(ActionEvent event) throws IOException {
        Parent showformView = FXMLLoader.load(getClass().getResource("FXML/showform.fxml"));
        Scene showformScene = new Scene(showformView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(showformScene);
        window.show();
    }

    /*
    private void loadData() {
        list.removelAll(list);
        String a="A";
        String b="B";
        String c="C";
        list.addAll(a,b,c);
        choiceMovie.getItems().getAddAll(list);
    }*/
}
