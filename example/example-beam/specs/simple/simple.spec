# Simple

* Given actor "John"

## Passthrough
* When "he" provides templated input "simple/{{now format='yyyy-MM-dd'}}.employees.csv.hbs"
* And "he" runs "simple" pipeline
* Then "he" ensures output matches snapshot "simple/employees.snapshot.csv"