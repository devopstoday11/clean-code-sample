Feature: Registration
  Scenario: User creation should be possible
    Then I should be able to register a user with a new e-mail address

  Scenario: User creation should send out a verification e-mail
    When I register a user with a new e-mail address
    Then I should receive a verification e-mail to that e-mail address