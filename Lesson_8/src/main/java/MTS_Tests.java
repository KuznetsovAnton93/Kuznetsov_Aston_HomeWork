import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MTS_Tests {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        // Настройка WebDriverManager для ChromeDriver с версией браузера
        String browserVersion = "128.0.6613.85";
        WebDriverManager.chromedriver().browserVersion(browserVersion).setup();

        // Настройка опций ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Инициализация ChromeDriver с указанными опциями
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockTitle() {
        // Поиск элемента заголовка блока по XPath
        WebElement header = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2"));

        // Ожидаемый текст заголовка
        String headerExpected = "Онлайн пополнение без комиссии";

        // Проверка соответствия текста заголовка
        Assert.assertEquals(header.getText().replace("\n", " "), headerExpected, "Название блока не совпадает");
    }

    @Test
    public void testPaymentSystemLogos() {
        // Проверка наличия логотипов платежных систем
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));
        Assert.assertTrue(logos.size() > 0, "Логотипы платежных систем не найдены");
    }

    @Test
    public void testReadMoreLink() throws InterruptedException {

        // Найти элемент ссылки "Подробнее о сервисе"
        WebElement readMoreLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));

        // Прокрутить страницу до элемента
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", readMoreLink);

        // Проверить, что ссылка отображается на странице
        Assert.assertTrue(readMoreLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается на странице.");

        // Сохранить текущий URL
        String currentUrl = driver.getCurrentUrl();

        // Кликнуть на ссылку
        readMoreLink.click();

        // Используем Thread.sleep для ожидания перехода на новую страницу
        Thread.sleep(5000);  // Ожидание 5 секунд

        // Проверить, что после клика произошел переход на правильный URL
        String expectedUrl = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl), "После клика на ссылку 'Подробнее о сервисе' произошел переход на неверный URL. Ожидаемый URL должен содержать: " + expectedUrl);
    }
}

