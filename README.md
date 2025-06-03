# Scala Pattern Matching Engine

[![MIT License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Scala](https://img.shields.io/badge/Scala-2.13%2B-red.svg)](https://www.scala-lang.org/)
[![Made with ❤️](https://img.shields.io/badge/Made%20with-%E2%9D%A4-red.svg)](https://github.com/your-username/scala-pattern-matching)

---

A simple Scala program that performs pattern matching on strings using basic regular expression operators like concatenation, alternation (`|`), and optionality (`?`). This project implements its own expression parsing and evaluation using a tree structure without relying on Scala's built-in regular expressions.

## Features
- Supports basic pattern matching operators:
  - Concatenation
  - Alternation (`|`)
  - Optionality (`?`)
- Tree-based representation using Scala case classes.
- Command-line interface for pattern input and string matching.
- Clean and minimal design, no external regex libraries used.

## Example Usage
```bash
Pattern? ((h|j)ell. worl?d)|(42)
String? hello world
=> Match

String? jello word
=> Match

String? jelly word
=> No Match

##Technologies Used
Scala 2.13+

Case Classes

Pattern Matching

## Getting Started
# Prerequisites
Scala installed.

##Installation
1. Clone the repository:


git clone https://github.com/Vandanp14/scala-pattern-matching.git
cd scala-pattern-matching

Compile and run:


scalac Main.scala
scala Main
How to Use
Run the program.

Enter a pattern following supported syntax.

Enter strings to test against the pattern.

Type quit or exit to stop the program.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgements
Developed as part of coursework at SUNY Oswego