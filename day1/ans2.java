import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class ans2 {
    private static final int DIAL_START = 50;
    private static final int DIAL_SIZE = 100;

    private static int wrapMod(int value, int mod) {
        return ((value % mod) + mod) % mod;
    }

    private static int countZeroCrossings(int start, int steps) {
        if (steps == 0) return 0;
        
        int count = 0;
        int current = start;
        int direction = (steps > 0) ? 1 : -1;
        int absSteps = Math.abs(steps);
        
        for (int i = 0; i < absSteps; i++) {
            current = wrapMod(current + direction, DIAL_SIZE);
            if (current == 0) {
                count++;
            }
        }
        return count;
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

            password += countZeroCrossings(dial, step);
            
            dial = wrapMod(dial + step, DIAL_SIZE);
        }

        System.out.println(password);
        return;
    }
    
}
