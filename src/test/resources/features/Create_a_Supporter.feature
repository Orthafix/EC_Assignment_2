Feature: Create a supporter account
  As a basketball supporter
  I would like to create a supporter account
  To be able to have a beneficial membership

  Background:
  """
    Create a supporter account for the basketball supporter
    """

  Scenario Outline: Enter member details, validation and confirmation
    Given I am using "<browser>" as browser
    Given Member is born <birthday>
    And The member's name is <firstName> and <lastName>
    And Email address has been added
    And Password is <password> and retype password is <retype_password>
    And Member has the role Basket Ball media
    And Member <handles_t_c> terms and conditions
    And Member accepts communication preferences
    And Member accepts coc
    Then date picker will display birthday
    And email address will be equal to confirm email
    And password will be <matched> to confirm password
    Then Confirm and join button is pressed
    And Last name <name_is_validated>
    And Members terms and conditions is <validated>
    And a new member account will be created or rejected

    Examples:
      | browser  | birthday   | firstName | lastName | password   | retype_password | handles_t_c | name_is_validated | matched   | validated |
      | Chrome   | 22/01/1978 | John      | Doe      | johndoe123 | johndoe123      | accepts     | is not empty      | equal     | accepted  |
      | Chrome   | 19/09/1981 | May       |          | johndoe123 | johndoe123      | accepts     | is empty          | equal     | accepted  |
      | Edge     | 03/07/2002 | Sam       | Anderson | johndoe123 | johnnydie123    | accepts     | is not empty      | not equal | accepted  |
      | Fire fox | 16/02/1999 | Anna      | Bronson  | johndoe123 | johndoe123      | rejects     | is not empty      | equal     | rejected  |