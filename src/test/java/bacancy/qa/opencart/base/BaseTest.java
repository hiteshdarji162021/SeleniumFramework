package bacancy.qa.opencart.base;

import bacancy.qa.opencart.factory.DriverFactory;
import bacancy.qa.opencart.tests.AccountPage;
import bacancy.qa.opencart.tests.LoginPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    DriverFactory df;
    WebDriver driver;
    protected LoginPage loginPage;
    protected AccountPage accPage;

    @BeforeTest
    public void setup() {
        df = new DriverFactory();
        driver = df.initDriver("chrome");
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
