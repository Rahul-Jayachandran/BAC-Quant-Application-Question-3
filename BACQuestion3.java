import java.io.*;
import java.util.*;

public class BACQuestion3 {
    public static void main(String[] args) {

        //define the array with the number of available values, and the board itself
        int[] num = {0,1,2,2,4,4,7};
        int[][] board = {   {0,0,0,0,5,0,0},
                            {0,0,0,3,0,0,0},
                            {0,0,0,0,0,0,4},
                            {0,1,0,0,0,6,0},
                            {4,0,0,0,0,0,0},
                            {0,0,0,6,0,0,0},
                            {0,0,2,0,0,0,0}};

        //a solved board for reference
        int[] numSol = {0,0,0,0,0,0,0};
        int[][] solution = {{0,5,5,0,5,0,5},
                            {0,0,7,3,6,0,4},
                            {0,7,0,0,2,7,4},
                            {7,1,6,0,0,6,0},
                            {4,0,0,6,7,3,0},
                            {3,0,0,6,0,4,7},
                            {6,7,2,5,0,0,0}};
    
        System.out.println("The unsolved board's result when passed to the validate function: " + validate(board));
        System.out.println("The solved board's result when passed to the validate function: " + validate(solution));
        System.out.println("The method to solve the board takes a bit to run, come back in a while to see the board solved by the boardFinder method!");

        //solve the board and print result
        System.out.println(boardFinder(num, board));
        print(board);

    }

    //recursively finds a solution to the board
    public static boolean boardFinder(int[] num, int[][] board) {

        //checks if all the numbers are in the board, and if so, returns whether or not the board is valid
        boolean complete = true;
        for(int i=0; i<num.length; i++) {
            if(num[i] != 0) {
                complete = false;
            }
        }

        if(complete) {
            return validate(board);
        }

        //checks that each row is still under to 20
        for(int a=0; a<board.length; a++) {
            int sum=0;
            for(int b=0; b<board[a].length; b++) {
                sum+=board[a][b];
            }
            if(sum>20) {
                return false;
            }
        }

        //checks that each column is still under to 20
        for(int b=0; b<board.length; b++) {
            int sum=0;
            for(int a=0; a<board[b].length; a++) {
                sum+=board[a][b];
            }
            if(sum>20) {
                return false;
            }
        }

        //tries all combinations of adding numbers in a DFS
        for(int a=0; a<board.length; a++) {
            for(int b=0; b<board[a].length; b++) {
                if(board[a][b]==0) {
                    for(int i=0; i<num.length; i++) {
                        if(num[i]!=0) {
                            board[a][b] = i+1;
                            num[i] = num[i]-1;
                            if(boardFinder(num,board)) {
                                return true;
                            } else {
                                board[a][b] = 0;
                                num[i] = num[i]+1;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    //validates whether a board with all numbers placed passes the placement requirements
    public static boolean validate (int[][] board) {

        //checks that each row sums to 20
        for(int a=0; a<board.length; a++) {
            int sum=0;
            for(int b=0; b<board[a].length; b++) {
                sum+=board[a][b];
            }
            if(sum!=20) {
                return false;
            }
        }

        //checks that each column sums to 20
        for(int b=0; b<board.length; b++) {
            int sum=0;
            for(int a=0; a<board[b].length; a++) {
                sum+=board[a][b];
            }
            if(sum!=20) {
                return false;
            }
        }
        
        //checks that all the rows with the red numbers have the correct beginning and end number
        for(int a=0; a<board.length; a++) {
            for(int b=0; b<board[a].length; b++) {
                if(a==0) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==5) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==1) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==5) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==3) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==6) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==6) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
            for(int b=board[a].length-1; b>=0; b--) {
                if(a==0) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==5) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==1) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==4) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==5) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(a==6) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==5) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }

        //checks that all the columns with the red numbers have the correct beginning and end number
        for(int b=0; b<board.length; b++) {
            for(int a=0; a<board[b].length; a++) {
                if(b==0) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==1) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==5) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==5) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==6) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==5) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
            for(int a=board[b].length-1; a>=0; a--) {
                if(b==0) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==6) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==1) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==5) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==4) {
                        break;
                    } else {
                        return false;
                    }
                }
                if(b==6) {
                    if(board[a][b]==0) {
                        continue;
                    } else if(board[a][b]==7) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }

        return connected(board);
    }

    //check that each value is connected to the rest using an adjacency matrix and graph theory
    public static boolean connected (int[][] board) {

        //create a new board for operations
        int[][] board2 = new int[7][7];
        for(int a=0; a<board.length; a++) {
            for(int b=0; b<board[a].length; b++) {
                board2[a][b] = board[a][b];
            }
        }

        //change each nonzero value in board2 to a unique number in [1,2,...,21]
        int ind=1;
        for(int a=0; a<board.length; a++) {
            for(int b=0; b<board[a].length; b++) {
                if(board2[a][b]!=0) {
                    board2[a][b] = ind;
                    ind++;
                }
            }
        }

        //define an adjacency matrix for all values in board
        int[][] adj = new int[28][28];

        for(int a=0; a<board2.length; a++) {
            for(int b=0; b<board2[a].length; b++) {
                if(board2[a][b]!=0) {
                    if(a>0) {
                        if(board2[a-1][b]!=0) {
                            adj[board2[a][b]-1][board2[a-1][b]-1] = 1;
                        }
                    }
                    if(a<board2.length-1) {
                        if(board2[a+1][b]!=0) {
                            adj[board2[a][b]-1][board2[a+1][b]-1] = 1;
                        }
                    }
                    if(b>0) {
                        if(board2[a][b-1]!=0) {
                            adj[board2[a][b]-1][board2[a][b-1]-1] = 1;
                        }
                    }
                    if(b<board2[a].length-1) {
                        if(board2[a][b+1]!=0) {
                            adj[board2[a][b]-1][board2[a][b+1]-1] = 1;
                        }
                    }
                }
            }
        }

        //conduct a 'DFS' of sorts on the adjacency matrix to determine if all values are reachable from one value
        int[] reachable = new int[28];
        for(int a=0; a<28; a++) {
            if(adj[0][a]!=0) {
                reachable[a] = 1;
            }
        }
        for(int i=2; i<=28; i++) {
            for(int a=0;a<28; a++) {
                if(reachable[a]==i-1) {
                    for(int b=0; b<28; b++) {
                        if(adj[a][b] != 0) {
                            reachable[b] = i;
                        }
                    }
                }
            }
        }

        for(int i=0; i<reachable.length; i++) {
            if(reachable[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public static void print (int[][] board) {
        for(int a=0; a<board.length; a++) {
            for(int b=0; b<board[a].length; b++) {
                System.out.print(board[a][b] + " ");
            }
            System.out.println();
        }
    }

}