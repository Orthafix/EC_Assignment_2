package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BeMStepdefs {
    @Given("Member is born {int} {string} {int}")
    public void memberIsBorn(int arg0, String arg1, int arg2) {
    }

    @When("date is selected")
    public void dateIsSelected() {
    }

    @Then("date picker will display {string}")
    public void datePickerWillDisplay(String arg0) {
    }

    @Given("The member's name is {} and {}")
    public void theMemberSNameIsAnd(String arg0, String arg1) {
    }

    @And("Email adress is {}")
    public void emailAdressIs(String arg0) {
    }

    @And("Confirm Email is {}")
    public void confirmEmailIs(String arg0) {
    }

    @And("Password is {}")
    public void passwordIs(String arg0) {
    }

    @And("Retype password of password is {}")
    public void retypePasswordOfPasswordIs(String arg0) {
    }

    @And("Member has the role {}")
    public void memberHasTheRole(String arg0) {
    }

    @And("Member accept account Confirmation with <Terms&Conditions> and <{int}years>")
    public void memberAcceptAccountConfirmationWithTermsConditionsAnd(String arg0) {
    }

    @And("Member accepts {}")
    public void memberAccepts(String arg0) {
    }

    @When("Confirm and join button is pressed")
    public void confirmAndJoinButtonIsPressed() {
    }

    @Then("{} will be validated with <Confirm Email> adress and then be accepted or rejected")
    public void willBeValidatedWithConfirmEmailAdressAndThenBeAcceptedOrRejected(String arg0) {
    }

    @And("{} will be validated with {} and then be accepted")
    public void willBeValidatedWithAndThenBeAccepted(String arg0, String arg1) {
    }

    @And("a new member will be created")
    public void aNewMemberWillBeCreated() {
    }

    @And("Member accept account Confirmation with {} and <is_{int}_years>")
    public void memberAcceptAccountConfirmationWithAnd(String arg0, String arg1) {
    }
}
