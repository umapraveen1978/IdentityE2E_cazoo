package AppTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import Steps.CarVerificationSteps;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class CarValueTest {

    @Managed
    WebDriver driver;

    @Steps
    CarVerificationSteps carVerificationSteps;

    @Test
    public void verifyCarRegistrationsExtractedFromFile() throws IOException {
       carVerificationSteps.getCarRegFromFile();
    }

    @Test
    public void verifyCarValuationFetchedFromCazoo() throws IOException {
        carVerificationSteps.getCarRegFromFile();

        carVerificationSteps.getMessageFromCazoo();

        assertTrue("Valuations were not fetched for all car registrations.",
                carVerificationSteps.extractRegistrationNumbers().size() == carVerificationSteps.extractMessage().size());
    }

    @Test
    public void verifyCarValuationMatchesExpectedResults() throws IOException {
           carVerificationSteps.getCarRegFromFile();

           carVerificationSteps.getMessageFromCazoo();

           carVerificationSteps.compareValuationsWithExpectedResults();

          assertTrue("Some valuations did not match expected results.",
                carVerificationSteps.areAllValuationsCorrect());
    }
}
