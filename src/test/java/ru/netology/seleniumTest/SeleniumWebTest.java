package ru.netology.seleniumTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumWebTest {

    ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--no-sandbox");
options.addArguments("--headless");
    driver = new ChromeDriver(options);
}

@BeforeAll
public static void setupAll() {
    WebDriverManager.chromedriver().setup();
}
