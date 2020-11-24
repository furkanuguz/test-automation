package Abstract;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class MainFunction extends Abstract {

    //Find Element ById
    public WebElement id(String Element) {
        WebDriverWait driverWait = new WebDriverWait(driver, waitDuration);
        WebElement elem = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(Element)));
        return elem;
    }
    //Find Element ByXpath
    public static WebElement xpath(String Element) {
        WebDriverWait driverWait = new WebDriverWait(driver, waitDuration);
        WebElement elem = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
        return elem;
    }
    //Find Element ByLinkText
    public WebElement linktext(String Element) {
        WebDriverWait driverWait = new WebDriverWait(driver, waitDuration);
        WebElement elem = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(Element)));
        return elem;
    }

    //Find Element ByCss
    public WebElement css(String Element) {
        WebDriverWait driverWait = new WebDriverWait(driver, waitDuration);
        WebElement elem = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Element)));
        return elem;
    }

    //Find Element ByClass
    public WebElement classname(String Element) {
        WebDriverWait driverWait = new WebDriverWait(driver, waitDuration);
        WebElement elem = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(Element)));
        return elem;
    }

    public static void scrollPageDown(WebElement element) {
        element.click();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void ScreenShot() {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        try {
            String folder = "apsiyon-case-study2020-11"+ dateFormatMonth.format(date) + "\\" + dateFormatDay.format(date) ;
            File file = new File(folder);
            if (!file.exists()) {
                try {
                    File dir = new File(folder);
                    dir.mkdir();
                } catch (Exception e) {
                    sysLog(e.getMessage());
                }
            }
            String className = getClass().getName().toString().replace(".", ",");
            String[] testName = className.split(",");
            String newFileName = folder.replace("\\", "/") + "/" + testName[testName.length - 1] + "-" + dateFormatFull.format(date).replace(":", ".").replace("/", "-") + ".png";
            FileUtils.copyFile(src, new File(newFileName));
            sysLog("Ekran resmi oluşturuldu, " + newFileName.replace("/", "\\"));
        } catch (Exception e) {
            sysLog(e.getMessage());
        }
    }

    DateFormat dateFormatFull = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // "yyyy/MM/dd HH:mm:ss" // 2019/04/14 13:08:43
    DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat dateFormatMonth = new SimpleDateFormat("yyyy-MM");

    public void sysLog(String log) {
        Date date = new Date();
        String className = getClass().getName().toString().replace(".", ",");
        String[] from = className.split(",");
        System.out.println(dateFormatFull.format(date) + " - " + from[from.length - 1] + " - " + log);
    }

    public static void assertText(String value,String text){
        Assert.assertEquals(xpath(value).getText(), text);
    }
    public static void assertCss(String value,String attribute,String text){
        Assert.assertEquals(xpath(value).getCssValue(attribute), text);
    }
    public static void assertAttribute(String value,String attribute,String text){
        Assert.assertEquals(xpath(value).getAttribute(attribute), text);
    }

    public static void deleteCookie() {
        Set<Cookie> ac = driver.manage().getCookies();
        System.out.println("Cookie Sayisi = " + ac.size());

        for (Cookie cookie : ac) {
            //   System.out.println("name   = " + cookie.getName())
            System.out.println("domain = " + cookie.getDomain());
            //   System.out.println("path   = " + cookie.getPath())
            //   System.out.println("value  = " + cookie.getValue())
            System.out.println("---------------");

        }
        driver.manage().deleteAllCookies();
        driver.manage().getCookies();
        Set<Cookie> afterDel = driver.manage().getCookies();
        System.out.println("Sildikten Sonra Cookie Sayisi = " + afterDel.size());

    }

    public void PopUpClose() {
        String parent = driver.getWindowHandle();
        System.out.println("Parent window id is " + parent);

        Set<String> allWindows = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<String>(allWindows);
        driver.switchTo().window(tabs.get(0));

        //driver.execute_script("console.log('hello script run');");

        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver)
                    .executeScript("$('.modal').not($(this)).each(function () { $(this).modal('hide'); });");
        }

    }
    public void Sleep(){
        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {
            log.info("Zaman Aşımı HATASI!!!");
            System.out.println("Zaman Aşımı HATASI!!!");
        };
    }
    public void TarayiciPopUp(){
        driver.switchTo().alert().accept();
    }
}
