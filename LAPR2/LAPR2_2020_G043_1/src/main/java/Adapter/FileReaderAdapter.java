/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Model.FileReader;
import java.io.File;

/**
 *
 * @author sandr
 */
public class FileReaderAdapter {

    /**
     * Reference to the file reader interface
     */
    private FileReader fileReader;

    /**
     * Creates an instance of FileReaderAdapter receiving an instance of the
     * FileReader class by parameter
     *
     * @param fr
     */
    public FileReaderAdapter(FileReader fr) {
        fileReader = fr;
    }

    /**
     * Receives a file by parameter and calls out the read method in the
     * FileReader class
     *
     * @param file
     */
    public void read(File file) {
        fileReader.read(file);
    }
}
