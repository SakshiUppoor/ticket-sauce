/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketsauce;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class InfoController implements Initializable {

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
    @FXML
    private ImageView poster;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label date;
    @FXML
    private Label directors;
    @FXML
    private Label budget;
    @FXML
    private Label boxoffice;
    
    
    @FXML
    private HBox show1;

    @FXML
    private Label time1;

    @FXML
    private Label showid1;

    @FXML
    private HBox show2;

    @FXML
    private Label time2;

    @FXML
    private Label showid2;

    @FXML
    private HBox show3;

    @FXML
    private Label time3;

    @FXML
    private Label showid3;

    @FXML
    private HBox show4;

    @FXML
    private Label time4;

    @FXML
    private Label showid4;

    @FXML
    private HBox show5;

    @FXML
    private Label time5;

    @FXML
    private Label showid5;

    @FXML
    private HBox show6;

    @FXML
    private Label time6;

    @FXML
    private Label showid6;
    
    @FXML
    private Label noshow;
    
    int idMovie,likesAmount;
    
    
    @FXML
    private Label likes;

    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
        show1.setVisible(false);
        show2.setVisible(false);
        show3.setVisible(false);
        show4.setVisible(false);
        show5.setVisible(false);
        show6.setVisible(false);
        showid1.setVisible(false);
        showid2.setVisible(false);
        showid3.setVisible(false);
        showid4.setVisible(false);
        showid5.setVisible(false);
        showid6.setVisible(false);
        noshow.setVisible(false);
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
        loadPage("showform Copy",event);
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
    
    public void setMovie(int idMovie) throws SQLException{
        System.out.println("hi!!!!!!!!!!!!!");
            try {
                ResultSet rs = null;
                try {
                    this.idMovie = idMovie;
                    System.out.println(idMovie);
                    String q1 = "SELECT * from movies WHERE idMovie=" + idMovie + ";";
                    try {
                        connection = handler.getConnection();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    pst = connection.prepareStatement(q1);
                    rs = pst.executeQuery();
                } catch (SQLException ex) {
                    Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                rs.next();
                Image image = new Image(getClass().getResourceAsStream("Images/" + rs.getString("name") +".jpg"));
                poster.setImage(image);
                name.setText(rs.getString("name"));
                likes.setText(String.valueOf(rs.getInt("likes")));
                System.out.println(rs.getInt("likes"));
                likesAmount = rs.getInt("likes");
                directors.setText(rs.getString("directors"));
                description.setText(rs.getString("description"));
                budget.setText(rs.getString("budget"));
                boxoffice.setText(rs.getString("boxoffice"));
                date.setText(rs.getString("releasedate"));
                
                String q1 = "SELECT shows.time,shows.idShow,shows.date   FROM movies INNER JOIN shows ON shows.idMovie = movies.idMovie WHERE shows.idMovie=" + idMovie + " order by time;;";
                
                connection = handler.getConnection();
                pst = connection.prepareStatement(q1);
                rs = pst.executeQuery();
                int count=0;

                Date today = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("d MMM");
                while(rs.next())
                {
                    if(rs.getString("date").equals(df.format(today)))
                    {
                        count++;
                        if(count==1)
                        {
                            show1.setVisible(true);
                            time1.setText(rs.getString("time"));
                            showid1.setText(String.valueOf(rs.getInt("idShow")));
                        }
                        if(count==2)
                        {
                            show2.setVisible(true);
                            time2.setText(rs.getString("time"));
                            showid2.setText(String.valueOf(rs.getInt("idShow")));
                        }
                        if(count==3)
                        {
                            show3.setVisible(true);
                            time3.setText(rs.getString("time"));
                            showid3.setText(String.valueOf(rs.getInt("idShow")));
                        }
                        if(count==4)
                        {
                            show4.setVisible(true);
                           time4.setText(rs.getString("time"));
                            showid4.setText(String.valueOf(rs.getInt("idShow")));
                        }
                        if(count==5)
                        {
                            show5.setVisible(true);
                            time5.setText(rs.getString("time"));
                            showid5.setText(String.valueOf(rs.getInt("idShow")));
                        }
                        if(count==6)
                        {
                            show6.setVisible(true);
                            time6.setText(rs.getString("time"));
                            showid6.setText(String.valueOf(rs.getInt("idShow")));
                            break;
                        }
                    }
                }
                if(count==0)
                    noshow.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    void likeAction(MouseEvent event) throws SQLException {
        ResultSet rs = null;
            try {
                this.idMovie = idMovie;
                System.out.println(idMovie);
                String q1 = "UPDATE movies SET likes=" + (likesAmount+1) +" WHERE idMovie=" + idMovie + ";";
                try {
                    connection = handler.getConnection();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ShowformController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                pst = connection.prepareStatement(q1);
                pst.executeUpdate();
                System.out.println("Updated!");
            } catch (SQLException ex) {
                Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setMovie(idMovie);
    }
    
    @FXML
    void goToShow(MouseEvent e) throws IOException, ClassNotFoundException, SQLException {
            String show_no = ((HBox)e.getSource()).getId();
            System.out.println(show_no);
            int newShowID=1;
            if(null!=show_no)
                switch (show_no) {
                    case "show1":
                          System.out.println(showid1.getText());
                        newShowID = Integer.parseInt(showid1.getText());
                        break;
                    case "show2":
                        newShowID = Integer.parseInt(showid2.getText());
                        break;
                    case "show3":
                        newShowID = Integer.parseInt(showid3.getText());
                        break;
                    default:
                        break;
                }
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/seatpg.fxml"));
            Parent signinView = (Parent) loader.load();
            Scene movieScene = new Scene(signinView);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            SeatpgController controller = loader.getController();
            controller.setShow(newShowID);
            window.setScene(movieScene);
            window.show();
    }

    }
    

