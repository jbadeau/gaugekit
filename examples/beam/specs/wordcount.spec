# Word Count

## Simple

* Given "John" provides source "wordcount/input.csv" and sink "wordcount/output.csv"
* When "he" runs pipeline "wordcount"
* Then "he" ensures output "wordcount/output.csv" isEqualTo snapshot "wordcount/output.snapshot.csv"