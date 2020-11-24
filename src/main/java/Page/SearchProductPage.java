package Page;

import Type.DataDefaults;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class SearchProductPage extends LoginPage {

    /* Arama barinda urun arama islemi gerçekleştirildi. Paging işlemi gerceklestirildi. Urun listelerinde bastan 3. urun favori tanimlamasi yapildi. Islemler kontrol edilerek urun favorilerden cikartildi. */

    public void searchSamsung () {

        classname(DataDefaults.SEARCH_INPUT).sendKeys("samsung");
        classname(DataDefaults.SEARCH_BTN).click();
        String keyword = xpath("//h1[contains(text(),'samsung')]").getText();
        Assert.assertEquals("samsung", keyword);
        log.info("Aratılan ile sonuç eşdeğer");
        System.out.println("Aratılan ile sonuç eşdeğer");

    }

    public void pagination () {
        classname(DataDefaults.PAGE_2).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.hepsiburada.com/ara?q=samsung&sayfa=2");
        log.info("Açılmak istenen 2. Sayfanın açıldığı teyit edildi.");
        System.out.println("Açılmak istenen 2. Sayfanın açıldığı teyit edildi.");

    }

    public void heartWrapper () {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "document.querySelectorAll('.search-item .heartFrame #heartWrapper')[2].click()");
        log.info("Ürün beğeniler listesine eklenildi");
        System.out.println("Ürün beğeniler listesine eklenildi");

    }

    public void likeList () {
        id(DataDefaults.ACCOUNT).click();
        Sleep();
        xpath(DataDefaults.BTN_LIKE).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        js.executeScript("window.scrollBy(0,250)");
        String itemCheck = id("ProductBox-HBV00000VSED9-Title").getText();
        Assert.assertEquals("Samsung Galaxy Note 20 256 GB (Samsung Türkiye Garantili)", itemCheck);
        log.info("Favorilere eklenen ürün doğruluğu kontrol edildi.");
        System.out.println("Favorilere eklenen ürün doğruluğu kontrol edildi.");
        ScreenShot();
        Sleep();

    }

    public void likeListDelete (){
        id(DataDefaults.BTN_SELECT).click();
        id(DataDefaults.BTN_REMOVE).click();
        id(DataDefaults.BTN_DELETE).click();
        Sleep();
        String deleteInfo = xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/strong[1]").getText();
        Assert.assertEquals("0 ürün",deleteInfo);
        log.info("Favorilerden ürün başarı ile silindi");
        System.out.println("Favorilerden ürün başarı ile silindi");
        ScreenShot();

    }

    public void logOut () {
        classname(DataDefaults.SF_ACCOUNT).click();
        classname(DataDefaults.BTN_LOGOUT).click();
        String logoutcheck = classname("sf-OldMyAccount-PhY-T").getText();
        Assert.assertEquals("Giriş Yap",logoutcheck);
        log.info("Logout işlemi başarılı");
        System.out.println("Logout işlemi başarılı");

    }
}
