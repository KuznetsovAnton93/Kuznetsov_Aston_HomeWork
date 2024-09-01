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
    public void selectServiceType(String serviceType) {
        driver.findElement(serviceTypeSelect).sendKeys(serviceType);
    }

    // Метод для клика по кнопке "Продолжить" с использованием JavaScript
    public void clickSubmitUsingJavaScript() {
        WebElement submitBtn = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitBtn);
    }

    // Метод для получения списка всех сообщений об ошибках
    public List<WebElement> getErrorMessages() {
        return driver.findElements(errorMessage);
    }
}
