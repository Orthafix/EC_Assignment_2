package Common;

import java.util.Random;

public class Common {

    private String email;
    private String confirmEmail;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String birthday;
    private boolean newMember;
    private final String[] HOST = {"@gmail", "@yahoo", "@hotmail", "@outlook", "@mail", "@aol", "@zoho", "@iCloud"};
    private final String[] DOMAIN = {".com", ".se", ".nu", ".de", ".io", ".org", ".net", ".co", ".app", ".uk", ".info"};
    private final String VOWELS = "aeiouyåäö";
    private final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private final int NAME_LENGTH = 4;

    public Common(){}

    public String getRandomEmail()
    {
        return generateRandomEmail();
    }

    public boolean getNewMember() {
        return newMember;
    }

    public void setNewMember(boolean newMember) {
        this.newMember = newMember;
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

}

