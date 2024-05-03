package bacancy.qa.opencart.pages;

import bacancy.qa.opencart.constants.AppConstants;
import bacancy.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By productHeader = By.cssSelector("div h1");
    private By productImages = By.cssSelector("ul.thumbnails img");
    private Map<String, String> productInfoMap;
    private By inputSearchBy = By.xpath("//input[@name='search']");
    private By productMetaDataBy = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
    private By productPriceDataBy = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("...getting product header value...")
    public String getProductHeaderValue() {
        String productHeaderVal = elementUtil.doElementGetText(productHeader);
        return productHeaderVal;
    }

    @Step("...getting product images count...")
    public int getProductImagesCount() {
        int imageCount = elementUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
        return imageCount;
    }

    @Step("...getting product meta data...")
    public Map<String, String> getProductInfo() {
        productInfoMap = new LinkedHashMap<String, String>();  // For Sequential data
        //productInfoMap = new HashMap<String,String>();  // if order does not meter than use hashmap
        //productInfoMap = new TreeMap<String,String>(); //if user need in alphabetical order
        productInfoMap.put("productname", getProductHeaderValue());
        getProductMetaData();
        System.out.println(productInfoMap);
        return productInfoMap;
    }

    public void getProductMetaData() {
        List<WebElement> metalist = elementUtil.getElements(productMetaDataBy);
        for (WebElement e : metalist) {
            String meta = e.getText();
            String metainfo[] = meta.split(":");
            String key = metainfo[0].trim();
            String value = metainfo[1].trim();
            productInfoMap.put(key, value);
        }
    }
}