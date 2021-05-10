import java.util.Random;

public class Solution {
    static int attack=0;


    public static EightQuenns[] firstCoordinate(){
        EightQuenns[] board=new EightQuenns[8];
        Random rn=new Random();
        for(int i=0;i<8;i++){
            board[i]=new EightQuenns(rn.nextInt(8),i);
        }
        return board;
    }

    public static void printBoard(EightQuenns[] b){

        int [][] tempboard=new int[8][8];
        for(int i=0;i<8;i++){
            tempboard[b[i].getRow()][b[i].getColumn()]=1;
        }
        //to print....
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(tempboard[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int findAttack(EightQuenns[] current){
        int attack=0;
        for(int i=0;i< current.length;i++){
            for(int j=i+1;j< current.length;j++){// neighboughr squre
                if(current[i].isAttack(current[j])){
                    attack++;
                }
            }
        }

        return attack;
    }

    public static EightQuenns[] targetBoard(EightQuenns[] state){
        EightQuenns[] nextBoard=new EightQuenns[8];
        EightQuenns[] tempBoard=new EightQuenns[8];
        int attackNumber=findAttack(state);
        int minAttack=attackNumber;
        int temp;


        for(int i=0;i<8;i++){
            nextBoard[i]=new EightQuenns(state[i].getRow(),state[i].getColumn());
            tempBoard[i]=nextBoard[i];
        }
        //for each column
        for(int i=0;i<8;i++){
            if(i>0){
                tempBoard[i-1]=new EightQuenns(state[i-1].getRow(),state[i-1].getColumn());
            }
            tempBoard[i]=new EightQuenns(0,tempBoard[i].getColumn());
            /////
            for(int j=0;j<8;j++){
                temp=findAttack(tempBoard);
                if(temp<minAttack){
                    minAttack=temp;
                    for(int k=0;k<8;k++){
                        nextBoard[k]=new EightQuenns(tempBoard[k].getRow(),tempBoard[k].getColumn());
                    }
                }
                if(tempBoard[i].getRow()!=7){
                    tempBoard[i].move();
                }
            }
        }
        //check local optimum;
        //Check whether the present bord and the best board found have same heuristic
        //Then randomly generate new board and assign it to best board
        if(minAttack==attackNumber){
            nextBoard=firstCoordinate();
            attack=findAttack(nextBoard);

        }
        else{
            attack=minAttack;
        }
        return nextBoard;
    }

    public static void main(String[] Args){
        System.out.println("deneme");

        int currentAttack;

        EightQuenns[] currentBoard=firstCoordinate();
        currentAttack=findAttack(currentBoard);
        while(currentAttack!=0){
            currentBoard=targetBoard(currentBoard);
            currentAttack=attack;
        }
        printBoard(currentBoard);
    }
}
