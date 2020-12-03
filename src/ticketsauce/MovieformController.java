/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MovieformController implements Initializable {

    @FXML
    private ImageView close;
    
    @FXML
    private ImageView movie;

    @FXML
    private ImageView ticket;

    @FXML
    private ImageView addMovie;

    @FXML
    private ImageView addShow;

    @FXML
    private ImageView logout;

    private boolean is_staff;
    private int currentUserID;  
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXButton submit;

    @FXML
    private JFXTextField rate;

    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField budget;

    @FXML
    private JFXTextField boxoffice;
    
    @FXML
    private JFXTextArea directors;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
        try {
            // TODO
            
            handler = new DBHandler();
            String q1 = "SELECT * from users where is_logged_in=1;";
            try {
                connection = handler.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                pst = connection.prepareStatement(q1);
            } catch (SQLException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = null;
            try {
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                while(rs.next())
                {
                    is_staff = rs.getBoolean("is_staff");
                    currentUserID = rs.getInt("idUsers");
                }
            } catch (SQLException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(is_staff==false)
        {
            addMovie.setVisible(false);
            addShow.setVisible(false);
        }
    }    

    @FXML
    private void createMovie(ActionEvent e) throws ClassNotFoundException {
        
    try {

        String insert = "INSERT INTO movies(name,description,duration,rate,directors,releasedate,budget,boxoffice)" + "VALUES(?,?,?,?,?,?,?,?);";
        connection = handler.getConnection();

        pst = connection.prepareStatement(insert);
        pst.setString(1, name.getText());
        pst.setString(2, description.getText());
        pst.setString(3, duration.getText());
        pst.setString(4, rate.getText());
        pst.setString(5, directors.getText());
        pst.setString(6, date.getText());
        pst.setString(7, budget.getText());
        pst.setString(8, boxoffice.getText());

        pst.executeUpdate();
        
        String q1 = "SELECT * FROM movies ORDER BY idMovie DESC LIMIT 0, 1";
            pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int newMovieID = rs.getInt("idMovie");
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/info copy.fxml"));
            Parent signinView = (Parent) loader.load();
            Scene movieScene = new Scene(signinView);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            InfoController controller = loader.getController();
            controller.setMovie(newMovieID);
            window.setScene(movieScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } catch (SQLException ex) {
        Logger.getLogger(MovieformController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    
        @FXML
        private void Moviebtn(MouseEvent event) throws IOException {
        loadPage("movie",event);
    }

        @FXML
        private void showBtn(MouseEvent event) throws IOException {
        loadPage("ticket",event);
    }

        @FXML
        private void addMoviebtn(MouseEvent event) throws IOException {
        loadPage("Movieform",event);
    }

        @FXML
        private void addShowbtn(MouseEvent event) throws IOException {
        loadPage("showform",event);
    }

        @FXML
        private void logout(MouseEvent event) {
        Platform.exit();
    }

        @FXML
        private void exitApplication(MouseEvent event) {
        Platform.exit();
                    }
        public void loadPage(String pagename,MouseEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/template.fxml"));
            Scene movieScene = new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(movieScene);
            window.show();
    }
    
    
}
