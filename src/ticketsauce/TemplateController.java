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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class TemplateController implements Initializable {

    @FXML
    private ImageView close;
    
    @FXML
    private Pane screen;
    
    @FXML
    private BorderPane pane;
    
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    try {
                        try {
                            // TODO
                            
                            handler = new DBHandler();
                            String q1 = "SELECT * from users where is_logged_in=1;";
                            connection = handler.getConnection();
                            pst = connection.prepareStatement(q1);
                            ResultSet rs = pst.executeQuery();
                            
                            while(rs.next())
                            {
                                is_staff = rs.getBoolean("is_staff");
                                currentUserID = rs.getInt("idUsers"); 
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if(is_staff==false)
                        {
                            addMovie.setVisible(false);
                            addShow.setVisible(false);
                        }
                        
                                loadPage("movie");
                    } catch (IOException ex) {
            Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                        
        @FXML
        private void Moviebtn(MouseEvent event) throws IOException {
        loadPage("movie");
    }

        @FXML
        private void showBtn(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
        	String q1="SELECT DISTINCT idTicket from seats where idUser=" + currentUserID + " order by -idTicket;";
                connection = handler.getConnection();
                pst = connection.prepareStatement(q1);
                ResultSet rs = pst.executeQuery();
                int idTicket1, idTicket2, idTicket3;
                if(rs.next())
                {
                    idTicket1 = rs.getInt("idTicket");
                    if(rs.next())
                    {
                        idTicket2 = rs.getInt("idTicket");
                        if(rs.next())
                            idTicket3 = rs.getInt("idTicket");
                        else
                            idTicket3 = 0;
                    }
                    else
                    {
                        idTicket2 = 0;
                        idTicket3 = 0;
                    }
                }
                else
                {
                    idTicket1 = 0;
                    idTicket2 = 0;
                    idTicket3 = 0;
                }
                System.out.println("Ticket values=" + " "  + idTicket1 + idTicket2 + " " + idTicket3);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ticket.fxml"));
                    Parent signinView = (Parent) loader.load();
                    Scene movieScene = new Scene(signinView);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    TicketController controller = loader.getController();
                    controller.showTickets(idTicket1,idTicket2,idTicket3);
                    window.setScene(movieScene);
                    window.show();
                
    }

        @FXML
        private void addMoviebtn(MouseEvent event) throws IOException {
        loadPage("Movieform");
    }

        @FXML
        private void addShowbtn(MouseEvent event) throws IOException {
        loadPage("showform");
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
    
    public void loadPage(String pagename) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/" + pagename +".fxml"));
        pane.setCenter(root);
    }
    
    public void setScene(String page, int n) throws IOException, SQLException
    {
        if(page.equals("info"))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/" + page +".fxml"));
            InfoController controller = loader.getController();
            controller.setMovie(n);
            
            
            Parent root = loader.load();
            pane.setCenter(root);
        }
        loadPage(page);
            
    }
}
