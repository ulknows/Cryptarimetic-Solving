import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExhaustiveSPMultiOP {

    static Map<Character, Integer> characterNum = new HashMap<>();
    static Map<Character, Integer> tempAddToList = new HashMap<>();
    static int time = 1;
    static ArrayList<Map<Character, Integer>> allResult = new ArrayList<>();
    static ArrayList<Character> listCha = new ArrayList<>();

    static ArrayList<String> listInput = new ArrayList<>();
    static ArrayList<String> resultIP = new ArrayList<>();

    void runLoop() {
        Scanner input = new Scanner(System.in);
        String word;
        do {
            word = input.next();
            if (!word.equals("-1")) {
                listInput.add(word);
                input(word);
            }
        } while (!word.equals("-1"));
        // System.out.println("TS : " + listInput.toString());
        if (listCha.size() > 10) {
            System.out.println("can't calculate");
            return;
        }
        permutations("1234567890");
        System.out.println("All unique character : " + listCha.toString());
        System.out.println("[Character : number] " + characterNum.size() + " => " + characterNum.toString() + "\n");
        System.out.println("All result : " + allResult.size() + "\n");

        System.out.println(allResult.get(0).toString());

        for (int i = 0; i < allResult.size(); i++) {
            resultIP = new ArrayList<>();
            // System.out.println(allResult.size() + " | " + listInput.size() + " | " +
            // listInput.toString());
            for (int j = 0; j < listInput.size(); j++) {
                resultIP.add(convertBackToInt(allResult.get(i), listInput.get(j)));
                // System.out.println("all "+ allResult.get(i)+" list "+ listInput.get(i)+" >
                // "+convertBackToInt(allResult.get(i), listInput.get(j)));
            }

            boolean check0 = true;
            for (int j = 0; j < resultIP.size(); j++) {
                if (resultIP.get(j).charAt(0) != '0') {
                    // System.out.println(resultIP.toString());
                    check0 = true;
                } else {
                    check0 = false;
                }
            }

            if (check0) {
                int temp = 0;
                for (int j = 0; j < resultIP.size() - 1; j++) {
                    // System.out.println(resultIP.get(j));
                    temp += Integer.parseInt(resultIP.get(j));
                }

                if (temp == Integer.parseInt(resultIP.get(resultIP.size() - 1))) {
                    for (int j = 0; j < resultIP.size(); j++) {
                        System.out.println();
                        printWord(listInput.get(j), listInput.get(j).length());
                        printWord(resultIP.get(j), resultIP.get(j).length());
                    }
                }
            }

        }

    }

    // input until -1
    void input(String word) {
        convertToMap(word);
    }

    private void reset() {
        characterNum.clear();
        tempAddToList.clear();
        time = 1;
        allResult.clear();
        listCha.clear();
    }

    private void printWord(String word, int times) {
        // printWord(times);
        for (int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i) + " ");
        }
        System.out.println("");
    }

    private void printWord(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("  ");
        }
    }

    private String convertBackToInt(Map<Character, Integer> maps, String word) {
        String temp = "";

        for (int j = 0; j < word.length(); j++) {
            // System.out.println(maps.toString());
            temp += String.valueOf(maps.get(word.charAt(j)));
        }

        return temp;
    }

    private static void convertToMap(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!characterNum.containsKey(word.charAt(i))) {
                if (time > 9) {
                    characterNum.put(word.charAt(i), 0);
                    listCha.add(word.charAt(i));
                } else {
                    characterNum.put(word.charAt(i), time++);
                    listCha.add(word.charAt(i));
                }
            }
        }
    }

    public void permutations(String str) {
        permutation("", str);
    }

    int timeSet = 0;

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == (10 - characterNum.size())) {
            tempAddToList = new HashMap<>();
            // System.out.println(prefix);

            for (int i = 0; i < listCha.size(); i++) {
                tempAddToList.put(listCha.get(i), Integer.parseInt(prefix.charAt(i) + ""));
            }

            allResult.add(tempAddToList);
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }
}

class mainSPT {
    public static void main(String[] args) {
        test2 esp = new test2();
        esp.runLoop();
    }
}
