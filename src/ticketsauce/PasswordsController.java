/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class PasswordsController implements Initializable{
    
    @FXML
    private VBox list;

    @FXML
    private JFXButton see;

    @FXML
    private JFXButton copy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {//To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    public void seePass(ActionEvent e) throws IOException
    {/*
        Parent signinView = FXMLLoader.load(getClass().getResource("FXML/hello.fxml"));
        Scene signinScene = new Scene(signinView);
        Stage window = (Stage)((Node)event.getSource()).;
        
        window.setScene(signinScene);
        window.show();*/
    }
}
