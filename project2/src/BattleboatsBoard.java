// Written by Phanx289 & Benkh008
import java.util.Scanner;
public class BattleboatsBoard {
    String[][] display = new String[0][0]; // display 2d array to be printed after each turn
    Boat[] fleet; // an array of boat objects
    private int shots_fired; // number of cannon shots fired
    private int nrows; // this is the numbers of rows in the board
    private int ncols; // this is the number of columns
    private String[][] board;
    private int turn_number = 1; // number of turns variable
    private int number_allowed_drone = 0; // number of drone usages allowed
    private int number_allowed_missle = 0; // number of missile usages allowed
    private  int number_of_boats = 0; // number of boats remaining in the game

    /* constructor to create a board object*/
    public BattleboatsBoard(int nrows, int ncols) {
        this.nrows = nrows;
        this.ncols = ncols;
        this.board = new String[nrows][ncols];
    }

    // get method to get the number of boats in the board
    public int get_number_boats() {
        return number_of_boats;
    }
    // get method to get the number of shots fired in the game
    public int getShots_fired() {
        return shots_fired;
    }
    // get method to get the number of turns in the game
    public int getTurn_number() {
        return turn_number;
    }
    // get method to get the number of rows in the board
    public int getNrows() {
        return nrows;
    }
    // get method to get the number of columns in a board
    public int getNcols() {
        return ncols;
    }
    // get method to get the board itself
    public String[][] getBoard() {
        return board;
    }

    // these getter methods are necessary since i need to access  them in another class


    /* method that initializes a standard board*/
    public void standard() {
        number_allowed_drone = 1;
        number_allowed_missle = 1;
        number_of_boats = 5;
        fleet = new Boat[5];
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                this.board[i][j] = "0"; // fill the board at first with zeros
            }

        }
        /* the fvfsfew lines do two things they first place the boats in their correct locations on the board and then put the boats in the fleet array
         * the this.place method. places the boat in the board and the fleet = new boat places the boat in the fleet array*/
        fleet[0] = new Boat(this.placeBoats(5));
        fleet[1] = new Boat(this.placeBoats(4));
        fleet[2] = new Boat(this.placeBoats(2));
        for (int i = 0; i < 2; i++) {
            fleet[3 + i] = new Boat(this.placeBoats(3));
        }
        display = new String[nrows][ncols]; // 2d array size equal to the board's size

    }
    // initializes an expert board
    public void expert() {
        int n = 0;
        number_of_boats = 10;
        fleet = new Boat[10];
        number_allowed_drone = 2;
        number_allowed_missle = 2;
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                this.board[i][j] = "0"; // loops through the board and fills it with zeros first
            }
        }
        /* the next few lines do two things they first place the boats in their correct locations on the board and then puts the boats in the fleet array
         * the this.place method. places the boat in the board and the fleet = new boat places the boat in the fleet array*/
        for (int i = 0; i < 2; i++) {
            fleet[n + i] = new Boat(this.placeBoats(5)); // methods that place boats
            fleet[n + i + 1] = new Boat(this.placeBoats(4));
            fleet[n + i + 2] = new Boat(this.placeBoats(2));
            n = 2;

        }
        for (int i = 0; i < 4; i++) {
            fleet[6 + i] = new Boat(this.placeBoats(3));

        }
        display = new String[nrows][ncols]; // 2d array size equal to the board's size
    }

    // method that checks whether the game is over by looping through the board and seeing if there is still an integer that is not zero on the board
    public boolean is_Over() {
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                // loops through the board to see if there is an integer that is not zero left on the board
                if (!this.board[i][j].equals("0") && !this.board[i][j].equals("X") && !this.board[i][j].equals("O")) {
                    return false;

                }
            }
        }
        return true;

    }

    /*method that places boats on the board*/
    public Coordinates[] placeBoats(int size) {
        Coordinates[] cell = new Coordinates[size]; // an array of coordinate objects that is later going to be used as a parameter for the boat object
        boolean is_placed = false; // boolean that checks if a boat has been placed on a give location on the board.
        int Random_x = (int) Math.floor(Math.random() * this.nrows); // picks a random x coordinate to place a boat
        int Random_y = (int) Math.floor(Math.random() * this.ncols); // picks a random y coordinate to place a boat
        while (is_placed == false) {
            Boolean is_empty = true; // a boolean that is used to check whether the space on the right side of the random x,y coordinate is empty
            Boolean is_empty2 = true; // a boolean that is used to check whether the space on the left  side of the random x,y coordinate is empty
            Boolean is_empty3 = true; // a boolean that is used to check whether the space on the upper side of the random x,y coordinate is empty
            Boolean is_empty4 = true; // a boolean that is used to check whether the space on the bottom side of the random x,y coordinate is empty
            /* checks if the location of the right side of the random x,y is empty*/
            if (Random_y + size <= this.nrows) {
                for (int i = 0; i < size; i++) {
                    if (!this.board[Random_x][Random_y + i].equals("0")) {
                        is_empty = false;
                        break;
                    }

                }
            }
            /* checks if the location of the left side of the random x,y is empty*/
            if (Random_y - size >= 0) {
                for (int i = 0; i < size; i++) {
                    if (!this.board[Random_x][Random_y - i].equals("0")) {
                        is_empty2 = false;
                        break;
                    }
                }
            }
            /* checks if the location of the upper side of the random x,y is empty*/
            if (Random_x + size <= this.nrows) {
                for (int i = 0; i < size; i++) {
                    if (!this.board[Random_x + i][Random_y].equals("0")) {
                        is_empty3 = false;
                        break;
                    }
                }
            }
            /* checks if the location of the lower side of the random x,y is empty*/
            if (Random_x - size >= 0) {
                for (int i = 0; i < size; i++) {
                    if (!this.board[Random_x - i][Random_y].equals("0")) {
                        is_empty4 = false;
                        break;
                    }
                }
            }
            /*checks is the right side of the random x,y location is both empty and there is enough room to place a boat of a given size there.
            if both those conditions are true that it places a boat there*/
            if ((int) Math.floor(Math.random() * 10) % 2 == 0) {
                if (Random_y + size <= this.nrows && is_empty == true) {
                    for (int i = 0; i < size; i++) {
                        this.board[Random_x][Random_y + i] = String.valueOf(size);
                        cell[i] = new Coordinates(Random_x, Random_y + i); // fills the cell array with coordinate objects
                    }
                    is_placed = true;
                }
                /*checks is the left side of the random x,y location is both empty and there is enough room to place a boat of a given size there.
                 if both those conditions are true that it places a boat there*/
                else if ((Random_y - size >= 0 && is_empty2 == true)) {
                    for (int i = 0; i < size; i++) {
                        this.board[Random_x][Random_y - i] = String.valueOf(size);
                        cell[i] = new Coordinates(Random_x, Random_y - i); // fills the cell array with coordinate objects
                    }
                    is_placed = true;
                }
            }
            /*checks is the upper side of the random x,y location is both empty and there is enough room to place a boat of a given size there.
             if both those conditions are true that it places a boat there*/
            else {
                if ((Random_x + size <= this.nrows && is_empty3 == true)) {
                    for (int i = 0; i < size; i++) {
                        this.board[Random_x + i][Random_y] = String.valueOf(size);
                        cell[i] = new Coordinates(Random_x + i, Random_y); // fills the cell array with coordinate objects
                    }
                    is_placed = true;
                }
                /*checks is the bottom side of the random x,y location is both empty and there is enough room to place a boat of a given size there.
                 if both those conditions are true that it places a boat there*/
                else if ((Random_x - size >= 0 && is_empty4 == true)) {
                    for (int i = 0; i < size; i++) {
                        this.board[Random_x - i][Random_y] = String.valueOf(size);
                        cell[i] = new Coordinates(Random_x - i, Random_y ); // fills the cell array with coordinate objects
                    }
                    is_placed = true;
                }
            }
            if (is_placed == false) {
                Random_x = (int) Math.floor(Math.random() * this.nrows); // if all directions of the x,y do not work this picks a new random x location
                Random_y = (int) Math.floor(Math.random() * this.ncols); // if all directions of the x,y do not work this picks a new random y location
            }
        }
        return cell; // returns the cell array
    }

    /*method that fire a cannon shot in a given location*/
    public void fire(int x, int y) {
        shots_fired += 1; // tracks number of shots fired
        String result = ""; // tracks the end result of the firing method
        // checks if the shot is within the bounds of the board
        if (x >= this.nrows || y >= this.ncols || x < 0 || y < 0) {
            result = "penality";

        } // checks if the shot is fired in a location that was fired at before
        else if (this.board[x][y].equals("O") || this.board[x][y].equals("X")) {
            result = "penality";
        } // checks if the shot missed a target
        else if (this.board[x][y].equals("0")) {
            result = "miss";
            this.board[x][y] = "O";
        } // checks if the shot hit target
        else if (!this.board[x][y].equals("0")) {
            // checks if the hitting the board caused the boat to sink
            if (is_Sunk(x,y) == true) {
                result = "sunk";
                this.board[x][y] = "X";
            } else {
                result = "hit";
                this.board[x][y] = "X";

            }
        }
        System.out.println("turn " + String.valueOf(turn_number) + ": " + result);
        if (result.equals("penality")) {
            turn_number += 1;
            System.out.println("turn " + String.valueOf(turn_number) + ": " + "skipped");
        }
        turn_number += 1; // adds the number of turns by one every time a shot is fired
        System.out.println();
        System.out.println("before turn " + turn_number);
        display(); // displays the results of firing
    }

    /* a method like the fire method above that is specifically only used to be used alongside the missile function and the missile function only.
    this is to avoid the before turn board that appears after firing a cannon shot form appearing after firing a missile. words pretty much same way as the other fire method
    but without the display method at the end*/
    public void missile_Fire(int x, int y) {

        if (x < 0 || y < 0 || y >= this.ncols || x >= this.nrows) {
            ;
        } else if (this.board[x][y].equals("0")) {
            this.board[x][y] = "O";
        } else if (!this.board[x][y].equals("0") && !this.board[x][y].equals("X") && !this.board[x][y].equals("O")) {
            this.board[x][y] = "X";
            is_Sunk(x,y);
        }
        else {
            ;
        }
    }
    // checks whether a boat has been sunk after the user launches a cannon or missile
    public boolean is_Sunk(int x, int y) {
        int counter = 0; // counts the number of squares that were sunk in the array
        boolean is_sunk = false; // tracks whether the boat was sunk or not
        Boat correct_boat = null; // initialized and empty boat object
        for (int i = 0; i < fleet.length ; i++) {
            for (int j = 0; j < fleet[i].boat.length; j++) {
                // loops through the whole fleet to find the boat with the coordinates that were fired at
                if (fleet[i].boat[j].getX() == x && fleet[i].boat[j].getY() == y) {
                    correct_boat = fleet[i];
                    break;

                }
            }
        }
        // loops through the boat that contains the coordinates that were fired at and counts how many spots in that boat were hit. if the number matches the size of the boat, then it sank
        for (int j = 0; j < correct_boat.boat.length ; j++) {
            // counts the number of squares in the correct board that were hit
            if (this.board[correct_boat.boat[j].getX()][correct_boat.boat[j].getY()].equals("X")) {
                counter +=1;
            }
        }
        if (counter == correct_boat.boat.length - 1 ) {
            is_sunk = true;
            number_of_boats -=1; // if the boat was sunk this subtracts one from the number of boats that were sunk
        }
        return is_sunk;
    }
    // displays the location of fired shots and whether those shots were a miss or hit by looping through the board and replacing zero with -
    // this is done by looping through the boards and replacing the coordinates  in the display 2d array by the corresponding information in the board
    public void display() {
        for (int i = 0; i <this.nrows ; i++) {
            for (int j = 0; j < this.ncols ; j++) {
                if (this.board[i][j].equals("X")) {
                    display[i][j] = "X";
                } else if (this.board[i][j].equals("O")) {
                    display[i][j] = "O";
                } else if (display[i][j] != "D") {
                    display[i][j] = "-";
                }
            }
        }
        for (int i = 0; i < nrows ; i++) {
            for (int j = 0; j < ncols ; j++) {
                System.out.print(display[i][j]);

            }
            System.out.println();

        }
    }
    // prints the full board with all the boats and the location that were fired at by looping through the whole board
    public void print() {
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
    // drone method that is used to scan a specific column or row that the user wants to scan
    public int drone() {
        turn_number +=1; // increases number of turns by one
        boolean valid_input = false; // boolean to check if the input given by the user is valid
        int counter = 0; // counts the number of targets  scanned
        while (valid_input == false ) {
            if (number_allowed_drone < 1) {
                System.out.println("Drone has been used max amount of times");
                return -1;
            }
            System.out.println("Would you like to scan a row or column?  Type in r for row or c for column.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            // sees whether the user wants to scan a row or a column, checks that their input is valid, and scans the give row or column
            if (input.equals("row") || input.equals("r")) {
                number_allowed_drone -=1; // decreases number of drones used by one
                System.out.println("which row would you like to scan. Enter an integer, no decimal points");
                int which_row = scanner.nextInt();
                // checks of the input given by the user is valid
                while (true) {
                    if (which_row >=this.nrows || which_row < 0) {
                        System.out.println("invalid row. try again");
                        which_row = scanner.nextInt();
                    }
                    else {
                        break; // breaks out when the user gives valid input
                    }
                }
                for (int i = 0; i < this.nrows; i++) {
                    if (!this.board[which_row][0 + i].equals("0") && !this.board[which_row][0 + i].equals("O"))  {
                        counter++; // increases counter by one in the case that a coordinate in the board is not 0 or O
                        display[which_row][0 + i] ="D"; // makes the coordinates that were scanned symbolized by letter D
                    }
                } valid_input = true;
            } else if (input.equals("colum") || input.equals("c")) {
                number_allowed_drone -=1; // decreases number of drones by one
                System.out.println("which column would you like to scan. Enter a integer, no decimal place");
                int which_column = scanner.nextInt();
                while (true) {
                    if (which_column >=this.ncols || which_column < 0) {
                        System.out.println("invalid column. try again");
                        which_column = scanner.nextInt();
                    }
                    else {
                        break;
                    }
                }
                for (int i = 0; i < this.ncols; i++) {
                    if (!this.board[0 + i][which_column].equals("0") && !this.board[0 + i][which_column].equals("O")) {
                        counter++;
                        display[0 + i][which_column] ="D"; // makes the coordinates that were scanned symbolized by letter D
                    }
                } valid_input = true;
            } else {
                System.out.println("you entered an  invalid input. try again");
            }
        }

        System.out.println("Drone scanned " + counter + " targets");
        System.out.println();
        System.out.println("After drone usage");
        display();
        return counter;
    } // missile that  fires in a give location that hits all 3 * 3 squared near the location where the missile was fired
    public void missile() {
        turn_number +=1; // increases turns by one
        number_allowed_missle -=1; // decreases the amount of missile allowed to be used by one
        // checks if the user used the missile the maximum amount of times allowed
        if (number_allowed_missle < 0) {
            System.out.println("Missile has been used max amount of times");
            return;
        }
        int iterations = 3; // tracks the number of iteration that method needs to loop through each time to hit every single location
        int x_coordinate = 0;
        int y_coordinate = 0;
        System.out.println("where would you like to launch, Enter integers separated by a space");
        Scanner scanner = new Scanner(System.in);
        x_coordinate = scanner.nextInt() - 1; // x coordinate chosen by the user -1. this is to make iteration through all the location that missile would hit easier
        y_coordinate = scanner.nextInt() -1; // y coordinate chosen by the user - 1
        // checks if the input provided by the user is valid
        while (true) {
            if (x_coordinate + 1 >= this.nrows || y_coordinate + 1 >= this.ncols || x_coordinate + 1 < 0 || y_coordinate + 1 < 0) {
                System.out.println("invalid coordinate. try again");
                x_coordinate = scanner.nextInt() - 1;
                y_coordinate = scanner.nextInt() - 1;
            }
            else {
                break;
            }
        } // fires missile
        for (int i = 0; i < iterations ; i++) {
            missile_Fire(x_coordinate, y_coordinate + i);
        }
        x_coordinate +=1;
        for (int i = 0; i < iterations ; i++) {
            missile_Fire(x_coordinate, y_coordinate + i);
        }
        x_coordinate +=1;
        for (int i = 0; i < iterations ; i++) {
            missile_Fire(x_coordinate, y_coordinate + i);
        }
        System.out.println("After Missile launch");
        display(); // displays the locations where the missile was launched and shows the result of the missile launch
    }
}