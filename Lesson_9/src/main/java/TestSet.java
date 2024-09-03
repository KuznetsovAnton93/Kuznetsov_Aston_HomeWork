import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.util.List;

public class TestSet {

    private final WebDriver driver;

    public TestSet(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов страницы
    private final By serviceTypeSelect = By.id("pay"); // Dropdown для выбора типа услуги
    private final By submitButton = By.cssSelector("form#pay-connection .button"); // Кнопка "Продолжить"
    private final By errorMessage = By.cssSelector(".input-wrapper p"); // Сообщения об ошибках

    // Метод для выбора типа услуги
    @Step("Выбираем тип услуги: {serviceType}")
    public void selectServiceType(String serviceType) {
        try {
            driver.findElement(serviceTypeSelect).sendKeys(serviceType);
        } catch (NoSuchElementException e) {
            captureScreenshot("Не удалось найти элемент для выбора типа услуги.");
            throw e;
        }
    }

    // Метод для клика по кнопке "Продолжить" с использованием JavaScript
    @Step("Кликаем по кнопке 'Продолжить' с использованием JavaScript")
    public void clickSubmitUsingJavaScript() {
        try {
            WebElement submitBtn = driver.findElement(submitButton);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", submitBtn);
        } catch (NoSuchElementException e) {
            captureScreenshot("Не удалось найти кнопку 'Продолжить'.");
            throw e;
        }
    }

    // Метод для получения списка всех сообщений об ошибках
    @Step("Получаем все сообщения об ошибках")
    public List<WebElement> getErrorMessages() {
        return driver.findElements(errorMessage);
    }

    // Метод для фиксации скриншота при ошибке
    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    public byte[] captureScreenshot(String description) {
        System.out.println(description);
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
