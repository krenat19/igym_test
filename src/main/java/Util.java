import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Util {

    WebDriver driver;

    private final By TEMPMAIL_RANDOM_BUTTON = By.xpath("//*[@value='Random']");
    private final By TEMP_EMAIL_ADDRESS = By.id("current-id");
    private final By TEMPMAIL_COOKIE_POLICY_BUTTON = By.xpath("//*[@class='cookie_policy_close']");
    private final By EMAIL_TITLE = By.xpath("//*[@id='mails']/div[1]");
    private final By EMAIL_LINK = By.xpath("//*[@id='tm-message']//a");
    private final By IGYM_ACCEPT_COOKIES = By.xpath("//app-accept-cookie//button");


    public Util(WebDriver driver) {
        this.driver = driver;
    }

    //this method generates a new disposable email address
    public String GenerateNewEmail() {
        driver.get(TestData.TEMP_MAIL_URL);
        driver.findElement(TEMPMAIL_COOKIE_POLICY_BUTTON).click();
        driver.findElement(TEMPMAIL_RANDOM_BUTTON).click();
        String email = driver.findElement(TEMP_EMAIL_ADDRESS).getAttribute("value");
        return email;
    }

    //confirming the registration
    public void ConfirmEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(EMAIL_TITLE))).click();
        driver.findElement(EMAIL_LINK).click();
    }

    public List<String> ReadFromFile(String filename)  {
        List<String> text = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                text.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return text;
    }

}
