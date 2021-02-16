package me.dkim19375.wordunscrambler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UnscramblerController {
    @FXML
    private ProgressBar startingLoadingBar;

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
    }
}
