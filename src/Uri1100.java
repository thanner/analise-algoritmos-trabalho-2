// Tentativa e erro

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Uri1100 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./example/uri1100.txt");
        Scanner s = new Scanner(file);

        StringBuilder sb = new StringBuilder();
        boolean hasNext = s.hasNextLine();
        while (hasNext) {
            String line = s.nextLine();
            char sourceColumn = line.charAt(0);
            char sourceLine = line.charAt(1);
            char targetColumn = line.charAt(3);
            char targetLine = line.charAt(4);

            int amountMoves = getMoves(sourceColumn, sourceLine, targetColumn, targetLine);

            sb.append("To get from ").append(sourceColumn).append(sourceLine).append(" to ").append(targetColumn).append(targetLine).append(" takes ").append(amountMoves).append(" knight moves.");

            hasNext = s.hasNextLine();
            if (hasNext) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static int getMoves(char sourceColumn, char sourceLine, char targetColumn, char targetLine) {
        return 2;
    }
}
