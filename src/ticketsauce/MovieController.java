/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MovieController implements Initializable {


    @FXML
    private VBox movie1;

    @FXML
    private ImageView poster1;

    @FXML
    private Label name1;

    @FXML
    private Label likes1;

    @FXML
    private Label id1;

    @FXML
    private VBox movie2;

    @FXML
    private ImageView poster2;

    @FXML
    private Label name2;

    @FXML
    private Label likes2;

    @FXML
    private Label id2;

    @FXML
    private VBox movie3;

    @FXML
    private ImageView poster3;

    @FXML
    private Label name3;

    @FXML
    private Label likes3;

    @FXML
    private Label id3;

    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             // TODO
             movie1.setVisible(false);
             movie2.setVisible(false);
             movie3.setVisible(false);
             id1.setVisible(false);
             id2.setVisible(false);
             id3.setVisible(false);
             handler = new DBHandler();
             String q1 = "SELECT idMovie, name, likes from movies order by -idMovie;";
             
             connection = handler.getConnection();
             
             pst = connection.prepareStatement(q1);
             ResultSet rs = pst.executeQuery(); 
             int count = 0;
             while(rs.next())
             {
                 count++;
                 if(count==1)
                 {
                    movie1.setVisible(true);
                    Image image = new Image(getClass().getResourceAsStream("Images/" + rs.getString("name") +".jpg"));
                    poster1.setImage(image);
                    name1.setText(rs.getString("name"));
                    likes1.setText(String.valueOf(rs.getInt("likes")));
                    id1.setText(rs.getString("idMovie"));
                 }
                 if(count==2)
                 {
                    movie2.setVisible(true);
                    Image image = new Image(getClass().getResourceAsStream("Images/" + rs.getString("name") +".jpg"));
                    poster2.setImage(image);
                    name2.setText(rs.getString("name"));
                    likes2.setText(String.valueOf(rs.getInt("likes")));
                    id2.setText(rs.getString("idMovie"));
                 }
                 if(count==3)
                 {
                    movie3.setVisible(true);
                    Image image = new Image(getClass().getResourceAsStream("Images/" + rs.getString("name") +".jpg"));
                    poster3.setImage(image);
                    name3.setText(rs.getString("name"));
                    likes3.setText(String.valueOf(rs.getInt("likes")));
                    id3.setText(rs.getString("idMovie"));
                    break;
                 }
             }
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
    @FXML
    void goToMovie(MouseEvent event) throws IOException {
        try {
            String movie_no = ((VBox)event.getSource()).getId();
            System.out.println(movie_no);
            int newMovieID=1;
            if(null!=movie_no)
                switch (movie_no) {
                    case "movie1":
            System.out.println(id1.getText());
                        newMovieID = Integer.parseInt(id1.getText());
                        break;
                    case "movie2":
                        newMovieID = Integer.parseInt(id2.getText());
                        break;
                    case "movie3":
                        newMovieID = Integer.parseInt(id3.getText());
                        break;
                    default:
                        break;
                }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/info copy.fxml"));
            Parent signinView = (Parent) loader.load();
            Scene movieScene = new Scene(signinView);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            InfoController controller = loader.getController();
            controller.setMovie(newMovieID);
            window.setScene(movieScene);
            window.show();
        } catch (SQLException ex) {
            Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
