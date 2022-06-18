import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class carsDemoProject {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omina\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.cars.com");
        String expectedNewUsed ="New & used cars";
        String expectedModel ="All models";
        String expectedDistance="20 miles";
        String expectedMake = "All makes";
        String expectedPrice = "No max price";
        Select selectNewUsed = new Select(driver.findElement(By.id("make-model-search-stocktype")));
       String actualNewUsed=selectNewUsed.getFirstSelectedOption().getText();
        Assert.assertEquals(actualNewUsed,expectedNewUsed);
        Select selectMake = new Select(driver.findElement(By.xpath("//select[@data-activitykey='make']")));
         String actualMake = selectMake.getFirstSelectedOption().getText();
        Assert.assertEquals(actualMake,expectedMake);
        Select selectPrice = new Select(driver.findElement(By.id("make-model-max-price")));
        String actualPrice = selectPrice.getFirstSelectedOption().getText();
        Assert.assertEquals(actualPrice,expectedPrice);
       Select selectDistance = new Select(driver.findElement(By.id("make-model-maximum-distance")));
       String actualDistance = selectDistance.getFirstSelectedOption().getText();
       Assert.assertEquals(actualDistance,expectedDistance);
       Select selectModel = new Select(driver.findElement(By.xpath("//select[@data-activitykey='model']")));
       String actualModel = selectModel.getFirstSelectedOption().getText();
       Assert.assertEquals(actualModel,expectedModel);
       String actualNewUsed2 = driver.findElement(By.id("make-model-search-stocktype")).getText();
       String expectedNewUsed2 = "New & used cars\nNew & certified cars\nNew cars\nUsed cars\nCertified cars";
       Assert.assertEquals(actualNewUsed2,expectedNewUsed2);
       selectNewUsed.selectByIndex(3);
       selectMake.selectByVisibleText("Tesla");
       String actualModels2 = driver.findElement(By.xpath("//select[@data-activitykey='model']")).getText();
       String expectedModels2 ="All models\nModel 3\nModel S\nModel X\nModel Y\nRoadster";
       Assert.assertEquals(actualModels2,expectedModels2);
       selectModel.selectByVisibleText("Model S");
       selectPrice.selectByVisibleText("$100,000");
       selectDistance.selectByVisibleText("50 miles");
       driver.findElement(By.xpath("//input[@id='make-model-zip']")).clear();
        driver.findElement(By.xpath("//input[@id='make-model-zip']")).sendKeys("22182");
        WebElement teslaCars = driver.findElement(By.xpath("//button[@data-searchtype='make']"));
       teslaCars.click();
       Select select = new Select(driver.findElement(By.xpath("//select[@id='pagination-dropdown']")));

       List<WebElement> totalCarList = driver.findElements(By.xpath("//h2[@class='title']"));
        Assert.assertEquals(totalCarList.size(),20);
        System.out.println(totalCarList.size());

      for (WebElement eachCar:totalCarList){
          System.out.println( eachCar.getText().contains("Tesla Model S"));
       }
      Select selectCarResult = new Select(driver.findElement(By.xpath("//select[@data-activitykey='sort-dropdown']")));

      selectCarResult.selectByIndex(1);
      Thread.sleep(1000);
      List<WebElement> lowestPriceCars = driver.findElements(By.xpath("//div[@class='price-section price-section-vehicle-card']"));
      for (WebElement lowestPriceCar: lowestPriceCars){
          System.out.println(lowestPriceCar.getText());
      }
     Assert.assertEquals(lowestPriceCars.get(0).getText(),"$33,990");

      selectCarResult.selectByIndex(4);
      Thread.sleep(1000);
      List<WebElement> hightMilageCars = driver.findElements(By.xpath("//div[@data-tracking-orientation='vertical']//div[@class='mileage']"));
      for (WebElement highMilageCar: hightMilageCars ){
          System.out.println(highMilageCar.getText());
      }
      Assert.assertEquals(hightMilageCars.get(0).getText(),"114,259 mi.");

      selectCarResult.selectByIndex(5);
        Thread.sleep(1000);
      List<WebElement> nearestLocationCars = driver.findElements(By.xpath("//div[@class='miles-from ']"));
      for (WebElement nearestLocationCar:nearestLocationCars ){
          System.out.println(nearestLocationCar.getText());
      }
      Assert.assertEquals(nearestLocationCars.get(0).getText(),"2 mi. from 22182");

      selectCarResult.selectByIndex(8);
        Thread.sleep(1000);
      List<WebElement> oldestYearCars = driver.findElements(By.xpath("//h2[@class='title']"));
      for (WebElement oldestYearCar:oldestYearCars){
          System.out.println(oldestYearCar.getText());
      }
      Assert.assertEquals(oldestYearCars.get(0).getText(),"2012 Tesla Model S");
      driver.quit();
    }
}
