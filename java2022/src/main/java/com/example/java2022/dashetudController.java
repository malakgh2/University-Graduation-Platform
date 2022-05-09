package com.example.java2022;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class dashetudController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Button back;
    @FXML
    private ImageView icon;
    @FXML
    private ImageView deposi;
    @FXML
    private ImageView etatt;
    @FXML
    private ImageView logo;



    public void logoutButtonOnAction(ActionEvent event){
        logout();
    }

    public void logout(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("login");
            login.setScene(new Scene(root,508,418));
            login.show();
            Stage st = (Stage)logout.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File iconfile= new File("img/education.png");
        Image iconimg= new Image(iconfile.toURI().toString());
        icon.setImage(iconimg);

        //Second image logo

        File logofile= new File("img/logo.png");
        Image logoimg= new Image(logofile.toURI().toString());
        logo.setImage(logoimg);
        //
        File uplofile= new File("img/uplo.png");
        Image uploimg= new Image(uplofile.toURI().toString());
        deposi.setImage(uploimg);

        //Second image logo

        File etatfile= new File("img/loading.png");
        Image etatimg= new Image(etatfile.toURI().toString());
        etatt.setImage(etatimg);

    }

    public  void clickajout(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("regstudent.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("Stage & PFE Details");
            login.setScene(new Scene(root,402,514));
            login.show();
            Stage st = (Stage)icon.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }




    }

    public  void clicketat(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("statepfe.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("PFE STATE & GRADE");
            login.setScene(new Scene(root,419,518));
            login.show();
            Stage st = (Stage)etatt.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }




    }




    //open page list
    public  void clicklist() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("deposit.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("Deposit PFE");
            login.setScene(new Scene(root, 411, 460));
            login.show();
            Stage st = (Stage) deposi.getScene().getWindow();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



}
