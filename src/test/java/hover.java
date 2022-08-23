import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class hover {
    public static WebDriver driver;
    @BeforeClass
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");
        driver.manage().window().maximize();
    }

    @Test
    void hover(){
        Actions mouse = new Actions(driver);
        mouse.moveToElement(driver.findElement(By.xpath("//div[@class='figure'][1]"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='figure'][1]/div[@class='figcaption']")).isDisplayed());
    }

    @Test
    void screenShot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
    }
    @AfterClass
    void closeBrowser(){
        driver.quit();
    }
}
