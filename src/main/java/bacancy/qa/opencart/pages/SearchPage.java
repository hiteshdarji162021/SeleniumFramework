package bacancy.qa.opencart.pages;

import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By searchProductResults = By.xpath("//div[@id='content']//h4");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("...checking product count in search result...")
    public int getSearchProductCount() {
        int productCount = elementUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        System.out.println("product count-->" + productCount);
        return productCount;
    }

    @Step("...getting product detail...")
    public ProductInfoPage selectProduct(String productName) {
        By productLocator = By.linkText(productName);
        elementUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
        return new ProductInfoPage(driver);
    }
}