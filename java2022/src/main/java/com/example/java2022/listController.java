package com.example.java2022;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import  java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class listController implements  Initializable {
    @FXML
    private Button back;
    @FXML
    private Label titre;
    @FXML
    private ListView<String> listvois ;
    @FXML
    private ChoiceBox<String> choir;
    private String[] choirs = {"Pick List ", "Student", "Professor","PFE"};
    @FXML
    private Button ts;
    @FXML
    private Label empty;
    public void backButonOnAction(ActionEvent event){
        getback();
    }

    public  void getback(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle("Dashboard");
            dashboard.setScene(new Scene(root,578,525));
            dashboard.show();
            Stage st = (Stage)back.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }

    }
    //
    public void showlistt(){
        listvois.getItems().clear();

        if(choir.getValue().equals("Student"))
        {
            titre.setText("LIST OF Students");
            liststudent();
        }
        else
        if(choir.getValue().equals("Professor"))
        {
            titre.setText("List of Professors");
            listprof();
        }
        else
        if(choir.getValue().equals("PFE"))
        {
            listpfe();
        }
        else
        {
            empty.setText("pick a choice please!");

        }

    }


    //view list students
    public void liststudent() {
        listvois.getItems().clear();
        Connection connection;
        Statement st;
        ResultSet rs;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=connection.createStatement();
            rs= st.executeQuery("select idstudent,surname,namee from student");
            while (rs.next()) {
                String id=rs.getString("idstudent");
                String Name=rs.getString("namee");
                String Surname=rs.getString("surname");
                String tout="\t\t\t\t "+id+" | "+Name+" | " + Surname ;
                listvois.getItems().add(tout);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void listpfe() {
        listvois.getItems().clear();
        Connection connection;
        Statement st;
        ResultSet rs;
        PreparedStatement pst;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=connection.createStatement();
            rs= st.executeQuery("select idpfe,sujet ,idstudent,name_s, idprof ,entreprise ,cin_s ,pfe_s ,contact_prof ,rapporteur,etat,note from pfe ");
            while (rs.next()) {
                String idpfee=rs.getString("idpfe");
                String sujete=rs.getString("sujet");
                String idproff=rs.getString("idprof");
                String idstudentt=rs.getString("idstudent");
                String Name_s=rs.getString("name_s");
                String entreprisee=rs.getString("entreprise");
                String cin_ss=rs.getString("cin_s");
                String pfe_ss=rs.getString("pfe_s");
                String contact_proff=rs.getString("contact_prof");
                String rapporteurr= String.valueOf(rs.getInt("rapporteur"));
                String etatt=rs.getString("etat");
                String notee= String.valueOf(rs.getFloat("note"));
                String tout=" "+idpfee+" | "+Name_s+" | " + sujete +"|" + idstudentt+ "|"+ idproff +"|" + entreprisee+ "|" + rapporteurr+ "|"+ etatt +"|" + notee;
                listvois.getItems().add(tout);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }



    //view list Proffesors
    public void listprof() {
        listvois.getItems().clear();

        Connection connection;
        Statement st;
        ResultSet rs;
        PreparedStatement pst;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=connection.createStatement();
            rs= st.executeQuery("select idprof,name_p,surname_p from prof");
            while (rs.next()) {
                String id=rs.getString("idprof");
                String Name=rs.getString("name_p");
                String Surname=rs.getString("surname_p");
                String tout=" \t\t\t\t "+id+"|"+Name+"|" + Surname ;
                listvois.getItems().add(tout);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choir.getItems().addAll(choirs);
        choir.getSelectionModel().select(0);

    }
}
