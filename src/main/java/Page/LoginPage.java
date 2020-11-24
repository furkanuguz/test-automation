package Page;

import Abstract.MainFunction;
import Type.DataDefaults;
import org.testng.Assert;

public class LoginPage extends MainFunction {

    /* Hepsiburada E-ticaret portalina login işlemi sağlanildi ve hesabim menusunde user kontrolu yapıldı. */

    public void homePage() {
        Assert.assertEquals("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", driver.getTitle());
        System.out.println("Site başlığı kontrol edildi");
        log.info("Site Anasayfasına giriş yapıldı");
        ScreenShot();

    }

    public void signIn () {
        Sleep();
        id(DataDefaults.ACCOUNT).click();
        Sleep();
        id(DataDefaults.SIGN_IN).click();
        id(DataDefaults.TXT_USER_NAME).sendKeys(DataDefaults.USERNAME);
        id(DataDefaults.TXT_PASSWORD).sendKeys(DataDefaults.PASSWORD);
        id(DataDefaults.BTN_LOGIN).click();
        System.out.println("Login işlemi başarılı");
        log.info("Login işlemi başarılı");
        ScreenShot();

    }

     public void accountCheck (){
        id(DataDefaults.ACCOUNT).click();
        Sleep();
        xpath(DataDefaults.ACCOUNT_MENU).click();
        Sleep();
        String username = xpath("//strong[contains(text(),'FURKAN UĞUZ')]").getText();
        Assert.assertEquals("furkan uğuz",username);
        System.out.println("Hesabım girişi kontrol edildi");
        ScreenShot();

     }
}






