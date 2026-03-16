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

public class DebitTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // Если нужно запускать без интерфейса
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    void shouldSubmitFormSuccessfully() {
        // Заполнение полей формы
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();

        // Отправка формы
        driver.findElement(By.cssSelector("[data-test-id=submit]")).click();

        // Проверка сообщения об успешной отправке
        String text = driver.findElement(By.className("alert-success")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

//    @Test
//    void shouldNotSubmitFormWithInvalidName() {
//        // Заполнение некорректным именем
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("12345");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//
//        // Отправка формы
//        driver.findElement(By.cssSelector("[data-test-id=submit]")).click();
//
//        // Проверка, что сообщение об успешной отправке не отображается
//        String errorMessage = driver.findElement(By.className("alert-error")).getText();
//        assertEquals("Имя и фамилия должны содержать только буквы.", errorMessage.trim());
//    }
//
//    @Test
//    void shouldNotSubmitFormWithInvalidPhone() {
//        // Заполнение некорректным номером телефона
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("1234567890"); // Неверный формат
//        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//
//        // Отправка формы
//        driver.findElement(By.cssSelector("[data-test-id=submit]")).click();
//
//        // Проверка, что сообщение об успешной отправке не отображается
//        String errorMessage = driver.findElement(By.className("alert-error")).getText();
//        assertEquals("Телефон введен неверно.", errorMessage.trim());
//    }
//
//    @Test
//    void shouldNotSubmitFormWithoutAgreement() {
//        // Заполнение корректными данными без согласия
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//
//        // Отправка формы без установки флажка согласия
//        driver.findElement(By.cssSelector("[data-test-id=submit]")).click();
//
//        // Проверка, что сообщение об успешной отправке не отображается
//        String errorMessage = driver.findElement(By.className("alert-error")).getText();
//        assertEquals("Необходимо согласие на обработку персональных данных.", errorMessage.trim());
//    }
}

