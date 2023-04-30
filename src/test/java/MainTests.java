import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTests extends Basis {

    @BeforeEach
    public void openBeforeEach() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
    }

    @Test
    public void CartTesting() {
        jsExec = (JavascriptExecutor) driver;

        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("#productForm > div:nth-child(1) > div > div.ok-product__visual.shine > div > a")).click();
        String productName = driver.findElement(By.cssSelector(".page-title.product-name")).getText();
        jsExec.executeScript("window.scrollBy(0,400)");
        driver.findElement(By.cssSelector("[data-gtm-id='add-to-cart-product']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("cart-alert"))));
        driver.findElement(By.cssSelector(".oformit-v-korzine")).click();

        assertThat(driver.getCurrentUrl()).isEqualTo(baseUrl + "shcart/");

        WebElement order_title = driver.findElement(By.cssSelector(".ok-order__title"));
        assertTrue(order_title.isDisplayed());
        assertTrue(order_title.getText().contentEquals("ОФОРМЛЕНИЕ ЗАКАЗА"));

        WebElement sewingMachine = driver.findElement(By.cssSelector("[id='2556'][data-product-id='2556']"));
        String productNameAlt = sewingMachine.findElement(By.cssSelector(".ok-order__image [alt]")).getText();
        assertThat(sewingMachine.findElement(By.cssSelector(".ok-order__image [alt]")).getText()).contains(productNameAlt);
        assertThat(sewingMachine.findElement(By.cssSelector(".ok-order__productName")).getText()).contains(productNameAlt);
        assertThat(sewingMachine.findElement(By.className("ok-order__text")).getText()).contains("Код: 20102");
        assertThat(sewingMachine.findElement(By.cssSelector("[data-gtm-id='delete-from-cart'] .material-icons")).getText()).contains("delete");
        String price = sewingMachine.findElement(By.cssSelector("[data-product-item-input-price]")).getAttribute("value");
        assertEquals(sewingMachine.findElement(By.cssSelector("[data-product-item-input-price]")).getAttribute("value"), price);
        assertEquals(sewingMachine.findElement(By.cssSelector("[data-product-item-input-quantity]")).getAttribute("value"), "1");
        assertEquals(sewingMachine.findElement(By.cssSelector(".ok-table-el.f-tac.-size-half.hidden-xs")).getText(), "шт.");
        assertEquals(sewingMachine.findElement(By.cssSelector("[data-product-item-input-sum]")).getAttribute("value"), "49500");

        driver.close();

    }
}
