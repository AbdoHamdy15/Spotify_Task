import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class SpotifyAutomation {

        WebDriver driver;
        WebDriverWait wait;
        String email = "rosenfield162@gmail.com";
        String password = "Rosenfield16.2.2025";
        String name = "Rosenfield";

        @BeforeClass
        public void setUp() {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        @Test(priority = 1)
        public void signUp() throws InterruptedException {
            driver.get("https://www.spotify.com/eg-ar/signup");
            driver.findElement(By.id("username")).sendKeys(email);
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
            nextButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-password"))).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("displayName"))).sendKeys(name);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("day"))).sendKeys("12");

            WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
            Select selectMonth = new Select(monthDropdown);
            selectMonth.selectByValue("7");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year"))).sendKeys("1999");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'Indicator-sc-hjfusp-0')]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sign up']"))).click();

        }

        @Test(priority = 2)
        public void login() {
            driver.get("https://accounts.spotify.com/en/login");
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span[text()='Log Out']]"))).click();
           wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn-link"))).click();
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username"))).sendKeys(email);
           wait.until(ExpectedConditions.elementToBeClickable(By.id("login-password"))).sendKeys(password);
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log In']"))).click();
           wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='web-player-link']"))).click();
        }

        @Test(priority = 3)
        public void createPlaylist() {
            driver.get("https://open.spotify.com/");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create playlist']"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@role='searchbox']"))).sendKeys("Hall of Fame");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='add-to-playlist-button']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//svg[@data-encore-id='icon']"))).click();

        }


        @AfterClass
        public void tearDown() {
                driver.quit();
        }
    }

