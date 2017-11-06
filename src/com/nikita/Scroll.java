package com.nikita;

import javax.swing.*;

public class Scroll extends JScrollPane {

    private final JTextArea textArea;
    private final boolean isOpened;
    private final String path;

    public Scroll(JTextArea text, boolean isOpened, String path) {
        super(text);
        this.textArea = text;
        this.isOpened = isOpened;
        this.path = path;
    }

    public String getText() {
        return textArea.getText();
    }


    public boolean isOpened() {
        return isOpened;
    }

    public String getPath() {
        return path;
    }
}
