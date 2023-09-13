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




import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class TestDesignPattern

{

    @Test
    public void test1() throws InterruptedException, IOException {
        // Create a WebDriver object for the Chrome browser.
        runData("100.86.0.0", "file_86");

    }
    @Test
    public void test2() throws InterruptedException, IOException {

        runData("100.94.0.0", "file_94");
    }
    @Test
    public void test3() throws InterruptedException, IOException {

        runData("100.106.0.0", "file_106");
    }
    @Test
    public void test4() throws InterruptedException, IOException {

        runData("100.112.0.0","file_112");
    }
    @Test
    public void test5() throws InterruptedException, IOException {

        runData("100.114.0.0", "file_114");
    }
    @Test
    public void test6() throws InterruptedException, IOException {

        runData("100.118.0.0", "file_118");
    }

    private void runData(String ipToSend, String fileName) throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();

        // Navigate to the URL of the Visual Subnet Calculator website.
        driver.get("https://www.davidc.net/sites/default/subnets/subnets.html");

        //Enter the network address and mask bits in the appropriate fields.
        driver.findElement(By.name("network")).sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        driver.findElement(By.name("network")).sendKeys(ipToSend);
        driver.findElement(By.name("netbits")).sendKeys(Keys.CONTROL + "A", Keys.DELETE);
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


        List<WebElement> ipAddressWebElementList = element.findElements(By.xpath("//tr/td[1]/a"));

        List<String> ipaddressList = new ArrayList<>();
        FileWriter writer = new FileWriter(fileName + ".txt");
        for(WebElement ipElement : ipAddressWebElementList){
            writer.write(ipElement.getText() + System.lineSeparator());
        }
        writer.close();

//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.writeValue(new File("simple_bean.xml"), new ArrayList<String>());
//        File file = new File("simple_bean.xml");
        // Close the browser.
    }


    @BeforeClass
    public void beforeClass()  {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

    }

//    public void afterMethod(ITestResult result) {
//        System.out.println("method name:" + result.getMethod().getMethodName());
//    }

}

