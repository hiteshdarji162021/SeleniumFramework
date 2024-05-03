package bacancy.qa.opencart.tests;

import java.util.Random;

import bacancy.qa.opencart.base.BaseTest;
import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ExcelUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("EPIC- 400: design registration page for open cart app")
@Story("US-Product: 401: registration page feature for open cart")
public class RegisterPageTest extends BaseTest {

    @BeforeClass
    public void regPageSetup() {
        registerPage = loginPage.navigateToRegisterPage();
    }

    public String getRandomEmail() {
        Random random = new Random();
        String email = "automation" + System.currentTimeMillis() + "@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][] getRegTestData() {
        Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
        return regData;
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("...checking registration successfully complete...")
    @Test(dataProvider = "getRegTestData")
    public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {

        Assert.assertTrue(
                registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
    }
}