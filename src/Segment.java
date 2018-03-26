/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Segment implements Comparable{
    private int firstPos[] = new int[2];
    private int lastPos[] = new int[2];
    private String direction;
    private int value;
    private int xCount;
    private int oCount;
    private int[] tmpvals = new int[4];

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
             return "horizontal";
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
                    if( lastPos[0] < firstPos[0] && firstPos[1]<lastPos[1]){
                        tmp[a] = state.getBoard()[fx-a][fy+a];
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
            if(tmp[a]==6){
                this.setxCount(this.getxCount()+1);
            }
            if(tmp[a]==0){
                this.setoCount(this.getoCount()+1);
            }
        }
        tmpvals = tmp;
        return tmp;
    }
    
    public int getSegmentValue(){
        return this.value;
    }
    
    public String TmpVals(){
        String result="[";
        for(int s=0;s<4;s++){
            result+=tmpvals[s]+",";
        }
        result+="]";
        return result;
    }
    
    //obtains segment utility value
    public void setSegMentValue(){
        if(this.xCount==4){
            //System.out.println("found an ending segment: "+this);
            this.value = -512;
        }
        if(this.oCount==4){
            this.value = 512;
        }
        if(this.xCount==0 && this.oCount==3){
            this.value = 50;
        }
        if(this.xCount==0 && this.oCount==2){
            this.value = 10;
        }
        if(this.xCount==0 && this.oCount==1){
            this.value = 1;
        }
        if(this.xCount==1 && this.oCount==0){
            this.value = -1;
        }
        if(this.xCount==2 && this.oCount==0){
            this.value = -10;
        }
        if(this.xCount==3 && this.oCount==0){
            this.value = -50;
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
        return "Segment{" + "firstPos=" + firstPos[0]+" ,"+firstPos[1] + ", xCount=" + xCount + ", oCount=" + oCount + ""+TmpVals()+", lastPos=" + lastPos[0]+" ,"+lastPos[1] + ", direction=" + direction + ", value=" + value + '}';
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
