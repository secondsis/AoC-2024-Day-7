import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));

        long sum = 0;
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] parts = line.split(" ");

            long result = Long.parseLong(parts[0].substring(0, parts[0].length()-1));

            ArrayList<Long> nums = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                nums.add(Long.parseLong(parts[i]));
            }

            // check every + and * combination
            sum += (calculate(nums.get(0), nums, 1, result)) ? result : 0;
        }
        System.out.println(sum);
    }

    // bruh im stupid
//    public static long calculateString(String problem) {
//        if(problem.isBlank()) return 0;
//        String[] parts = problem.split("\\+");
//        long result = 0;
//        for(String part : parts) {
//            if(part.isBlank()) continue;
//            String[] numbersToMultiply = part.split("\\*");
//            long multResult = 1;
//            for(String num : numbersToMultiply) {
//                if(num.isBlank()) continue;
//                multResult *= Long.parseLong(num);
//            }
//            result += multResult;
//        }
//        return result;
//    }

    public static boolean calculate(long prevCalc, ArrayList<Long> nums, int currIndex, long goal) {
        if(currIndex >= nums.size()) {
            return prevCalc == goal;
        }

        // Check + and *
        if(calculate(prevCalc + nums.get(currIndex), nums, currIndex + 1, goal)) return true;
        return calculate(prevCalc * nums.get(currIndex), nums, currIndex + 1, goal);
    }
}