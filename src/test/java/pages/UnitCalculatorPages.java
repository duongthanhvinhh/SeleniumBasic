package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UnitCalculatorPages {
    public WebDriver driver;
    private By clearButn = By.xpath("//img[@class='clearbtn']");
    private By ageTextBox = By.id("cage");
    private By maleCheckBox = By.xpath("//label[normalize-space()='Male']");
    private By femaleCheckBox = By.xpath("//label[normalize-space()='Female']");
    private By resultLabel = By.className("bigtext");
    private By metricUnitTab = By.xpath("//a[normalize-space()='Metric Units']");
    public UnitCalculatorPages(WebDriver driver){
        this.driver = driver;
    }
    public void clearExampleForm() {
        driver.findElement(clearButn).click();
    }

    public void fillForm(String age, String gender, String height, String weight) {
        driver.findElement(ageTextBox).sendKeys(age);
        if(gender.equalsIgnoreCase("Male")){
            driver.findElement(maleCheckBox).click();
        }else {
            driver.findElement(femaleCheckBox).click();
        }
        driver.findElement(By.id("cheightmeter")).sendKeys(height);
        driver.findElement(By.id("ckg")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@value='Calculate']")).click();
    }

    public String getResult() {
        return driver.findElement(resultLabel).getText();
    }


    public void selectMetricUnits() {
        driver.findElement(metricUnitTab).click();
    }
}
