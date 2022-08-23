package bmi;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UnitCalculatorPages;

public class UnitCalculatorTest {
    static WebDriver driver;
    @BeforeClass
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.calculator.net/bmi-calculator.html");
        driver.manage().window().maximize();
    }
    @Test
    void Example1(){
        UnitCalculatorPages unitCalculatorPages = new UnitCalculatorPages(driver);
        unitCalculatorPages.selectMetricUnits();
        unitCalculatorPages.clearExampleForm();
        unitCalculatorPages.fillForm("25","Male","180","65");
        String result = unitCalculatorPages.getResult();
        Assert.assertEquals(result,"BMI = 20.1 kg/m2   (Normal)");
    }

    @AfterClass
    void closeBrowser(){
        driver.quit();
    }
}
