package stream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.*;
import java.text.DecimalFormat;

/**
 * Writer class- write the contents to file
 * Every new write requires a new Writer instance
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class Writer {

    private FileWriter fstream = null;
    private String fileName ;
    private static final String newLine = System.getProperty("line.separator");

    /**
     * Writer constructor
     *
     * @param fileName path of file to write
     */
    public Writer(String fileName){
        this.fileName = fileName;
        File file = new File(fileName);
        try {
            fstream = new FileWriter(file, true);

        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    /**
     * Writes the value to file
     * @param value value to write
     */
    public void write(double value){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        double result = new Double(df.format(value));
        try {
            BufferedWriter out = new BufferedWriter(fstream);
            String val = String.format("%1.2f", result);
            out.write(val);
            out.newLine();
            out.close();
        }
        catch (IOException ioex){

        }
    }

    /**
     * Delete the file
     * Called only in unit tests
     */
    public void delete(){
        try {
            Path path = Paths.get(this.fileName);;
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " input_file or directory%n",fileName);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", fileName);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }
}
