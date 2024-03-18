package drivers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    @Step("Выбор драйвера")
    public static WebDriver getDriver(String browserName){
        switch (browserName){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-fullscreen", "--incognito");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--kiosk", "-private");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new RuntimeException("Некорректное название браузера");
        }
    }
}
