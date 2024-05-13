package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import bacancy.qa.opencart.constants.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

@Epic("EPIC- 200: design account page for open cart app")
@Story("US-Account: 201: account page feature for open cart")
public class  AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("...checking the title of the page...")
    @Test
    public void accPageTitleTest() {
        String title = accPage.getAccPageTitle();
        Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking the url of the page...")
    @Test
    public void accPageURLTest() {
        String url = accPage.getAccPageURL();
        Assert.assertTrue(url.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("...checking logout link is exist...")
    @Test
    public void isLogoutLinkExist() throws InterruptedException {
        Assert.assertTrue(accPage.isLogoutLinkExists());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking account headers count...")
    @Test
    public void accHeadersTest() {
        List<String> actualAccPageHeaderList = accPage.getAccountsPageHeadersList();
        Assert.assertEquals(actualAccPageHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking account page headers value...")
    @Test
    public void accHeadervaluesTest() {
        List<String> actualAccPageHeaderList = accPage.getAccountsPageHeadersList();
        System.out.println(actualAccPageHeaderList);
        Assert.assertEquals(actualAccPageHeaderList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][]{
                {"MacBook"},
                {"iMac"},
                {"Apple"},
                {"Samsung"}
        };
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking product count...")
    @Test(dataProvider = "getProductData")
    public void searchProductCountTest(String searchKey) {
        searchPage = accPage.performSearch(searchKey);
        Assert.assertTrue(searchPage.getSearchProductCount() > 0);
    }

    @DataProvider
    public Object[][] getProductTestData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro"},
                {"iMac", "iMac"},
                {"Apple", "Apple Cinema 30\""},
                {"Samsung", "Samsung SyncMaster 941BW"}
        };
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking product header...")
    @Test(dataProvider = "getProductTestData")
    public void searchProductTest(String searchKey, String productName) {
        searchPage = accPage.performSearch(searchKey);
        if (searchPage.getSearchProductCount() > 0) {
            productInfoPage = searchPage.selectProduct(productName);
            String actualProductHeader = productInfoPage.getProductHeaderValue();
            Assert.assertEquals(actualProductHeader, productName);
        }
    }
}