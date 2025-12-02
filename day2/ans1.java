import java.nio.file.Files;
import java.nio.file.Path;

public class ans1 {

    static long ans;
    public static void main(String[] args) {
        String data = "";
        ans = 0;
        try {
            data = Files.readString(Path.of("inp1.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }
        for(String range : data.split(",")){
            String[] rangeSplit = range.split("-");
            System.out.println(rangeSplit[0] + " " + rangeSplit[1]);
            long start = Long.parseLong(rangeSplit[0]);
            long end = Long.parseLong(rangeSplit[1]);
            findSequence(start, end);
        }

        System.out.println(ans);

    }

    private static void findSequence(long start, long end) {
        for(long i = start; i <= end; i++){
            if(isSequence(i)){
                ans+=i;
            }
        }
    } 

    private static boolean isSequence(long num) {
        String numStr = Long.toString(num);
        int len = numStr.length();
        if(len % 2 != 0) return false;
        String firstHalf = numStr.substring(0, len/2);
        String secondHalf = numStr.substring(len/2);
        return firstHalf.equals(secondHalf);
    }
}
