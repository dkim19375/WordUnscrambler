package me.dkim19375.wordunscrambler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UnscramblerController {
    @FXML
    private ProgressBar startingLoadingBar;
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

    Set<String> dictionary;

    @FXML
    private void initialize() {
        startingLoadingBar.setVisible(false);
        startingLoadingBar.setProgress(0.0);
        startingLoadingBar.setVisible(true);
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(this::start, 0, TimeUnit.NANOSECONDS);
    }

    private void start() {
        this.dictionary = FileUtils.getStringPerLine(FileUtils.copyFromEmbedded("dictionary.txt", getClass()),
                startingLoadingBar);
        WordUnscramber.showMain(getClass());
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
        final Set<String> dictionary = new HashSet<>(this.dictionary);
        bar.setProgress(0.0);
        final double totalAmount = dictionary.size();
        double forEach = 1.0 / totalAmount;
        final Set<String> words = new HashSet<>();
        for (String word : dictionary) {
            bar.setProgress(bar.getProgress() + forEach);
            final char[] input = unscrambleText.getText().toCharArray();
            final char[] match = word.toCharArray();
            if (input.length != match.length) {
                continue;
            }
            int i = 0;
            boolean valid = true;
            for (char inputChar : input) {
                if (inputChar == '*') {
                    continue;
                }
                if (inputChar == match[i]) {
                    continue;
                }
                valid = false;
            }
            if (!valid) {
                continue;
            }
            words.add(word);
            wordsList.add(word);
            unscrambledWordsList.setItems(wordsList);
        }
        return words;
    }
}