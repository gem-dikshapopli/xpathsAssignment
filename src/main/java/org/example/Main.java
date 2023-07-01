package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.amazon.in");
        try {
            //---------------For the implicit wait-------------------------//
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            //------------To maximize The screen---------------------------//
            driver.manage().window().maximize();
            //-----------To Find the search bar and send keys to type Books in the search bar---------------------------//
            driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Books");
            Thread.sleep(1000);
            //---------------Click the search icon-------------------------//
            driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
            Thread.sleep(2000);

            //---------------------To verify if the results we get matched or not----------------//
            String searchedItem=driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText();
            String matchedString=searchedItem.substring(1,searchedItem.length()-1);
            if(matchedString.equals("Books")){
                System.out.println("Matched");
            }
            else{
                System.out.println("Not Matched");
            }

            Thread.sleep(2000);
            //------------------To scroll down to the required result-----------------//
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")));

            Thread.sleep(2000);

            //----------------------To get the name of the first appeared book-------------------//
            String firstBook=driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
            System.out.println("Required First Book : "+firstBook);
            Thread.sleep(2000);
            //------------To click on svg element---------------------------//
            WebElement shopNow= driver.findElement(By.xpath("(//span[@class='a-truncate-full']//descendant::*[local-name()='svg' ]/*[local-name()='path'])[1]"));

            Actions action = new Actions(driver);
            action.moveToElement(shopNow).click().build().perform();
            Thread.sleep(2000);

            action.moveToElement(driver.findElement(By.xpath("//span[@class='icp-nav-link-inner']"))).build().perform();
            driver.findElement(By.xpath("//*[text()='हिन्दी'][1]")).click();

            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.close();
    }
}