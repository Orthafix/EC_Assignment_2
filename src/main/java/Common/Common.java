package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Common {

    private String email;
    private String confirmEmail;
    private String firstName;
    private String lastName;
    private List<String> errorMessages;
    private String birthday;
    private boolean memberStatus = true;
    private final String[] HOST = {"@gmail", "@yahoo", "@hotmail", "@outlook", "@mail", "@aol", "@zoho", "@iCloud"};
    private final String[] DOMAIN = {".com", ".se", ".nu", ".de", ".io", ".org", ".net", ".co", ".app", ".uk", ".info"};
    private final String VOWELS = "aeiouyåäö";
    private final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private final int NAME_LENGTH = 4;

    public Common() {
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessages = new ArrayList<>();
        this.errorMessages.add(errorMessage);
    }


    public List<String> getErrorMessages()
    {
        return this.errorMessages;
    }

    public String getRandomEmail() {
        return generateRandomEmail();
    }

    public boolean getMemberStatus() {
        return memberStatus;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getConfirmEmail() {
        return this.confirmEmail;
    }

    public String generateRandomEmail() {

        Random random = new Random();
        // Use string builder for easier string appending handling
        StringBuilder email = new StringBuilder();

        try {
            // Loop from 0 to name length and append a random consonant and vocal in each iteration
            for (int i = 0; i < NAME_LENGTH; i++) {
                email.append(consonant(random.nextInt(CONSONANTS.length())));
                email.append(vowel(random.nextInt(VOWELS.length())));
            }
            //append
            email.append(HOST[random.nextInt(HOST.length)]);
            email.append(DOMAIN[random.nextInt(DOMAIN.length)]);
            //Error handling in case of out of bounds
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Array was too long or short (out of bounds). " +
                    "See this message " + e.getMessage());

        }
        return email.toString();
    }

    private char vowel(int randVocal) {

        return VOWELS.charAt(randVocal);
    }

    private char consonant(int randCons) {
        return CONSONANTS.charAt(randCons);
    }

    //Validates if the membership number has correct format
    public boolean isValidMembershipNumber(String membershipNumber) {
        String regex = "[A-Z]\\d{6}";
        return membershipNumber.matches(regex);
    }

    public boolean hasErrors(WebDriver driver, String errorMessage) {
        boolean hasErrors = false;

        List<WebElement> elements = driver.findElements(By.className("field-validation-error"));

        for(WebElement e:elements)
        {
            String errorTxt = e.getText();
            //if any field contains error messages hasErrors return true and adds error message to error list
            //If the error message from test case is not in the list then do not add it.
            if(!errorTxt.isEmpty() && (!getErrorMessages().equals(errorMessage)))
            {
                setErrorMessage(errorTxt);
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    public boolean hasErrors(WebDriver driver) {
        boolean hasErrors = false;

        List<WebElement> elements = driver.findElements(By.className("field-validation-error"));

        for(WebElement e:elements)
        {
            String errorTxt = e.getText();
            //if any field contains error messages hasErrors return true and adds error message to error list
            //If the error message from test case is not in the list then do not add it.
            if(!errorTxt.isEmpty())
            {
                setErrorMessage(errorTxt);
                hasErrors = true;
            }
        }
        return hasErrors;
    }

}

