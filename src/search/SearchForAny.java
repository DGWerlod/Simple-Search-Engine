package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SearchForAny extends SearchStrategy {

    @Override
    public HashSet<Integer> getMatches(ArrayList<String> entries, HashMap<String, HashSet<Integer>> lookup, String query) {

        // Begin assuming no indices are matched
        HashSet<Integer> matchedIndices = new HashSet<>();

        // For each query, unison with every match
        String[] queryTokens = query.split(" ");
        for (String s : queryTokens) {
            HashSet<Integer> newMatches = lookup.get(s);
            if (newMatches != null) {
                matchedIndices.addAll(newMatches);
            }
        }

        return matchedIndices;

    }

}
