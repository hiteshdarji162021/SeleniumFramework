package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import bacancy.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageTitleTest() {

        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
    }

    @Test(priority = 2)
    public void loginPageURLTest() {

        String actualURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }

    @Test(priority = 3)
    public void forgotPwdLinkExistTest() {
        boolean status = loginPage.isForgotPwdLinkExist();
        Assert.assertTrue(status);
    }

    @Test(priority = 4)
    public void loginTest() {
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
        Assert.assertTrue(accPage.isLogoutLinkExists());
    }
}