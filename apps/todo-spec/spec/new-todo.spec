# New todo form is in focus & accepts the input

* Given I am on "TodoMVC" page

## New todo form is in focus
* Given no todo item exists
* Then I should see the new todo form focussed

## Submitting form with blank string
* Given no todo item exists
* When I enter "         " in the new todo form
* And I submit the new todo form
* Then I should not see any change in the todo list

## Submitting form with non-blank string
* Given no todo item exists
* When I enter "A fancy new todo item" in the new todo form
* And I press enter key
* Then I should see "A fancy new todo item" in the todo list

## User input is trimmed for leading & trailing spaces
* Given no todo item exists
* When I enter "   A fancy new todo item  " in the new todo form
* And I press enter key
* Then I should see "A fancy new todo item" in the todo list

## Submitting with enter key
* Given no todo item exists
* When I enter "         " in the new todo form
* And I press enter key
* Then I should not see any change in the todo list
