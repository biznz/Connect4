
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author b1z
 */
public class MinMax {
    //the algorithm generates all descendants
    // problemas de busca nao é sabido onde esta a soluçao
    // recursao rebenta pilhas
    
    //a maior profundidade maior a probabilidade de ter resultados
    // adversarial search nao procura soluçao
    public static int negativeInf = (int)Double.NEGATIVE_INFINITY;
    public static int positiveInf = (int)Double.POSITIVE_INFINITY;
    private static int[] win = {1,positiveInf};
    private static int[] defeat = {-1,negativeInf};
    public static int depthLimit;
    
    
    
    public static Move ALPHA_BETA_SEARCH(State state){
        int v = MAX_VAL(state,negativeInf,positiveInf);
        return getMove(v, state);
    }
    
    public static int MAX_VAL(State state,int alfa,int beta){
        if(TERMINAL_TEST(state)){
            return UTILITY(state);
        }
        int v = negativeInf;
        for(State s:SUCCESSOR(state)){
            v = Math.max(v, MIN_VAL(s,alfa,beta));
            if(v>=beta){state.setUtility(v);return v;}
            alfa = Math.max(alfa, v);
        }
        state.setUtility(v);
        return v;
    }
    
    public static int MIN_VAL(State state,int alfa,int beta){
        if(TERMINAL_TEST(state)){
            return UTILITY(state);
        }
        int v = positiveInf;
        for(State s:SUCCESSOR(state)){
            v = Math.min(v, MAX_VAL(s,alfa,beta));
            if(v<=alfa){return v;}
            beta = Math.min(beta, v);
        }
        state.setUtility(v);
        return v;
    }
    
    // min max algorithm
    public static Move MINMAX_DECISION(State state){
        int v = MAX_VALUE(state);
        return getMove(v, state);
    }
    
    public static int MAX_VALUE(State state){
        if(TERMINAL_TEST(state)){
            return UTILITY(state);
        }
        int v = negativeInf;
        for(State s:SUCCESSOR(state)){
            v = Math.max(v, MIN_VALUE(s));
        }
        state.setUtility(v);
        return v;
    }
    
    public static int MIN_VALUE(State state){
        if(TERMINAL_TEST(state)){
            return UTILITY(state);
        }
        int v = positiveInf;
        for(State s:SUCCESSOR(state)){
            v = Math.min(v, MAX_VALUE(s));
        }
        state.setUtility(v);
        return v;
    }
    
    public static boolean TERMINAL_TEST(State state){
        if(state.isFull()){state.setUtility(UTILITY(state));return true;}
        if(state.getDepth()!=0){
            if(state.getDepth()>=depthLimit){state.setUtility(UTILITY(state));return true;}
        }
        if(Math.abs(UTILITY(state))==512){
            state.setUtility(UTILITY(state));
            return true;}
        return false;
    }
    
    public static Set<State> SUCCESSOR(State state){
        HashSet<State> children = new HashSet<State>();
        Move move = null;
        if(TERMINAL_TEST(state)){
            return children;
        }
        for(int[] s:state.getValidPos()){
            if(state.getMove()==null){
                if(Game.currentPlayer.getWho().equals("human")){
                    move = new Move(Game.cpu,s);
                }
                if(Game.currentPlayer.getWho().equals("cpu")){
                    move = new Move(Game.human,s);
                }
            }
            else{
                if(state.getMove().getPlayer().getWho().equals("human")){
                    move = new Move(Game.cpu,s);
                }
                if(state.getMove().getPlayer().getWho().equals("cpu")){
                    move = new Move(Game.human,s);
                }
            }
            State newState = new State(state.getBoard(),move,state.getDepth()+1);
            children.add(newState);
            state.addChild(newState);
        }
        return state.getChildren();
    }
    
    public static int UTILITY(State state){
        int total = 0;
        for(Segment s:state.getSegments()){
            s.setSegMentValue();
            int current = s.getSegmentValue();
            if(Math.abs(current)==512){
                return current;
            }
            total+=current;
        }
        if(state.getMove()!=null){
            if(state.getMove().getPlayer().getWho().equals("cpu")){total+=16;}
            if(state.getMove().getPlayer().getWho().equals("human")){total+=-16;}
        }
        return total;
    }
    
    public static Move getMove(int v,State state){
        for(State s: state.getChildren()){
            if(s.getUtility()==v){
                return s.getMove();
            }
        }
        return null;
    }
}
