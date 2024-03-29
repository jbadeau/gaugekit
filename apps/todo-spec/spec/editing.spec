# Editing a todo item

* Given I am on "TodoMVC" page
* And there are 5 todo items

## Label is hidden for the item
* Given I am working on first item in the list
* When I double click on the item
* Then I do not see the label for the item

## Focus is on the form for the item
* Given I am working on first item in the list
* When I double click on the item
* Then I should see the edit form for the item
* And I should see the edit form for the item focussed

## Removing focus from the form saves the item
* Given I am working on first item in the list
* When I double click on the item
* And I clear the edit form for the item
* And I enter "Updated todo item" in the edit form for the item
* And I click on the page footer
* Then I should see "Updated todo item" in the list

## Pressing 'Enter' key from within the form saves the item
* Given I am working on first item in the list
* When I double click on the item
* And I clear the edit form for the item
* And I enter "Updated todo item" in the edit form for the item
* And I press enter key
* Then I should see "Updated todo item" in the list

## Pressing 'Esc' key from within the form discards the changes
* Given I am working on first item in the list
* When I double click on the item
* And I clear the edit form for the item
* And I enter "Updated todo item" in the edit form for the item
* And I press esc key
* Then I should see the item unchanged in the list

## User input is trimmed for leading & trailing spaces
* Given I am working on first item in the list
* When I double click on the item
* And I clear the edit form for the item
* And I enter "   Updated todo item   " in the edit form for the item
* And I press enter key
* Then I should see "Updated todo item" in the list

## Submitting form with blank string removes the item
* Given I am working on first item in the list
* When I double click on the item
* And I clear the edit form for the item
* And I press enter key
* Then I should not see the item in the list
