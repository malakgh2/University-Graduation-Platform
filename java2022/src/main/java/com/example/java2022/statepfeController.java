package com.example.java2022;
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


public class statepfeController implements  Initializable {
    @FXML
    private ImageView logo;
    @FXML
    private ImageView pfee;
    @FXML
    private Label state;
    @FXML
    private Label luck;
    @FXML
    private Label grade;
    @FXML
    private Label idcheck;
    @FXML
    private Button back;
    @FXML
    private Button search;
    @FXML
    private TextField mat;
    public String aux1;
    @FXML
    private Label rapi;
    @FXML
    private Label enca;


    public void searcheck(){
       checkmatt();
       search.setDisable(false);
    }

    public void checkmatt() {
        Connection connection;
        ResultSet rs;
        Statement st;
        if(mat.getText().isEmpty())
        {
            idcheck.setText("Please Enter you ID");
        }
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                st = connection.createStatement();
                rs = st.executeQuery("select etat,idstudent,mail_prof,nom_prof,nom_rapporteur,mail_rapp,name_s,note,note from pfe where idstudent='" + mat.getText() + "'");
                if (rs.next()) {
                    String etat =rs.getString("etat");
                    Float gradee =rs.getFloat("note");
                    String nam_prof =rs.getString("nom_prof");
                    String m_prof =rs.getString("mail_prof");
                    String nam_rap =rs.getString("nom_rapporteur");
                    String ma_rap =rs.getString("mail_rapp");
                    String name_s=rs.getString("name_s");
                    enca.setText("Votre Encadreur est '"+nam_prof+"' '"+m_prof+"'");
                    rapi.setText("Votre Rapporteur est '"+nam_rap+"' '"+ma_rap+"'");
                    luck.setText("Hello "+name_s+", we hope you're doing well in your pfe , we wish you best of luck !");
                    String id = rs.getString("idstudent");
                    mat.setText(id);
                    state.setText(etat);
                    grade.setText(String.valueOf(gradee));
                    search.setDisable(true);
                    mat.setDisable(true);

                } else {
                    idcheck.setText("Wrong Id, Please Verify Your Id!!");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                ex.getCause();

            }
        }
    }





    public void backButonOnAction(ActionEvent event){
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

        File logofile= new File("img/logo.png");
        Image logoimg= new Image(logofile.toURI().toString());
        logo.setImage(logoimg);

        File pfeefile= new File("img/pfe.png");
        Image pfeeimg= new Image(pfeefile.toURI().toString());
        pfee.setImage(pfeeimg);


    }
}
