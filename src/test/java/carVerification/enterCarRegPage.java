package carVerification;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DefaultUrl("https://www.cazoo.co.uk/value-my-car/")
public class enterCarRegPage extends PageObject {
    @FindBy(xpath = "//input[@id='vrm']")
    static
    WebElementFacade enterReg;
    @FindBy(xpath = "//p[contains(.,'Make/model: Jaguar XE 2.0d Portfolio Saloon 4dr Diesel Auto Euro 6 (s/s) (163 ps)')]")
    static
    WebElementFacade carInfo;
    public void carreg(){
        enterReg.sendKeys("OE65JZM");
        enterReg.sendKeys(Keys.TAB);
        enterReg.sendKeys(Keys.ENTER);
    }
    public String valueCar(){
        return carInfo.getText();

    }
    public void getCarRegFromFile(){
        String fileName = "C:\\Users\\umapr\\Downloads\\car_input_v2.txt";
        Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}\\s*[A-Z]{3}");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.replaceAll("\\s+",""));
                if (matcher.find()) {
                    String CarRegs = matcher.group().replaceAll("\\s+","");
                    System.out.println(CarRegs);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
