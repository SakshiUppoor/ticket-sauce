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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author hp
 */
public class Admin implements Initializable {
    
    @FXML
    private JFXButton join;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    
    @FXML
    private HBox warning;

    @FXML
    private ImageView warningicon;

    @FXML
    private Label message;

    @FXML
    private JFXCheckBox remember;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton forgot;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warning.setVisible(false);
        handler = new DBHandler();
    }    
        
    public void loginAction(ActionEvent e) throws ClassNotFoundException, SQLException, IOException
    {
        //Retrieve data from database
        String q1 = "SELECT * from users where Username=?";
        connection = handler.getConnection();
            
        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1, username.getText());
            ResultSet rs = pst.executeQuery();
            
            int count=0;
            
            while(rs.next())
            {
                count = count + 1;
            }
            
            if(count!=1)
            {
                
                warning.setVisible(true);
                message.setText("Username does not exist.");
            }
            else
            {
                q1 = "SELECT * from users where Username=? and Password=?";
                connection = handler.getConnection();

                try {
                    pst = connection.prepareStatement(q1);
                    pst.setString(1, username.getText());
                    pst.setString(2, password.getText());
                    rs = pst.executeQuery();

                    count=0;

                    while(rs.next())
                    {
                        count = count + 1;
                    }

                    if(count==1)
                    {
                        Parent mainView = FXMLLoader.load(getClass().getResource("FXML/hello.fxml"));
                        Scene mainScene = new Scene(mainView);
                        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                        window.setScene(mainScene);
                        window.show();
                        System.out.println("Login succesful");
                        warning.setVisible(false);
                    }

                    else
                    {
                        warning.setVisible(true);
                        message.setText("Incorrect password");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void signupAction(ActionEvent event) throws IOException
    {
        Parent signinView = FXMLLoader.load(getClass().getResource("FXML/Signup.fxml"));
        Scene signinScene = new Scene(signinView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(signinScene);
        window.show();
    }
}
