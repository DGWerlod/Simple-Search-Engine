package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0 || !args[0].equals("--data")) {
            throw new IllegalArgumentException("Missing required command line argument: --data");
        } else if (args.length != 2 || args[1] == null) {
            throw new IllegalArgumentException("Missing required qualifier for command line argument: --data");
        }

        File source = new File(args[1]);
        try (Scanner file = new Scanner(source)) {
            Scanner scan = new Scanner(System.in);

            ArrayList<String> entries = new ArrayList<>();
            while (file.hasNextLine()) {
                entries.add(file.nextLine().trim());
            }

            HashMap<String, HashSet<Integer>> invertedIndex = new HashMap<>();
            for (int i = 0; i < entries.size(); i++) {
                String[] tokens = entries.get(i).split(" ");
                for (String token : tokens) {
                    token = token.toLowerCase(); // case insensitivity!
                    HashSet<Integer> indices = invertedIndex.getOrDefault(token, new HashSet<>());
                    indices.add(i); // add current entry as an instance of occurrence
                    invertedIndex.put(token, indices);
                }
            }

            while (true) {

                System.out.println("\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit");
                int selection = Integer.parseInt(scan.nextLine().trim());
                System.out.println();

                if (selection == 0) {
                    break;
                }

                switch (selection) {
                    case 1:
                        System.out.println("Select a matching strategy: ANY, ALL, NONE");
                        String strategyChoice = scan.nextLine().trim();

                        MatchFinder finder = new MatchFinder();
                        if ("ALL".equalsIgnoreCase(strategyChoice)) {
                            finder.setStrategy(new SearchForAll());
                        } else if ("ANY".equalsIgnoreCase(strategyChoice)) {
                            finder.setStrategy(new SearchForAny());
                        } else if ("NONE".equalsIgnoreCase(strategyChoice)) {
                            finder.setStrategy(new SearchForNone());
                        } else {
                            System.out.println("Invalid; try again.");
                            break;
                        }

                        System.out.println("\nEnter a name or email to search all suitable people.");
                        String query = scan.nextLine().trim();
                        System.out.println();
                        finder.printMatches(entries, invertedIndex, query);
                        break;
                    case 2:
                        System.out.println("=== List of people ===");
                        for (String entry : entries) {
                            System.out.println(entry);
                        }
                        break;
                    default:
                        System.out.println("Incorrect option! Try again.");
                        break;
                }

            }

            System.out.println("Bye!");
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.print("File not found: ");
            e.printStackTrace();
        }

    }

}

/*
        HashSet<Integer> foundIndices = lookup.get(query);
        if (foundIndices == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(foundIndices.size() + " person(s) found:");
            for (int index : foundIndices) {
                System.out.println(entries.get(index));
            }
        }
 */