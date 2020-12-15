// Written by Phanx289 & Benkh008
import java.util.Scanner;
// this is where the main method is. run the program from here.
public class BattleBoatsGame {
    static private BattleboatsBoard playing_board = new BattleboatsBoard(0,0);
    public static void main(String[] args) {
        System.out.println("enter which mode you want. type standard for a standard board or expert for an expert board");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // checks whether the input given by the user is valid
        while (true) {
            // sets a standard board if the user chooses standard
            if (input.equals("standard")) {
                System.out.println("standard board has 5 boats");
                BattleboatsBoard stanboard = new BattleboatsBoard(8, 8); // creates a new BattleboatsBoard objectstandard
                stanboard.standard(); // initializes it to standard
                playing_board = stanboard; // makes the playing board the standard board that was just initialized
                while (stanboard.is_Over() == false) {
                    // calls the method play_game to start the battle ship game while the number of boats remaining is greater than zero
                    BattleBoatsGame.play_game();
                }
                int total_tunrs = stanboard.getTurn_number() - 1; // decreases the total turns by 1 to get the right number since turn number was initialized to 1 not zero
                // prints the number of turns the game took and the number of cannon shots fired
                System.out.println("your number of tunrs is " + total_tunrs + " and your total number of shots is " + stanboard.getShots_fired());
                break;

            } else if (input.equals("expert")) {
                System.out.println("the expert board has 10 boats");
                // sets an expert board if the user chooses expert
                BattleboatsBoard experboard = new BattleboatsBoard(12, 12); // creates a new BattleboatsBoard object
                playing_board = experboard; // initialized the board to an expert board
                experboard.expert();
                while (experboard.is_Over() == false) {
                    // calls the play game method that runs the game while the number of remaining boards is greater than zero
                    BattleBoatsGame.play_game();
                }
                int total_tunrs = experboard.getTurn_number() - 1; // decreases the total turns by 1 to get the right number since turn number was initialized to 1 not zero
                // prints the number of turns the game took and the number of cannon shots fired
                System.out.println("your number of tunrs is " + total_tunrs + " and your total number of shots is " + experboard.getShots_fired());
                break;

            } else {
                System.out.println("you entered a wrong mode, try again");
                input = scanner.nextLine();
            }
        }
    }
    // method that runs the game
    public static void play_game () {
        System.out.println("type in fire to fire a cannon shot, missile to use a missile,  drone to use a drone, and print to print the board");
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();
        // checks whether user wants to fire a cannon shot, use drone or missile, or print the board
        if (user_input.equals("fire")) {
            System.out.println("enter the  coordinates you want to fire at. Make sure to enter two integers (no decimal numbers). put a space between the integers ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            playing_board.fire(x, y);
        }
        else if (user_input.equals("drone")) {
            playing_board.drone();
        }
        else if (user_input.equals("missile")) {
            playing_board.missile();
        }
        else if (user_input.equals("print")) {
            playing_board.print();
        }
        else {
            System.out.println("entered a wrong command try again");
        }

    }
}
