package controller;

import java.util.LinkedList;


public class BackAndForth {
    private static LinkedList<String> ribbonState = new LinkedList<>();
    private static int currentIndex = -1;
    private static int maxIndex = -1;

    public static LinkedList<String> getRibbonState() {
        return ribbonState;
    }

    public static void setRibbonState(LinkedList<String> ribbonState) {
        BackAndForth.ribbonState = ribbonState;
    }

    public static void addOnePage(String page) {
        if(currentIndex == maxIndex){
            while (ribbonState.size() > currentIndex + 1) {
                ribbonState.removeLast();
            }
            ribbonState.add(page);
            currentIndex++;
            maxIndex++;
        } else {
            int pagesToRemove = maxIndex - currentIndex;
            for (int i = 0; i < pagesToRemove; i++) {
                ribbonState.removeLast();
            }
            ribbonState.add(page);
            currentIndex++;
            maxIndex = currentIndex;
        }
    }

    public static String getIndexPage() {
        if (currentIndex >= 0 && currentIndex < ribbonState.size()) {
            return ribbonState.get(currentIndex);
        }
        return null;
    }

    public static int getIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int currentIndex) {
        BackAndForth.currentIndex = currentIndex;
    }

    public static String gotoPreviousPage() {
        if (currentIndex > 0) {
            currentIndex--;
            return ribbonState.get(currentIndex);
        } else {
            return null;
        }
    }

    public static String gotoForthPage() {
        if (currentIndex < ribbonState.size() - 1) {
            currentIndex++;
            return ribbonState.get(currentIndex);
        } else {
            return null;
        }
    }


    public static int getMaxIndex() {
        return maxIndex;
    }

    public static void setMaxIndex(int maxIndex) {
        BackAndForth.maxIndex = maxIndex;
    }
    public static String gotoCurrentPage(){
        return ribbonState.get(currentIndex);
    }


}