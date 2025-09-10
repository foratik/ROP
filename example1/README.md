### Example 1: Functional without Railway

This example shows a straightforward flow using exceptions and null checks.

Pipeline:
- sanitize input
- validate required fields and email format
- check uniqueness
- persist
- send welcome email

Run main:
```bash
mvn -q -DskipTests exec:java -Dexec.mainClass=org.example.example1.Example1Main
```

