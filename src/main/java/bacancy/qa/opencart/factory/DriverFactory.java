package bacancy.qa.opencart.factory;

import bacancy.qa.opencart.exception.FrameworkException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    public Properties prop;
    public OptionsManager optionsmanager;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public WebDriver initDriver(Properties prop) {
        optionsmanager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            if(Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver("chrome");
            } else {
            tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
            }
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if(Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver("firefox");
            } else {
                tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
            }
        } else if (browserName.equalsIgnoreCase("safari")) {
            tlDriver.set(new SafariDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            if(Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver("edge");
            } else {
                tlDriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
            }
        } else {
            System.out.println("please pass the right browse..." + browserName);
            throw new FrameworkException("NO BROWSER FOUND EXCEPTION...");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    /*
    get the local thread copy of the driver;
     */
    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");
        System.out.println("Running test cases on Env: " + envName);
        try {
            if (envName == null) {
                System.out.println("no env is passed....Running tests on QA env...");
                ip = new FileInputStream("./src/test/resources/config/qa.properties");
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.properties");
                        break;
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/config/prod.properties");
                        break;
                    default:
                        System.out.println("....Wrong env is passed....No need to run the test cases....");
                        throw new FrameworkException("WRONG ENV IS PASSED...");
                }
            }
        } catch (FileNotFoundException e) {
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * take screenshot
     */
    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    private void init_remoteDriver(String browserName) {
        System.out.println("Running grid server on browser name==>"+browserName);
        try {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getEdgeOptions()));
                    break;
                default:
                    System.out.println("Please pass the right browser for remote execution");
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}