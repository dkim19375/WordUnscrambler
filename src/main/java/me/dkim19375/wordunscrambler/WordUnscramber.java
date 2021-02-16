package me.dkim19375.wordunscrambler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class WordUnscramber extends Application {
    private static Stage primaryStage;
    private static Parent mainRoot;

    protected static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WordUnscramber.primaryStage = primaryStage;
        final URL loadingURL = Objects.requireNonNull(getClass().getClassLoader().getResource("loading.fxml"));
        final Parent loadingRoot = FXMLLoader.load(loadingURL);
        final URL mainURL = Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml"));
        mainRoot = FXMLLoader.load(mainURL);
        primaryStage.setTitle("Word Unscrambler");
        primaryStage.setScene(new Scene(loadingRoot, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() { return primaryStage; }

    public static Parent getMainRoot() { return mainRoot; }
}
