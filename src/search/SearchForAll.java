package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SearchForAll extends SearchStrategy {

    @Override
    public HashSet<Integer> getMatches(ArrayList<String> entries, HashMap<String, HashSet<Integer>> lookup, String query) {

        // Begin assuming all indices are matched
        HashSet<Integer> matchedIndices = new HashSet<>();
        for (int i = 0; i < entries.size(); i++) {
            matchedIndices.add(i);
        }

        // For each query, retain only those that match
        String[] queryTokens = query.split(" ");
        for (String s : queryTokens) {
            HashSet<Integer> newMatches = lookup.get(s);
            if (newMatches != null) {
                matchedIndices.retainAll(newMatches);
            }
        }

        return matchedIndices;

    }

}
