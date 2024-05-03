package bacancy.qa.opencart.base;

import bacancy.qa.opencart.factory.DriverFactory;
import bacancy.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    WebDriver driver;
    protected LoginPage loginPage;
    protected AccountPage accPage;
    protected Properties prop;
    protected SearchPage searchPage;
    protected ProductInfoPage productInfoPage;
    protected SoftAssert softAssert;
    protected RegisterPage registerPage;

    @BeforeTest
    public void setup() {
        df = new DriverFactory();
        prop = df.initProp();
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}