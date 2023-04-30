import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basis {
    static WebDriver driver;
    static String baseUrl = "https://demo.beseller.by/";
    JavascriptExecutor jsExec;

    WebDriverWait wait;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

}
