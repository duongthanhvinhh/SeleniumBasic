import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FirefoxBrowserTest {
    @Test
    void openFirefoxNormalMode() {
        WebDriverManager.firefoxdriver().setup();
        new FirefoxDriver();
    }

    @Test
    void openFirefoxHeadlessMode() {
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
//        new FirefoxDriver(options);
        WebDriverManager.firefoxdriver().setup();

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        new FirefoxDriver(firefoxOptions);

    }

}
