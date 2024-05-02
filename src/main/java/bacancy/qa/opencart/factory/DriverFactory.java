package bacancy.qa.opencart.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager optionsmanager;
    public static String highlight;

    public WebDriver initDriver(Properties prop) {
        optionsmanager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(optionsmanager.getChromeOptions());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver(optionsmanager.getEdgeOptions());
        } else {
            System.out.println("please pass the right browse..." + browserName);
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    public Properties initProp() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}