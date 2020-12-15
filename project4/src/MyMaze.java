// Names:
// x500s:

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public MyMaze(int rows, int cols) {
        this.maze = new Cell[rows][cols];
        for (int i = 0; i <rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new Cell();
            }

        }
    }

    public int getRows(){
        return this.maze.length;
    }

    public  int getCols(){
        return MyMaze.this.maze[0].length;
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze myMaze = new MyMaze(rows,cols);
        Stack1Gen<Index> stack = new Stack1Gen<Index>();
        stack.push(new Index(0, 0));
        myMaze.maze[0][0].setVisited(true);
        myMaze.maze[rows - 1][cols - 1].setRight(false);
        while (stack.isEmpty() == false) {
            Index currentIndex = stack.top();
            ArrayList<Index> neighbors = myMaze.findNeighbors(currentIndex);
            if (neighbors.isEmpty()) {
                stack.pop();
            } else {
                int number = new Random().nextInt(neighbors.size());
                Index neighbor_index = neighbors.get(number);
                myMaze.maze[neighbor_index.getX()][neighbor_index.getY()].setVisited(true);
                stack.push(neighbor_index);
                if (neighbor_index.getDirection() == "TOP") {
                    myMaze.maze[neighbor_index.getX()][neighbor_index.getY()].setBottom(false);
                } else if (neighbor_index.getDirection() == "BOTTOM") {
                    myMaze.maze[currentIndex.getX()][currentIndex.getY()].setBottom(false);
                } else if (neighbor_index.getDirection() == "RIGHT") {
                    myMaze.maze[currentIndex.getX()][currentIndex.getY()].setRight(false);

                } else if (neighbor_index.getDirection() == "LEFT") {
                    myMaze.maze[neighbor_index.getX()][neighbor_index.getY()].setRight(false);

                }

            }

        }
        for (int k = 0; k < myMaze.maze.length; k++) {
            for (int l = 0; l < myMaze.maze[0].length ; l++) {
                myMaze.maze[k][l].setVisited(false);

            }
        }
        return myMaze;
    }


    public ArrayList<Index> findNeighbors(Index currentIndex){
        ArrayList<Index> neighbor_array = new ArrayList<Index>();
            if( currentIndex.getX() - 1 > 0 ){
                Index index = new Index(currentIndex.getX() - 1, currentIndex.getY(), "TOP");
                if (this.maze[index.getX()][index.getY()].getVisited() == false) {
                    neighbor_array.add(index);
                }
            }

            if( currentIndex.getX()  + 1 < this.getRows() ){
                Index index =  new Index( currentIndex.getX() +  1, currentIndex.getY(), "BOTTOM") ;
                if (this.maze[index.getX()][index.getY()].getVisited() == false) {
                    neighbor_array.add(index);
                }

            }

            if( currentIndex.getY()  + 1 < this.getCols() ){
                Index index =  new Index( currentIndex.getX() , currentIndex.getY() + 1, "RIGHT") ;
                if (this.maze[index.getX()][index.getY()].getVisited() == false) {
                    neighbor_array.add(index);
                }
            }
            if( currentIndex.getY()  - 1 > 0 ){
                Index index = new Index( currentIndex.getX() , currentIndex.getY() - 1, "LEFT") ;
                if (this.maze[index.getX()][index.getY()].getVisited() == false) {
                    neighbor_array.add(index);
                }
            }
        return neighbor_array;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze(boolean path) {
        System.out.print("|---".repeat(getCols()) + "|");
        System.out.println();
        for (int i = 0; i <maze.length ; i++) {
            String v = "";
            for (int j = 0; j <maze[0].length ; j++) {
                if (j==0 && i != 0 ) v+= "|"; else if (j == 0 && i == 0) v+=" ";
                if (maze[i][j].getVisited() ) {v+=" * "; } else v+= "   ";
                if (maze[i][j].getRight())v+="|"; else v+=" ";
            }
            if (path == true)System.out.println(v);
            for (int j = 0; j <maze[0].length ; j++) {
                if (j==0 && i != 0 ) System.out.print("|"); else if (j == 0 && i == 0)System.out.print(" ");
                if (maze[i][j].getBottom()) System.out.print("---"); else System.out.print("   ");
                if (maze[i][j].getRight()) System.out.print("|"); else System.out.print(" ");

            }
            System.out.println();

        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        ArrayList<Index> last_array = new ArrayList<Index>();
        boolean over = false;
        Q1Gen queue = new Q1Gen();
        queue.add(new Index(0, 0));
        while (!queue.isEmpty() && over == false) {
            Index start = (Index) queue.remove();
            this.maze[start.getX()][start.getY()].setVisited(true);
            ArrayList<Index> neighbors = this.findNeighbors(start);
            for (Index neighbor: neighbors) {
                if (neighbor.getX() == getRows() -1  && neighbor.getY() == getCols() - 1) {
                    last_array = neighbors;
                    this.maze[neighbor.getX()][neighbor.getY()].setVisited(true);
                    over = true;
                }
                else if (this.maze[neighbor.getX()][neighbor.getY()].getVisited() == false ){
                    String direction = neighbor.getDirection();
                    if (direction == "TOP" && this.maze[neighbor.getX()][neighbor.getY()].getBottom() == false) {
                        queue.add(neighbor);
                    }
                    else if (direction == "BOTTOM" && this.maze[start.getX()][start.getY()].getBottom() == false) {
                        queue.add(neighbor);
                    }
                    else if (direction == "RIGHT" && this.maze[start.getX()][start.getY()].getRight() == false) {
                        queue.add(neighbor);
                    }
                    else if (direction == "LEFT" && this.maze[neighbor.getX()][neighbor.getY()].getRight() == false) {
                        queue.add(neighbor);
                    }

                }

            }


        }
        for (Index neighbor: last_array) {
            if (neighbor.getX() != getRows() - 1 && neighbor.getY() != getCols() - 1) queue.add(neighbor);
        }

    }

    public static void main(String[] args){
        MyMaze maze = makeMaze(5, 35);
        maze.solveMaze();
        maze.printMaze(true);

        /* Any testing can be put in this main function */
    }
}
