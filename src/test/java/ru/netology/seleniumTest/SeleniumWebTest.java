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
    public void testValidForm() {
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

    @Test
    public void testInvalidFormName() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Пустое поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Нажимаем кнопку отправить
        driver.findElement(By.cssSelector("button.button_view_extra"))
                .click();
        String result = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();
        assertEquals("Поле обязательно для заполнения",
                result.trim());
    }

    @Test
    public void testInvalidFormPhone() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Валидное ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Нажимаем кнопку отправить
        driver.findElement(By.cssSelector("button.button_view_extra"))
                .click();
        String result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        assertEquals("Поле обязательно для заполнения",
                result.trim());
    }

    @Test
    public void testInvalidFormNameEN() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Невалидное поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Ivanov Ivan");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Нажимаем кнопку отправить
        driver.findElement(By.cssSelector("button.button_view_extra"))
                .click();
        String result = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                result.trim());
    }

    @Test
    public void testInvalidFormPhoneStart8() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Валидное поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        // Невалидный номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("81234567890");
        // Нажимаем кнопку отправить
        driver.findElement(By.cssSelector("button.button_view_extra"))
                .click();
        String result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                result.trim());
    }

    @Test
    public void testValidFormNameSpecialSimbol() {
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
        // Вводим валидные данные в поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Фёдор");
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