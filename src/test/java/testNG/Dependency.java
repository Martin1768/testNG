package testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dependency {

    @Test
    public void LoginTestCase() {
        System.out.println("This is test case Login");
        System.out.println(1/0);
    }

    @Test(dependsOnMethods = {"LoginTestCase"})
    public void Dashboard() {
        System.out.println("This is test case Dashboard");
    }
}
