package Docker;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class StartDockerGrid {
    @BeforeTest
    public void startDockerGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");// Starting the Grid environment
        Thread.sleep(15000); // Waiting for 15 seconds
    }
}
