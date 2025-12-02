import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class ans1 {
    private static final int DIAL_START = 50;
    private static final int DIAL_SIZE = 100;

    private static int wrapMod(int value, int mod) {
        return ((value % mod) + mod) % mod;
    }

    public static void main(String[] args) throws IOException {
        int dial = DIAL_START;
        int password = 0;
        List<String> linesList = Files.readAllLines(Path.of("inp1.txt"));
        String[] lines = linesList.toArray(new String[0]);

        for (String line : lines) {
            int steps = Integer.parseInt(line.substring(1));
            char turn = line.charAt(0);
            int step = (turn == 'R') ? steps : -steps;

            dial = wrapMod(dial + step, DIAL_SIZE);
            if (dial == 0) {
                password++;
            }
        }

        System.out.println(password);
        return;
    }
}