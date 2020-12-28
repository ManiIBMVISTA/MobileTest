package practise.AppiumFramework;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class GeneralStoreTest extends Capability{	
		
		AndroidDriver<AndroidElement> driver;
		@BeforeTest 
		public void beforetest() throws IOException, InterruptedException {
			
			  Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		        Thread.sleep(2000);
		
		}
		
		@Test
		public void tc1() throws InterruptedException, IOException
		{
			service = startserver();
			driver = capability(appPackage,appActivity,deviceName,chromedriverExecutable);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Thread.sleep(10000);
			
			AndroidElement name= driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField"));
			name.sendKeys("Mani");
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();		      
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"))").click();
		    Thread.sleep(5000);
		    String actual =driver.findElement(MobileBy.id("android:id/text1")).getText();    
		    String expected="India";
		    Assert.assertEquals(actual, expected);
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		    Thread.sleep(5000);
		    service.stop();
			
		}
		
		@Test(enabled=false)
		public void tc2() throws InterruptedException
		{
			//AndroidElement name= driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField"));
			//name.sendKeys("Mani");
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();		      
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		    Thread.sleep(5000);
		    String actual =driver.findElement(MobileBy.id("android:id/text1")).getText();    
		    String expected="Australia";
		    Assert.assertEquals(actual, expected);
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		    Thread.sleep(5000);
		    String actualError = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		    String expectedError="please enter your name";
		    Assert.assertEquals(actualError, expectedError);
			
		}
		
		//3) I want to scroll and select the product and add to cart and checkout --> 
		//comapre the product which we selected is the same in the checkout page 
		
		@Test(enabled=false)
		public void tc3() throws InterruptedException
		{
			AndroidElement name= driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField"));
			name.sendKeys("Mani");
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();		      
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Albania\"))").click();
		    Thread.sleep(5000);
		    String actual =driver.findElement(MobileBy.id("android:id/text1")).getText();    
		    String expected="Albania";
		    Assert.assertEquals(actual, expected);
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		    Thread.sleep(5000);
			//System.out.println((driver.findElementsByClassName("android.widget.ImageView")).size());
		    
		    // to find the size of the list 
		    
		    driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(textMatches(\"Jordan 6 Rings\"))");
            int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
            System.out.println(count);
            // Know i want to click on the element which i wanted
            for(int i=0;i<count;i++)
            {
                String productName = driver.findElements(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).get(i).getText();
                if(productName.equals("Jordan 6 Rings"))
                {
                    System.out.println(productName);
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                    break;
                }
            }
            driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
            Thread.sleep(3000); 
            String checkoutname = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
            Thread.sleep(3000); 
            String expectedname = "Air Jordan 9 Retro";
            Assert.assertEquals(expectedname, checkoutname);
		}
		
		
		@Test (enabled=false)
		public void tc4() throws InterruptedException
		{
			AndroidElement name= driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField"));
			name.sendKeys("Mani");
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();		      
		    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Albania\"))").click();
		    Thread.sleep(5000);
		    String actual =driver.findElement(MobileBy.id("android:id/text1")).getText();    
		    String expected="Albania";
		    Assert.assertEquals(actual, expected);
		    driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); 
		    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		    Thread.sleep(3000); 
		    
	        String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
	        amount1 = amount1.substring(1);
	        double amount1value = Double.parseDouble(amount1);
	        
	        
	        String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
	        amount2 = amount2.substring(1);
	        double amount2value = Double.parseDouble(amount2);
	        
	        //Sum of the amount of first two product
	        double TotalAmount = amount1value + amount2value;
	        
	        //Final one
	        String finalamount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	        finalamount = finalamount.substring(1);
	        double FinalTotal = Double.parseDouble(finalamount);
	        
	    Assert.assertEquals(FinalTotal, TotalAmount);
	    
	    driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	  
	    AndroidElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
	        TouchAction t = new TouchAction(driver);
	        t.tap(tapOptions().withElement(element(checkbox))).perform();
	        AndroidElement terms =driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
	        t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(3))).release().perform();
	        System.out.println(driver.findElement(By.id("android:id/message")).getText());
	        driver.findElement(By.xpath("//*[@text='CLOSE']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	        Thread.sleep(20000);
	        
	        // to shift from native app to web app in hybrid application 
	        
	        Set<String> contextNames= driver.getContextHandles();
	        for (String contextName : contextNames )
	        {
	        	System.out.println(contextName);
	        }
	        driver.context("WEBVIEW_com.androidsample.generalstore");
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("IBM");
	        driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.RETURN);
	        Thread.sleep(5000);
	        driver.pressKey(new KeyEvent(AndroidKey.BACK));
	        Thread.sleep(5000);
	        driver.context("NATIVE_APP");
		}
		
		


}
