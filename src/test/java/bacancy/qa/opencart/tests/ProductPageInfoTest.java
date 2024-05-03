package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("EPIC- 300: design product page for open cart app")
@Story("US-Product: 301: product page feature for open cart")
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

    @Severity(SeverityLevel.NORMAL)
    @Description("...checking product images count...")
    @Test(dataProvider = "getProductTestData")
    public void productImageCountTest(String searchKey, String ProductName, int imgCount) {
        searchPage = accPage.performSearch(searchKey);
        productInfoPage = searchPage.selectProduct(ProductName);
        int imageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(imageCount, imgCount);
    }

    @DataProvider
    public Object[][] getProdTestData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro", "Apple", "Product 18"},
                {"iMac", "iMac", "Apple", "Product 14"},
                {"Apple", "Apple Cinema 30\"", "Apple", "Product 15"},
        };
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("...checking the product metadata...")
    @Test(dataProvider = "getProdTestData")
    public void productInfoTest(String searchKey, String productName, String brandName, String productCode) {
        searchPage = accPage.performSearch(searchKey);
        if (searchPage.getSearchProductCount() > 0) {
            productInfoPage = searchPage.selectProduct(productName);
            Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
            softAssert.assertEquals(actProductInfoMap.get("productname"), productName);
            softAssert.assertEquals(actProductInfoMap.get("Brand"), brandName);
            softAssert.assertEquals(actProductInfoMap.get("Product Code"), productCode);
            softAssert.assertAll();
        }
    }
}
