package org.example;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 

{

    //public static void main(String[] args) {
    //    System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver");
    //    WebDriver driver = new ChromeDriver();
    //
    //    driver.get("https://path2usa.com/travel-companion");
    //
    //}

    public static void main(String[] args) throws InterruptedException {
        // Create a WebDriver object for the Chrome browser.
        System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver");

        WebDriver driver = new ChromeDriver();

        // Navigate to the URL of the Visual Subnet Calculator website.
        driver.get("https://www.davidc.net/sites/default/subnets/subnets.html");

         //Enter the network address and mask bits in the appropriate fields.
        driver.findElement(By.name("network")).sendKeys(Keys.COMMAND + "A", Keys.DELETE);
        driver.findElement(By.name("network")).sendKeys("100.116.0.0");
        driver.findElement(By.name("netbits")).sendKeys(Keys.COMMAND + "A", Keys.DELETE);
        driver.findElement(By.name("netbits")).sendKeys("15");

        //// Click on the "Update" button.
        driver.findElement(By.cssSelector("input[value='Update'")).click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        //driver.switchTo().alert().accept();
        //Thread.sleep(1000);

        // Get the initial number of rows in the table.
        WebElement element = driver.findElement(By.id("calcbody"));
        //List<WebElement> divideButtons = element.findElements(By.xpath("//tr/td[5]/a"));
        //int numberOfRows = divideButtons.size();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean flag = true;
        do {
            // Click on the "Divide" anchor tag for each row that has a number of hosts greater than or equal to 30.
            //List<WebElement> divideButtons = element.findElements(By.xpath("//tr/td[5]/a"));
            int numberOfRows = 1;
            List<WebElement> divideButtons = element.findElements(By.xpath("//tr/td[5]/a"));

            for (int i = 0; i < numberOfRows; ) {
                if (Integer.parseInt(divideButtons.get(i).findElement(By.xpath("//tr["+(i+1)+"]/td[5]/a/preceding::td[1]")).getText()) > 30) {
                    divideButtons.get(i).click();
                    divideButtons = element.findElements(By.xpath("//tr/td[5]/a"));
                    numberOfRows = divideButtons.size();
                    js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
                }
                else {
                    i++;
                    js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
                }
            }
            flag = false;

            // Get the new number of rows in the table.
            numberOfRows = driver.findElements(By.id("hosts")).size();
        } while (flag);

        // Close the browser.


}
}
