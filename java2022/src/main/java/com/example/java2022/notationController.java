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

public class notationController implements Initializable{
    @FXML
    private Button back;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView pfee;
    @FXML
    private Button search;
    @FXML
    private TextField mat;
    @FXML
    private TextField grade;
    @FXML
    private Label checkmat;
    @FXML
    private Button affecter;


    public void backButonOnAction(ActionEvent event){
        getback();
    }

    public  void getback(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashprof.fxml"));
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

public void checkmati(){
    Connection con;
    Statement st;
    ResultSet rs;

    if(mat.getText().isEmpty())
    {checkmat.setText("please fill the informations needed ");}
    else {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            st = con.createStatement();
            rs = st.executeQuery("select idpfe from pfe where idpfe='" + mat.getText() + "' and pfe_s is not null ");
            if (rs.next()) {
                    mat.setText(rs.getString("idpfe"));
                checkmat.setText("verified ID");

            }
            else
            {
                checkmat.setText("Wrong ID or no pfe file");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

}
    public void down(){


        Connection con;
        Statement st;
        ResultSet rs;
            if(grade.getText().isEmpty())
            {
                checkmat.setText("please enter your grade");
            }
            else{
        try {
           {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                st = con.createStatement();
                st.executeUpdate("update pfe set note='" + grade.getText() + "', etat='note' where idpfe='"+mat.getText()+"'");
                checkmat.setText("PFE Details Succesfully Registered");

            }

        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getStackTrace();

        }}

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
