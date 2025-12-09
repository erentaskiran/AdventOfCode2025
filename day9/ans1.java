import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ans1 {
    public static void main(String[] args) {
        String[] lines = null;
        try {
            lines = Files.readAllLines(Paths.get("input.txt")).toArray(new String[0]);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        BigInteger maxArea = BigInteger.ZERO;
        for (int i = 0; i < lines.length; i++) {
            for(int j = 0; j < lines.length; j++) {
                String[] dimensions1 = lines[i].split(",");
                int x1 = Integer.parseInt(dimensions1[0]);
                int y1 = Integer.parseInt(dimensions1[1]);
                String[] dimensions2 = lines[j].split(",");
                int x2 = Integer.parseInt(dimensions2[0]);
                int y2 = Integer.parseInt(dimensions2[1]);
            
                BigInteger area = BigInteger.valueOf(Math.abs(x1-x2) + 1).multiply(BigInteger.valueOf(Math.abs(y1-y2) + 1));
                System.out.println("Area between (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ") is: " + area);
                if(area.compareTo(maxArea) > 0) {
                    maxArea = area;
                }
            }
        }
        System.out.println(maxArea);
    }
}
