package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OnlineStore {
    @ParameterizedTest(name = "{index} - SearchQuery")
    @ValueSource(strings = {"Laptop", "Smartphone", "Fiction"})
    void searchQuery(String name) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demowebshop.tricentis.com/");

        driver.manage().window().maximize();

        driver.findElement(By.id("small-searchterms")).sendKeys(name);

        driver.findElement(By.xpath("//input[@ value='Search']")).click();

        driver.findElement(By.xpath("//*[@ value='Add to cart'][1]")).click();

        WebElement productTitle = driver.findElement(By.xpath("//*[@class='product-title'][1]"));
        String nameInCard = productTitle.getText();

        driver.findElement(By.id("topcartlink")).click();

        WebElement cart = driver.findElement(By.xpath("//*[@class='product-name'][1]"));
        String nameInCart = cart.getText();
        Assertions.assertEquals(nameInCart, (nameInCard));

        driver.quit();
    }
}