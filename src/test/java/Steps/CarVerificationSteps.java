package Steps;

import net.thucydides.core.annotations.Step;

import java.io.*;

public class CarVerificationSteps extends CarVerification.EnterCarRegPage {
    private CarVerification.EnterCarRegPage Entercarregpage;

    @Step
    public void getCarRegFromFile() throws IOException {
        Entercarregpage.extractRegistrationNumbers();
    }
    @Step
    public void getMessageFromCazoo()throws IOException {

        Entercarregpage.extractMessage(Entercarregpage.extractRegistrationNumbers());
    }
//    @Step
//    public void verifyMessage(){
//        Entercarregpage.verifyOutput();
//    }
}

