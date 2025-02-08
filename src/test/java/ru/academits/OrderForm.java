package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class OrderForm {
    private WebDriver driver;

    @Test
    public void OrderFormTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://formdesigner.ru/examples/order.html");

        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.id("c-p-bn")).click();

        WebElement tittle = driver.findElement(By.xpath("//h3[contains(text(),'Пример готовой формы')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tittle);

        WebElement iframe = driver.findElement(By.cssSelector("#form_1006>iframe"));
        driver.switchTo().frame(iframe);

        driver.findElement(By.cssSelector("[name='submit']")).click();

        String fullName = driver.findElement(By.xpath("//*[@class='errorSummary errorSummary_top']/ul/li[1]")).getText();
        Assertions.assertTrue(fullName.contains("Необходимо заполнить поле ФИО:."));

        String eMail = driver.findElement(By.xpath("//*[@class='errorSummary errorSummary_top']/ul/li[2]")).getText();
        Assertions.assertTrue(eMail.contains("Необходимо заполнить поле E-mail."));

        String quantity = driver.findElement(By.xpath("//*[@class='errorSummary errorSummary_top']/ul/li[3]")).getText();
        Assertions.assertTrue(quantity.contains("Необходимо заполнить поле Количество."));

        String date = driver.findElement(By.xpath("//*[@class='errorSummary errorSummary_top']/ul/li[4]")).getText();
        Assertions.assertTrue(date.contains("Необходимо заполнить поле Дата доставки."));

        driver.quit();
    }
}


