/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Segment {
    private int firstPos[] = new int[2];
    private int lastPos[] = new int[2];
    private String direction;
    private int value;
    private int xCount;
    private int oCount;

    public Segment(int[] firstPos, int[] lastPos,State state) {
        this.firstPos= firstPos;
        this.lastPos= lastPos;
        this.direction = checkDirection(firstPos,lastPos);
        this.value = 0;
        this.xCount = 0;
        this.oCount = 0;
        //System.out.println(this);
        this.countPieces(state);
    }
    
    //
    private String checkDirection(int[] firstPos,int[] lastPos){
        if(firstPos[0] == lastPos[0]){
             return "horziontal";
        }
        if(firstPos[1] == lastPos[1]){
             return "vertical";
        }
        return "diagonal";
    }
    
    //counts x and o pieces from the board
    public int[] countPieces(State state){
        int tmp[] = new int[4];
        switch(direction){
            //left to right eval
            case "horizontal":{
                for(int a=0;a<4;a++){
                    int x = firstPos[0];
                    int y = firstPos[1];
                    tmp[a]=state.getBoard()[x][y+a];
                }
                break;
            }
            //top to bottom eval
            case "vertical":{
                for(int a=0;a<4;a++){
                    int x = firstPos[0];
                    int y = firstPos[1];
                    tmp[a]=state.getBoard()[x+a][y];
                }
                break;
            }
            case "diagonal":{
                int fx = firstPos[0];
                int fy = firstPos[1];
                int lx = lastPos[0];
                int ly = lastPos[1];
                for(int a=0;a<4;a++){
                    // this orientation \ bigger x bigger y
                    if(lastPos[0]>firstPos[0] && lastPos[1]>firstPos[1]){
                        tmp[a] = state.getBoard()[fx+a][fy+a];
                    }
                    // this orientation / smaller x bigger y
                    if( lastPos[0] > firstPos[0] && lastPos[1] > firstPos[1]){
                        tmp[a] = state.getBoard()[fx+3-a][fy+a];
                    }
                    //if()
                }
                
                // --\-- top to bottom
                /*if(y<lastPos[1]){
                    for(int a=0;a<4;a++){
                           tmp[a] = state.getBoard()[fx+a][fy+a];
                        }
                }
                else if(firstPos[1]>lastPos[1]){ // --/-- bottom to top
                    for(int a=0;a<4;a++){
                        tmp[a] = state.getBoard()[fx-a][fy+a];
                    }
                }*/
                break;
            }
        }
        for(int a=0;a<4;a++){
            if(tmp[a]==1){
                this.setxCount(this.getxCount()+1);
            }
            if(tmp[a]==0){
                this.setoCount(this.getoCount()+1);
            }
        }
        return tmp;
    }
    
    public int getSegmentValue(){
        return this.value;
    }
    
    //obtains segment utility value
    public void setSegMentValue(){
        if(xCount==4){
            //System.out.println("found an ending segment: "+this);
            value = 512;
        }
        if(oCount==4){
            value = -512;
        }
        if(xCount==0 && oCount==3){
            value = -50;
        }
        if(xCount==0 && oCount==2){
            value = -10;
        }
        if(xCount==0 && oCount==1){
            value = -1;
        }
        if(xCount==0 && oCount==0 || xCount==1 && oCount==1 || xCount==2 && oCount==2){
            value = 0;
        }
        if(xCount==1 && oCount==0){
            value = 1;
        }
        if(xCount==2 && oCount==0){
            value = 10;
        }
        if(xCount==3 && oCount==0){
            value = 50;
        }
    }
    
    
    public void setFirstPos(int[] firstPos) {
        this.firstPos = firstPos;
    }

    public void setLastPos(int[] lastPos) {
        this.lastPos = lastPos;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int[] getFirstPos() {
        return firstPos;
    }

    public int[] getLastPos() {
        return lastPos;
    }

    public String getDirection() {
        return direction;
    }
    
    public int getxCount() {
        return xCount;
    }

    public int getoCount() {
        return oCount;
    }

    public void setxCount(int xCount) {
        this.xCount = xCount;
    }

    public void setoCount(int oCount) {
        this.oCount = oCount;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Segment{" + "firstPos=" + firstPos[0]+" ,"+firstPos[1] + ", xCount=" + xCount + ", oCount=" + oCount + ", lastPos=" + lastPos[0]+" ,"+lastPos[1] + ", direction=" + direction + ", value=" + value + '}';
    }
    
    
}
