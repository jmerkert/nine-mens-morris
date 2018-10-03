package com.merkert.morris.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameBoard extends Application {

    private BackgroundImage gameBoardImage;

    @Override
    public void init() {
        gameBoardImage = new BackgroundImage(new Image("gameBoard.gif", 800, 800, true, true), //
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1200, 800);
        root.setBackground(new Background(gameBoardImage));
        stage.setTitle("Mühle");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
    }

    public static void main(String[] parameters) {
        launch(parameters);
    }

}