/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.mysql.cj.xdevapi.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShowformController implements Initializable {
    @FXML
    private DatePicker date;

    @FXML
    private JFXTimePicker time;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    int n=0;
    List<String> list = new ArrayList<String>();
    ObservableList<String> movieList = FXCollections.observableArrayList(list);
    @FXML
    private ChoiceBox<String> choiceMovie;

    int CurrentUserID = getCurrentUserID();
    public int getCurrentUserID()
    {
        
        try {
            handler = new DBHandler();
            String q1 = "SELECT idUsers from users WHERE is_logged_in=1;";
            
            connection = handler.getConnection();
            
            pst = connection.prepareStatement(q1);
            ResultSet rs = pst.executeQuery();
            rs.next();        
            return rs.getInt("idUsers");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            handler = new DBHandler();
            
            System.out.println("Connected.");
            
            String q1 = "SELECT name from movies;";
            try {
                connection = handler.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pst = connection.prepareStatement(q1);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                n=n+1;
                String newmovie = rs.getString("name");
                
                System.out.println(newmovie+"-n="+n);
                movieList.add(newmovie);
                if(n==1)
                {
                    choiceMovie.setValue(newmovie);
                }
            }

            System.out.println();
            choiceMovie.setItems(movieList);
        } catch (SQLException ex) {
            Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @FXML
    void createShow(ActionEvent e) throws ClassNotFoundException, SQLException {
        System.out.println("hi");
        String q1 = "SELECT idMovie from movies WHERE name='" + choiceMovie.getValue() + "';";
        
        connection = handler.getConnection();

        pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int movieId = rs.getInt("idMovie");
        LocalDate showDate = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime showTime = time.getValue();
        
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("d MMM");
        System.out.println("Movie ID is" + movieId + " " + showDate.format(formatter) + " " + showTime.format(timeFormatter));
    try {

        String insert = "INSERT INTO shows(idMovie,date,time)" + "VALUES(?,?,?);";

        pst = connection.prepareStatement(insert);
        pst.setInt(1, movieId);
        pst.setString(2, showDate.format(formatter));
        pst.setString(3, showTime.format(timeFormatter));

        pst.executeUpdate();
        
        String q2 = "SELECT * FROM shows ORDER BY idShow DESC LIMIT 0, 1";
            pst = connection.prepareStatement(q2);
        rs = pst.executeQuery();
        rs.next();
        int newShowID = rs.getInt("idShow");
        int k=0;
        for(int i=1;i<=5;i++)
        {
            for(int j=1;j<=9;j++)
            {
                {
                    if(!((i==1 || i==5)&&(j==1 || j==2 || j==8 || j==9)))
                            {
                    k++;
                    String q3 = "INSERT INTO seats(idShow, rowId, columnId)" + "VALUES(?,?,?);";
                    connection = handler.getConnection();
                    pst = connection.prepareStatement(q3);
                    pst.setInt(1, newShowID);
                    pst.setString(2, String.valueOf(i));
                    pst.setString(3, String.valueOf((char)(j + 64)));
                    System.out.println(String.valueOf(i) + " " + String.valueOf((char)(j + 64)) + " " + CurrentUserID + " " + k);
                    pst.executeUpdate();
                            }
                } 
            }
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/seatpg.fxml"));
            Parent signinView = (Parent) loader.load();
            Scene movieScene = new Scene(signinView);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            SeatpgController controller = loader.getController();
            controller.setShow(newShowID);
            window.setScene(movieScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } catch (SQLException ex) {
        Logger.getLogger(MovieformController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    
}
