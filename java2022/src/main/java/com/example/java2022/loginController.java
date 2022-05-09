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

public class loginController implements Initializable {

        @FXML
        private Button exit;
        @FXML
        private Label invalid;
        @FXML
        private Button login;

        @FXML
        private ImageView grad;
        @FXML
        private ImageView logo;
        @FXML
        private TextField username;
        @FXML
        private PasswordField password;
        //code du exit

        public void exitOnAction(ActionEvent event) {

            Stage st = (Stage)exit.getScene().getWindow();
            st.close();
        }

        //code du login

        public void loginbuttonOnAction(ActionEvent event)
        {
            if ((username.getText().equals("admin")) && (password.getText().equals("admin")))
                        {
                            dashboard();

                        }
                else
                    if(checkstud())
                    {
                        dashboardetud();
                    }
                    else
                        if (checksprof())
                        {
                            dashboardpro();
                        }
                        else
                        {
                            invalid.setText("please check Your Informations");
                        }
            }
            //fonction checkstud

        public boolean checkstud(){
            boolean test=false;
            Connection connection;
            PreparedStatement pst;
            ResultSet rs;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                pst = (connection.prepareStatement("select idstudent,pass from student where idstudent= '"+username.getText()+"' and pass='"+ password.getText()+"'"));
                rs= pst.executeQuery();
                if (rs.next())
                {
                    test=true;
                }
                else
                {
                    invalid.setText("False Informations please Try again");
                }

            }catch (Exception ex)
            {
                System.out.println("base moshkel");
            }
            return test;
        }
    //fontion checkprof
    public boolean checksprof(){
        boolean test=false;
        Connection connection;
        PreparedStatement pst;
        ResultSet rs;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            pst = (connection.prepareStatement("select idprof ,pass_p from prof where idprof= '"+username.getText()+"' and pass_p='"+ password.getText()+"'"));
            rs= pst.executeQuery();
            if (rs.next())
            {
                test=true;
            }
            else
            {
                invalid.setText("False Informations please Try again");
            }

        }catch (Exception ex)
        {
            System.out.println("base moshkel");
        }
        return test;
    }




    //code importations des images

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            File gradfile= new File("img/grad.jpg");
            Image gradimg= new Image(gradfile.toURI().toString());
            grad.setImage(gradimg);

            //Second image logo

            File logofile= new File("img/logo.png");
            Image logoimg= new Image(logofile.toURI().toString());
            logo.setImage(logoimg);

    }
    //code form 2 sign up
    public  void dashboard(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle("Dashboard");
            dashboard.setScene(new Scene(root,578,525));
            dashboard.show();
            Stage st = (Stage)login.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public  void dashboardpro(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashprof.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle("Dashboard");
            dashboard.setScene(new Scene(root,578,525));
            dashboard.show();
            Stage st = (Stage)login.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public  void dashboardetud(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashetud.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle(" Dashboard");
            dashboard.setScene(new Scene(root,578,525));
            dashboard.show();
            Stage st = (Stage)login.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void regist(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("regstudent.fxml"));
            Stage dashboard = new Stage();
            dashboard.initStyle(StageStyle.UNDECORATED);
            dashboard.setTitle("Register PFE");
            dashboard.setScene(new Scene(root,402,514));
            dashboard.show();
            Stage st = (Stage)login.getScene().getWindow();
            st.close();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }



    }






