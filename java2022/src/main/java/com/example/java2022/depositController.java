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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import  java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class depositController implements Initializable {
    @FXML
    private ImageView pfee;
    @FXML
    private ImageView logo;
    @FXML
    private TextField filev;
    @FXML
    private Label yess;
    @FXML
    private Label no;
    @FXML
    private Button deposit;
    @FXML
    private TextField mat;
    @FXML
    private Label idcheck;
    public String aux1;
    public File selectfile;
    @FXML
    private Button back;
    @FXML
    private Button deposit1;
    @FXML
    private Label luck;


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

    @FXML
    void filerpicker(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF FILES", "*.pdf"));
        selectfile = fc.showOpenDialog(null);
        yess.setText("");
        no.setText("");

        if (selectfile != null) {
            filev.setText(selectfile.getAbsolutePath());
            yess.setText("✔");
            deposit.setDisable(true);
            deposit1.setDisable(false);
        } else {
            filev.setText("you have to pick a file");
            no.setText("❌");

        }
    }


    public void upd(){
        Connection con;
        PreparedStatement pst;
        if(!filev.getText().isEmpty()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                pst = con.prepareStatement("update pfe set pfe_s=? ,etat='En Attente Dun Rapporteur' where idstudent='" + aux1+ "'");
                FileInputStream input = new FileInputStream(selectfile);
                pst.setBinaryStream(1, input);
                pst.executeUpdate();
                idcheck.setText(" your PFE FILE is well deposited");

            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
                filev.setText("data is too long");

            }
        }
        else
        {
           filev.setText("please Deposit A file First");
        }
    }

    public void checkmatt() {
        Connection connection;
        PreparedStatement pst;
        ResultSet rs;
        ResultSet rs1;
        Statement st, st2;
        if(mat.getText().isEmpty())
        {
           idcheck.setText("Please Enter you ID");
        }
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
                st = connection.createStatement();
                rs = st.executeQuery("select idstudent ,namee from student where idstudent='" + mat.getText() + "'");
                if (rs.next()) {
                    String id = rs.getString("idstudent");
                    String name_s =rs.getString("namee");
                    luck.setText("Hello "+name_s+", we hope you're doing well in your pfe , we wish you best of luck !");
                    mat.setText(id);
                    aux1=mat.getText();
                    deposit.setDisable(false);


                } else {
                    idcheck.setText("Wrong Id, Please Verify Your Id!!");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                ex.getCause();

            }
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
