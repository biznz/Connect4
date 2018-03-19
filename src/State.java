
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class State {
    private int[][] board;
    private Move move;
    private int depth;
    private int utility;
    private boolean full;
    private Set<Segment> segments;

    
    public State(int[][] board, Move move, int depth) {
        this.board = copyBoard(board);
        this.move = move;
        this.depth = depth;
        this.full = isFull();
        this.segments=buildSet();
    }

    public State(){
        this.board = new int[6][7];
        for(int a=0;a<6;a++){
            for(int h=0;h<7;h++){
                this.board[a][h] = -1;
            }
        }
        this.move = null;
        this.depth = 0;
        this.full = false;
        this.segments = buildSet();
    }
    
    //copies a array board to another one
    public int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[6][7];
        for(int i=0;i<6;i++){
            for(int s=0;s<7;s++){
                newBoard[i][s] = board[i][s];
            }
        }
        return newBoard;
    }
    
    public void setFull(){
        this.full = true;
        for(int i=0;i<6;i++){
            for(int s=0;s<7;s++){
                if(this.board[i][s] == -1){
                    this.full = false;
                }
            }
        }
    }
    
    public void printSegmentSet(){
        if(this.segments.isEmpty())return;
        for(Segment s:this.segments){
            System.out.println(s);
        }
        System.out.println("number of segments:"+this.segments.size());
    }
    
    public Set<Segment> buildSet(){
        HashSet<Segment> temp = new HashSet<Segment>();
        Segment seg = null;
        int f=0;
        for(int k=0;k<6-3;k++){
            for(int l=0;l<7;l++){
                    f++;
                    int a[] = {k,l};
                    int b[] = {k+3,l};
                    seg = new Segment(a,b);
                    temp.add(seg);
            }
        }
        for(int s=0;s<6;s++){
            for(int h=0;h<7;h++){
                if(h+3<7){
                    int a[] = {s,h};
                    int b[] = {s,h+3};
                    seg = new Segment(a,b);
                    temp.add(seg);
                }
                // // diagonals
                if(s-3>=0 && h+3<7){
                    int a[] = {s,h};
                    int b[] = {s-3,h+3};seg = new Segment(a,b);
                    temp.add(seg);
                // \\ diagonals
                }
                if(h+3<7 && s+3<6){
                    int c[] = {s,h};
                    int d[] = {s+3,h+3};
                    seg = new Segment(c,d);
                    temp.add(seg);
                }
            }
        }
        return temp;
    }
    
    public String printBoard(){
        String result="";
        for(int a=0;a<6;a++){
            for(int h=0;h<7;h++){
                if(this.board[a][h]==-1)result+=" _";
                if(this.board[a][h]==1)result+=" X";
                if(this.board[a][h]==0)result+=" O";
                if(h==6)result+="\n";
            }
        }
        result+="\n";
        for(int a=1;a<=7;a++){
            result+=" "+a;
        }
        result+="\n";
        return result;
    }
    
    public boolean isFull(){
        setFull();
        return this.full;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
    
    public int[][] getBoard() {
        return board;
    }

    public Move getMove() {
        return move;
    }

    public int getDepth() {
        return depth;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "State{" + "board=\n" + printBoard() + ", move=" + move + ", depth=" + depth + '}';
    }
    
    
}
