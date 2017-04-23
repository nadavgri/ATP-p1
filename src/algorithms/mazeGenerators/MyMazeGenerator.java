package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zaks on 11/04/2017.
 * MyMazeGenerator, extends AMazeGenerator
 * based on Prim's Randomly maze generator algorithm
 * 1. create maze full of walls
 * 2. pick random start position on the border
 * 3. add to list all its unvisited(walls) neighbours
 * 4. while list isnt empty randomly pick from the list a position
 * 4.1 if position has only one passage neighbour
 * 4.1.1 add to list all its unvisited neighbours
 * 4.1.2 turn the picked position to a passage
 * 4.2 remove picked position from list
 */

//Randomized Prim's algorithm - 0 passage - visited, 1 wall , 2 unvisited
public class MyMazeGenerator extends AMazeGenerator {
    public Maze generate(int col, int row) {
        ArrayList<Position> list = new ArrayList<>();
        int[][] build = new int[col][row];
        for (int i = 0; i < col; i++)
            for (int j = 0; j < row; j++)
                build[i][j] = 1;

        Position start = randomPos(col, row);
        build[start.getX()][start.getY()] = 0;

        //filling list with surrounding walls
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (build[start.getX() + j][start.getY() + i] == 1 && !(i!=0 && j!=0)) {
                        int listCount = 0;
                        if (list.isEmpty())
                            list.add(new Position(start.getX() + j, start.getY() + i));
                        else {
                            for (int k = 0; k < list.size(); k++) {
                                if (list.get(k).getX() == start.getX() + j && list.get(k).getY() == start.getY() + i)
                                    listCount++;
                            }
                            if (listCount == 0)
                                list.add(new Position(start.getX() + j, start.getY() + i));
                        }
                    }
                } catch (Exception e) {
                    // out of bound
                    continue;
                }
            }
        }

        //while list isnt empty, pick from the list a position with only one passage-neighbour and add its neighbours
        while (!list.isEmpty()) {
            Random sy = new Random();
            int i = sy.nextInt(list.size());
            int count = 0;
            //count amount of passage neighbours surround the picked position
            try {
                if (build[list.get(i).getX()][list.get(i).getY() - 1] == 0) {
                    count++;
                }
            } catch (Exception e) {
                // out of bound.
                System.out.print("");
            }
            try {
                if (build[list.get(i).getX()][list.get(i).getY() + 1] == 0) {
                    count++;
                }
            } catch (Exception e) {
                // out of bound.
                System.out.print("");

            }
            try {
                if (build[list.get(i).getX() + 1][list.get(i).getY()] == 0) {
                    count++;
                }
            } catch (Exception e) {
                // out of bound.
                System.out.print("");

            }
            try {
                if (build[list.get(i).getX() - 1][list.get(i).getY()] == 0) {
                    count++;
                }
            } catch (Exception e) {
                // out of bound.
                System.out.print("");

            }
            if (count == 1) {
                Position pos = new Position(list.get(i).getX(), list.get(i).getY());
                for (int l = -1; l <= 1; l++) {
                    for (int j = -1; j <= 1; j++) {
                        try {

                            if (build[pos.getX() + j][pos.getY() + l] == 1) {
                                if (list.isEmpty())
                                    list.add(new Position(pos.getX() + j, pos.getY() + l));
                                else {
                                    int listCount = 0;
                                    for (int k = 0; k < list.size(); k++) {
                                        if (list.get(k).getX() == pos.getX() + j && list.get(k).getY() == pos.getY() + l) {
                                            listCount++;
                                            break;
                                        }
                                    }
                                    if (listCount == 0)
                                        list.add(new Position(pos.getX() + j, pos.getY() + l));
                                }

                            }
                        } catch (Exception e) {
                            // out of bound
                            System.out.print("");

                        }
                    }
                }
                //turn the picked position to a passage
                build[pos.getX()][pos.getY()] = 0;
            }
            //remove picked position from the list
            list.remove(i);
        }
        //creates a randomly exit
        Position goal = randomPos(col, row);
        while (start.getX() == goal.getX() && start.getY() == goal.getY()) {
            goal = randomPos(col, row);
        }
        build[goal.getX()][goal.getY()] = 0;

        //construct the maze
        Maze m = new Maze(start, goal, build);

        return m;
    }
}