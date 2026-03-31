package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class OrderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Первая форма (локаторы)
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Вторая форма (локаторы)
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-control");
    private final By blackColor = By.id("black");
    private final By greyColor = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("(//button[text()='Заказать'])[2]");


    // Подтверждение
    private final By yesButton = By.xpath("//button[text()='Да']");


    // МЕТОДЫ

    // Заполняем первую форму
    public void fillFirstForm(String name, String lastName, String address, String metro, String phone) {

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);

        // Метро — выбор из списка
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metro);

        By metroOption = By.xpath("//div[contains(@class,'select-search__select')]//div[text()='" + metro + "']");
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();

        driver.findElement(phoneField).sendKeys(phone);

        driver.findElement(nextButton).click();
    }

    public void fillSecondForm(String date, String rentalPeriod, String color, String comment) {

        // Дата
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.ESCAPE);

        // Срок аренды
        driver.findElement(rentalPeriodField).click();
        By period = By.xpath("//div[text()='" + rentalPeriod + "']");
        wait.until(ExpectedConditions.elementToBeClickable(period)).click();

        // Цвет
        if (color.equals("black")) {
            driver.findElement(blackColor).click(); // Чёрный жемчуг
        } else if (color.equals("grey")) {
            driver.findElement(greyColor).click(); // Серая безысходность
        }

        // Комментарий
        driver.findElement(commentField).sendKeys(comment);

        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    // Проверка
    private final By orderSuccess = By.xpath("//div[contains(@class,'Order_ModalHeader') and contains(text(),'Заказ оформлен')]");

    public String getOrderSuccessText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccess)).getText();
    }
}



