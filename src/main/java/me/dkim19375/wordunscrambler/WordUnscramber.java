package me.dkim19375.wordunscrambler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WordUnscramber extends Application {
    private static Stage primaryStage;
    private static Parent mainRoot;
    private static Parent landingRoot;

    protected static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WordUnscramber.primaryStage = primaryStage;
        landingRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("loading.fxml")));
        primaryStage.setTitle("Word Unscrambler");
        primaryStage.setScene(new Scene(landingRoot, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() { return primaryStage; }

    public static Parent getMainRoot() { return mainRoot; }

    public static Parent getLandingRoot() { return landingRoot; }

    public static void showMain(Class<?> clazz) {
        try {
            mainRoot = FXMLLoader.load(Objects.requireNonNull(clazz.getClassLoader().getResource("main.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(mainRoot);
    }
}