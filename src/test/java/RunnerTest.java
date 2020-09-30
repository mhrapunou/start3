import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;

public class RunnerTest extends TestCase {

    @Test
    public void testMain() {
        Runner.main(new String[]{"src/in.json"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMainEmptyFilename(){
        Runner.main(new String[]{""});
    }

    @Test(expected = FileNotFoundException.class)
    public void testMainFileNotFound(){
        Runner.main(new String[]{"dfuhuh"});
    }
}