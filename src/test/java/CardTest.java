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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupAll1() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:9999/");
    }

    @Test
    void shouldTest3() {
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иван Иванов");
        elements.get(1).sendKeys("+79807765544");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("Success_successBlock_2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время", text.trim());
    }


//    @Test
//    public void shouldTest() {
//
//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79123456789");
//        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();
//        driver.findElement(By.tagName("button")).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='order-success']")));
//         String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
//
//         assertTrue(actualText.contains("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));


//    @Test
//    void shouldTest1() {
//        WebElement form = driver.findElement(By.cssSelector("[data-test-id=callback-form]"));
//        form.findElement(By.cssSelector("[data-test-id=name] input_inner")).sendKeys("Василий Иванов");
//        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79270000000");
//        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        form.findElement(By.cssSelector("[data-test-id=submit]")).click();
//        String text = driver.findElement(By.className("alert-success")).getText();
//        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
//    }



}


