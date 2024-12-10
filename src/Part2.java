import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
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

            // check every + and * combination and ||
            // make it not start at 1 maybe
            sum += (calculate(0, nums, 0, result)) ? result : 0;
        }
        System.out.println(sum);
    }

    // // im stupid again
    // public static long calcString(String problemRaw) {
    //     if(problemRaw.isBlank()) return 0;
    //     String problem = new String(problemRaw.replaceAll("\\|", ""));
    //     if(problemRaw.charAt(0) == '+' || problemRaw.charAt(0) == '*') {
    //         problem = problem.substring(1);
    //     }
    //     ArrayList<Integer> nums = new ArrayList<>();
    //     long solution = 0;
    //     String number = "";

    //     for (int i = 0; i < problem.toCharArray().length; i++) {
    //         char c = problem.toCharArray()[i];
    //         if(c == '|') {

    //             continue;
    //         }
    //         if(c == '*') {
    //             if(solution == 0) solution = 1;
    //             solution *= Long.parseLong(number);
    //             continue;
    //         }
    //         if(c == '+') {
    //             solution += Long.parseLong(number);
    //             continue;
    //         }
    //         number += Character.toString(c);
    //     }
    //     return solution;
    // }
    // // why it doesnt work with 7290

    public static boolean calculate(long prevCalc, ArrayList<Long> nums, int currIndex, long goal) {
        if(currIndex >= nums.size()) {
            boolean val = prevCalc == goal;
            if(val) System.out.println("Works with " + goal);
            return val;
        }

        long nextNum = nums.get(currIndex);
        if(calculate(prevCalc + nextNum, nums, currIndex + 1, goal)) {
            return true;
        }
        if(prevCalc == 0) {
            prevCalc = 1;
        if(calculate(prevCalc * nextNum, nums, currIndex + 1, goal)) return true;
        prevCalc = 0;
        } else {
            if(calculate(prevCalc * nextNum, nums, currIndex + 1, goal)) return true;

        }

            if(goal == 7290) {
                System.out.println("Prev: " + prevCalc + "\tnextNum: " + nextNum);
            }
            //System.out.println("Num1: " + nums.get(currIndex) + "\tNum2: " + nums.get(currIndex + 1) + "\tGoal: " + goal);

            if(prevCalc == 0 && calculate(nextNum, nums, currIndex + 1, goal)) return true;
            // check with this too
            if(calculate(Long.parseLong(prevCalc + "" + nextNum), nums, currIndex + 1, goal)) {
                //System.out.println("Works");
                return true;
            }

        // Check + and * and ||
        return false;
    }
}