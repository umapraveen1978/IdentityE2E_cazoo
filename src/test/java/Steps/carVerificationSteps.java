package Steps;

import carVerification.enterCarRegPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class carVerificationSteps {
    private enterCarRegPage Entercarregpage;

    @Step
    public void getCarRegFromFile() {
        Entercarregpage.getCarRegFromFile();
    }
    @Step
    public void isOnEnterCarRegPage(){
        Entercarregpage.open();
    }
    @Step
    public void entercarreg(){
        Entercarregpage.carreg();
    }
    @Step
    public void checkcar(){
        Assert.assertTrue(Entercarregpage.valueCar().equals("Make/model: Jaguar XE 2.0d Portfolio Saloon 4dr Diesel Auto Euro 6 (s/s) (163 ps)"));
    }
}
