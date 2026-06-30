# CS-6359 — Video Store Refactoring (HW2)

A Java implementation of the classic **Video Store** example from Martin Fowler's *Refactoring*.
The original `Customer.statement()` "code smell" version has been refactored into a clean,
object-oriented design using a **Movie class hierarchy** and **polymorphism** instead of
price-code constants and `switch` statements.

The program prints a customer's rental record in two formats: a **plain-text statement** and an
**XML statement**.

---

## Table of Contents
- [Functionality](#functionality)
- [Design Overview](#design-overview)
- [Project Structure](#project-structure)
- [Pricing & Frequent-Renter Rules](#pricing--frequent-renter-rules)
- [Requirements](#requirements)
- [How to Run](#how-to-run)
- [Expected Output](#expected-output)
- [Refactorings Applied](#refactorings-applied)

---

## Functionality

The application models a video rental store and produces a customer statement:

- **Customer management** — a `Customer` holds a name and a list of `Rental`s.
- **Rentals** — each `Rental` links a `Movie` to the number of days it was rented.
- **Movie types** — three concrete movie categories, each with its own pricing rule:
  - Regular
  - New Release
  - Children's
- **Charge calculation** — each movie type computes its own rental charge (polymorphism).
- **Frequent-renter points** — points are accumulated per rental, with a bonus rule for new releases.
- **Text statement** — `Customer.statement()` produces a human-readable rental record.
- **XML statement** — `Customer.xmlStatement()` produces the same data as a well-formed XML document.

---

## Design Overview

Instead of an `int` price code plus `switch` statements (the original "smelly" design),
movie behavior is expressed through an **abstract base class** and **subclass overrides**:

```
        Movie (abstract)
        ├── getCharge(daysRented)              (abstract — each subclass defines pricing)
        └── getFrequentRenterPoints(daysRented) (default = 1 point)
              ▲
              │ extends
   ┌──────────┼─────────────────┐
   │          │                 │
RegularMovie  NewReleaseMovie    ChildrensMovie
              (overrides points rule)
```

- `Rental` delegates charge/points calculation to its `Movie` — logic lives with the data it uses.
- `Customer` aggregates totals and formats the output (text and XML).

---

## Project Structure

| File                   | Responsibility                                                             |
|------------------------|----------------------------------------------------------------------------|
| `Main.java`            | Entry point — builds a sample customer and prints both statements.         |
| `Customer.java`        | Holds rentals; builds the text (`statement()`) and XML (`xmlStatement()`). |
| `Rental.java`          | Associates a `Movie` with days rented; delegates charge/points to `Movie`. |
| `Movie.java`           | Abstract base class defining the movie contract.                           |
| `RegularMovie.java`    | Regular pricing rule.                                                       |
| `NewReleaseMovie.java` | New-release pricing + bonus frequent-renter points.                        |
| `ChildrensMovie.java`  | Children's pricing rule.                                                    |
| `PriceCode.java`       | Legacy `PriceCode` enum (kept for reference; not used by the new design).  |

> **Note:** `HW2_Refactoring_Report.docx` documents the refactoring steps. Compiled `*.class`
> files are excluded from version control via `.gitignore`.

---

## Pricing & Frequent-Renter Rules

| Movie type   | Charge formula                                          | Frequent-renter points              |
|--------------|---------------------------------------------------------|-------------------------------------|
| Regular      | `2.0` base; `+1.5` per day beyond the first **2** days  | 1                                   |
| New Release  | `3.0 × daysRented`                                       | 2 if rented **> 1 day**, else 1     |
| Children's   | `1.5` base; `+1.5` per day beyond the first **3** days  | 1                                   |

---

## Requirements

- **Java JDK 8 or newer** (developed/verified with **OpenJDK 21**).
- No external libraries or build tools required — plain `javac` / `java`.

Check your installation:

```bash
java -version
javac -version
```

---

## How to Run

From the project root (`CS-6359-Project/`):

```bash
# 1. Compile all source files into a build directory
javac -d out *.java

# 2. Run the program
java -cp out Main
```

Or, to compile and run in place (class files land in the current directory):

```bash
javac *.java
java Main
```

> `*.class` files are git-ignored, so they won't be committed.

---

## Expected Output

```
=== Text Statement ===
Rental Record for John Smith
	The Matrix	3.5
	Independence Day	6.0
	Toy Story	4.5
Amount owed is 14.0
You earned 4 frequent renter points

=== XML Statement ===
<statement>
  <name>John Smith</name>
  <rental>
    <movie>The Matrix</movie>
    <daysRented>3</daysRented>
    <charge>3.5</charge>
  </rental>
  <rental>
    <movie>Independence Day</movie>
    <daysRented>2</daysRented>
    <charge>6.0</charge>
  </rental>
  <rental>
    <movie>Toy Story</movie>
    <daysRented>5</daysRented>
    <charge>4.5</charge>
  </rental>
  <totalAmount>14.0</totalAmount>
  <frequentRenterPoints>4</frequentRenterPoints>
</statement>
```

**How the totals are derived** (sample data in `Main.java`):

| Movie            | Type        | Days | Charge                         | Points |
|------------------|-------------|------|--------------------------------|--------|
| The Matrix       | Regular     | 3    | `2.0 + (3−2)×1.5` = **3.5**    | 1      |
| Independence Day | New Release | 2    | `2 × 3.0` = **6.0**            | 2      |
| Toy Story        | Children's  | 5    | `1.5 + (5−3)×1.5` = **4.5**    | 1      |
| **Total**        |             |      | **14.0**                       | **4**  |

---

## Refactorings Applied

Compared to the original Fowler "before" code, this version applies:

- **Replace Type Code with Subclasses / State** — `Movie` hierarchy replaces `int` price codes and `switch` statements.
- **Move Method** — `calculateCharge()` and `getFrequentRenterPoints()` moved from `Customer` to `Rental`/`Movie`, so logic lives with its data.
- **Extract Method** — `Customer` extracts `calculateTotalCharge()`, `calculateTotalFrequentRenterPoints()`, and `buildRentalLines()`.
- **Replace Magic Numbers with Named Constants** — e.g. `BASE_PRICE`, `BASE_PERIOD`, `EXTRA_RATE`, `DAILY_RATE`.
- **Rename Variables/Fields** — underscore-prefixed fields (`_name`, `_movie`) renamed to clean camelCase.
- **Replace Data Structure** — `Vector` replaced with `List<Rental>` / `ArrayList<Rental>`.
- **New feature** — `xmlStatement()` added with no duplication of the pricing logic, demonstrating the payoff of the refactored design.
