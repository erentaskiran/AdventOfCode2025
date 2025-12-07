import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


class ans2 {
    public static void main(String[] args) throws IOException {
        char[][] lines = null;
        lines = Files.lines(Paths.get("./input.txt")).map(String::toCharArray).toArray(char[][]::new);


        long[][] dp = new long[lines.length][lines[0].length];

        for(int i = 0; i < lines[0].length; i++){
            if(lines[0][i] == 'S') dp[0][i] = 1;
        }

        for(int i = 1; i < lines.length; i++){
            for(int j = 0; j < lines[0].length; j++){

                if(lines[i][j] == '^'){
                    if(j-1 >= 0) dp[i][j-1] += dp[i-1][j];
                    if(j+1 < lines[0].length) dp[i][j+1] += dp[i-1][j];
                }
                if(dp[i-1][j] > 0 && lines[i][j] != '^') dp[i][j] += dp[i-1][j];
            }
        }

        long sum = 0;
        for(int i = 0; i < dp[0].length; i++){
            sum += dp[dp.length-1][i]; 
        }

        System.out.println(sum);
    }
}