package testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Annotations {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("I'm beforeMethod()");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("I'm afterMethod()");
    }

    @Test
    public void AtestCase() {
        System.out.println("This is test case A");
    }

    @Test(groups = "smoke")
    public void BtestCase() {
        System.out.println("This is test case B");
    }
}
