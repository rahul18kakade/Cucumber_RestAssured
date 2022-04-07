Feature: Test for Book Store API

  Background: User generates token for authorization
    Given User is authorized

  Scenario: Authorized user is able to add a book
    Given list of books are available
    When User add a book to list
    Then The book is added to list

  Scenario: Authorized user is able to delete a book
    When User remove a book from list
    Then The book is removed
