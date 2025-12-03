package day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ans2 {
    
    public static void main(String[] args) {
        long ans = 0;
        List<String> linesList = null;
        String[] lines = {};
        try {
            linesList = Files.readAllLines(Path.of("inp1.txt"));
            lines = linesList.toArray(new String[0]);
        } catch (Exception e) {
            System.out.println(e);
        }
        for (String line : lines) {
            long max = findMax(line.toCharArray());
            ans += max;
            System.out.println(max);
        }
        System.out.println(ans);
    }

    private static long findMax(char[] chars) {
        int targetDigits = 12;
        int n = chars.length;
        
        StringBuilder result = new StringBuilder();
        int start = 0;
        
        for (int i = 0; i < targetDigits; i++) {
            int remaining = targetDigits - i;
            int end = n - remaining;
            
            int maxDigit = -1;
            int maxIndex = start;
            for (int j = start; j <= end; j++) {
                int digit = chars[j] - '0';
                if (digit > maxDigit) {
                    maxDigit = digit;
                    maxIndex = j;
                    if (maxDigit == 9) break;
                }
            }
            
            result.append((char)('0' + maxDigit));
            start = maxIndex + 1;
        }
        
        return Long.parseLong(result.toString());
    }
}
