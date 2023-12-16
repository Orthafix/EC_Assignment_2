package stepDefinitions;

import Common.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MyStepdefs {
    private WebDriver driver;
    private final Common helper = new Common();
    @Before
    public void Setup() {
        //TODO: Se om du kan ordna så den öppnar browsern härifrån
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

    @Given("Member is born {}")
    public void memberIsBornDriverUsingBrowser(String date) throws InterruptedException {
        helper.setBirthday(date);
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys(date);
        driver.findElement(By.id("dp")).sendKeys(Keys.ENTER);
        WebElement dateBorn = (new WebDriverWait(driver,
                Duration.ofSeconds(10))).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dp")));
        String actual = dateBorn.getAttribute("value");
        String expected = helper.getBirthday();
        Assert.assertEquals(actual, expected);
        Thread.sleep(1000);
    }

    @And("The member's name is {} and {}")
    public void theMemberSNameIsAnd(String firstname, String lastname) throws InterruptedException {
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys(firstname);
        driver.findElement(By.id("member_lastname")).sendKeys(lastname);
        helper.setFirstName(firstname);
        helper.setLastName(lastname);
        Thread.sleep(1000);
    }

    @And("Email address has been added")
    public void emailAddressHasRandomlyBeenCreated() throws InterruptedException {
        String email = helper.getRandomEmail();
        driver.findElement(By.id("member_emailaddress")).click();
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        helper.setEmail(email);
        helper.setConfirmEmail(email);

        driver.findElement(By.id("member_confirmemailaddress")).click();
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        Thread.sleep(1000);

    }


    @And("Password is {} and retype password is {}")
    public void passwordIs(String password, String retypePassword) {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(retypePassword);
    }

    @And("Member has the role Basket Ball media")
    public void memberHasTheRoleBasketBallMedia() {
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(3) .box")).click();

    }

    @And("Member accepts terms and conditions")
    public void memberAcceptsTermsAndConditions() {

        WebElement element1 = driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box"));
        WebElement element2 =  driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
        element1.click();
        element2.click();
    }

    @And("Member rejects terms and conditions")
    public void memberRejectsTermsAndConditions() {
        WebElement element2 =  driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
        element2.click();

        String value1 = driver.findElement(By.cssSelector("#sign_up_25")).getAttribute("value");
        String value2 = driver.findElement(By.cssSelector("#sign_up_26")).getAttribute("value");
        Assert.assertEquals(value1,"false");
        Assert.assertEquals(value2,"true");
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

    @Then("date picker will display birthday")
    public void datePickerWillDisplay() {
        WebElement dateBorn = (new WebDriverWait(driver,
                Duration.ofSeconds(10))).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dp")));
        String actual = dateBorn.getAttribute("value");
        String expected = helper.getBirthday();
        Assert.assertEquals(expected, actual);
    }

    @And("first and last name is not empty")
    public void firstAndLastNameMustNotEmpty() {
        String firstName = helper.getFirstName();
        String lastName = helper.getLastName();

        Assert.assertNotNull(firstName);
        Assert.assertNotNull(lastName);
    }

    @And("first or last name is empty")
    public void firstOrLastNameIsEmpty() {
        String firstName = helper.getFirstName();
        String lastName = helper.getLastName();
        String expectedErrorFirst = "First Name is required";
        String expectedErrorLast = "Last Name is required";
        WebElement elErrorFirstName = driver.findElement(By.cssSelector(
                "#signup_form > div:nth-child(6) > div:nth-child(1) > div > span"));
        String actualErrorFirst = elErrorFirstName.getAttribute("value");
        WebElement elErrorLastName = driver.findElement(By.cssSelector(
                "#signup_form > div:nth-child(6) > div:nth-child(2) > div > span"));
        String actualErrorLastName = elErrorLastName.getAttribute("value");

        if (firstName.isEmpty())
            Assert.assertEquals(expectedErrorFirst, actualErrorFirst);
        else if (lastName.isEmpty())
            Assert.assertEquals(expectedErrorLast, actualErrorLastName);
    }

    @And("email address will be equal to confirm email")
    public void emailAddressWillBeEqualToConfirmEmail() {
        String expectedEmail = helper.getEmail();
        String expectedConfEmail = helper.getConfirmEmail();
        Assert.assertEquals(expectedEmail, expectedConfEmail);

    }

    @And("email address will be not equal to confirm email")
    public void emailAddressWillBeNotEqualToConfirmEmail() {
        String expectedEmail = helper.getEmail();
        String expectedConfEmail = helper.getConfirmEmail();
        Assert.assertNotEquals(expectedEmail, expectedConfEmail);
    }



    @And("password will be equal to confirm password")
    public void PasswordWillBeEqualWithConfirmPassword() {
        String passw = driver.findElement(By.id("signupunlicenced_password")).getAttribute("value");
        String cpassw = driver.findElement(By.id("signupunlicenced_confirmpassword")).getAttribute("value");

        Assert.assertEquals(passw, cpassw);
    }

    @And("password will be not equal to confirm password")
    public void PasswordWillBeNotEqualWith() {
        String passw = driver.findElement(By.id("signupunlicenced_password")).getAttribute("value");
        String cpassw = driver.findElement(By.id("signupunlicenced_confirmpassword")).getAttribute("value");

        Assert.assertNotEquals(passw, cpassw);
    }

    @And("Members terms and conditions is validated")
    public void membersTermsAndConditionsIsValidated() {
        String value1 = driver.findElement(By.cssSelector("#sign_up_25")).getAttribute("value");
        String value2 = driver.findElement(By.cssSelector("#sign_up_26")).getAttribute("value");
        Assert.assertEquals(value1,"true");
        Assert.assertEquals(value2,"true");
    }

    @When("Confirm and join button is pressed")
    public void confirmAndJoinButtonIsPressed() {
        driver.findElement(By.cssSelector(
                "#signup_form > div.form-actions.noborder > input")).submit();
    }

    @And("a new member account will be created or rejected")
    public void aNewMemberWillBeAccepted() {
        String expectedMessage = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actualMessage = driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText();
        String memberNumber =  (new WebDriverWait(driver,
                Duration.ofSeconds(10))).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "/html/body/div/div[2]/div/div/h2"))).getText();
        // check if valid member number
        boolean checkValidMemberNumber = helper.isValidMembershipNumber(memberNumber);

        Assert.assertEquals(expectedMessage, actualMessage);
        Assert.assertTrue(checkValidMemberNumber);
    }

    @After
    public void tearDown() {
        driver.quit();

    }

}
