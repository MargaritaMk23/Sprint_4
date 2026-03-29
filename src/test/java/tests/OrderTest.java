package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String lastName, String address, String metro, String phone,
                     String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {
                        "Елена", "Кострова", "Москва, ул. Бобруйская", "Кунцевская",
                        "89099073577", "10.07.2026", "сутки", "black", "Позвонить за 5 минут"
                },
                {
                        "Алексей", "Смирнов", "Москва, ул. Ленина", "Сокольники",
                        "89919682020", "12.07.2026", "двое суток", "grey", "Не звонить"
                }
        });
    }

    @Test
    public void makeOrderFromUpButton() {

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickOrderUp();

        OrderPage orderPage = new OrderPage(driver);

        // Первая форма
        orderPage.fillFirstForm(name, lastName, address, metro, phone);

        // Вторая форма
        orderPage.fillSecondForm(date, rentalPeriod, color, comment);


        // Подтверждение
        orderPage.confirmOrder();

        // Проверка
        String successText = orderPage.getOrderSuccessText();
        assertTrue("Заказ не оформлен", successText.contains("Заказ оформлен"));
    }

    @Test
    public void makeOrderFromDownButton() {

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickOrderDown();

        OrderPage orderPage = new OrderPage(driver);

        // Первая форма
        orderPage.fillFirstForm(name, lastName, address, metro, phone);

        // Вторая форма
        orderPage.fillSecondForm(date, rentalPeriod, color, comment);

        // Подтверждение
        orderPage.confirmOrder();

        // Проверка
        String successText = orderPage.getOrderSuccessText();
        assertTrue("Заказ не оформлен", successText.contains("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}