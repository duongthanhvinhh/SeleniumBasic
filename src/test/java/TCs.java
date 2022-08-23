import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TCs {
    public static WebDriver driver;

    @Test
    void TC01() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        /*
        TagName: input
        Attrs:
            type="password" --> có nghĩa nên giữ lại
            name="password"
            id="password"
         */
//        driver.findElement(By.name("username")).sendKeys("tomsmith");
//        driver.findElement(By.tagName("input")).sendKeys("tomsmith");
//
//        driver.findElement(By.cssSelector("input")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("input#username")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("[name=username]")).sendKeys("tomsmith");
//        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("tomsmith");
//
//        driver.findElement(By.xpath("//input")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//*[contains(@name,'username')]")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys("tomsmith");
//        driver.findElement(By.xpath("//*[contains(@id,'username')]")).sendKeys("tomsmith");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.tagName("i")).click();
        Thread.sleep(5000);
        WebElement logoutButton = driver.findElement(By.xpath("//i[normalize-space()='Logout']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Log In FAILED");
        driver.quit();
    }

    @Test
    void TC02_VerifyCheckbox() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        Thread.sleep(3000);
        WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        } else {
            System.out.println("checkbox1 has already been selected");
        }
        Assert.assertTrue(checkbox1.isSelected());
        if (!checkbox2.isSelected()) {
            checkbox2.click();
        } else {
            System.out.println("checkbox2 has already been selected");
        }
        Assert.assertTrue(checkbox2.isSelected());
        driver.quit();
    }

    @Test
    void TC03_VerifyDropdown() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        //! xpath to verify dropdown is selected
        WebElement option1 = driver.findElement(By.xpath("//option[@value='1']"));
//        WebElement option1 = driver.findElement(By.xpath("//option[@selected='selected']"));
//        WebElement option1 = driver.findElement(By.xpath("//option[text()='Option 1']"));
//        WebElement option1 = driver.findElement(By.xpath("//option[.='Option 1']"));
        Assert.assertTrue(option1.isSelected());
        driver.quit();
    }

    @Test
    void TC04_CreateTodo() {
        /*
        Scenario:
            1. Open browser
            2. Navigate to https://todomvc.com/examples/vanillajs/#/completed
            3. Fill in new ToDo with "Do Homework"
            4. Enter
            5. Verify new ToDo is appear in all/active view
            6. Verify Item left +1
         */
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vanillajs/#/completed");
        String currentTodos = driver.findElement(By.className("todo-count")).findElement(By.tagName("strong")).getText();
        if (currentTodos.equalsIgnoreCase("")){
            currentTodos = "0";
        }
        driver.findElement(By.className("new-todo")).sendKeys("Do Homework\n");
        //driver.findElement(By.className("new-todo")).sendKeys("Do Homework",Keys.ENTER);
        //driver.findElement(By.className("new-todo")).sendKeys(Keys.ENTER);
        //Verify Todo is displayed in all view
        driver.findElement(By.xpath("//a[normalize-space()='All']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[.='Do Homework']")).isDisplayed());
        //Verify Todo is displayed in active view
        driver.findElement(By.xpath("//a[normalize-space()='Active']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[.='Do Homework']")).isDisplayed());
        //Verify Item left +1
        int todoCount = Integer.parseInt(driver.findElement(By.className("todo-count"))
                .findElement(By.tagName("strong")).getText());
        Assert.assertEquals(todoCount,Integer.parseInt(currentTodos)+1);
        driver.quit();
    }

    @Test
    void TC05_nestedIframe(){

    }

    @Test
    void TC06_accept_JavaScriptAlerts(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
        driver.switchTo().alert().accept();
        WebElement result_element = driver.findElement(By.id("result"));
        Assert.assertTrue(result_element.isDisplayed());
        String result = result_element.getText();
        Assert.assertEquals(result,"You clicked: Ok");
        driver.quit();
    }

    @Test
    void TC07_cancel_JavaScriptAlerts(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        WebElement result_element = driver.findElement(By.id("result"));
        Assert.assertTrue(result_element.isDisplayed());
        String result = result_element.getText();
        Assert.assertEquals(result,"You clicked: Cancel");
        driver.quit();
    }

    @Test
    void TC08_BMICalculator(){

    }

}
