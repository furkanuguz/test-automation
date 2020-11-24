package ApsiyonCase;

import Function.SearchProductFunction;
import Listener.Listener;
import bsh.BshMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({Listener.class})
public class ProductTest extends SearchProductFunction {

    private BshMethod method;

    @Test
    public void productSearch() throws InterruptedException {

        searchProduct();

    }
}
