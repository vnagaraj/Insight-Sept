package unittest;

import launch.Executor;
import org.junit.AfterClass;
import org.junit.Test;
import stream.Reader;
import stream.Writer;

import static org.junit.Assert.assertTrue;

/**
 * ExecutorTest class - Junit test to validate the Executor class
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 July 7th, 2016
 */
public class ExecutorTest {
    private static String INPUT_FILE = null;
    private static String OUTPUT_FILE = null;


    @Test
    public void executeTest()
    {
        INPUT_FILE = UnitTestConstants.CURRENT_DIR + UnitTestConstants.UNITTEST_PATH + UnitTestConstants.EXECUTOR_INPUT_FILE;
        OUTPUT_FILE = UnitTestConstants.CURRENT_DIR + UnitTestConstants.UNITTEST_PATH + UnitTestConstants.OUTPUT_FILE;
        Executor.getInstance(INPUT_FILE, OUTPUT_FILE);
        Reader reader = new Reader(OUTPUT_FILE);
        assertTrue(reader.readLine() .equals("1.00"));
        assertTrue(reader.readLine() .equals("1.00"));
        assertTrue(reader.readLine() .equals("1.50"));
        assertTrue(reader.readLine() .equals("2.00"));
        assertTrue(reader.readLine() .equals("1.00"));
        assertTrue(reader.readLine() .equals("1.50"));
        assertTrue(reader.readLine() .equals("1.50"));
        assertTrue(reader.readLine() .equals("1.00"));
    }

    @AfterClass
    public static void tearDown(){
        new Writer(OUTPUT_FILE).delete();
    }


}
