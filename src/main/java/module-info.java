module WordUnscrambler.main {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires org.apache.commons.io;

    opens me.dkim19375.wordunscrambler to javafx.fxml;

    exports me.dkim19375.wordunscrambler;
}