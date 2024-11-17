package Steps;
import net.thucydides.core.annotations.Step;
import java.io.IOException;

public class CarVerificationSteps extends CarVerification.EnterCarRegPage {
    private CarVerification.EnterCarRegPage Entercarregpage;

    @Step
    public void getCarRegFromFile() throws IOException {
        Entercarregpage.extractRegistrationNumbers();
    }

    @Step
    public void getMessageFromCazoo() throws IOException {

        Entercarregpage.extractMessage();
    }

    @Step
    public void compareValuationsWithExpectedResults() throws IOException {
        Map<Integer, String> mismatches = new HashMap<>();
        int lineNumber = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;


                if (line.trim().isEmpty()) {
                    continue;
                }


                String[] parts = line.split(delimiter);
                if (parts.length < 2) {
                    mismatches.put(lineNumber, "Invalid format (missing expected or actual result).");
                    continue;
                }

                String expected = parts[0].trim();
                String actual = parts[1].trim();


                if (!expected.equals(actual)) {
                    mismatches.put(lineNumber, "Expected: " + expected + ", Actual: " + actual);
                }
            }
        }
    }
    @Step
    public boolean areAllValuationsCorrect() throws IOException {
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue();

            if (!actualValues.containsKey(key)) {
                System.out.println("Key not found in actual values: " + key);
                return false;
            }

            String actualValue = actualValues.get(key);
            if (!expectedValue.equals(actualValue)) {
                System.out.println("Mismatch for key: " + key + ". Expected: " + expectedValue + ", Actual: " + actualValue);
                return false;
            }
        }
        return true;
    }
    }



