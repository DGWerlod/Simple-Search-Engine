package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MatchFinder {

    private SearchStrategy s;

    public void setStrategy(SearchStrategy s) {
        this.s = s;
    }

    public void printMatches(ArrayList<String> entries, HashMap<String, HashSet<Integer>> invertedIndex, String query) {
        HashSet<Integer> foundIndices = s.getMatches(entries, invertedIndex, query);
        if (foundIndices == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(foundIndices.size() + " person(s) found:");
            for (int index : foundIndices) {
                System.out.println(entries.get(index));
            }
        }
    }

}