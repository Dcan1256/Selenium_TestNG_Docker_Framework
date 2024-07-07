package Docker;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class StopDockerGrid {


    @AfterTest
    public void stopDockerGrid() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat"); // Stopping the Grid environment
        Thread.sleep(5000); // Waiting for 5 seconds

        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
    }
}
