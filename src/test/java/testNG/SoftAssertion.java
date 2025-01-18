package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SoftAssertion {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // nezobrazí okno prohlížeče
        options.addArguments("--disable-gpu"); // Volitelné pro lepší výkon
        options.addArguments("--no-sandbox"); // Rychlejší start
        options.addArguments("--disable-dev-shm-usage"); // Lepší práce s pamětí

        driver = new ChromeDriver(options);
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    @Test(groups = "smoke")
    public void loginTestCase() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys("Admin");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("Hum@nhrm123");
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        boolean statusOfLoginBtn = loginButton.isEnabled();
//        boolean statusOfLoginBtn = !loginButton.isEnabled();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(statusOfLoginBtn);
        System.out.println("statusOfLoginBtn = " + statusOfLoginBtn);
        loginButton.click();

        WebElement actualMsg = driver.findElement(By.id("welcome"));
        String actualMessage = actualMsg.getText();
        softAssert.assertEquals(actualMessage, "Welcome Admin");
        softAssert.assertAll();
    }

}
