package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
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
    public void demoTest() {

        String firstName = "Natalya";
        driver.findElement(By.id("firstName")).sendKeys(firstName);

        String lastName = "Braun";
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        String userEmail = "fire@mail.ru";
        driver.findElement(By.id("userEmail")).sendKeys(userEmail);

        WebElement gender = driver.findElement(By.xpath("//div[2]/label"));
        gender.click();
        String userGender = gender.getText();

        String userNumber = "8913913319";
        driver.findElement(By.id("userNumber")).sendKeys(userNumber);

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("custom-control-label")));

        WebElement hobbiesCheckbox = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbiesCheckbox);
        String userHobbies = hobbiesCheckbox.getText();

        File picture = new File("src/main/resources/img.jpg");
        WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
        uploadPicture.sendKeys(picture.getAbsolutePath());

        String userAddress = "Sector 25 Huda, Panipat";
        driver.findElement(By.id("currentAddress")).sendKeys(userAddress);

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

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(driver.findElement(By.xpath("//tr[1]/td[2]")).getText()).contains(firstName, lastName);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[2]/td[2]")).getText()).isEqualTo(userEmail);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[3]/td[2]")).getText()).isEqualTo(userGender);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[4]/td[2]")).getText()).isEqualTo(userNumber);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[5]/td[2]")).getText()).contains(userYear);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[6]/td[2]")).getText()).isEqualTo(userSubjects);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[7]/td[2]")).getText()).isEqualTo(userHobbies);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[8]/td[2]")).isDisplayed());
        softAssert.assertThat(driver.findElement(By.xpath("//tr[9]/td[2]")).getText()).contains(userAddress);
        softAssert.assertThat(driver.findElement(By.xpath("//tr[10]/td[2]")).getText()).contains(userState, userCity);

        softAssert.assertAll();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}