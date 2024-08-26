import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class MTS_submitTest {

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

    @Test
    public void test_Form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Согласие с куки
        try {
            // Попытка найти и нажать кнопку согласия с куки
            WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn btn_black cookie__ok")));
            cookieAcceptButton.click();
        } catch (Exception e) {
            System.out.println("Баннер с куки не найден или уже закрыт.");
        }

        // 1. Найти и выбрать "Услуги связи" в выпадающем списке
        WebElement selectElement = driver.findElement(By.id("pay"));
        selectElement.sendKeys("Услуги связи");

        // 2. Найти форму для "Услуги связи"
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        WebElement emailInput = driver.findElement(By.id("connection-email"));
        WebElement submitButton = driver.findElement(By.cssSelector("form#pay-connection .button"));

        // 3. Заполнить поля
        phoneInput.sendKeys("297777777");
        sumInput.sendKeys("1000");
        emailInput.sendKeys("test@example.com");

        // 4. Нажать кнопку "Продолжить"
        submitButton.click();

        // 5. Ожидание загрузки страницы и завершения всех запросов
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Ждем, пока страница загрузится и состояние документа будет 'complete'
        wait.until(webDriver -> jsExecutor.executeScript("return document.readyState").equals("complete"));

        // Ждем появления элемента
        try {
            WebElement successContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bepaid-iframe")));
            Assert.assertNotNull(successContent, "Элемент с классом 'bepaid-iframe' не отображается после отправки формы.");
        } catch (Exception e) {
            Assert.fail("Элемент с классом 'bepaid-iframe' не найден в течение 20 секунд.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
