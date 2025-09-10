### Example 2: Railway-Oriented Programming

This example introduces a minimal `Result<T, E>` type with `map`, `flatMap`, `mapError`, `tap`, and `tapError` to build a railway pipeline.

Pipeline:
- sanitize → validateRequired → validateEmail → ensureUnique → map to User → save → sendWelcome
- success and errors are values; no exceptions for control flow

Run main:
```bash
mvn -q -DskipTests exec:java -Dexec.mainClass=org.example.example2.Example2Main
```

