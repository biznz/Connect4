
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
    static int depth=4;
    public static void main(String[] args) {
        // TODO code application logic here
        int option1=-1;
        int option2=-1;
        int input=-1;
        int input2=-1;
        human = new Player("human");
        cpu = new Player("cpu");
        finished = false;
        in = new Scanner(System.in);
        while(option1==-1){
            System.out.println("Please Select who plays first");
            System.out.println("1.cpu : O");
            System.out.println("2.you : X");
            if(in.hasNext()){
                input = in.nextInt();
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
                if(input==1 || input ==2){
                    while(option2==-1){
                        System.out.println("Please choose cpu approach");
                        System.out.println("1. minimax");
                        System.out.println("2. alpha-beta-pruning");
                        if(in.hasNext()){
                            input2 = in.nextInt();
                            if(input2==1 || input2==2){
                                option2 = input;
                                current = new State();
                                //current.setMove(new Move(currentPlayer,null));
                                setMaximumDepth(depth);
                                if(input2 == 1){
                                    System.out.println("Chosen minimax");
                                }
                                if(input2 == 2){
                                    System.out.println("Chosen alpha-beta-pruning");
                                }
                            }
                        }
                    }
                }
            }
        }
        while(!finished && input2==1){
            System.out.println(current);
            if(option1==1){
                if(getCurrentPlayer().getWho().equals("human")){if(playCPU(0)){System.out.println("CPU wins!!");break;}}
                else{if(playHuman()){
//                    System.out.println(current);
                    System.out.println("You win!!");break;}
                }
            }
            if(option1==2){if(getCurrentPlayer().getWho().equals("cpu")){if(playHuman()){System.out.println("You win!!");break;}}
                else{if(playCPU(0)){
//                    System.out.println(current);
                    System.out.println("CPU wins!!");break;}}
            }
        }
        while(!finished && input2==2){
            System.out.println(current);
            if(option1==1){
                if(getCurrentPlayer().getWho().equals("human")){
                    if(playCPU(1)){
                        
                        System.out.println("CPU wins!!");
                        break;
                    }
                }
                else{
                    if(playHuman()){
//                        System.out.println(current);
                        System.out.println("You win!!");
                        break;
                    }
                }
            }
            if(option1==2){
                if(getCurrentPlayer().getWho().equals("cpu")){
                    if(playHuman()){
                        System.out.println("You win!!");
                        break;
                    }
                }
                else{
                    if(playCPU(1)){
                        System.out.println("CPU wins!!");
                        break;
                    }
                }
            }
        }
        
    }
    
    //does a cpu move
    private static boolean playCPU(int i){
//        System.out.println(current);
//        System.out.println("UTILITY: "+MinMax.UTILITY(current));
        Move move=null;
        long endtime=-1;
        long starttime = System.nanoTime();
        if(i==0){
            move = MinMax.MINMAX_DECISION(current);
            endtime = System.nanoTime();
        }
        if(i==1){
            move = MinMax.ALPHA_BETA_SEARCH(current);
            endtime = System.nanoTime();
        }
        long total_time = endtime-starttime;
        System.out.println("The total runtime:"+total_time);
        State newState = new State(current.getBoard(),move,current.getDepth()+1);
        setCurrentState(newState);
        setCurrentPlayer(cpu);
//        System.out.println(current);
        if(MinMax.UTILITY(current) == 512){System.out.println(current);return true;}
        setMaximumDepth(current.getDepth()+depth);
//        System.out.println(current.getValidPosAsString());
        return false;
    }
    
    //does a human move
    private static boolean playHuman(){
        int result[]=promptUserForMove();
        State newState = new State(current.getBoard(),new Move(human,result),current.getDepth()+1);
        setCurrentState(newState);
        setCurrentPlayer(human);
//        System.out.println(current);
        if(MinMax.UTILITY(current) == -512){System.out.println(current);return true;}
//        System.out.println("UTILITY: "+MinMax.UTILITY(current));
        setMaximumDepth(current.getDepth()+depth);
        //if(MinMax.UTILITY(current) == -512){return true;}
//        setMaximumDepth(current.getDepth()+depth);
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
        MinMax.depthLimit = s;
    }
    
}
