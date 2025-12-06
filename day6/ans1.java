import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ans1 {
    
    public static void main(String[] args) {
        String[] lines = null;
        try {
            lines = Files.readAllLines(Paths.get("inp1.txt")).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] grid = new String[lines.length][];
        for (int i = 0; i<lines.length; i++) {
            grid[i] = lines[i].trim().split("\\s+");
        }

        long ans = 0;
        for (int j = 0; j<grid[0].length; j++) {
            char operator = grid[grid.length - 1][j].trim().charAt(0);
            long tmp = operator == '+' ? 0 : 1;
            for (int i = 0; i<grid.length - 1; i++) {
                long num = Long.parseLong(grid[i][j].trim());
                if (operator == '+') {
                    tmp += num;
                } else if (operator == '*') {
                    tmp *= num;
                }
            }
            ans += tmp;
        }
        System.out.println(ans);
    }
}
