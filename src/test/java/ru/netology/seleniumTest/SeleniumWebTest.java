package ru.netology.seleniumTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.awt.SystemColor.text;
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
        // тут адрес SUT, который крутится локально
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testValidForm() {
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
        // Пустое поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Ставил галочку в CheckBox Согласие
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"))
                .click();
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
        // Валидное ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Ставил галочку в CheckBox Согласие
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"))
                .click();
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
        // Невалидное поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Ivanov Ivan");
        // Пустой номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("");
        // Ставил галочку в CheckBox Согласие
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"))
                .click();
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
        // Валидное поле ФИО
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        // Невалидный номер телефона
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("81234567890");
        // Ставил галочку в CheckBox Согласие
        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"))
                .click();
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

    @Test
    public void testCheckboxNotChecked() {
        // Заполняем обязательные поля
        driver.findElement(By.cssSelector("[data-test-id='name'] input"))
                .sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input"))
                .sendKeys("+71234567890");

        // Чекбокс специально НЕ нажимаем
        // Нажимаем кнопку отправки
        driver.findElement(By.cssSelector("button.button_view_extra")).click();

        // Находим текст чекбокса
        WebElement invalidCheckbox = driver.findElement(
                By.cssSelector("label.input_invalid[data-test-id='agreement']"));
        // Ожидаемый цвет RGBA:
        assertEquals(invalidCheckbox.isDisplayed(), true);
    }


}