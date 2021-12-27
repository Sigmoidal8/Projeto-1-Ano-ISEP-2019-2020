/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Adapter.FileReaderAdapter;
import Model.CSVReader;
import Model.TXTReader;
import java.io.File;

/**
 *
 * @author sandr
 */
public class LoadFileController {

    /**
     * Checks the file format and calls out a file reader depending on the type
     * of format
     *
     * @param file
     */
    public void loadFile(File file) {
        FileReaderAdapter fileReaderAdapter = null;

        if (endsWith(file, "csv")) {
            fileReaderAdapter = new FileReaderAdapter(new CSVReader());
        } else if (endsWith(file, "txt")) {
            fileReaderAdapter = new FileReaderAdapter(new TXTReader());
        }
        fileReaderAdapter.read(file);
    }

    /**
     * Checks if the loaded file format equals the format received by parameter
     *
     * @param file
     * @param format
     * @return if the loaded file format equals the format received by parameter
     */
    private boolean endsWith(File file, String format) {
        String string = file.getPath().substring(file.getPath().length() - 4);
        return string.equals("." + format);
    }
}
