# Heavy Java Projects Collection

A comprehensive collection of Java programming projects covering various computer science concepts including data structures, algorithms, game theory, cryptography, GUI applications, and parallel computing.

## 📁 Project Structure

### 🎯 Exercise Projects (01-07)

#### **01-BigFraction-Calculator** - Arbitrary Precision Fraction Calculator
- **Purpose**: Implements a `BigFraction` class for precise arithmetic operations on fractions
- **Features**: 
  - Supports addition, subtraction, multiplication, and division
  - Automatic simplification and GCD calculation
  - String parsing and BigDecimal conversion
  - Handles negative denominators properly

#### **02-NearestNeighbor-2D** - 2D Point Nearest Neighbor Finder
- **Purpose**: Finds the closest point in a collection to a given 2D point
- **Features**:
  - Euclidean distance calculations
  - Dynamic point addition
  - Efficient nearest neighbor search algorithm

#### **03-LineColoring-Game** - Line Coloring Game Solver
- **Purpose**: Solves the line coloring game using game theory
- **Features**:
  - Determines winning moves for players
  - Implements minimax algorithm
  - Validates move legality and win conditions

#### **04-Joke-Management-System** - Joke Management System
- **Purpose**: Manages and serves jokes from a text file
- **Features**:
  - Loads jokes from file with "joke:" delimiter
  - Random joke selection
  - Add new jokes dynamically
  - Save jokes back to file
  - Index-based joke retrieval

#### **05-Chomp-Game-JavaFX** - Chomp Game with JavaFX GUI
- **Purpose**: Interactive implementation of the Chomp mathematical game
- **Features**:
  - JavaFX-based graphical interface
  - 9x9 game board with cookie/skull graphics
  - Click-based gameplay
  - Visual feedback for game state

#### **06-Parallel-Dice-Roll** - Parallel Computing Demonstration
- **Purpose**: Demonstrates parallel vs serial processing for dice roll simulations
- **Features**:
  - Multi-threaded dice roll generation
  - Performance comparison between serial and parallel execution
  - Configurable array size for testing

#### **07-CSV-Sorter** - CSV File Sorter
- **Purpose**: Sorts CSV data by specified column
- **Features**:
  - Handles mixed data types (String, Integer, Double)
  - Command-line column specification
  - Proper CSV parsing with quoted strings
  - Maintains data integrity during sorting

### 🏠 Homework Projects (08-15)

#### **08-Catalan-Numbers-Game-Theory** - Catalan Numbers & Game Theory
- **Catalan.java**: Calculates Catalan numbers using recursive formula
- **ToadsAndFrogsSolver.java**: Solves the Toads and Frogs puzzle game
- **PigRollSequences.java**: Calculates possible dice roll sequences in Pig game

#### **09-Freecell-Card-Game** - Freecell Card Game
- **Purpose**: Complete implementation of the Freecell solitaire game
- **Features**:
  - Full game logic with 16 card stacks
  - Move validation and illegal play detection
  - Text-based interface with numbered stacks
  - Win condition checking

#### **10-Data-Structures-Implementation** - Data Structures Implementation
- **MyLinkedList.java**: Custom linked list implementation
- **MyQueue.java**: Queue data structure
- **MyStack.java**: Stack data structure
- **MahFravrit.java**: Custom data structure implementation

#### **11-Search-Algorithms-Cryptography** - Search Algorithms & Cryptography
- **Searcher.java**: Abstract search algorithm framework
- **BreadthFirstSearcher.java**: BFS implementation
- **DepthFirstSearcher.java**: DFS implementation
- **CaesarCipher.java**: Caesar cipher encryption/decryption
- **StreamCipher.java**: Stream cipher implementation
- **UnicodeSubstitutionCipher.java**: Unicode-based substitution cipher
- **SudokuNode.java**: Sudoku puzzle solver using search algorithms

#### **12-JavaFX-Card-Display-System** - JavaFX Card Display System
- **CardPane.java**: Custom JavaFX component for displaying playing cards
- **CardGridPane.java**: Grid layout for multiple cards
- **Features**:
  - Visual card representation with suit symbols
  - Responsive design with proper aspect ratios
  - Support for all standard playing card ranks

#### **13-Lights-Out-Puzzle-Game** - Lights Out Puzzle Game
- **Purpose**: Interactive Lights Out puzzle with JavaFX GUI
- **Features**:
  - Configurable grid sizes (3x3 to 9x9)
  - Visual light toggle mechanics
  - Randomize and chase lights algorithms
  - Real-time game state feedback

#### **14-N-Queens-Problem-Solver** - N-Queens Problem Solver
- **Purpose**: Solves the N-Queens puzzle using both serial and parallel approaches
- **Features**:
  - Depth-first search algorithm
  - Multi-threaded parallel solution
  - Performance timing comparison
  - Configurable board sizes

#### **15-Traffic-Lights-Game-Solver** - Traffic Lights Game Solver
- **Purpose**: Solves the Traffic Lights combinatorial game
- **Features**:
  - Game state analysis using minimax algorithm
  - Binary file storage for computed solutions
  - Interactive gameplay with solution hints
  - 3x4 grid with color progression (OFF→GREEN→YELLOW→RED)

### 🔧 Utility Projects (16-28)

#### **16-Primality-Tester** - Prime Number Detection
- **Purpose**: Tests numbers for primality using various algorithms

#### **17-Perfect-Shuffler** - Card Shuffling Algorithm
- **Purpose**: Implements perfect shuffle algorithms for card decks

#### **18-Line-Length-Analyzer** - Text Processing Utility
- **Purpose**: Analyzes line lengths in text files

#### **19-List-Stack-Queue-Demo** - Data Structure Demonstrations
- **Purpose**: Demonstrates list, stack, and queue operations

#### **20-Pig-Turn-Mathematics** - Pig Game Mathematics
- **Purpose**: Mathematical analysis of the Pig dice game

#### **21-Last-Friday-Calculator** - Date Calculation Utility
- **Purpose**: Finds the last Friday of each month

#### **22-Input-Sign-Validator** - Input Validation
- **Purpose**: Validates and processes user input

#### **JavaFX Examples**
- **23-JavaFX-Fun-Demos**: Basic JavaFX demonstrations
- **24-JavaFX-Basic-Test**: JavaFX basic testing
- **25-JavaFX-Experimentation**: JavaFX experimentation

#### **Additional Projects**
- **26-Personal-Projects**: Personal Java projects
- **27-Person-Class-Demo**: Person class demonstration
- **28-Test-Files**: Test files and data

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- JavaFX SDK (for GUI projects)
- Git

### Running Projects

1. **Compile Java files**:
   ```bash
   javac *.java
   ```

2. **Run specific projects**:
   ```bash
   # For GUI applications (JavaFX)
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml Chomp
   
   # For console applications
   java BigFractionRunner
   
   # Example: Running Freecell game
   cd 09-Freecell-Card-Game
   javac *.java
   java Freecell
   ```

3. **For projects with dependencies**:
   ```bash
   # Example: Running Freecell game
   cd 09-Freecell-Card-Game
   javac *.java
   java Freecell
   ```

## 📚 Learning Objectives

This collection demonstrates mastery of:

- **Object-Oriented Programming**: Classes, inheritance, polymorphism
- **Data Structures**: Linked lists, stacks, queues, trees
- **Algorithms**: Search algorithms (BFS, DFS), sorting, dynamic programming
- **Game Theory**: Minimax algorithm, combinatorial game analysis
- **Cryptography**: Various cipher implementations
- **GUI Programming**: JavaFX applications and custom components
- **Parallel Computing**: Multi-threading and concurrent programming
- **File I/O**: Binary and text file operations
- **Error Handling**: Exception management and input validation

## 🎮 Interactive Projects

Several projects provide interactive experiences:
- **Chomp**: Mathematical puzzle game
- **Lights Out**: Logic puzzle with visual interface
- **Freecell**: Classic solitaire card game
- **Traffic Lights**: Strategic board game

## 🔬 Algorithm Demonstrations

- **Search Algorithms**: BFS, DFS implementations
- **Dynamic Programming**: Catalan numbers, Pig roll sequences
- **Parallel Processing**: Multi-threaded N-Queens solver
- **Game Theory**: Optimal play analysis for various games

## 📝 Notes

- All projects are self-contained and can be run independently
- GUI projects require JavaFX runtime
- Some projects include test files for validation
- Performance comparisons are included where relevant
- Documentation and comments are provided throughout the code

## 👨‍💻 Author

Joseph Sackitey

---

*This collection represents a comprehensive journey through advanced Java programming concepts, from basic data structures to complex algorithmic implementations and interactive applications.*
