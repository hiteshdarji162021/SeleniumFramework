package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import bacancy.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accPage = loginPage.doLogin("hiteshdarji100@gmail.com", "9925472398@hH");
    }

    @Test
    public void accPageTitleTest() {
        String title = accPage.getAccPageTitle();
        Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
    }

    @Test
    public void accPageURLTest() {
        String url = accPage.getAccPageURL();
        Assert.assertTrue(url.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
    }

    @Test
    public void isLogoutLinkExist() throws InterruptedException {
        Assert.assertTrue(accPage.isLogoutLinkExists());
    }

    @Test
    public void accHeadersTest() {
        List<String> actualAccPageHeaderList = accPage.getAccountsPageHeadersList();
        Assert.assertEquals(actualAccPageHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
    }
}