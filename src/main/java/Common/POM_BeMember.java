package Common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class POM_BeMember {

    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String email;
    private String emailConf;
    private String password;
    private String passwordConf;
    private String[] roles;
    private boolean accountConfirmation;
    private boolean isPlus18Years;
    private boolean likeMarketingCommunication;
    private boolean likeCommunicationPartner;
    private boolean hasCocEthics;
    //private boolean isMemberAccepted;

    public POM_BeMember(String dateOfBirth, String firstName, String lastName, String email, String emailConf,
                        String password, String passwordConf, String[] roles, boolean accountConfirmation,
                        boolean isPlus18Years, boolean likeMarketingCommunication, boolean likeCommunicationPartner,
                        boolean hasCocEthics){
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailConf = emailConf;
        this.password = password;
        this.passwordConf = passwordConf;
        this.roles = roles;
        this.accountConfirmation = accountConfirmation;
        this.isPlus18Years = isPlus18Years;
        this.likeMarketingCommunication = likeMarketingCommunication;
        this.likeCommunicationPartner = likeCommunicationPartner;
        this.hasCocEthics = hasCocEthics;
        //this.isMemberAccepted = isMemberAccepted;
    }

    //TODO: Lägg till alla element för POM:en i egna metoder som kan anropas och användas från BEMStepdefs.java

    public String getFirstName()
    {
        //WebDriver driver_fox = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        WebElement firstName = driver.findElement(By.id("sign_up_6"));
        return firstName.toString();
    }

    private String getLastName()
    {
        //WebDriver driver_fox = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        WebElement lastName = driver.findElement(By.id("sign_up_8"));
        return lastName.toString();
    }

    //TODO: Skapa en generell click metod
    public static void click(WebDriver driver, By by)
    {
    }

}

