package me.dkim19375.wordunscrambler;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadingController {
    @FXML
    private ProgressBar startingLoadingBar;

    @FXML
    private void initialize() {
        startingLoadingBar.setVisible(false);
        startingLoadingBar.setProgress(0.0);
        startingLoadingBar.setVisible(true);
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(this::start, 0, TimeUnit.NANOSECONDS);
    }

    private void start() {
        UnscramblerController.setDictionary(FileUtils.getStringPerLine(FileUtils.getExternalFile("dictionary.txt",
                getClass()), startingLoadingBar));
        WordUnscramber.showMain(getClass());
    }
}