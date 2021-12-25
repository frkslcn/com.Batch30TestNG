package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class C04_SoftAssert {
    //Yeni bir Class Olusturun : D11_SoftAssert1
    //1. “http:https://www.hepsiburada.com/” Adresine gidin
    //2. Basliginin "Turkiye'nin En Buyuk Alisveris Sitesi" oldugunu
    //dogrulayin
    //3. search kutusuna araba yazip arattirin
    //4. bulunan sonuc sayisini yazdirin
    //5. sonuc yazisinin "araba" icerdigini dogrulayin
    //6. Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin

    WebDriver driver;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }
    @Test
    public void test(){
        driver.get("https://www.hepsiburada.com");
        //2. Basliginin "Turkiye'nin En Buyuk Alisveris Sitesi" oldugunu
        //dogrulayin
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(driver.getTitle().contains("Turkiye'nin En Buyuk Alisveris Sitesi"),"title istenen cumleyi icermiyor");

        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys("araba"+ Keys.ENTER);

        WebElement sonucYazisiElementi = driver.findElement(By.xpath("//div[@class='searchResultSummaryBar-mainText']"));
        System.out.println(sonucYazisiElementi.getText());

        softAssert.assertTrue(sonucYazisiElementi.getText().contains("araba"),"sonuc yazisi araba iceriyor");
//6. Sonuc yazisinin “oto” kelimesi icermedigini dogrulayin
        softAssert.assertFalse(sonucYazisiElementi.getText().contains("oto"),"oto kelimesi iceriyor");
    }
    @AfterClass
    public void tearDown(){
       driver.close();
    }







}
