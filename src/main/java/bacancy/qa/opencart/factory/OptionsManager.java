package bacancy.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
            co.addArguments("--headless");
            System.out.println("====running chrome in headless mode");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
            co.addArguments("--incognito");
        }
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
            fo.addArguments("--headless");
            System.out.println("====running firefox in headless mode");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
            fo.addArguments("--incognito");
        }
        return fo;
    }

    public EdgeOptions getEdgeOptions() {
        eo = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
            eo.addArguments("--headless");
            System.out.println("====running edge in headless mode");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
            eo.addArguments("--incognito");
        }
        return eo;
    }
}