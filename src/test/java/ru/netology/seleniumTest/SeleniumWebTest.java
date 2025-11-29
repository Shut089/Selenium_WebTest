package ru.netology.seleniumTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumWebTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        // WebDriverManager сам подберёт и скачает нужную версию chromedriver под ОС
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldOpenMainPage() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Вводим валидные данные в поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        // Вводим валидный номер телефона 11 цифр
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("+71234567890");
        // Ставил галочку в CheckBox Согласие
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"))
                .click();
        // Нажимаем кнопку отправить
        driver.findElement(By.cssSelector("button.button_view_extra"))
                .click();
        String result = driver.findElement(By.cssSelector("[data-test-id='order-success']"))
                .getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
                result.trim());


    }
}