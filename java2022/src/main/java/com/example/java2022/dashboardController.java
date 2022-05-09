package com.example.java2022;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import  java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;




public class dashboardController implements Initializable{
    @FXML
    private ImageView icon;
    @FXML
    private ImageView aff;
    @FXML
    private ImageView list;
    @FXML
    private ImageView notif;
    @FXML
    private Button logout;
    @FXML
    private ImageView logo;

    public void logoutButtonOnAction(ActionEvent event){
        logout();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //code icon
        File iconfile= new File("img/icon.png");
        Image iconimg= new Image(iconfile.toURI().toString());
        icon.setImage(iconimg);
        // code aff
        File affile= new File("img/pfe.png");
        Image affimg= new Image(affile.toURI().toString());
        aff.setImage(affimg);
        //code list
        File listfile= new File("img/list.png");
        Image listimg= new Image(listfile.toURI().toString());
        list.setImage(listimg);
        //code notif
        File notiffile= new File("img/jweb.png");
        Image notifimg= new Image(notiffile.toURI().toString());
        notif.setImage(notifimg);

        File logofile= new File("img/logo.png");
        Image logoimg= new Image(logofile.toURI().toString());
        logo.setImage(logoimg);

    }


    //open page register
    public  void clickajout(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("Sign Up");
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
    //open page list
    public  void clicklist() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("checklist.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("Lists ");
            login.setScene(new Scene(root, 442, 476));
            login.show();
            Stage st = (Stage) list.getScene().getWindow();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public  void clickaff() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("listencad.fxml"));
            Stage login = new Stage();
            login.initStyle(StageStyle.UNDECORATED);
            login.setTitle("List students ");
            login.setScene(new Scene(root, 431, 514));
            login.show();
            Stage st = (Stage) aff.getScene().getWindow();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
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




}
