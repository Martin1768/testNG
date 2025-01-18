package testNG;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Data_Provider {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // nezobrazí okno prohlížeče
        options.addArguments("--disable-gpu"); // Volitelné pro lepší výkon
        options.addArguments("--no-sandbox"); // Rychlejší start
        options.addArguments("--disable-dev-shm-usage"); // Lepší práce s pamětí

        driver = new ChromeDriver(options);
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    @DataProvider(name="credentials")
    public Object[][] data()  {
        Object[][] loginData = {
        {"Admin", "12345", "Invalid credentials"},
        {"ABCD", "Hum@nhrm123", "Invalid credentials"},
        {"Admin", "", "Password is Empty"},
        {"", "Hum@nhrm123", "Username cannot be empty"}
    };
    return loginData;
    }

    @Test(dataProvider = "credentials")
    public void loginTest(String usrname, String pass, String ErrorMsg) {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys(usrname);
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(pass);
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();
        WebElement errorElement = driver.findElement(By.id("spanMessage"));
        String actualErrorMessage = errorElement.getText();

        Assert.assertEquals(actualErrorMessage, ErrorMsg);
        /*
        boolean statusOfLoginBtn = loginButton.isEnabled();
        Assert.assertTrue(statusOfLoginBtn);
        WebElement actualMsg = driver.findElement(By.id("welcome"));
        String actualMessage = actualMsg.getText();
        Assert.assertEquals(actualMessage, "Welcome Admin");
*/
    }

}
