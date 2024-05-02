package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductPageInfoTest extends BaseTest {

    @BeforeClass
    public void productInfoPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

    @DataProvider
    public Object[][] getProductTestData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro", 4},
                {"iMac", "iMac", 3},
                {"Apple", "Apple Cinema 30\"", 6},
                {"Samsung", "Samsung SyncMaster 941BW", 1}
        };
    }

    @Test(dataProvider = "getProductTestData")
    public void productImageCountTest(String searchKey, String ProductName, int imgCount) {
        searchPage = accPage.performSearch(searchKey);
        productInfoPage = searchPage.selectProduct(ProductName);
        int imageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(imageCount, imgCount);
    }
}
