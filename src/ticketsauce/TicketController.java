/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class TicketController  implements Initializable {

    @FXML
    private ImageView ticketbg1;

    @FXML
    private ImageView ticketbg2;

    @FXML
    private ImageView ticketbg3;

    @FXML
    private Label name1;

    @FXML
    private Label date1;

    @FXML
    private Label seats1;

    @FXML
    private Label time1;

    @FXML
    private Label name2;

    @FXML
    private Label date2;

    @FXML
    private Label time2;

    @FXML
    private Label seats2;

    @FXML
    private Label name3;

    @FXML
    private Label date3;

    @FXML
    private Label time3;

    @FXML
    private Label seats3;
    
    
    @FXML
    private VBox info11;
    @FXML
    private VBox info12;
    @FXML
    private VBox info13;
    @FXML
    private VBox info14;
   
    @FXML
    private VBox info21;
    @FXML
    private VBox info22;
    @FXML
    private VBox info23;
    @FXML
    private VBox info24;
    
    @FXML
    private VBox info31;
    @FXML
    private VBox info32;
    @FXML
    private VBox info33;
    @FXML
    private VBox info34;
    
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    
    @FXML
    private HBox success;

    @FXML
    private Label help;
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
    
    @FXML
    private Label none;

    private boolean is_staff;
    private int currentUserID;
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeVisible(0,false);
            none.setVisible(false);
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

    
    public void changeVisible(int n, boolean b)
    {
        if(n==0 || n==1)
        {
            ticketbg1.setVisible(b);
            info11.setVisible(b);
            info12.setVisible(b);
            info13.setVisible(b);
            info14.setVisible(b);
            line1.setVisible(b);
        }
        
        if(n==0 || n==2)
        {
            ticketbg2.setVisible(b);
            info21.setVisible(b);
            info22.setVisible(b);
            info23.setVisible(b);
            info24.setVisible(b);
            line2.setVisible(b);
        }
        
        if(n==0 || n==3)
        {
            ticketbg3.setVisible(b);
            info31.setVisible(b);
            info32.setVisible(b);
            info33.setVisible(b);
            info34.setVisible(b);
            line3.setVisible(b);
        }
    }
    
    public void setInfo(int n, String date,String time,String seatslist, String name)
    {
        if(n==1)
        {
            name1.setText(name);
            date1.setText(date);
            time1.setText(time);
            seats1.setText(seatslist);
        }
        else if(n==2)
        {
            name2.setText(name);
            date2.setText(date);
            time2.setText(time);
            seats2.setText(seatslist);System.out.println("Printed!");
        }
        else if(n==3)
        {
            name3.setText(name);
            date3.setText(date);
            time3.setText(time);
            seats3.setText(seatslist);
        }
    }

    public void showTickets(int idTicket) throws ClassNotFoundException, SQLException
    {
        help.setVisible(false);
        changeVisible(2,true);
        String q1 ="SELECT movies.name, shows.date, shows.time, seats.rowId, seats.columnId, shows.date,shows.time FROM shows INNER JOIN seats ON seats.iDshow = shows.idShow INNER JOIN movies ON movies.idMovie = shows.idMovie WHERE seats.idTicket=" + idTicket + " order by columnId;";
        
        connection = handler.getConnection();
        System.out.println(q1);
        pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        rs.next();
        String seatlist = rs.getString("columnId") + rs.getString("rowId");
        date2.setText(rs.getString("date"));
        setInfo(2,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
        while(rs.next())
        {
            seatlist += ", ";
            seatlist += rs.getString("columnId") + rs.getString("rowId");
            setInfo(2,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
        }
    }
    
    public void showTickets(int idTicket1, int idTicket2, int idTicket3) throws ClassNotFoundException, SQLException
    {
        success.setVisible(false);
        if(idTicket1!=0)
        {
            changeVisible(1,true);
            String q1 ="SELECT movies.name, shows.date, shows.time, seats.rowId, seats.columnId, shows.date,shows.time FROM shows INNER JOIN seats ON seats.iDshow = shows.idShow INNER JOIN movies ON movies.idMovie = shows.idMovie WHERE seats.idTicket=" + idTicket1 + " order by columnId;";
            try {
                connection = handler.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(q1);
            pst = connection.prepareStatement(q1);
            ResultSet rs = pst.executeQuery();
            rs.next();
            String seatlist = rs.getString("columnId") + rs.getString("rowId");
            date2.setText(rs.getString("date"));
            setInfo(1,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            while(rs.next())
            {
                seatlist += ", ";
                seatlist += rs.getString("columnId") + rs.getString("rowId");
                setInfo(1,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            }
        }
        else
            none.setVisible(true);
        if(idTicket2!=0)
        {
            changeVisible(2,true);
            String q1 ="SELECT movies.name, shows.date, shows.time, seats.rowId, seats.columnId, shows.date,shows.time FROM shows INNER JOIN seats ON seats.iDshow = shows.idShow INNER JOIN movies ON movies.idMovie = shows.idMovie WHERE seats.idTicket=" + idTicket2 + " order by columnId;";
            try {
                connection = handler.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(q1);
            pst = connection.prepareStatement(q1);
            ResultSet rs = pst.executeQuery();
            rs.next();
            String seatlist = rs.getString("columnId") + rs.getString("rowId");
            date2.setText(rs.getString("date"));
            setInfo(2,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            while(rs.next())
            {
                seatlist += ", ";
                seatlist += rs.getString("columnId") + rs.getString("rowId");
                setInfo(2,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            }
        }
        if(idTicket3!=0)
        {
            changeVisible(3,true);
            String q1 ="SELECT movies.name, shows.date, shows.time, seats.rowId, seats.columnId, shows.date,shows.time FROM shows INNER JOIN seats ON seats.iDshow = shows.idShow INNER JOIN movies ON movies.idMovie = shows.idMovie WHERE seats.idTicket=" + idTicket3 + " order by columnId;";
            try {
                connection = handler.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(q1);
            pst = connection.prepareStatement(q1);
            ResultSet rs = pst.executeQuery();
            rs.next();
            String seatlist = rs.getString("columnId") + rs.getString("rowId");
            date2.setText(rs.getString("date"));
            setInfo(3,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            while(rs.next())
            {
                seatlist += ", ";
                seatlist += rs.getString("columnId") + rs.getString("rowId");
                setInfo(3,rs.getString("date"),rs.getString("time"),seatlist,rs.getString("name"));
            }
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
        private void logout(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
            
        handler = new DBHandler();
        String q1="UPDATE users SET is_logged_in=0;";
        connection = handler.getConnection();
        pst = connection.prepareStatement(q1);
        pst.executeUpdate();
        Parent signinView = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        Scene signinScene = new Scene(signinView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(signinScene);
        window.show();
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