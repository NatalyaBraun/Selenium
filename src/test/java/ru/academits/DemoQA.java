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
    public void DemoTest() {

        driver.findElement(By.id("firstName")).sendKeys("Natalya");

        driver.findElement(By.id("lastName")).sendKeys("Braun");

        driver.findElement(By.id("userEmail")).sendKeys("fire@mail.ru");

        driver.findElement(By.xpath("//div[2]/label")).click();

        driver.findElement(By.id("userNumber")).sendKeys("8913913319");

        driver.findElement(By.id("currentAddress")).sendKeys("Sector 25 Huda, Panipat");

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

        driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();

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
