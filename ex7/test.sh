#!/bin/bash

echo "Testing SortCSV program..."

# Compile the program
echo "Compiling SortCSV.java..."
javac SortCSV.java

# Test 1: Sort by first column (Double)
echo -e "\nTest 1: Sorting by first column (Double)"
cat test.csv | java SortCSV 0

# Test 2: Sort by second column (Double)
echo -e "\nTest 2: Sorting by second column (Double)"
cat test.csv | java SortCSV 1

# Test 3: Sort by third column (Integer)
echo -e "\nTest 3: Sorting by third column (Integer)"
cat test.csv | java SortCSV 2

# Test 4: Sort by fourth column (Integer)
echo -e "\nTest 4: Sorting by fourth column (Integer)"
cat test.csv | java SortCSV 3

# Test 5: Sort by fifth column (String)
echo -e "\nTest 5: Sorting by fifth column (String)"
cat test.csv | java SortCSV 4

# Test 6: Sort by sixth column (String)
echo -e "\nTest 6: Sorting by sixth column (String)"
cat test.csv | java SortCSV 5

echo -e "\nAll tests completed!" 