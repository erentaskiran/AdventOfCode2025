import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ans2 {
    public static void main(String[] args) {
        int ans=0;
        String[] lines = null;
        int prevAns = 0;
        try{
            List<String> tmp = Files.readAllLines(Paths.get("inp1.txt"));
            lines = tmp.toArray(new String[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
        while (prevAns >=0) {
            for(int i=0; i<lines.length; i++){
                for(int j=0; j<lines[0].length(); j++){
                    if(lines[i].charAt(j) != '@'){
                        continue;
                    }

                    int count = 0;
                    for(int k = i-1; k<=i+1; k++){
                        for(int l = j-1; l<=j+1; l++){
                            if(k == i && l == j){
                                continue;
                            }
                            if(k>=0 && k<lines.length && l>=0 && l<lines[0].length()){
                                if(lines[k].charAt(l) == '@' || lines[k].charAt(l) == 'x'){
                                    count++;
                                }
                            }
                        }
                    }
                    if(count < 4){
                        ans++;
                        lines[i] = lines[i].substring(0, j) + 'x' + lines[i].substring(j + 1);
                    }
                }
            }
            if (ans == prevAns) {
                break;
            }
            prevAns = ans;
            replaceXsWithDots(lines);
        }
        System.out.println(ans);

    }

    public static void replaceXsWithDots(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replace('x', '.');
        }
    }
}
