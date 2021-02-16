package me.dkim19375.wordunscrambler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnscramblerController {
    @FXML
    private Button changeButton;
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
        unscrambleButton.setOnAction((event) -> {
            unscrambleProgress.setVisible(true);
            final ObservableList<String> words = FXCollections.observableArrayList();
            unscrambledWordsList.setItems(words);
            unscrambledWordsList.setVisible(true);
            unscrambledWordsLabel.setVisible(true);
            unscramble();
            unscrambleProgress.setVisible(false);
        });
    }

    public Set<String> unscramble() {
        final ObservableList<String> wordsList = FXCollections.observableArrayList();
        final ProgressBar bar = unscrambleProgress;
        final Set<String> dictionary = new HashSet<>(UnscramblerController.dictionary);
        bar.setProgress(0.0);
        final double totalAmount = dictionary.size();
        double forEach = 1.0 / totalAmount;
        final Set<String> words = new HashSet<>();
        for (String word : dictionary) {
            bar.setProgress(bar.getProgress() + forEach);
            if (!isSame(unscrambleText.getText(), word)) {
                continue;
            }
            words.add(word);
            wordsList.add(word);
            unscrambledWordsList.setItems(wordsList);
        }
        System.out.println(words);
        return words;
    }

    public boolean isSame(final String first, final String second) {
        final char[] firstChar = first.toLowerCase().toCharArray();
        Arrays.sort(firstChar);
        final char[] secondChar = first.toLowerCase().toCharArray();
        Arrays.sort(secondChar);
        return firstChar == secondChar;
    }

    public static Set<String> getDictionary() {
        return dictionary;
    }

    public static void setDictionary(Set<String> dictionary) {
        UnscramblerController.dictionary = dictionary;
    }
}
