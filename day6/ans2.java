import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ans2 {
    public static void main(String[] args) {
           String[] lines = null;
        try {
            lines = Files.readAllLines(Paths.get("inp1.txt")).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxLen = 0;
        for (String line : lines) {
            if (line.length() > maxLen) {
                maxLen = line.length();
            }
        }
        String[] padded = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            padded[i] = String.format("%-" + maxLen + "s", lines[i]);
        }

        long ans = 0;
        List<Long> numbersInProblem = new ArrayList<>();
        Character currentOp = null;

        for (int col = maxLen - 1; col >= 0; col--) {
            boolean isSeparator = true;
            for (String row : padded) {
                if (row.charAt(col) != ' ') {
                    isSeparator = false;
                    break;
                }
            }

            if (isSeparator) {
                if (!numbersInProblem.isEmpty() && currentOp != null) {
                    ans += evaluate(numbersInProblem, currentOp);
                    numbersInProblem.clear();
                    currentOp = null;
                }
                continue;
            }

            char opChar = padded[padded.length - 1].charAt(col);
            if (opChar == '+' || opChar == '*') {
                if (currentOp == null) {
                    currentOp = opChar;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < padded.length - 1; row++) {
                char ch = padded[row].charAt(col);
                if (ch != ' ') {
                    sb.append(ch);
                }
            }

            if (sb.length() > 0) {
                numbersInProblem.add(Long.parseLong(sb.toString()));
            }
        }

        if (!numbersInProblem.isEmpty() && currentOp != null) {
            ans += evaluate(numbersInProblem, currentOp);
        }

        System.out.println(ans);
    }

    private static long evaluate(List<Long> numbers, char op) {
        long result = (op == '+') ? 0 : 1;
        for (long n : numbers) {
            if (op == '+') {
                result += n;
            } else {
                result *= n;
            }
        }
        return result;
    }
}
