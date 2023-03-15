package AppTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import Steps.carVerificationSteps;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class CarValueTest {

    @Steps
    carVerificationSteps carVerificationSteps;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void carverificationTest(){
        carVerificationSteps.getCarRegFromFile();
        carVerificationSteps.isOnEnterCarRegPage();
        carVerificationSteps.entercarreg();
        carVerificationSteps.checkcar();

    }
}
