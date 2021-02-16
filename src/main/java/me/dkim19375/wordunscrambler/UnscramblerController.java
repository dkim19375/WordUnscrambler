package me.dkim19375.wordunscrambler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnscramblerController {
    @FXML
    private Button reloadButton;
    @FXML
    private Button unscrambleButton;
    @FXML
    private TextField unscrambleText;
    @FXML
    private Label unscrambledWordsLabel;
    @FXML
    private ListView<String> unscrambledWordsList;
    @FXML
    private ProgressBar unscrambleProgress;

    private static Set<String> dictionary;

    @FXML
    private void initialize() {
        unscrambleProgress.setVisible(false);
        unscrambledWordsList.setVisible(false);
        unscrambledWordsLabel.setVisible(false);
        unscrambleButton.setOnAction((event) -> buttonAction());
        unscrambleText.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonAction();
            }
        });
        reloadButton.setOnAction((event) -> {
            WordUnscramber.getPrimaryStage().getScene().setRoot(WordUnscramber.getLandingRoot());
            dictionary.clear();
            WordUnscramber.getLoadingController().initializeLoader();
        });
    }

    public void buttonAction() {
        unscrambleProgress.setVisible(true);
        final ObservableList<String> words = FXCollections.observableArrayList();
        unscrambledWordsList.setItems(words);
        unscrambledWordsList.setScaleX(2.0);
        unscrambledWordsList.setScaleY(2.0);
        unscrambledWordsList.setScaleZ(2.0);
        unscrambledWordsList.setVisible(true);
        unscrambledWordsLabel.setVisible(true);
        unscramble();
        unscrambleProgress.setVisible(false);
    }

    public void unscramble() {
        final ObservableList<String> wordsList = FXCollections.observableArrayList();
        final ProgressBar bar = unscrambleProgress;
        final Set<String> dictionary = new HashSet<>(UnscramblerController.dictionary);
        bar.setProgress(0.0);
        final double totalAmount = dictionary.size();
        double forEach = 1.0 / totalAmount;
        for (String word : dictionary) {
            bar.setProgress(bar.getProgress() + forEach);
            if (!isSame(unscrambleText.getText(), word)) {
                continue;
            }
            wordsList.add(word);
            unscrambledWordsList.setItems(wordsList);
        }
    }

    public boolean isSame(final String first, final String second) {
        final char[] firstChar = first.toLowerCase().toCharArray();
        Arrays.sort(firstChar);
        final char[] secondChar = second.toLowerCase().toCharArray();
        Arrays.sort(secondChar);
        return new String(firstChar).equalsIgnoreCase(new String(secondChar));
    }

    public static Set<String> getDictionary() {
        return dictionary;
    }

    public static void setDictionary(Set<String> dictionary) {
        UnscramblerController.dictionary = dictionary;
    }
}
