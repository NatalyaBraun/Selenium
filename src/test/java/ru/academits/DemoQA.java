package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

public class DemoQA {
    private WebDriver driver;

    @BeforeEach

    public void setup() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        String url = "https://demoqa.com/automation-practice-form";

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void DemoTest()  {

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Natalya");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Braun");

        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("fire@mail.ru");

        driver.findElement(By.xpath("//div[2]/label")).click();

        WebElement userNumber = driver.findElement(By.id("userNumber"));
        userNumber.sendKeys("8913913319");

        driver.findElement(By.xpath("//input[@id='dateOfBirthInput']")).click();

        WebElement year = driver.findElement(By.xpath("//*[@class='react-datepicker__year-select']"));
        String userYear = "1986";
        year.sendKeys(userYear);
        year.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@class='react-datepicker__day react-datepicker__day--021']")).click();

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        String userSubjects = "Arts";
        subjects.sendKeys(userSubjects);
        subjects.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30),Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("custom-control-label")));

        WebElement hobbiesCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        hobbiesCheckbox.click();

        File picture = new File("src/main/resources/img.jpg");
        WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
        uploadPicture.sendKeys(picture.getAbsolutePath());

        driver.findElement(By.id("currentAddress")).sendKeys("Sector 25 Huda, Panipat");

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        String userState = "NCR";
        state.sendKeys(userState);
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        String userCity = "Delhi";
        city.sendKeys(userCity);
        city.sendKeys(Keys.ENTER);

        driver.findElement(By.id("submit")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
