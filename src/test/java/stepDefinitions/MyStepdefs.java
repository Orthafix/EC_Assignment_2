package stepDefinitions;

import Common.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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
import java.util.List;


public class MyStepdefs {
    private WebDriver driver;
    private final Common helper = new Common();


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
    public void memberIsBornDriverUsingBrowser(String date) {
        helper.setBirthday(date);
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys(date);
        driver.findElement(By.id("dp")).sendKeys(Keys.ENTER);
        WebElement dateBorn = WaitForLocator(By.id("dp"));
        String actual = dateBorn.getAttribute("value");
        String expected = helper.getBirthday();
        Assert.assertEquals(actual, expected);
    }

    // A private explicit wait
    private WebElement WaitForLocator(By locator) {
        return (new WebDriverWait(driver,
                Duration.ofSeconds(10))).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @And("The member's name is {} and {}")
    public void theMemberSNameIsAnd(String firstname, String lastname)  {
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys(firstname);
        driver.findElement(By.id("member_lastname")).sendKeys(lastname);
        WebElement idLastName = driver.findElement
                (By.cssSelector("input[id='member_lastname']"));

        helper.setLastName(idLastName.getText());
    }

    @And("Email address has been added")
    public void emailAddressHasRandomlyBeenCreated() {
        String email = helper.getRandomEmail();
        driver.findElement(By.id("member_emailaddress")).click();
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);

        driver.findElement(By.id("member_confirmemailaddress")).click();
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
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

    @When("Member accepts terms and conditions")
    public void memberAcceptsTermsAndConditions() {
        WebElement element1 = driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box"));
        WebElement element2 = driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
        element1.click();
        element2.click();

        WebElement check1 = driver.findElement
                (By.cssSelector("input[id='sign_up_25']"));
        WebElement check2 = driver.findElement
                (By.cssSelector("input[id='sign_up_26']"));

        // Save for later verification after submit
        helper.setAcceptTermsAndConditions(check1.isSelected());

        Assert.assertTrue(check1.isSelected());
        Assert.assertTrue(check2.isSelected());

    }

    @And("Member rejects terms and conditions")
    public void memberRejectsTermsAndConditions() {
        WebElement element2 = driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
        element2.click();
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
        WebElement dateBorn = WaitForLocator(By.id("dp"));
        String actual = dateBorn.getAttribute("value");
        String expected = helper.getBirthday();
        Assert.assertEquals(expected, actual);
    }

    @And("email address will be equal to confirm email")
    public void emailAddressWillBeEqualToConfirmEmail() {
        WebElement expectedEmail = driver.findElement
                (By.cssSelector("input[id='member_emailaddress']"));

        WebElement expectedConfEmail = driver.findElement
                (By.cssSelector("input[id='member_confirmemailaddress']"));

        // Assert that email and confirmation email is equal
        Assert.assertEquals(expectedEmail.getAttribute("value"), expectedConfEmail.getAttribute("value"));

    }

    @And("password will be equal to confirm password")
    public void PasswordWillBeEqualWithConfirmPassword() {
        String passw = driver.findElement(By.id("signupunlicenced_password")).getAttribute("value");
        String cpassw = driver.findElement(By.id("signupunlicenced_confirmpassword")).getAttribute("value");

        // Assert that password and confirm password is equal
        Assert.assertEquals(passw, cpassw);
    }

    @And("password will be not equal to confirm password")
    public void PasswordWillBeNotEqualWith() {
        String errorMessage = "Password did not match";
        String passw = driver.findElement(By.id("signupunlicenced_password")).getAttribute("value");
        String cpassw = driver.findElement(By.id("signupunlicenced_confirmpassword")).getAttribute("value");

        boolean hasWarnings = helper.hasErrors(driver);

        // Assert error message from error list
        if (hasWarnings) {
            List<String> errors = helper.getErrorMessages();
            Assert.assertTrue(errors.contains(errorMessage));
        }
        // Assert that password and confirm password is NOT equal
        Assert.assertNotEquals(passw, cpassw);
    }

    @When("Confirm and join button is pressed")
    public void confirmAndJoinButtonIsPressed() {
        driver.findElement(By.cssSelector(
                "#signup_form > div.form-actions.noborder > input")).submit();

    }

    @Then("Last name is not empty")
    public void LastNameIsNotEmpty() {
        String lastName = helper.getLastName();
        Assert.assertNotNull(lastName);
    }

    @And("Last name is empty")
    public void LastNameIsEmpty() {
        String expectedErrorLast = "Last Name is required";

        WebElement elErrorLastName = (new WebDriverWait(driver,
                Duration.ofSeconds(10))).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//*[@id=\"signup_form\"]/div[5]/div[2]/div/span")));
        String actualErrorLast = elErrorLastName.getText();
        Assert.assertEquals(expectedErrorLast, actualErrorLast);
    }

    @And("Members terms and conditions is accepted")
    public void membersTermsAndConditionsIsAccepted() {
        Assert.assertTrue(helper.getAcceptTermsAndConditions());
    }

    @And("Members terms and conditions is rejected")
    public void membersTermsAndConditionsIsRejected() {

        boolean hasWarnings = helper.hasErrors(driver);
        if (hasWarnings) {
            String errorMessage = "You must confirm that you have read and accepted our Terms and Conditions";

            WebElement errorElement = (new WebDriverWait(driver,
                    Duration.ofSeconds(10))).until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/span/span")));
            String actual = errorElement.getText();
            Assert.assertEquals(errorMessage, actual);
        }
    }

    @And("a new member account will be created or rejected")
    public void aNewMemberWillBeAcceptedOrRejected() {

        boolean hasWarnings = helper.hasErrors(driver);

        //check if member status is accepted - if not, do nothing as previous negative tests is already made
        if (!hasWarnings) {
            String expectedMessage = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
            String actualMessage = driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText();
            String memberNumber = (new WebDriverWait(driver,
                    Duration.ofSeconds(10))).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(
                            "/html/body/div/div[2]/div/div/h2"))).getText();
            // check if valid member number
            boolean checkValidMemberNumber = helper.isValidMembershipNumber(memberNumber);

            //Assert that confirmation message and member number is displayed
            Assert.assertEquals(expectedMessage, actualMessage);
            Assert.assertTrue(checkValidMemberNumber);
        }

        driver.quit();
    }

}
