# RustyBytecode

rustybytecode is a Java bytecode analysis library written in Rust.

## Documentation

The documentation of the released version is available at [docs.rs](https://docs.rs/rustybytecode).
The documentation of the latest commit is available at [github.io](https://github.com/cozmoe0/rustybytecode)

## Usage

### Adding the dependency

Run the following command in the root directory of your project.

```sh
cargo add rustybytecode
```

Alternatively, to follow the latest commit version, run the following command instead.
Before building your project, run `cargo update` to fetch the latest commit.

```sh
cargo add --git https://github.com/cozmoe0/rustybytecode.git rustybytecode
```

### Parsing a class

```rust
use rustybytecode::jvm::class::Class;

fn parse_class() -> Result<Class, Box<dyn std::error::Error>> {
    let reader: std::io::Read = todo!("Some reader for the byte code");
    let class = Class::from_reader(reader)?;
    Ok(class)
}
```

### RustybytecodeIR

RustybytecodeIR is an intermediate representation of JVM bytecode in [rustybytecode](https://github.com/cozmoe0/rustybytecode).
To learn more, please refer to [docs/RustybytecodeIR.md](docs/RustyBytecodeIIR.md)

## Building

Make sure you have the following tools installed:

- The latest stable version of Rust
- The latest release version of JDK

Compile the project and run the tests with the following command.

```bash
cargo build --all-features
cargo test --all-features
```

## Contributing

Cool. Contributions are welcomed. See the [contribution guide](docs/CONTRIBUTING.md) for more information.
