package bacancy.qa.opencart.base;

import bacancy.qa.opencart.factory.DriverFactory;
import bacancy.qa.opencart.tests.AccountPage;
import bacancy.qa.opencart.tests.LoginPage;
import bacancy.qa.opencart.tests.ProductInfoPage;
import bacancy.qa.opencart.tests.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    WebDriver driver;
    protected LoginPage loginPage;
    protected AccountPage accPage;
    protected Properties prop;
    protected SearchPage searchPage;
    protected ProductInfoPage productInfoPage;

    @BeforeTest
    public void setup() {
        df = new DriverFactory();
        prop = df.initProp();
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}