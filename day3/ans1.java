package day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ans1 {

    public static void main(String[] args) {
        int ans = 0;
        List<String> linesList = null;
        String[] lines = {};
        try {
            linesList = Files.readAllLines(Path.of("inp1.txt"));
            lines = linesList.toArray(new String[0]);
        } catch (Exception e) {
            System.out.println(e);
        }
        for (String line : lines) {
            int max = findMax(line.toCharArray());
            ans += max;
            System.out.println(max);
        }
        System.out.println(ans);
    }

    private static int findMax(char[] chars) {
        int max = 0;
        for(int i = 0; i < chars.length; i++){
            for(int j = i + 1; j < chars.length; j++){
                int curr = Integer.parseInt(chars[i] + "" + chars[j]);
                if(curr > max){
                    max = curr;
                }
            }
        }
        return max;
    }
}
