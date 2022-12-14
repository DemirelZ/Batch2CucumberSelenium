package com.krafttech.step_definitions;

import com.krafttech.utilities.BrowserUtils;
import com.krafttech.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("\tThis is coming from Before Method");
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @After
    public void tearDown(Scenario scenario){

        System.out.println("\tThis is coming from After Method");

        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        BrowserUtils.waitFor(3);
        Driver.closeDriver();

    }

    @Before ("@db")
    public void setUpDb(){
        System.out.println("\tConnecting DB");


    }
    @After ("@db")
    public void tearDownDb(){
        System.out.println("\tConnecting DB");
    }


}
