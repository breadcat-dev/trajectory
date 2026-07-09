# Trajectory

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/Java-21-blue)
![Status](https://img.shields.io/badge/status-alpha-red)
![Release](https://img.shields.io/github/v/release/breadcat-dev/trajectory)

> A lightweight immutable mathematics library for Java games, simulations and graphics

Part of the TANK Series.

---

### ⚠️ Trajectory is currently in **alpha**. ⚠️
**The API is expected to change frequently until v1.0.0.**

---

## Features

### Vectors
- Immutable Vector2, Vector3 and Vector4 types
- float, double and int variants
- Vector arithmetic
- Dot product
- Cross product
- Distance and length calculations
- Normalization (floating-point vectors)

### Matrices *(v0.2.0)*
- Immutable Matrix2, Matrix3 and Matrix4 types
- float and double variants
- Matrix arithmetic
- Matrix × Matrix multiplication
- Matrix × Vector multiplication
- Determinant calculation
- Matrix transpose
- Matrix inversion
- Identity and zero matrices

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

### Vectors

```java
Vector3f position = new Vector3f(5, 2, 1);
Vector3f velocity = new Vector3f(1, 0, -2);

Vector3f next = position.add(velocity);

System.out.println(next);
```

> Vector3f(6.0, 2.0, -1.0)

### Matrices

```java
Matrix4d m1 = new Matrix4d(
        0, 2, 1, 2,
        3, 0, 3, 4,
        5, 6, 0, 6,
        7, 8, 7, 0
);

Matrix4d m2 = Matrix4d.IDENTITY;
Matrix4d m3 = m1.divide(m2);

System.out.println(m3);
```

```text
Matrix4d [
    [0.0 2.0 1.0 2.0]
    [3.0 0.0 3.0 4.0]
    [5.0 6.0 0.0 6.0]
    [7.0 8.0 7.0 0.0]
]
```

## Dependencies:

- Toolbox - [Github](https://github.com/breadcat-dev/toolbox)