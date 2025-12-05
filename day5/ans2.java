import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ans2 {
    public static void main(String[] args) {
        String[] lines = null;
        try {
            List<String> tmp = Files.readAllLines(Paths.get("inp1.txt"));
            lines = tmp.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<long[]> ranges = new ArrayList<long[]>();
        long ans = 0;
        for (String line : lines) {
            if(line.trim().isEmpty()){
                break;
            }

            String[] parts = line.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            compoundRanges(ranges,start, end);
        }
        for (long[] range : ranges) {
            ans += (range[1] - range[0] + 1);
        }
        System.out.println(ans);
    }

    public static void compoundRanges(List<long[]> ranges, long start, long end) {
        long newStart = start;
        long newEnd = end;
        
        List<long[]> toRemove = new ArrayList<>();
        for (long[] range : ranges) {
            if (!(newEnd < range[0] || newStart > range[1])) {
                newStart = Math.min(newStart, range[0]);
                newEnd = Math.max(newEnd, range[1]);
                toRemove.add(range);
            }
        }
        
        ranges.removeAll(toRemove);
        
        ranges.add(new long[]{newStart, newEnd});
    }
}
