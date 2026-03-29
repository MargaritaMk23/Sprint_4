package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // ЛОКАТОРЫ

     // FQA вопросы
     private By question(int index) {
         return By.id("accordion__heading-" + index);
     }

     // FQA ответы
     private By answer(int index) {
         return By.id("accordion__panel-" + index);
     }

     // Кнопка "Заказать" вверху
    private final By orderButtonUp = By.xpath("//button[text()='Заказать']");

     // Кнопка "Заказать" внизу
    private final By orderButtonDown = By.xpath("(//button[text()='Заказать'])[2]");

    // Cookies
    private final By cookieButton = By.id("rcc-confirm-button");

    // МЕТОДЫ

    // Ожидание и закрытие Cookies
    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookieButton))
                .click();
    }

    // Проверка текста вопроса
    public String getQuestionText(int index) {
        return driver.findElement(question(index)).getText();
    }

    // Скролл вниз и клик на вопрос
    public void clickQuestion(int index) {
        WebElement element = driver.findElement(question(index));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        element.click();
    }

    // Ожидание и проверка текста ответа
    public String getAnswerText(int index) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answer(index)))
                .getText();
    }

    // Кнопки заказа
    public void clickOrderUp() {
        driver.findElement(By.xpath("//button[text()='Заказать']")).click();
    }

    public void clickOrderDown() {
        WebElement button = driver.findElement(By.xpath("(//button[text()='Заказать'])[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }
}
