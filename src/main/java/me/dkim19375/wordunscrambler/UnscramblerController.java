package me.dkim19375.wordunscrambler;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.util.Set;

public class UnscramblerController {
    @FXML
    private ProgressBar startingLoadingBar;

    Set<String> dictionary;

    @FXML
    private void initialize() {
        startingLoadingBar.setVisible(false);
        startingLoadingBar.setProgress(0.0);
        startingLoadingBar.setVisible(true);
        this.dictionary = FileUtils.getStringPerLine(FileUtils.copyFromEmbedded("dictionary.txt", getClass()), startingLoadingBar);
        WordUnscramber.getPrimaryStage().getScene().setRoot(WordUnscramber.getMainRoot());
    }
}
