
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
    static boolean finished;
    static State current;
    public static void main(String[] args) {
        // TODO code application logic here
        int option1=-1;
        int option2=-1;
        Player human = new Player("human");
        Player cpu = new Player("cpu");
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
                    }
                    if(input==2){
                        startingPlayer = human;
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
                            }
                        }
                       
                    }
                }
            }
        }
        while(!finished){
            if(option1==1){
                System.out.println(current);
                current.printSegmentSet();
                break;
            }
            if(option1==2){
            }
        }
        
    }
}
