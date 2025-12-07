import java.nio.file.Files;
import java.nio.file.Paths;

class ans1 {
    public static void main(String[] args) {
        char[][] lines = null;
        try {
            lines = Files.lines(Paths.get("input.txt"))
                         .map(String::toCharArray)
                         .toArray(char[][]::new);
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        int start = 0;
        for(int i = 0; i < lines[0].length; i++) {
            if(lines[0][i] == 'S'){
                start = i;
                break;
            }
        }

        int ans = 0;
        lines[1][start]='|';
        for (int i = 2; i < lines.length; i++) {
            for(int j = 0; j < lines[0].length; j++) {
                if(lines[i-1][j] == '|'){
                    if(lines[i][j] == '^'){
                        ans++;
                        if(j-1>=0){
                            lines[i][j-1]='|';
                        }

                        if(j+1<lines[0].length){
                            lines[i][j+1]='|';
                        }
                    }else{
                        lines[i][j]='|';
                    }
                }
            }
        }
        printArray(lines);

        System.out.println(ans);
    }
    private static void printArray(char[][] arr) {
        for (char[] row : arr) {
            System.out.println(new String(row));
        }
    }
}