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

public class signupController implements Initializable{
    @FXML
    private  Button  signup;
    @FXML
    private ChoiceBox<String> choir;
    private String[] choirs = {"Choose Registration Table ", "Student", "Professor"};
    @FXML
    private Button exit;
    @FXML
    private TextField name;
    @FXML
    private Label config;
    @FXML
    private Label matchless;
    @FXML
    private TextField surname;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private Button back;
    @FXML
    private ImageView logoo;

    //code du exit

    public void exitOnAction(ActionEvent event) {
        Stage stt = (Stage) exit.getScene().getWindow();
        stt.close();

    }

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

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            choir.getItems().addAll(choirs);
            choir.getSelectionModel().select(0);


            //Second image logo
                File logoofile= new File("img/logo.png");
                Image logooimg= new Image(logoofile.toURI().toString());
                logoo.setImage(logooimg);
            }
         public void signupButtonOnAction(ActionEvent event)
         {
             if(name.getText().isEmpty() || surname.getText().isEmpty() || password.getText().isEmpty() || confirm.getText().isEmpty() )
                {
                    config.setText("Please Enter your Informations");
                }
             else
             if(choir.getValue().equals("Student"))
             {
                 fill();
             }
             else
                if(choir.getValue().equals("Professor"))
             {
                 fillP();
             }
                else
                {
                    config.setText("please Pick Registration");
                }

         }
         //fill of student
         public  void fill()
         {
             Connection con;
             Statement st;
             try {
                 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java ", "root", "admin");
                 if(password.getText().equals(confirm.getText())) {
                     st = con.createStatement();
                      st.executeUpdate("insert into student(surname,namee,pass) values ('" + surname.getText() + "','" + name.getText() + "','" + confirm.getText() + "')");
                        config.setText("Student Registered");

                 }
                 else
                 {
                     matchless.setText("Password Doesn't match !");
                 }

             } catch (SQLException e) {
                 e.printStackTrace();
             }


         }
             public  void fillP()
             {
                Connection con;
                 Statement st;

                 try {
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java ", "root", "admin");
                        if(password.getText().equals(confirm.getText()))
                            {
                                        st = con.createStatement();
                                        st.executeUpdate("insert into prof(name_p,surname_p,pass_p) values ('" + name.getText() + "','" + surname.getText() + "','" + confirm.getText() + "')");
                                        config.setText("Professor Registered");
                            }
                        else
                            {
                                matchless.setText("Password Doesn't match !");
                            }

                    } catch (SQLException e)
                        {
                            e.printStackTrace();
                        }


            }

    }






