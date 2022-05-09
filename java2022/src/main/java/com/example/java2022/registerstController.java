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

public class registerstController implements Initializable {
    @FXML
    private TextField matri;
    @FXML
    private Label checkmat;
    @FXML
    private Button back;
    @FXML
    private ImageView logo;
    @FXML
    private TextField nom;
    @FXML
    private Button search;
    @FXML
    private TextField cin;
    @FXML
    private Button reg;
    @FXML
    private TextField entreprise;
    @FXML
    private TextField sujet;
    @FXML
    private TextField binome;
    @FXML
    private TextField cbinome;
    public String aux;




    public void searchButtonOnAction(ActionEvent event) {
        checkmatt();


    }


    public void backButonOnAction(ActionEvent event) {
        getback();
    }

    public  void getback(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashetud.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle("Dashboard");
            dashboard.setScene(new Scene(root,578,528));
            dashboard.show();
            Stage st = (Stage)back.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logofile = new File("img/logo.png");
        Image logoimg = new Image(logofile.toURI().toString());
        logo.setImage(logoimg);

    }

    public void checkmatt() {
        Connection connection;
        PreparedStatement pst;
        ResultSet rs;
        ResultSet rs1,rs3;
        Statement st, st2,st3;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st = connection.createStatement();
            rs = st.executeQuery("select idstudent,namee from student where idstudent='" + matri.getText() + "'");
            aux=matri.getText();
            if (rs.next() ) {
                st3=connection.createStatement();
                rs3=st3.executeQuery("select idstudent,name_s from pfe where idstudent='"+matri.getText()+"'");
                if (rs3.next()) {
                    String id = rs.getString("idstudent");
                    String Name = rs.getString("namee");
                    matri.setText(id);
                    nom.setText(Name);
                    checkmat.setText("If you have Mistaken your id Please click again on the search button");
                    reg.setDisable(false);
                }
                else
                {
                    String id = rs.getString("idstudent");
                    String Name = rs.getString("namee");
                    matri.setText(id);
                    nom.setText(Name);
                    st2 = connection.createStatement();
                    st2.executeUpdate("insert into pfe(idstudent,name_s) values ('" + matri.getText() + "','" + nom.getText() + "')");
                    reg.setDisable(false);
                    checkmat.setText("If you have Mistaken your id Please click again on the search button");


                }

            }
            else {
                checkmat.setText("False ID please Try again");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();

        }
    }
    //code register student pfe

    public void insertcheck() {
        Connection connection;
        Statement st;
        if(cin.getText().isEmpty() || sujet.getText().isEmpty() || entreprise.getText().isEmpty()  )
        {
            checkmat.setText("Please Enter your Informations");
        }
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                st = connection.createStatement();
                st.executeUpdate("update pfe set cin_s='" + cin.getText() + "',sujet='" + sujet.getText() + "' ,entreprise='" + entreprise.getText() + "' ,etat='EN Attente dEncadrement' where idstudent='" + aux+ "'");
                checkmat.setText("PFE Details Succesfully Registered");
                cin.clear();
                sujet.clear();
                entreprise.clear();
                reg.setDisable(true);
                matri.clear();
                nom.clear();
                search.setDisable(false);
                cin.clear();
                sujet.clear();
                entreprise.clear();
                binome.clear();
                cbinome.clear();

            } catch (SQLException ex) {
                ex.printStackTrace();
                ex.getCause();

            }
        }


    }





}
