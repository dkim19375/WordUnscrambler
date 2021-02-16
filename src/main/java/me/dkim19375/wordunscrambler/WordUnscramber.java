package me.dkim19375.wordunscrambler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        final Parent loadingRoot = FXMLLoader.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("loading.fxml")));
        mainRoot = FXMLLoader.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("main.fxml")));
        primaryStage.setTitle("Word Unscrambler");
        primaryStage.setScene(new Scene(loadingRoot, 1280, 720));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() { return primaryStage; }

    public static Parent getMainRoot() { return mainRoot; }
}
