package com.example.java2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

import java.io.IOException;

public class first extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stagelogin = new Stage();
        stagelogin.initStyle(StageStyle.UNDECORATED);
        stagelogin.setTitle("login");
        stagelogin.setScene(new Scene(root,508,418));
        stagelogin.show();
    }

    public static void main(String[] args) {
        launch();
    }
}