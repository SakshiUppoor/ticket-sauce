/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SeatpgController implements Initializable {

    
    
    @FXML
    private CheckBox E2;

    @FXML
    private CheckBox D2;

    @FXML
    private CheckBox D1;

    @FXML
    private CheckBox E1;

    @FXML
    private CheckBox D3;

    @FXML
    private CheckBox E3;

    @FXML
    private CheckBox E4;

    @FXML
    private CheckBox D4;

    @FXML
    private CheckBox D5;

    @FXML
    private CheckBox E5;

    @FXML
    private CheckBox F1;

    @FXML
    private CheckBox F2;

    @FXML
    private CheckBox F3;

    @FXML
    private CheckBox F4;

    @FXML
    private CheckBox F5;

    @FXML
    private CheckBox C1;

    @FXML
    private CheckBox C2;

    @FXML
    private CheckBox C3;

    @FXML
    private CheckBox C4;

    @FXML
    private CheckBox C5;

    @FXML
    private CheckBox H2;

    @FXML
    private CheckBox H3;

    @FXML
    private CheckBox H4;

    @FXML
    private CheckBox I2;

    @FXML
    private CheckBox I3;

    @FXML
    private CheckBox I4;

    @FXML
    private CheckBox A2;

    @FXML
    private CheckBox A3;

    @FXML
    private CheckBox A4;

    @FXML
    private CheckBox B2;

    @FXML
    private CheckBox B3;

    @FXML
    private CheckBox B4;

    @FXML
    private CheckBox G1;

    @FXML
    private CheckBox G2;

    @FXML
    private CheckBox G3;

    @FXML
    private CheckBox G4;

    @FXML
    private CheckBox G5;

    @FXML
    private HBox seat1;

    @FXML
    private HBox seat2;

    @FXML
    private HBox seat3;

    @FXML
    private HBox seat4;

    @FXML
    private HBox seat5;

    @FXML
    private HBox seat6;

    @FXML
    private HBox seat7;

    @FXML
    private HBox seat8;

    @FXML
    private HBox seat9;

    @FXML
    private Label total;

    @FXML
    private JFXButton btn;

    @FXML
    private Label movieName;

    @FXML
    private Label showdetails;

    @FXML
    private List<CheckBox> seats;
    
    
    @FXML
    private List<HBox> seatsinfo;
    
    
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
    
    int rate;
    int price;
    int CurrentUSerID=getCurrentUserID();
    
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
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    
    int idShow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
                try {
                    
                    // TODO
                    seat1.setVisible(false);
                    handler = new DBHandler();
                    total.setText("0");
                    btn.setDisable(true);
                    total.setText("₹ 0");
                    /*
                    for(int i=1;i<=5;i++)
                    {
                    for(int j=1;j<=9;j++)
                    {
                    {
                    if(!((i==1 || i==5)&&(j==1 || j==2 || j==8 || j==9)))
                    {
                    if()
                    k++;
                    System.out.println(String.valueOf(i) + " " + String.valueOf((char)(j + 64)) + " " + CurrentUserID + " " + k);
                    
                    }
                    }
                    }
                    }*/
                    
                    String q1 = "SELECT * from users where is_logged_in=1;";
                    try {
                        connection = handler.getConnection();
                    } catch (SQLException ex) {
                        Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SeatpgController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        pst = connection.prepareStatement(q1);
                    } catch (SQLException ex) {
                        Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ResultSet rs = null;
                    rs = pst.executeQuery();
                    
                    while(rs.next())
                    {
                        try {
                            is_staff = rs.getBoolean("is_staff");
                            currentUserID = rs.getInt("idUsers");
                        } catch (SQLException ex) {
                            Logger.getLogger(SeatpgController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(is_staff==false)
                    {
                        addMovie.setVisible(false);
                        addShow.setVisible(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SeatpgController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void setShow(int idShow) throws ClassNotFoundException, SQLException
    {
        this.idShow = idShow;       
        System.out.println("SHOW=" + idShow);
        String q1 = "SELECT shows.time, shows.date, movies.name, movies.rate FROM movies INNER JOIN shows ON shows.idMovie = movies.idMovie  Where shows.idShow=" + idShow + " order by time;";
        connection = handler.getConnection();
        pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        rs.next();
        showdetails.setText(rs.getString("time") + " " + rs.getString("date"));
        movieName.setText(rs.getString("name"));
        rate = rs.getInt("rate");
        
            int k=0;
            String q3 = "SELECT * FROM seats WHERE idShow=" + idShow + ";";
            connection = handler.getConnection();
            pst = connection.prepareStatement(q3);
            rs = pst.executeQuery();
            int i=0;
            int j=0;
            while(rs.next())
            {
                i++;
                for (CheckBox checkbox : seats) 
                {
                    if((rs.getString("columnId")+rs.getString("rowId")) == null ? checkbox.getId() == null : (rs.getString("columnId")+rs.getString("rowId")).equals(checkbox.getId()))
                    {
                        if(!rs.getBoolean("is_available"))
                        {
                            checkbox.setDisable(true);
                            System.out.println(checkbox.getId()+" is not available!");
                        }
                        else
                        {
                            j++;
                        }
                    }
                }
            }
            if(j==0)
            {
                System.out.println("HouseFull");
            }
            System.out.println(i);
    }
    
    
    @FXML
    void updateSeats(ActionEvent event) {
        int n=0;
        int i=0;
        for (CheckBox checkbox : seats) 
        {
            if(checkbox.isSelected())
            {
                n++;
                
                btn.setDisable(false);
                for(HBox hbox : seatsinfo)
                {
                    //System.out.println(hbox.getId());
                    if(n==Character.getNumericValue(hbox.getId().charAt(4)))
                    {
                        System.out.println("i=" + i + "n=" + n);
                        hbox.setVisible(true);
                        ((Label)hbox.getChildren().get(0)).setText("Row " + checkbox.getId().charAt(0) + " / Column " + checkbox.getId().charAt(1));
                        ((Label)hbox.getChildren().get(1)).setText("₹ " + rate);
                        i++;
                    }
                    else if(Character.getNumericValue(hbox.getId().charAt(4))>n)
                    {
                        hbox.setVisible(false);
                    }
                }
            }
            if(n==0)
            {
                seat1.setVisible(false);
                btn.setDisable(true);
            }
        }
        total.setText("₹ " + n*rate);
    }
    
    @FXML
    void bookTicket(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
    {
        String q1;
        q1 = "INSERT INTO tickets() VALUES();";
        connection = handler.getConnection();
        System.out.println(q1);
        pst = connection.prepareStatement(q1);
        pst.executeUpdate();
        q1 = "SELECT * FROM tickets ORDER BY idTicket DESC LIMIT 0, 1";
        connection = handler.getConnection();
        System.out.println(q1);
        pst = connection.prepareStatement(q1);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int idTicket = rs.getInt("idTicket");
        System.out.println("TicketID =" + idTicket);
        int n=0;
        for (CheckBox checkbox : seats) 
        {
            if(checkbox.isSelected())
            {
                try {
                    n++;
                    q1="UPDATE seats SET is_available=0, idUser=" + CurrentUSerID +", idTicket=" + idTicket + " WHERE idShow = " + idShow + " AND columnId ='" + checkbox.getId().charAt(0)+ "' AND rowId = " + Character.getNumericValue(checkbox.getId().charAt(1)) +";";
                    connection = handler.getConnection();
                    System.out.println(q1);
                    pst = connection.prepareStatement(q1);
                    pst.executeUpdate();
                    //System.out.println("Updated!");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SeatpgController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SeatpgController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            System.out.println(n + " seats booked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ticket.fxml"));
                    Parent signinView = (Parent) loader.load();
                    Scene movieScene = new Scene(signinView);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    TicketController controller = loader.getController();
                    controller.showTickets(idTicket);
                    window.setScene(movieScene);
                    window.show();
        /*
        int k=0;
        for(int i=1;i<=5;i++)
        {
            for(int j=1;j<=9;j++)
            {
                {
                    if(!((i==1 || i==5)&&(j==1 || j==2 || j==8 || j==9)))
                            {
                    k++;
                    String q3 = "INSERT INTO seats(idShow, rowId, columnId, idUser)" + "VALUES(?,?,?,?);";
                    connection = handler.getConnection();
                    pst = connection.prepareStatement(q3);
                    pst.setInt(1, newShowID);
                    pst.setString(2, String.valueOf(i));
                    pst.setString(3, String.valueOf((char)(j + 64)));
                    pst.setInt(4, CurrentUserID);
                    System.out.println(String.valueOf(i) + " " + String.valueOf((char)(j + 64)) + " " + CurrentUserID + " " + k);
                    pst.executeUpdate();
                            }
                } 
            }
        }*/
    }
}
