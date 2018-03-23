
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Game {
    
    static Scanner in;
    static Player startingPlayer;
    static Player currentPlayer;
    static boolean finished;
    static State current;
    static Player human;
    static Player cpu;
    static int depth=5;
    public static void main(String[] args) {
        // TODO code application logic here
        int option1=-1;
        int option2=-1;
        human = new Player("human");
        cpu = new Player("cpu");
        finished = false;
        in = new Scanner(System.in);
        while(option1==-1){
            System.out.println("Please Select who plays first");
            System.out.println("1.cpu");
            System.out.println("2.you");
            if(in.hasNext()){
                int input = in.nextInt();
                if(input==1 || input==2){
                    option1 = input;
                    if(input==1){
                        startingPlayer = cpu;
                        currentPlayer = human;
                    }
                    if(input==2){
                        startingPlayer = human;
                        currentPlayer = cpu;
                    }
                }
                if(input==1){
                    while(option2==-1){
                        System.out.println("Please choose cpu approach");
                        System.out.println("1. minmax");
                        System.out.println("2. alpha beta pruning");
                        if(in.hasNext()){
                            int input2 = in.nextInt();
                            if(input2==1 || input2==2){
                                option2 = input;
                                current = new State();
                                setMaximumDepth(5);
                            }
                        }
                    }
                }
            }
        }
        while(!finished){
            if(option1==1){
                if(getCurrentPlayer().getWho().equals("human")){
                    playCPU();
                }
                else{
                    playHuman();
                }
            }
            if(option1==2){
            }
        }
        
    }
    
    //does a cpu move
    private static boolean playCPU(){
        System.out.println(current);
        System.out.println(current.getValidPosAsString());
        Move move = MinMax.MINMAX_DECISION(current);
        State newState = new State(current.getBoard(),move,current.getDepth()+1);
        setCurrentState(newState);
        setCurrentPlayer(cpu);
        setMaximumDepth(5);
        System.out.println(current.getValidPosAsString());
        return false;
    }
    
    //does a human move
    private static boolean playHuman(){
        System.out.println(current);
        int result[]=promptUserForMove();
        State newState = new State(current.getBoard(),new Move(human,result),current.getDepth()+1);
        setCurrentState(newState);
        setCurrentPlayer(human);
        setMaximumDepth(5);
        return false;
    }
    
    
    private static void setCurrentState(State state){
        current = state;
    }
    
    private static void setCurrentPlayer(Player player){
        currentPlayer = player;
    }
    
    private static Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    public static int[] promptUserForMove(){
        int option1=-1;
        int[] pos = null;
        while(option1==-1){
            System.out.println("Your turn, choose a valid column where to place a piece");
            //System.out.println(current);
            if(in.hasNext()){
                int input = in.nextInt();
                for(int[] s: current.getValidPos()){
                    if(s[1]==input-1){
                        return s;
                    }
                }
            }
        }
        //System.out.println("playing to option"+option1);
        return pos;
    }
    
    private static void setMaximumDepth(int s){
        depth = s;
        MinMax.depthLimit = current.getDepth()+depth;
    }
    
}
