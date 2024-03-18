package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Page {

    protected final WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Ожидание видимости элемента")
    protected void waitForVisibility(By element) {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public abstract void waitForLoad();

}
