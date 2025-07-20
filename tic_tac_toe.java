import java.util.*;
public class tic_tac_toe {
    public static void main(String[] args) {
        // tic tac toe
        char board[][]=new char[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=' ';
            }
        }
        char player='X';
        boolean gameend=false;
        Scanner s=new Scanner(System.in);
        while(!gameend)
        {
            printboard(board);
            System.out.println("Player "+player+"enter row and column");
            int row=s.nextInt();
            int col=s.nextInt();
            if(board[row][col]==' ')
            {
                board[row][col]=player;
                gameend=havewon(board,player);
                if(gameend)
                {
                    System.out.println("player "+player +" has won the game");
                }
                else{
                    player=(player=='X')?'O':'X';
                }
            }
            else{
                System.out.println("Invalid move try again");
            }
        }
    }
    public static boolean havewon(char board[][],char player)
    { for(int i=0;i<3;i++)
    {
        if(board[i][0]==player && board[i][1]==player && board[i][2]==player)
        {
            return true;
        }
    }
        for(int j=0;j<3;j++)
        {
            if(board[0][j]==player && board[1][j]==player && board[2][j]==player)
            {
                return true;
            }
        }
        if(board[0][0]==player && board[1][1]==player && board[2][2]==player)
        {
            return true;
        }
        if(board[0][2]==player && board[1][1]==player && board[2][0]==player)
        {
            return true;
        }
        return false;

    }
    public static void printboard(char board[][])
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
        }
    }



}