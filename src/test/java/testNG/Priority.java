package testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Priority {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("I'm beforeMethod()");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("I'm afterMethod()");
    }

    @Test(priority = 3)
    public void AtestCase() {
        System.out.println("This is test case A");
    }

    @Test(priority = 1, groups = "smoke")
    public void BtestCase() {
        System.out.println("This is test case B");
    }

    @Test(priority = 2)
    public void CtestCase() {
        System.out.println("This is test case C");
    }

    @Test
    public void FtestCase() {
        System.out.println("This is test case F");
    }
}
