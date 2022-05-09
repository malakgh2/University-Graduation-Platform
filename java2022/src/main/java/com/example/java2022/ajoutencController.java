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

public class ajoutencController implements Initializable{
    @FXML
    private Button ajout;
    @FXML
    private TextField titre;
    @FXML
    private  TextField pfeidtxt;
    @FXML
    private TextField profidtxt;
    @FXML
    private TextField email;
    @FXML
    private ImageView logo;
    @FXML
    private Button back;
    @FXML
    private ListView<String> listvois;
    @FXML
    private ChoiceBox<String> choir;
    private String[] choirs = {"Pick Student Category ", "Supervisor", "Reporter"};
    @FXML
    private Label check;
    public String aux;
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
    public void showlistt(){

        if(choir.getValue().equals("Supervisor"))
        {
            liststudentenc();
        }
        else
        if(choir.getValue().equals("Reporter"))
        {
            liststudentrep();
        }
        else
        {
            check.setText("pick a choice please!");

        }

    }
    public void liststudentenc() {
        listvois.getItems().clear();

        Connection connection;
        Statement st;
        ResultSet rs;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=connection.createStatement();
            rs= st.executeQuery("select idstudent,idpfe,name_s,sujet from pfe where idprof is null and sujet is not null  ;");
            while (rs.next()) {
                String id=rs.getString("idstudent");
                String Name=rs.getString("name_s");
                String Sujet=rs.getString("sujet");
                String idpef=rs.getString("idpfe");
                String tout="\t\t\t"+idpef+" | "+Name+" | " + Sujet + "|" +id ;
                listvois.getItems().add(tout);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    public void liststudentrep() {
        listvois.getItems().clear();
        Connection connection;
        Statement st;
        ResultSet rs;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=connection.createStatement();
            rs= st.executeQuery("select idstudent,idpfe,name_s,sujet from pfe where rapporteur is null and idprof is not null and pfe_s is not null ;");
            while (rs.next()) {
                String id=rs.getString("idstudent");
                String Name=rs.getString("name_s");
                String Sujet=rs.getString("sujet");
                String idpef=rs.getString("idpfe");
                String tout="\t\t\t"+idpef+" | "+Name+" | " + Sujet + "|" +id ;
                listvois.getItems().add(tout);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logofile= new File("img/logo.png");
        Image logoimg= new Image(logofile.toURI().toString());
        logo.setImage(logoimg);
        choir.getItems().addAll(choirs);
        choir.getSelectionModel().select(0);

    }
    public void ajt(){

        if(pfeidtxt.getText().isEmpty() || profidtxt.getText().isEmpty() || email.getText().isEmpty())
        {
            check.setText("Please fill your informations");
        }
        else
            if(choir.getValue().equals("Supervisor"))
        {
            checkmattenc();


        }
        else
        if(choir.getValue().equals("Reporter"))
        {
            checkmattrap();



        }

    }

    public void checkmattrap() {

        Connection connection;
        PreparedStatement pst;
        ResultSet rs,rs2;
        ResultSet rs1,rs3,rs4;
        Statement st , st2,st3,st4;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st2 = connection.createStatement();
            rs2 = st2.executeQuery("select idpfe from pfe where idpfe='" + pfeidtxt.getText()+"'");
            if (rs2.next() ) {
                st = connection.createStatement();
                rs = st.executeQuery("select name_p,idprof from prof where idprof='" + profidtxt.getText() + "'");
                if(rs.next()) {
                    aux=rs.getString("name_p");
                    st4 = connection.createStatement();
                    rs4 = st.executeQuery("select idprof , idpfe from pfe where idprof='" + profidtxt.getText() + "' and idpfe='"+pfeidtxt.getText()+"'");
                    if(rs4.next()) {
                        st3 = connection.createStatement();
                        st3.executeUpdate("update pfe set rapporteur='"+profidtxt.getText()+"',nom_rapporteur='" + aux + "',mail_rapp='" + email.getText() + "', etat='en cours d’évaluation' where idpfe='" + pfeidtxt.getText() + "'");
                        check.setText("Student now have a reporter");
                        listvois.getItems().clear();



                    }
                    else
                    { check.setText("student doesnt have supervisor");

                    }
                }

            }
            else {
                check.setText("False ID please Try again");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();

        }
    }

    public void checkmattenc() {
        Connection connection;
        PreparedStatement pst;
        ResultSet rs,rs2;
        ResultSet rs1,rs3;
        Statement st , st2,st3;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st2 = connection.createStatement();
            rs2 = st2.executeQuery("select idpfe from pfe where idpfe='" + pfeidtxt.getText()+"'");
            if (rs2.next() ) {
                st = connection.createStatement();
                rs = st.executeQuery("select name_p from prof where idprof='" + profidtxt.getText() + "'");
                if(rs.next()) {
                    aux=rs.getString("name_p");
                    st3 = connection.createStatement();
                    st3.executeUpdate("update pfe set idprof='"+profidtxt.getText()+"', nom_prof='"+aux+"',mail_prof='"+email.getText()+"', etat='Encadre' where idpfe='" + pfeidtxt.getText() + "'");
                    check.setText("Student now have an advisor");
                    listvois.getItems().clear();


                }
                else
                {
                    check.setText("ID PROF FALSE");
                }

            }
            else {
                check.setText("False ID please Try again");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();

        }
    }




    }












