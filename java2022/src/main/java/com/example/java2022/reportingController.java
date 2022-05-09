package com.example.java2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class reportingController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private ListView<String> listvois;
    @FXML
    private Label check;
    @FXML
    private ChoiceBox<String> choir;
    private String[] choirs = {"Pick Category ", "Supervisor", "Reporter"};
    @FXML
    private TextField profidtxt;
    @FXML
    private Button ajout;
    @FXML
    private Button back;
    @FXML
    private Button download ;
    @FXML
    private TextField dwfield;


    public void backButonOnAction(ActionEvent event){
        getback();
    }

    public  void getback(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashprof.fxml"));
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



    public void down(){


        Connection con;
        Statement st;
        ResultSet rs;
        InputStream input=null;
        FileOutputStream output=null;



        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st=con.createStatement();
            rs=st.executeQuery("select pfe_s from pfe where idpfe='"+dwfield.getText()+"' and rapporteur='"+profidtxt.getText()+"' ");
            if(rs.next()){
                File thefile = new File("pfe.pdf");
                output=new FileOutputStream(thefile);
                input=rs.getBinaryStream("pfe_s");
                byte[] buffer=new byte[1024];
                while (input.read(buffer)>0)
                {
                    output.write(buffer);
                }
                dwfield.setText(thefile.getAbsolutePath());
            }
            else {
                check.setText("no pfe existing ");
            }






        }
        catch (SQLException | IOException ex){
            ex.printStackTrace();
            ex.getStackTrace();

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
            rs= st.executeQuery("select idstudent,idpfe,name_s,sujet from pfe where idprof='"+profidtxt.getText()+"' ;");
            if(rs.next()) {
                while (rs.next()) {
                    String id = rs.getString("idstudent");
                    String Name = rs.getString("name_s");
                    String Sujet = rs.getString("sujet");
                    String pfee = rs.getString("idpfe");
                    String tout = "\t\t" + pfee + " | " + Name + " | " + Sujet+"|" +id ;
                    listvois.getItems().add(tout);
                }
            }
            else {check.setText("Wrong ID");}

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
            rs= st.executeQuery("select idstudent,idpfe,name_s,sujet from pfe where rapporteur='"+profidtxt.getText()+"' ;");
            if (rs.next()) {
            while (rs.next()) {
                String id=rs.getString("idstudent");
                String Name=rs.getString("name_s");
                String Sujet=rs.getString("sujet");
                String pfee = rs.getString("idpfe");
                String tout = "\t\t" + pfee + " | " + Name + " | " + Sujet+"|" +id ;
                listvois.getItems().add(tout);
            }}
            else{
                check.setText("wrong id ");
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
}
