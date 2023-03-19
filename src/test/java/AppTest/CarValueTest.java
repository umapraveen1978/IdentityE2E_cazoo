package AppTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import Steps.carVerificationSteps;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

@RunWith(SerenityRunner.class)
public class CarValueTest {

    @Steps
    carVerificationSteps carVerificationSteps;

//    @Managed (driver = "edge")
//    WebDriver driver;

    @Test
    public void carverificationTest() throws IOException {
        carVerificationSteps.getCarRegFromFile();
        carVerificationSteps.getMessageFromCazoo();
        carVerificationSteps.getMessageFromCazoo();

    }
}
