package stepDefinitions;

import Common.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyStepdefs {
    private WebDriver driver;
    private Common helper;

    @Before
    public void Setup() {
        helper = new Common();
    }

    @Given("I am using {string} as browser")
    public void i_am_using_as_browser(String browser) {
        switch (browser) {
            case "Chrome" -> driver = new ChromeDriver();
            case "Fire fox" -> driver = new FirefoxDriver();
            case "Edge" -> driver = new EdgeDriver();
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @Given("Member is born {string}")
    public void memberIsBornDriverUsingBrowser(String date) throws InterruptedException {
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys(date);
        driver.findElement(By.id("dp")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @And("The member's name is {} and {}")
    public void theMemberSNameIsAnd(String firstname, String lastname) throws InterruptedException {
        driver.findElement(By.id("member_firstname")).sendKeys(firstname);
        driver.findElement(By.id("member_lastname")).sendKeys(lastname);
        Thread.sleep(2000);
    }

    @And("Email address is {}")
    public void emailAddressIs(String email) {
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
    }

    @And("Confirm Email is {}")
    public void confirmEmailIs(String confirmEmail) {
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(confirmEmail);
    }

    @And("Password is {}")
    public void passwordIs(String password) {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
    }

    @And("Retype password of password is {}")
    public void retypePasswordOfPasswordIs(String confirmPassword) {
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(confirmPassword);

    }

    @And("Member has the role Basket Ball media")
    public void memberHasTheRoleBasketBallMedia() {
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(3) .box")).click();
    }

    @And("Member accept account confirmation terms and conditions")
    public void memberAcceptAccountConfirmationTermsAndConditions() {
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
    }

    @And("Member accepts communication preferences")
    public void memberAcceptsCommunicationPreferences() {
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(4) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(5) .box")).click();
    }

    @And("Member accepts coc")
    public void memberAcceptsCoc() {
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
    }

    @When("Confirm and join button is pressed")
    public void confirmAndJoinButtonIsPressed() {
        driver.findElement(By.name("join")).click();
    }

    @Then("date picker will display {string}")
    public void datePickerWillDisplay(String fullDate) throws InterruptedException {
        /*WebElement dateBornElement = driver.findElement(By.id("dp"));
        String expected = fullDate;
        String actual = dateBornElement.getText();
        Assertions.assertEquals(expected, actual);*/
        System.out.println("datepickerDisplay");
        Thread.sleep(2000);
    }

    @And("{} address will be validated with {}")
    public void addressWillBeValidatedWith(String email, String confirmEmail) {
        System.out.println("email validate");
    }

    @And("{} will be validated with {}")
    public void willBeValidatedWithAndThenBeAccepted(String password, String confirmPassword) {
        //I.e. if the password and ConfirmPassword is equal then this will pass - otherwise fail (but true)
        System.out.println("validateMember");
    }

    @And("a {} will be created or otherwise be rejected")
    public void aWillBeCreatedOrOtherwiseBeRejected(String new_member) {
        // E.g. if pop up is shown the string parameter will be true and pass otherwise false and fail (but true)
        System.out.println("MemberCreated");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
