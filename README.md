# Mid-small-world-phe: Kevin Bacon Game

## Project Overview
This project implements the **Kevin Bacon Game**, which calculates the "Bacon number" for actors based on their connections through movies. The **Bacon number** of an actor is the number of degrees of separation between the actor and actor Kevin Bacon, based on their movie collaborations. The goal is to determine the shortest path (in terms of movie connections) between an actor and Kevin Bacon.

This project reads a movie dataset and constructs a graph to represent actor relationships. It then uses **Breadth-First Search (BFS)** to calculate the Bacon numbers and provides functionality to display the shortest path (movie chain) connecting any actor to Kevin Bacon.

## Features
- **Bacon Number Calculation**: For a given actor, the program computes the Bacon number, which is the shortest path from that actor to Kevin Bacon, based on movie collaborations.
- **Shortest Path Display**: It also shows the chain of movies (with actors) that links the actor to Kevin Bacon.
- **Movie Dataset Processing**: The program processes a dataset of movies, where each movie is followed by a list of actors, to build an actor relationship graph.
- **Bacon Number Distribution**: The program outputs the distribution of Bacon numbers (i.e., how many actors have each Bacon number).

## File Descriptions
- **IMDB_Input(1).txt**: Input file containing movie data. Each line represents a movie followed by the actors who appeared in it, separated by slashes (`/`).
- **KevinBaconGame.java**: The main Java class implementing the algorithm. It reads the movie data, constructs an actor graph, and calculates the Bacon numbers using BFS.
- **KevinBaconGame.class**: The compiled `.class` file for the `KevinBaconGame` Java program.
- **main.c**: An auxiliary C program that may be used for further computations (if applicable).
- **sample-input-2.txt**: Another sample input file used for testing the program.
- **.DS_Store**: macOS system file, not necessary for the project but may appear in directories on macOS systems.

## How to Run the Program

### Prerequisites
Ensure you have the following installed on your system:
- Java 8 or higher for running `KevinBaconGame.java`.
- C compiler (optional) for compiling and running `main.c`.

### Running the Java Program
1. **Compile the Java Program**:
   First, compile the `KevinBaconGame.java` file:

   ```bash
   javac KevinBaconGame.java

2. **Run the Java Program**:
   After compiling the Java program, run it using the following command:

   ```bash
   java KevinBaconGame
   ```

   The program will prompt you to enter the filename of the movie dataset. Enter the path to `IMDB_Input(1).txt` or another dataset file you wish to use.

   Example:

   ```
   Enter the movie data filename: IMDB_Input(1).txt
   ```

3. **Interact with the Program**:
   After loading the dataset, the program will calculate the Bacon number for each actor and print out the Bacon number distribution. You can also enter an actor's name to find their Bacon number and the shortest movie chain connecting them to Kevin Bacon.

   Example:

   ```
   Enter an actor's name (or type 'E' to exit): Smith, Will
   ```

   If the actor is connected to Kevin Bacon, the program will show the shortest chain of movies linking the actor to Kevin Bacon.

### Running the C Program (optional)

1. Compile the C program:

   ```bash
   gcc main.c -o main
   ```
2. Run the compiled program:

   ```bash
   ./main
   ```

## Usage

1. After running the program, you will be asked to enter a filename containing movie data. The format of the file should be:

   ```
   Movie Title/Actor1/Actor2/Actor3/...
   ```
2. The program will calculate the **Bacon number** for Kevin Bacon and all actors in the dataset.
3. It will display the distribution of Bacon numbers across all actors.
4. You can enter the name of any actor to get their **Bacon number** and the **shortest path** (movies) to Kevin Bacon. To exit the program, type 'E'.

### Example Output

```
Enter the movie data filename: IMDB_Input(1).txt
Bacon number    Frequency
-------------------------
            0        1
            1       50
            2       200
            3       300
            4       100
            5        30
            6         5
       infinity        20

Enter an actor's name (or type 'E' to exit): Smith, Will
Smith, Will has a Bacon number of 2
Smith, Will was in "Enemy of the State" with Hackman, Gene
Hackman, Gene was in "The Royal Tenenbaums" with Bacon, Kevin
```
Here is an example of the program's output:
![Java Program Output](./images/截屏2025-05-31%2017.13.40.png)

## Project Structure

```
.
├── .DS_Store
├── IMDB_Input(1).txt
├── KevinBaconGame.class
├── KevinBaconGame.java
├── main.c
├── sample-input-2.txt
```

## Contribution

Feel free to contribute to the project by submitting pull requests or reporting issues. Any improvements or bug fixes are welcome!
