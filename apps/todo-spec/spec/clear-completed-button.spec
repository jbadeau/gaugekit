# Clear completed button

* Given I am on "TodoMVC" page

## Hidden when no items are completed
* Given there are 3 todo items
* Then I should not see clear completed button

## Removes completed todos on clicking
* Given there are 5 todo items
* And first two todo items are marked as complete
* When I click clear completed button
* Then I should see only 3 todo items in the list
