import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExhaustiveSP {

    static Map<Character, Integer> characterNum = new HashMap<>();
    static Map<Character, Integer> tempAddToList = new HashMap<>();
    static int time = 1;
    ArrayList<Map<Character, Integer>> allResult = new ArrayList<>();
    static ArrayList<Character> listCha = new ArrayList<>();

    static ArrayList<String> listInput = new ArrayList<>();

    void run() {
        Scanner input = new Scanner(System.in);
        input(input.next(), input.next(), input.next());
        input.close();
    }

    void input(String firstWord, String secondWord, String lastWord) {
        String valueW1 = "";
        String valueW2 = "";
        String valueW3 = "";

        convertToMap(firstWord);
        convertToMap(secondWord);
        convertToMap(lastWord);
        permutations("1234567890");

        System.out.println("All unique character : " + listCha.toString());
        System.out.println("[Character : number] => " + characterNum.toString() + "\n");

        if (listCha.size() > 10) {
            System.out.println("can't calculate");
            return;
        }

        System.out.println("All result : " + allResult.size() + "\n");

        for (int i = 0; i < allResult.size(); i++) {
            int range = 10;

            valueW1 = convertBackToInt(allResult.get(i), firstWord);
            valueW2 = convertBackToInt(allResult.get(i), secondWord);
            valueW3 = convertBackToInt(allResult.get(i), lastWord);

            // System.out.println(allResult.get(i).toString());
            if (valueW1.charAt(0) != '0' && valueW2.charAt(0) != '0' && valueW3.charAt(0) != '0') {
                if (Integer.parseInt(valueW1) + Integer.parseInt(valueW2) == Integer.parseInt(valueW3)) {
                    for (int j = 0; j < range + (valueW1.length() * 2) - 1; j++) {
                        System.out.print("-");
                    }

                    System.out.println();
                    printWord(firstWord, range - valueW1.length());
                    printWord(valueW1, range - valueW1.length());
                    System.out.println();
                    printWord(secondWord, range - valueW2.length());
                    printWord(valueW2, range - valueW2.length());
                    System.out.println();
                    printWord(lastWord, range - valueW3.length());
                    printWord(valueW3, range - valueW3.length());

                    for (int j = 0; j < range + (valueW1.length() * 2) - 1; j++) {
                        System.out.print("-");
                    }
                    System.out.println();
                }
            }
        }

        reset();
    }

    private void reset() {
        characterNum.clear();
        tempAddToList.clear();
        time = 1;
        allResult.clear();
        listCha.clear();
    }

    private void printWord(String word, int times) {
        printWord(times);
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
            temp += maps.get(word.charAt(j));
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

    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == (10 - characterNum.size())) {
            tempAddToList = new HashMap<>();

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

class mainSP {
    public static void main(String[] args) {
        ExhaustiveSP eht = new ExhaustiveSP();
        eht.run();
    }
}
