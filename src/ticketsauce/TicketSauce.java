/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hp
 */
public class TicketSauce extends Application {
    
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        handler = new DBHandler();
        String q1="UPDATE users SET is_logged_in=0;";
        connection = handler.getConnection();
        pst = connection.prepareStatement(q1);
        pst.executeUpdate();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Simple Text Editor");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(300);

        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        primaryStage.setMaximized(true);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

interface UserInterface {
    public void setDetails(int idUsers, String Username, String Email, String Password, boolean is_ataff, boolean is_logged_in);
    public void setIdUsers(int idUsers);
    public void setUsername(String Username);
    public void setEmail(String Email);
    public void setPassword(String Password);
    public void setIs_staff(boolean is_staff);
    public void setIs_logged_in(boolean is_logged_in);

}
  
class User implements UserInterface {
    int idUsers;
    public String Username, Email, Password;
    public boolean is_staff, is_logged_in;
    
    public void setDetails(int idUsers, String Username, String Email, String Password, boolean is_ataff, boolean is_logged_in)
    {
        setIdUsers(idUsers);
        setUsername(Username);
        setEmail(Email);
        setPassword(Password);
        setIs_staff(is_staff);
        setIs_logged_in(is_logged_in);
    }
    
    public void setIdUsers(int idUsers)
    {
        this.idUsers = idUsers;
    }
    
    public void setUsername(String Username)
    {
        this.Username = Username;
    }
    
    public void setEmail(String Email)
    {
        this.Email = Email;
    }
    
    public void setPassword(String Password)
    {
        this.Password = Password;
    }
    
    public void setIs_staff(boolean is_staff)
    {
        this.is_staff = is_staff;
    }
    
    public void setIs_logged_in(boolean is_logged_in)
    {
        this.is_logged_in = is_logged_in;
    }
}

class Movie {
    public int idMovie, duration, likes, rate;
    public String name, description, directors, releasedate, budget, boxoffice;
}

class Show extends Movie{
    int idShow;
    String time, date;
}

class Seat extends Show {
    
    int idUsers, idSeat, row, column;
    public String Username, Email, Password;
    public boolean is_staff, is_logged_in, is_available;
    
    public void setUserDetails(int idUsers, int idSeat, Movie movie, String Username, String Email, String Password, boolean is_ataff, boolean is_logged_in)
    {
        this.idUsers = idUsers;
        this.idSeat = idSeat;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.is_staff = is_staff;
        this.is_logged_in = is_logged_in;
    }    
    
    @FXML
    void exitApplication(ActionEvent event) {
        Platform.exit();
    }
 
    /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ticket.fxml"));
        Parent root = (Parent) loader.load();
        TicketController controller = loader.getController();
        controller.showTickets(21,22,23);
*/
}