/**
 * Classname : Main
 *
 *
 * @author Natalie Zviertseva , NTU "KhPI"
 *
 * 1. Parse the file logs.txt (see the attachment).
 * Extract to a separate file all the logs.
 * 2. Calculate the total number of logs (lines).
 * 3. Calculate the total  number of  ERROR logs.
 * Use previous skills - String.split
 * 4.  Calculate the total number of ERROR logs. Use Files.lines.
 * 5. Compare two results.
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {

        //-----Task1---------
        String text = new String(Files
                .readAllBytes(Paths.get("logs.txt")));

        String logs = text
                .replaceAll("\\.", "")
                .replaceAll(",", "")
                .replaceAll("!", "")
                .replaceAll("\\?", "")
                .replaceAll("-", "")
                .replaceAll("-", " ")
                .replaceAll("—", " ")
                .replaceAll(";", "")
                .replaceAll(":", "")
                .replaceAll("  ", " ")
                .toLowerCase();

        // Extract to a separate file all the logs.
        String[] logsLines = logs.split("\\n");

        //-------Task2--------
        System.out.println("Total number of logs: " + logsLines.length);

        //-------Task3--------
        LocalDateTime startSplit = LocalDateTime.now();

        int errorNumber = 0;
        for (int i = 0; i < logsLines.length; i++) {
            if (logsLines[i].contains("error"))
                errorNumber++;
        }
        System.out.println("\n"
                + "Use string.split \n"
                + "Total number of error: " + errorNumber);

        LocalDateTime finishSplit = LocalDateTime.now();

        //------Task4---------
        LocalDateTime startFiles = LocalDateTime.now();

        int countErrors = (int) Files.lines(Paths.get("logs.txt"))
                .filter(line -> line.contains("error"))
                .count();
        System.out.println("\n"
                + "Use Files.lines \n"
                + "Total number of error: " + countErrors);

        LocalDateTime finishFiles = LocalDateTime.now();

        //-------Task5---------
        long durationSplit = ChronoUnit.MILLIS
                .between(startSplit, finishSplit);
        long durationFiles= ChronoUnit.MILLIS
                .between(startFiles, finishFiles);

        System.out.println("\n"
                + "Compare two results. \n"
                + (durationFiles < durationSplit
                ? "Use Files.lines" : "Use String.split") + " was faster.\n"
                + " Files.lines time = " + durationFiles + "\n"
                + " String.split time = " + durationSplit + "\n");
    }
}
