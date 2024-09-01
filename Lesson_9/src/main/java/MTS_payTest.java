import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MTS_payTest {

    private WebDriver driver;
    private TestSet testSet;

    @BeforeClass
    public void setup() {
        String browserVersion = "128.0.6613.113";
        WebDriverManager.chromedriver().browserVersion(browserVersion).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        testSet = new TestSet(driver);
    }

    @Test
    public void test_ordersForm() {
        String[] serviceTypes = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        boolean allChecksPassed = true;

        for (String serviceType : serviceTypes) {
            testSet.selectServiceType(serviceType);
            testSet.clickSubmitUsingJavaScript();

            List<WebElement> errors = testSet.getErrorMessages();
            if (errors.size() <= 0) {
                System.out.println("Ошибка: Ожидаются ошибки в незаполненных полях для типа услуги: " + serviceType);
                allChecksPassed = false;
            }
        }

        if (allChecksPassed) {
            System.out.println("Проверка надписей в незаполненных полях каждого варианта оплаты услуг прошла успешно");
        } else {
            Assert.fail("Не все проверки надписей в незаполненных полях прошли успешно.");
        }
    }

    @Test
    public void test_paymentForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

            // Переключаемся на фрейм
            driver.switchTo().frame(successContent);

            // Находим элемент с суммой
            WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pay-description__cost span")));
            String amountText = amountElement.getText();

            // Проверяем корректность суммы
            Assert.assertEquals(amountText, "100.00 BYN", "Сумма в фрейме не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Сумма отображается корректно.");

            // Находим элемент с оплатой и номером телефона
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pay-description__text span")));
            String titleText = titleElement.getText();

            // Проверяем корректность оплаты и номера телефона
            Assert.assertEquals(titleText, "Оплата: Услуги связи Номер:375297777777", " не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Оплата и номер телефона отображается корректно.");

            // Проверка метки для номера карты
            WebElement cardNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#cc-number + label")));
            Assert.assertEquals(cardNumberLabel.getText(), "Номер карты", "Метка для номера карты не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Метка для номера карты соответствует ожидаемому значению.");

            // Поле "Срок действия"
            WebElement expirationDateLabel = driver.findElement(By.xpath("//input[@formcontrolname='expirationDate']/following-sibling::label"));
            String expirationDateLabelText = expirationDateLabel.getText();
            Assert.assertEquals(expirationDateLabelText, "Срок действия", "Надпись для поля 'Срок действия' не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Метка для срока действия карты соответствует ожидаемому значению.");

            // Поле "CVC"
            WebElement cvcDateLabel = driver.findElement(By.xpath("//input[@formcontrolname='cvc']/following-sibling::label"));
            String cvcDateLabelText = cvcDateLabel.getText();
            Assert.assertEquals(cvcDateLabelText, "CVC", "Надпись для поля 'CVC' не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Метка для CVC карты соответствует ожидаемому значению.");

            // Поле "Имя держателя карты"
            WebElement holderDateLabel = driver.findElement(By.xpath("//input[@formcontrolname='holder']/following-sibling::label"));
            String holderDateLabelText = holderDateLabel.getText();
            Assert.assertEquals(holderDateLabelText, "Имя держателя (как на карте)", "Надпись для поля 'Имя держателя карты' не соответствует ожидаемому значению.");

            // Выводим сообщение об успешной проверке
            System.out.println("Метка для имени держателя карты соответствует ожидаемому значению.");

        } catch (Exception e) {
            Assert.fail("Не удалось проверить данные в фрейме: " + e.getMessage());
        }

        // Проверка наличия иконок платежных систем
        try {
            // Иконка Visa
            WebElement visaIcon = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']"));
            Assert.assertNotNull(visaIcon, "Иконка Visa не отображается.");

            // Иконка MasterCard
            WebElement mastercardIcon = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']"));
            Assert.assertNotNull(mastercardIcon, "Иконка MasterCard не отображается.");

            // Иконка Belkart
            WebElement belkartIcon = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']"));
            Assert.assertNotNull(belkartIcon, "Иконка Belkart не отображается.");

            // Иконка Maestro
            WebElement maestroIcon = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/maestro-system.svg']"));
            Assert.assertNotNull(maestroIcon, "Иконка Maestro не отображается.");

            // Иконка Mir
            WebElement mirIcon = driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/mir-system-ru.svg']"));
            Assert.assertNotNull(mirIcon, "Иконка Mir не отображается.");

            // Выводим сообщение об успешной проверке
            System.out.println("Иконки платежных систем отображаются корректно.");

        } catch (Exception e) {
            Assert.fail("Не удалось проверить иконки платежных систем: " + e.getMessage());
        } finally {
            // Возвращаемся к основному контенту
            driver.switchTo().defaultContent();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
