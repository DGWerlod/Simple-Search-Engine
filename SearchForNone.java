package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SearchForNone extends SearchStrategy {

    @Override
    public HashSet<Integer> getMatches(ArrayList<String> entries, HashMap<String, HashSet<Integer>> lookup, String query) {

        // Negation of SearchForAny's results
        HashSet<Integer> badIndices = new SearchForAny().getMatches(entries, lookup, query);
        HashSet<Integer> matchedIndices = new HashSet<>();
        for (int i = 0; i < entries.size(); i++) {
            if (!badIndices.contains(i)) {
                matchedIndices.add(i);
            }
        }

        return matchedIndices;

    }

}
