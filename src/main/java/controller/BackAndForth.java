package controller;

import java.util.LinkedList;

public class BackAndForth {
    private static LinkedList<String> ribbonState = new LinkedList<>();
    private static int maxIndex = 0;
    private static int index = 0;

    public static LinkedList<String> getRibbonState() {
        return ribbonState;
    }

    public static void setRibbonState(LinkedList<String> ribbonState) {
        BackAndForth.ribbonState = ribbonState;
    }
    public static void addOnePage(String page){
//        if(index!=maxIndex){
//            for(int i=index;i<maxIndex;i++){
//                ribbonState.pop();
//                //heki qato perpara qe jen kan
//            }
//        }
        ribbonState.add(page);
        index++;
        maxIndex++;

    }
    public static String getIndexPage(){
        return ribbonState.get(index);
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        BackAndForth.index = index;
    }
    public static String gotoPreviousPage(){
        if(index>0) {
            index--;
           return ribbonState.get(index);
        }else{
            return ribbonState.get(0);
        }
    }
    public static String gotoForthPage(){
        index++;
        if(!(index >=ribbonState.size())) {
            return ribbonState.get(index);
        }
        return null;
    }

    public static int getMaxIndex() {
        return maxIndex;
    }
}
