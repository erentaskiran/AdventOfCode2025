import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ans1 {
    public static void main(String[] args) {
        String[] lines = null;
        try {
            List<String> tmp = Files.readAllLines(Paths.get("inp1.txt"));
            lines = tmp.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<long[]> ranges = new ArrayList<long[]>();
        boolean isRange = true;
        long ans = 0;
        for (String line : lines) {
            if(line.trim().isEmpty()){
                isRange = false;
                continue;
            }

            if (isRange) {
                String[] parts = line.split("-");
                long start = Long.parseLong(parts[0]);
                long end = Long.parseLong(parts[1]);
                ranges.add(new long[]{start, end});
            }else{
                long num = Long.parseLong(line.trim());
                boolean inRange = false;
                for (long[] range : ranges) {
                    if (num >= range[0] && num <= range[1]) {
                        inRange = true;
                        break;
                    }
                }
                if (inRange) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
