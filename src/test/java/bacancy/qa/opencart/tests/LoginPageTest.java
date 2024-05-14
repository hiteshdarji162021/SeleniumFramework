package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import bacancy.qa.opencart.constants.AppConstants;
import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.testng.Assert;
import org.testng.annotations.Test;



@Epic("EPIC- 100: design login page for open cart app")//Optional as per project. Its part of allure report.
@Story("US-Login: 101: design login page feature for open cart")////Optional as per project. Its part of allure report.
public class LoginPageTest extends BaseTest {
    private final Logger logger = Logger.getLogger(LoginPageTest.class);
    @Severity(SeverityLevel.MINOR)
    @Description("...checking the title of the page...")
    @Test(priority = 1)
    public void loginPageTitleTest() {
        MDC.put("testClassName", this.getClass().getSimpleName());
        logger.info("This is a log message from loginPageTitleTest");
        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
    }
    @Severity(SeverityLevel.NORMAL)
    @Description("...checking the url of the page...")
    @Test(priority = 2)
    public void loginPageURLTest() {
        MDC.put("testClassName", this.getClass().getSimpleName());
        logger.info("This is  log message from loginPageURLTest");
        String actualURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("...checking forgot password link is exist...")
    @Test(priority = 3)
    public void forgotPwdLinkExistTest() {
        MDC.put("testClassName", this.getClass().getSimpleName());
        logger.info("This is a log message from forgotPwdLinkExistTest");
        boolean status = loginPage.isForgotPwdLinkExist();
        Assert.assertTrue(status);
    }
    @Severity(SeverityLevel.BLOCKER)
    @Description("...checking user is able to login to app with correct username and password...")
    @Test(priority = 4)
    public void loginTest() {
        MDC.put("testClassName", this.getClass().getSimpleName());
        logger.info("This is a log message from loginTest");
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
        Assert.assertTrue(accPage.isLogoutLinkExists());
    }
}