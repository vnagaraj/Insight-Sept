package stream;

import parser.ParserConstants;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reader class - read the contents from file line by line
 * Uses file scanner functionality
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class Reader {

    private Scanner textScanner;

    /**
     * Reader constructor
     * @param filename path of file
     */
    public Reader(String filename){
        Path fFilePath = Paths.get(filename);
        try {
            textScanner = new Scanner(fFilePath, ParserConstants.ENCODING.name());
        }
        catch (IOException e){
            System.err.print("Exception while reading file");
        }
    }

    /**
     * Gets the line from the text
     * @return line
     */
    public String readLine(){
        while(textScanner.hasNext()){
            return textScanner.nextLine();
        }
        return null;
    }

    /**
     * Checks for the next line from the text
     * @return true if line found
     */
    public boolean hasNext(){
        return textScanner.hasNext();
    }

    /**
     * Close the scanner
     */
    public void close(){
        textScanner.close();
    }
}
