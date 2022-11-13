package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.CapabilityFactory;
import pages.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public HomePage homePage;
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    @Parameters(value={"browser"})
    public void classLevelSetup(String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://192.168.0.211:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver.get());
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass void terminate () {
        //Remove the ThreadLocalMap element
        driver.remove();
    }
}