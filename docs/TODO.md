# Planned Features for rustybytecode

## JVM Elements

- [ ] Generic signature.
      Currently the signatures of fields and methods (located in module `rustybytecode::types::signatures`) are aliased to `String`.
      We plan to extend it to structured ones (See `FieldType` and `MethodDescriptor`) to make it easier for type checking.

## Static Analysis

- [ ] Fixed point analysis leveraging multiple CPU cores.
      The current implementation of fixed point analysis (See `rustybytecode::analysis::fixed_point::Analyzer`) uses only one CPU core.
      We plan to implement a `ParAnalyzer` to leverage multi-core CPUs for a faster analysis.

## Tests

- [ ] Unit tests are needed to assure the correctness of rustybytecode.
      See [Codecov](https://app.codecov.io/gh/cozmoe0/rustybytecode) for uncovered code.
      It would be nice if we can leverage [property-based tests](https://proptest-rs.github.io/proptest/) to exhaustively test the features.
- [ ] [Doc tests](https://doc.rust-lang.org/rustdoc/write-documentation/documentation-tests.html) and example usage of APIs.

## CI

- [ ] Run doc tests and include their coverage in the report.

## Modification of Byte Code

- [ ] APIs for turning rustybytecode data structures into JVM byte code.
      Such APIs enable the modification of JVM byte code in scenarios like instrumentation.
