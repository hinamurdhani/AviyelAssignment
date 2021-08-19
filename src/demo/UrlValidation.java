package demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class UrlValidation {
	
	public WebDriver driver;
  @Test
  public void f() {
	  
	 String url = "";
	 HttpURLConnection con = null;
     int responseCode = 200;
     System.setProperty("webdriver.chrome.driver","./src/driver/chromedriver.exe");
     driver = new ChromeDriver();
     driver.get("http://uitestingplayground.com/home");
     driver.manage().window().maximize();	
     List<WebElement> links = driver.findElements(By.tagName("a"));

     Iterator<WebElement> iter = links.iterator();
     
     while(iter.hasNext()){

    	 url = iter.next().getAttribute("href");

    	 System.out.println(url);

    	 if(url == null || url.isEmpty()){
    	 System.out.println("URL is either not configured for anchor tag or it is empty");
    	 continue;
    	 }

    	 

    	 try {
    		 con = (HttpURLConnection)(new URL(url).openConnection());

    		 con.setRequestMethod("HEAD");

    		 con.connect();

    		 responseCode = con.getResponseCode();

    		 if(responseCode >= 400){
    			 System.out.println(url+" is a broken link");
    		 }
    		 else{
    			 System.out.println(url+" is a valid link");
    		 }

    	 } catch (Exception e) {
    	 
    		 e.printStackTrace();
    	 }
     }
  }
  
  public void metatag() {
	  
	  String title = driver.getTitle();
	  System.out.println("=====title"+title);
	  String metatitle = driver.findElement(By.id("title")).getText();
	  System.out.println("=====metatitle"+metatitle);
	  
  }
  
  @BeforeClass
  public void beforeClass() {
	  
	  System.out.println("session started");
  }

  @AfterClass
  public void afterClass() {
	  
	  System.out.println("session ended");
  }

}
