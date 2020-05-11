package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class SearchStrategy {

    public abstract HashSet<Integer> getMatches(ArrayList<String> entries,
                                                HashMap<String, HashSet<Integer>> lookup, String query);

}
