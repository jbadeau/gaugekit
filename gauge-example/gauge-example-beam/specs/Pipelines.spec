# Pipeline

## Simple pipeline
* Given actor "Mark"
* And templated input "simple/{{now format='yyyy-MM-dd'}}.employees.csv.hbs"
* When run "simple" pipeline
* Then output matches snapshot "simple/employees.output.csv"