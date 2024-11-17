package CarVerification;

import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.FindBy;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DefaultUrl("https://www.cazoo.co.uk/value-my-car/")
public class EnterCarRegPage extends PageObject {
    static CarVerification.EnterCarRegPage EnterCarRegPage;

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        regNumbers = new ArrayList<String>();
    }


    public static List<String> regNumbers;
    public String inputFilePath = "C:\\Users\\umapr\\IdeaProjects\\IdentityE2E_cazoo\\src\\test\\java\\AppTest\\car_input.txt";
    public String outputFilePath = "C:\\Users\\umapr\\IdeaProjects\\IdentityE2E_cazoo\\src\\test\\java\\AppTest\\car_output.txt";
    @FindBy(xpath = "//input[@id='vrm']")
    static
    WebElementFacade enterReg;
    @FindBy(xpath = "//p[contains(.,'Make/model:')]")
    static
    WebElementFacade makenmodel;


    public Object extractRegistrationNumbers() throws IOException {

        String extractCarReg = new String(Files.readAllBytes(Paths.get("C:\\Users\\umapr\\OneDrive\\Documents\\IdentityE2E\\car_output.txt"))).replaceAll("\\s+", "");
        List<String> regNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Za-z]{2}[0-9]{2}[A-Za-z]{3})");
        Matcher matcher = pattern.matcher(extractCarReg);

        while (matcher.find()) {
            regNumbers.add(matcher.group());
        }
        System.out.println(regNumbers);
        return null;
    }
    public Object extractMessage()  {
//        regNumbers = new ArrayList<String>();
        try {
            for (String regNumber : CarVerification.EnterCarRegPage.regNumbers) {
                driver.navigate().to("https://www.cazoo.co.uk/value-my-car/");
                enterReg.sendKeys(regNumber);
                enterReg.sendKeys(Keys.TAB);
                enterReg.sendKeys(Keys.ENTER);
                String makeModel = makenmodel.getText().split(":")[1].trim();
                String output = String.format("%s", makeModel);
                verifyOutput(output, regNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void verifyOutput(String output, String regNumber) throws FileNotFoundException
{
        try {
            String expectedOutput;
            String line;
            BufferedReader br = new BufferedReader(new FileReader(outputFilePath);

            while (true) {
                try {
                    while (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line.startsWith(regNumber)) {
                    expectedOutput = line.substring(regNumber.length() + 1);
                    break;
                }
            }
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertNotNull(expectedOutput, "No expected output found for registration number: " + regNumber);
            Assert.assertEquals(output, expectedOutput, "Output does not match expected output for registration number: " + regNumber);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

