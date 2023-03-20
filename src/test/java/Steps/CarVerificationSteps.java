package Steps;

import carVerification.enterCarRegPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class carVerificationSteps extends enterCarRegPage{
    private enterCarRegPage Entercarregpage;

    @Step
    public void getCarRegFromFile() throws IOException {
        Entercarregpage.extractRegistrationNumbers();
    }
    @Step
    public void getMessageFromCazoo()throws IOException {
        Entercarregpage.extractMessage();
    }
//    @Step
//    public void verifyMessage(){
//        Entercarregpage.verifyOutput();
//    }
}

