import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String inputFile1 = "src/input1.txt";
        String inputFile2 = "src/input2.txt";
        String mergedFile = "src/merged.txt";
        String commonFile = "src/common.txt";

        try {
            Set<Integer> set1 = readIntegersFromFile(inputFile1);
            Set<Integer> set2 = readIntegersFromFile(inputFile2);

            Set<Integer> commonSet = new HashSet<>(set1);
            commonSet.retainAll(set2);

            writeIntegersToFile(set1, mergedFile);
            writeIntegersToFile(commonSet, commonFile);

            System.out.println("Merging completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while merging the files: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer format in the input files: " + e.getMessage());
        }
    }

    private static Set<Integer> readIntegersFromFile(String fileName) throws IOException, NumberFormatException {
        Set<Integer> integers = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                integers.add(Integer.parseInt(line));
            }
        }

        return integers;
    }

    private static void writeIntegersToFile(Set<Integer> integers, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer number : integers) {
                writer.write(number.toString());
                writer.newLine();
            }
        }
    }
}

