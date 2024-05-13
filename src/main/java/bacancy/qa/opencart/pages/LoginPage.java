package bacancy.qa.opencart.pages;

import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //1. Private By locators
    private By emailIDBy = By.id("input-email");
    private By passwordBy = By.id("input-password");
    private By forgotPwdLinkBy = By.xpath("//input[@id='input-password']//following-sibling ::a");
    private By loginBtnBy = By.xpath("//input[@type='submit']");
    private By registerLink = By.linkText("Register");

    //2. Page Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. page Actions/methods
    @Step("...getting login page title...")
    public String getLoginPageTitle() {
        String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_LONG_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
        System.out.println("Login page Title: " + title);
        return title;
    }

    @Step("...getting login page url...")
    public String getLoginPageURL() {
        String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
        System.out.println("login page url: " + url);
        return url;
    }

    @Step("...checking forgot password link is exist...")
    public boolean isForgotPwdLinkExist() {
        return eleUtil.waitForElementVisible(forgotPwdLinkBy, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }

    @Step("login with username : {0} and password : {1}")
    public AccountPage doLogin(String un, String pwd) {
        eleUtil.waitForElementVisible(emailIDBy, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
        eleUtil.doSendKeys(passwordBy, pwd);
        eleUtil.doClick(loginBtnBy);
        return new AccountPage(driver);
    }

    @Step("navigating to registration page")
    public RegisterPage navigateToRegisterPage() {
        eleUtil.waitForElementsVisible(registerLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
        eleUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }
}