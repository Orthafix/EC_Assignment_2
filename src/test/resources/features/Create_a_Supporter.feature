Feature: Create a supporter account
  As a basketball supporter
  I would like to create a supporter account
  To be able to have a beneficial membership
  Background:
    """
    Create a supporter account for the basketball supporter
    """

  #TODO: Make a Scenario outline of this instead
  Scenario: Add birthday of member
    Given Member is born 19 "September" 1981
    When date is selected
    Then date picker will display "19/09/1981"

  Scenario Outline: Enter member details, validation and confirmation
    Given The member's name is <firstName> and <lastName>
    And Email adress is <email>
    And Confirm Email is <confirm_email>
    And Password is <password>
    And Retype password of password is <retype_password>
    And Member has the role <role>
    And Member accept account Confirmation with <terms_condition> and <is_18_years>
    And Member accepts <communication_preferences>
    And Member accepts Code of Conducts and ethics <coc>
    When Confirm and join button is pressed
    Then <email> will be validated with <Confirm Email> adress and then be accepted or rejected
    And <password> will be validated with <retype_password> and then be accepted
    And a new member will be created
  Examples: The first row represent the "happy path" and what follows is the "sad paths".
    | firstName | lastName | email    | confirm_email      | password   | retype_password | role             | terms_condition | is_18_years | communication_preferences   | coc   |
    | John      | Doe      | john.doe | john.doe@test.com  | johndoe123 | johndoe123      | Basketball media | true            | true        | marketing communication     | true  |
    |           | Doe      | john.doe | john.die@test.com  | johndoe123 | johndoe123      | None             | false           | false       | communication from partners | false |
    | John      | Doe      | john.doe | john.doe@test.com  |            |                 | Fan              | false           | true        | marketing communication     | true  |
    | John      | Doe      |          | john.doeAttest.com | johndoe123 | johndoe123      | Official         | true            | false       | communication from partners | false |
    | John      |          | john.doe | john.doe@testcom   | johndoe123 | johndoe123      | Player           | true            | true        | marketing communication     | true  |
    | John      | Doe      | john.doe | john.doe@test.com  | johndoe123 | johndoe123      |                  | false           | false       | communication from partners | false |
