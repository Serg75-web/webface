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

//    @BeforeEach
//    public void setupAll1() {
//        driver = new ChromeDriver();
//    }


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
    public void validData() { // введение валидных данных

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79123456789");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement actualElement = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
        String actualText = actualElement.getText().trim();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
        assertTrue(actualElement.isDisplayed());

    }

    @Test
    public void invalidName() { // невалидные фамилия и имя

//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79123456789");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        assertEquals("Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")). getText().trim());

        assertTrue(driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).isDisplayed());

    }

    @Test
    public void invalidPhone() { // невалидный номера телефона

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79123456789");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        assertEquals("Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")). getText().trim());

        assertTrue(driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).isDisplayed());

    }


    @Test
    public void noClickCheckbox() { // ненажатие чекбокса

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012345678");
//        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй",
                driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")). getText().trim());

        assertTrue(driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).isDisplayed());

    }

}


