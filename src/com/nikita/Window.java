package com.nikita;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Window {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private final String NAME = "NewFile";
    private JFileChooser f = new JFileChooser();

    public Window() {
		
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("TextEdit");
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu file = new JMenu("Файл");
        JMenu edit = new JMenu("Правка");
        JMenu search = new JMenu("Search");
        
        JMenuItem newFile = new JMenuItem("Создать файл");
        JMenuItem openFile = new JMenuItem("Открыть файл");
        JMenuItem saveFile = new JMenuItem("Сохранить файл");
        JMenuItem printFile = new JMenuItem("Печать файла");
        
        JMenuItem undo = new JMenuItem("undo");
        JMenuItem redo = new JMenuItem("redo");
        JMenuItem cut = new JMenuItem("cut");
        JMenuItem copy = new JMenuItem("copy");
        JMenuItem past = new JMenuItem("past");
        JMenuItem selectAll = new JMenuItem("selectAll");
        
        JMenuItem find = new JMenuItem("find");
        JMenuItem findNext = new JMenuItem("findNext");
        JMenuItem findPrevious = new JMenuItem("findPrevious");
        JMenuItem replace = new JMenuItem("replace");
        
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
		
		edit.add(undo);
		edit.add(redo);
		edit.add(cut);
		edit.add(copy);
		edit.add(past);
		edit.add(selectAll);
		
		search.add(find);
		search.add(findNext);
		search.add(findPrevious);
		search.add(replace);
		
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(search);

        newFile.addActionListener(e -> {
            JTextArea textArea = new JTextArea();
            Scroll scroll = new Scroll(textArea, false, "");
            tabbedPane.add(NAME, scroll);
        });

        saveFile.addActionListener((ActionEvent e) -> {
            Scroll textArea = (Scroll) tabbedPane.getSelectedComponent();
            String output = textArea.getText();
            if (tabbedPane.countComponents() != 0) {
                if (textArea.isOpened()) try {
                    FileOutputStream writer = new FileOutputStream(textArea.getPath());
                    writer.write(output.getBytes());
                } catch (IOException eq) {
                    eq.printStackTrace();
                } else {
                    f.showSaveDialog(null);
                    File file2 = f.getSelectedFile();
                    try {
                        FileOutputStream writer = new FileOutputStream(file2);
                        writer.write(output.getBytes());
                    } catch (IOException eq) {
                        eq.printStackTrace();
                    }
                }
            }
        });

        openFile.addActionListener((ActionEvent e) -> {
            try {
                f.showOpenDialog(null);
                File file2 = f.getSelectedFile();
                String input = new String(Files.readAllBytes(Paths.get(file2.getAbsolutePath())), "UTF-8");
                JTextArea textArea = new JTextArea(input);
                Scroll scroll = new Scroll(textArea, true, file2.getAbsolutePath());
                tabbedPane.addTab(file2.getName(), scroll);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        printFile.addActionListener((ActionEvent e) -> {
            Scroll textArea = (Scroll) tabbedPane.getSelectedComponent();
            try {
                Desktop.getDesktop().print(new File(textArea.getPath()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
