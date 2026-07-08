# Trajectory

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/Java-21-blue)
![Status](https://img.shields.io/badge/status-alpha-red)
![Release](https://img.shields.io/github/v/release/breadcat-dev/trajectory)

> A lightweight math library for Java projects

Part of the TANK Series.

### ⚠️ Trajectory is currently in **alpha**. ⚠️
**The API is expected to change frequently until v1.0.0.**

---

## Features

Current features:

- Immutable Vector2, Vector3 and Vector4 types
- float, double and int variants
- Vector arithmetic
- Dot product
- Cross product (3D)
- Distance and length calculations
- Normalization (floating-point vectors)
- No external dependencies

## Design Goals

- Lightweight
- Immutable by default
- Suitable for games, simulations and graphics
- Consistent API across numeric types


## Installation

Currently, Trajectory is not on Maven Central.
To use it, clone the repository and publish it to your local Maven Repository.


```sh
git clone https://github.com/breadcat-dev/trajectory.git
cd trajectory
```

### Linux / MacOS
```sh
./gradlew publishToMavenLocal
```
### Windows
```sh
./gradlew.bat publishToMavenLocal
```

Once installed, add the dependency:

### Groovy
```gradle
implementation "cat.breadcat:trajectory:<version>"
```

### Kotlin DSL
```gradle
implementation("cat.breadcat:trajectory:<version>")
```

## Examples

```java
Vector3f position = new Vector3f(5, 2, 1);
Vector3f velocity = new Vector3f(1, 0, -2);

Vector3f next = position.add(velocity);

System.out.println(next);
```

> Vector3f(6.0, 2.0, -1.0)

## Dependencies:

This library has no external dependencies.