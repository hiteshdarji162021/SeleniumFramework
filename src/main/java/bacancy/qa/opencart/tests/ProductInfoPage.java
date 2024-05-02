package bacancy.qa.opencart.tests;

import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By productHeader = By.cssSelector("div h1");
    private By productImages = By.cssSelector("ul.thumbnails img");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getProductHeaderValue() {
        String productHeaderVal = elementUtil.doElementGetText(productHeader);
        return productHeaderVal;
    }

    public int getProductImagesCount() {
        int imageCount = elementUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        return imageCount;
    }
}