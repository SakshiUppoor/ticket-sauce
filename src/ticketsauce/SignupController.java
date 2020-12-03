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
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author hp
 */
public class SignupController implements Initializable {
    
    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXPasswordField password2;
    
    @FXML
    private HBox warning;

    @FXML
    private ImageView warningicon;

    @FXML
    private Label message;

    @FXML
    private JFXButton signup;
    
    
    @FXML
    private ImageView close;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    private Stage myStage;
    private double x, y;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warning.setVisible(false);
        handler = new DBHandler();
    }    
        
    public void signupAction(ActionEvent e) throws ClassNotFoundException, SQLException
    {
        //Saving Data
        String insert = "INSERT INTO users(Username,Email,Password)" + "VALUES(?,?,?)";
        connection = handler.getConnection();
        
        String q1 = "SELECT * from users where Username=?";
            
        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1, username.getText());
            ResultSet rs = pst.executeQuery();
            
            int count=0;
            
            while(rs.next())
            {
                count = count + 1;
            }
            
            if(count==1)
            {
                warning.setVisible(true);
                message.setText("Username taken");
            }
            
            else
            {
                q1 = "SELECT * from users where Email=?";
            
                try 
                {
                    pst = connection.prepareStatement(q1);
                    pst.setString(1, email.getText());
                    rs = pst.executeQuery();

                    count=0;

                    while(rs.next())
                    {
                        count = count + 1;
                    }

                    if(count==1)
                    {
                        warning.setVisible(true);
                        message.setText("Email adress already exists.");
                    }
                    
                    else
                    {
                        System.out.println(password1.getText() + password1.getText());
                        if(!password1.getText().equals(password2.getText()))
                        {
                            warning.setVisible(true);
                            message.setText("Passwords don't match");
                        }
                        
                        else 
                        {

                            pst = connection.prepareStatement(insert);
                            pst.setString(1, username.getText());
                            pst.setString(2, email.getText());
                            pst.setString(3, password1.getText());

                            pst.executeUpdate();
                            Parent signinView;
                            try {
                                signinView = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
                                Scene signinScene = new Scene(signinView);
                                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                                window.setScene(signinScene);
                                window.show();
                                System.out.println("Signed up succesfully");
                            } catch (IOException ex) {
                                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void loginAction(ActionEvent event) throws IOException
    {
        Parent signinView = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene signinScene = new Scene(signinView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(signinScene);
        window.show();
    }
    
    public void setStage(Stage stage, Parent root)
    {
        myStage = stage;
        root = root;
    }
    
    @FXML
    void exitApplication(MouseEvent event) throws ClassNotFoundException, SQLException {
        
        handler = new DBHandler();
        String q1="UPDATE users SET is_logged_in=0;";
        connection = handler.getConnection();
        pst = connection.prepareStatement(q1);
        pst.executeUpdate();
        Platform.exit();
    }
}
