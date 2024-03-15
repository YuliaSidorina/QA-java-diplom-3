package browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Browser {

    public static void browserChoice() {
        String browser = getBrowserFromProperties();
        if (browser.equals("Yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            Configuration.browserBinary = "Applications/Yandex.app";
            WebDriver yandexDriver = new ChromeDriver();
            WebDriverRunner.setWebDriver(yandexDriver);
        } else {
            Configuration.browser = browser;
        }
    }

    public static void closeNotChromeBrowser() {
        String browser = getBrowserFromProperties();
        if (!browser.equals("Chrome")) closeWebDriver();
    }

    private static String getBrowserFromProperties() {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = System.getenv("BROWSER");
        }
        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty("BROWSER");
        }
        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty("browser", "Chrome");
        }
        return browser;
    }
}
