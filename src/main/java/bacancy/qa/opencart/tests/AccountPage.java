package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class AccountPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private By inputSearchBy = By.name("search");
    private By accHeadersBy = By.xpath("//div[@id='content']/h2");
    private By linkLogoutBy = By.linkText("Logout");
    private By searchIconBy = By.xpath("//div[@id='search']//button");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getAccPageTitle() {
        String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
        System.out.println("page title is: " + title);
        return title;
    }

    public String getAccPageURL() {
        String URL = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
        System.out.println("page url is: " + URL);
        return URL;
    }

    public boolean isLogoutLinkExists() {
        return eleUtil.waitForElementVisible(linkLogoutBy, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }

    public boolean isSearchExist() {
        return eleUtil.waitForElementVisible(inputSearchBy, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }

    public List<String> getAccountsPageHeadersList() {
        List<WebElement> accHeaderList = eleUtil.waitForElementsVisible(accHeadersBy, AppConstants.DEFAULT_SHORT_TIME_OUT);
        List<String> accHeaderValList = new ArrayList<String>();
        for (WebElement e : accHeaderList) {
            String value = e.getText();
            accHeaderValList.add(value);
        }
        return accHeaderValList;
    }

    public SearchPage performSearch(String searchKey) {
        if (isSearchExist()) {
            eleUtil.doSendKeys(inputSearchBy, searchKey);
            eleUtil.doClick(searchIconBy);
            return new SearchPage(driver);
        } else {
            System.out.println("search field is not present on the page....");
            return null;
        }
    }
}