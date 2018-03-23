
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
    private LinkedList<int[]> list;
    private Set<State> childStates;

    
    public State(int[][] board, Move move, int depth) {
        this.board = copyBoard(board);
        this.move = move;
        this.depth = depth;
        this.full = isFull();
        this.segments=buildSet();
//        System.out.println(move);
        this.insertMove(this.board, this.move);
        this.list = buildFreePos(this.board);
//        System.out.println(this);
//        this.printSegmentSet();
        //System.out.println("THE UTILITY"+MinMax.UTILITY(this));
//        System.out.println(this.segments.size());
//        System.out.println(this.getValidPosAsString());
    }
    
    //inserts an already defined move in a board matrix
    public void insertMove(int[][] board,Move move){
        switch(move.getPlayer().getWho()){
            case "human":{
                this.board[move.getPosition()[0]][move.getPosition()[1]]=1;
                break;
            }
            case "cpu":{
                this.board[move.getPosition()[0]][move.getPosition()[1]]=0;
                break;
            }
        }
    }
    
    //builds the list of free positions in a board
    public LinkedList<int[]> buildFreePos(int[][] board){
        LinkedList<int[]> result = new LinkedList<int[]>();
        for(int s=0;s<6;s++){
            for(int h=0;h<7;h++){
                if(board[s][h]!=-1){
                    if(s-1>=0){
                        if(board[s-1][h]==-1){
                            int[] pos = new int[2];
                            pos[0] = s-1;
                            pos[1] = h;
                            result.add(pos);
                        }
                    }
                }
                else{
                    if(s==5){
                        int[] pos = new int[2];
                        pos[0] = s;
                        pos[1] = h;
                        result.add(pos);
                    }
                }
            }
        }
        return result;
    }

    public State(){
        this.board = new int[6][7];
        this.list = new LinkedList<int[]>();
        for(int a=0;a<6;a++){
            for(int h=0;h<7;h++){
                this.board[a][h] = -1;
            }
        }
        this.move = null;
        this.depth = 0;
        this.full = false;
        this.segments = buildSet();
        for(int a=0;a<7;a++){
            int[] available_pos = new int[2];
            available_pos[0]=5;
            available_pos[1]=a;
            this.list.add(available_pos);
        }
        
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
    
    //checks if board is full
    // sets full param to true if so
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
    
    
    public void setChildren(Set<State> children){
        this.childStates = children;
    }
    
    public Set<State> getChildren(){
        return this.childStates;
    }
    
    public Set<Segment> getSegments(){
        return this.segments;
    }
    
    //prints the board segments
    public void printSegmentSet(){
        if(this.segments.isEmpty())return;
        for(Segment s:this.segments){
            System.out.println(s);
        }
        System.out.println("number of segments:"+this.segments.size());
    }
    
    //builds the board segments
    public Set<Segment> buildSet(){
        HashSet<Segment> temp = new HashSet<Segment>();
        Segment seg = null;
        int f=0;
        //verticais
        for(int k=0;k<6-3;k++){
            for(int l=0;l<7;l++){
                    f++;
                    int a[] = {k,l};
                    int b[] = {k+3,l};
                    seg = new Segment(a,b,this);
                    temp.add(seg);
            }
        }
        for(int s=0;s<6;s++){
            for(int h=0;h<7;h++){
                if(h+3<7){
                    //horizontais
                    int a[] = {s,h};
                    int b[] = {s,h+3};
                    seg = new Segment(a,b,this);
                    temp.add(seg);
                }
                // // diagonals
                if(s-3>=0 && h+3<7){
                    int a[] = {s,h};
                    int b[] = {s-3,h+3};seg = new Segment(a,b,this);
                    temp.add(seg);
                // \\ diagonals
                }
                if(h+3<7 && s+3<6){
                    int c[] = {s,h};
                    int d[] = {s+3,h+3};
                    seg = new Segment(c,d,this);
                    temp.add(seg);
                }
            }
        }
        return temp;
    }
    
    //prints the board
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
            result+=" ^";
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

    public LinkedList<int[]> getValidPos(){
        return this.list;
    }
    
    public String getValidPosAsString(){
        String result="valid Pos: ";
        int counter=0;
        for(int[] s:this.list){
            counter+=1;
            result+=""+counter+"- x: "+s[0]+",1:"+s[1]+"| ";
        }
        return result;
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
