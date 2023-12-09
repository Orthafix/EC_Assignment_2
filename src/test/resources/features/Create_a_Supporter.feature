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
    And Email address is <email>
    And Confirm Email is <confirm_email>
    And Password is <password>
    And Retype password of password is <retype_password>
    And Member has the role Basket Ball media
    And Member accept account confirmation terms and conditions
    And Member accepts communication preferences
    And Member accepts coc
    When Confirm and join button is pressed
    Then date picker will display <birthday>
    Then <email> address will be validated with <confirm_email>
    And <password> will be validated with <retype_password>
    And a <new_member> will be created or otherwise be rejected

    Examples:
      | browser  | birthday   | firstName | lastName | email                 | confirm_email         | password   | retype_password | new_member |
      | Chrome   | 22/01/1978 | John      | Doe      | john.doe@test.com     | john.doe@test.com     | johndoe123 | johndoe123      | True       |
      | Fire fox | 19/09/1981 | May       | Smith    | may.smith@test.com    | mike.smith@test.com   | johndoe123 | johndoe123      | false      |
      | Edge     | 03/07/2002 | Sam       | Anderson | sam.anderson@test.com | sam.anderson@test.com | johndoe123 | johnnydie123    | false      |
      | Chrome   | 16/02/1999 | Anna      | Bronson  | anna.bronson@test.com | anna.bronson@test.com | johndoe123 | johndoe123      | false      |
      |          |            |           |          |                       |                       |            |                 |            |
      |          |            |           |          |                       |                       |            |                 |            |
