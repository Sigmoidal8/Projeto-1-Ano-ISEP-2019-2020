/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.LoadFileController;
import javafx.stage.FileChooser;

/**
 *
 * @author sandr
 */
public class FileChooserUI {

    private FileChooser fileChooser;
    private LoadFileController appController;

    private FileChooserUI() {
        fileChooser = new FileChooser();
        associateFilter("Transactions File", "*.txt");
        associateFilter("Transactions File", "*.csv");
    }

    public static FileChooser createFileChooser() {
        FileChooserUI transactionsFile = new FileChooserUI();
        return transactionsFile.fileChooser;
    }

    private void associateFilter(String description, String extension) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(description, extension);
        fileChooser.getExtensionFilters().add(filter);
    }
    
    public void associarParentUI(MainWindowUI main){
        appController = main.getLoadFileController();
    }
}

