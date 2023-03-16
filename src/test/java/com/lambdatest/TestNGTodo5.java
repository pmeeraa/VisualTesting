package com.lambdatest;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
public class TestNGTodo5 {
    public RemoteWebDriver driver;
    public String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "Your LT username";
        String authkey = "Your LT password";
        String hub = "@hub.lambdatest.com/wd/hub";

        Hashtable<String, Integer> errorColor= new Hashtable<>();
        errorColor.put("red",500);
        errorColor.put("green",0);
        errorColor.put("blue",0);

        HashMap<String,Object> output= new HashMap<String, Object>();
        output.put("errorColor",errorColor);//Output Difference error color
        output.put("transparency",0.1);// Set transparency of Output
        output.put("largeImageThreshold",1200);// the granularity to which the comparison happens(the scale or level of detail in a set of data.)Range-100-1200

        HashMap<String, Object> sm=new HashMap<String, Object>();
        sm.put("output",output);
        sm.put("scaleToSameSize",true);//scale to same size, when baseline image and comparision image is of different size, use true

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Avengers");
        capabilities.setCapability("name", "Visual Ui Testing");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("version","latest");
        capabilities.setCapability("visual",true);
        capabilities.setCapability("smartUI.project","Mindtree");
        //capabilities.setCapability("smartUI.build","10689a4");
        capabilities.setCapability("smartUI.options",sm);
        //capabilities.setCapability("smartUI.baseline",true);


        String[] Tags = new String[] { "Feature", "Tag", "Moderate" };
        capabilities.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), capabilities);


    }

    @Test
    public void basicTest() throws InterruptedException {
        driver.get("https://www.google.com");

        //Thread.sleep(10000);

        driver.executeScript("smartui.takeScreenshot");


        //  driver.executeScript("lambda-status=passed");

        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
