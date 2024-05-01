package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageTitleTest() {

        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle,"Account Login");
    }

    @Test
    public void loginPageURLTest() {

        String actualURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actualURL.contains("route=account/login"));
    }

    @Test
    public void forgotPwdLinkExistTest() {
        boolean status= loginPage.isForgotPwdLinkExist();
        Assert.assertTrue(status);
    }

    @Test
    public void loginTest() {
        accPage= loginPage.doLogin("hiteshdarji100@gmail.com","9925472398@hH");
        Assert.assertTrue(accPage.isLogoutLinkExists());

    }

}
