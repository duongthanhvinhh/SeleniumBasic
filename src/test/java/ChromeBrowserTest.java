import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.emulation.Emulation;
import org.openqa.selenium.devtools.v103.performance.Performance;
import org.openqa.selenium.devtools.v103.performance.model.Metric;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ChromeBrowserTest {
    public static WebDriver driver;

    //    public static void main(String[] args) {
//        //System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
//        //new ChromeDriver();
//    }
    @Test
    void openChromeNormalMode() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    void openChromeHeadlessMode() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        new ChromeDriver(options);
    }

    @Test
    void openMobileView() {
        WebDriverManager.chromedriver().setup();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 390);
        deviceMetrics.put("height", 844);
        deviceMetrics.put("pixelRatio", 1.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1");

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com");
    }

    @Test
    void devTool() {
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));

        driver.get("https://my-location.org/");
        driver.quit();
    }

    @Test
    void collectBrowserMetric() {
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        driver.get("https://google.com");
        driver.quit();

        for (Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }
    }

    @Test
    void testGoogleSearch(){
        openChromeNormalMode();
        driver.get("https://google.com");
        //------------------------------------------------------------------
        //Check combo box search:
        /*
        * name="q"  ----> nen dung vi co nghia
        * role="combobox"
        * spellcheck="false"
        * title="Tìm kiếm"  ----> nen dung vi co nghia, nhung no bi phu thuoc vao ngon ngu
        * value=""
        * aria-label="Tìm kiếm"  ----> nen dung vi co nghia, nhung no bi phu thuoc vao ngon ngu
        * data-ved="0ahUKEwjVos33l6D5AhUrJaYKHcKoBvAQ39UDCAQ">
        */
        //------------------------------------------------------------------
        //driver.findElement(By.tagName("input")).sendKeys("Selenium Java");
        //driver.findElement(By.className("gLFyf")).sendKeys("Selenium Java"); // ! Classname has value chua dau cach, thi input vao cum string truoc dau cach thoi la duoc.
        //driver.findElement(By.cssSelector("input")).sendKeys("Selenium Java");
        //driver.findElement(By.cssSelector("input.gLFyf")).sendKeys("Selenium Java");
        //driver.findElement(By.cssSelector("input[title='Tìm kiếm']")).sendKeys("Selenium Java");
        //driver.findElement(By.xpath("//input")).sendKeys("Selenium Java");
        //driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium Java");
        driver.findElement(By.xpath("//input[@title='Tìm kiếm']")).sendKeys("Selenium Java");
    }

}
