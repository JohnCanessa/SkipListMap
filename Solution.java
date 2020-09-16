import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Experiment with the ConcurrentSkipListMap class in java.
 */
public class Solution {


    /**
     * Look up the key in the specified list map.
     * If not found look for the next largest key and return the value.
     * If not found, return the value associated with the lowest key.
     */
    static int getNextValue(int randKey, ConcurrentSkipListMap<Integer, Integer> slm) {

        // **** display the random key ****
        System.out.println("getNextValue <<< randKey: " + randKey);

        // ***** check if in the list map OR use the next larger key ****
        Integer nextKey = slm.ceilingKey(randKey);

        // **** no larger key found  ****
        if (nextKey == null) {

            // **** display a message ****
            System.out.println("getNextValue <<< nextKey is null");

            // **** go for the first key in the list map ****
            nextKey = slm.ceilingKey(0);
        }

        // **** display the values ****
        System.out.println("getNextValue <<< nextKey: " + nextKey);

        // **** return the value associated with this key ****
        return slm.get(nextKey);
    }


    /**
     * Test scaffolding
     */
    public static void main(String[] args) {

        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read number of keys and maximum value ****
        String[] strs = sc.nextLine().split(",");

        // **** close scanner ****
        sc.close();

        // **** ****
        int K = Integer.parseInt(strs[0]);
        int V = Integer.parseInt(strs[1]);

        // **** ****
        System.out.println("main <<< K: " + K + " V: " + V);

        // **** instantiate list map ****
        ConcurrentSkipListMap<Integer, Integer> slm = new ConcurrentSkipListMap<Integer, Integer>();

        // **** to generate random integers for the keys and values ****
        Random rand = new Random();

        // **** loop populating the list map ****
        for (int i = 0; i < K; i++) {

            // **** generate random key:value pair ****
            int key     = rand.nextInt(K);
            int value   = rand.nextInt(V);

            // **** display key:value pair ****
            System.out.println("main <<< i: " + i + " (" + key + "," + value + ")");

            // **** put the pair in the list map ****
            slm.put(key, value);
        }

        // **** display the list map ****
        System.out.println("main <<< slm.size: " + slm.size());
        System.out.println("main <<<      slm: " + slm.toString());
        System.out.println();

        // **** check if the list maps match ****
         System.out.println("main <<<   equals: " + slm.equals(slm));
         System.out.println("main <<<   equals: " + slm.equals(new ConcurrentSkipListMap<Integer, Integer>()));
         System.out.println();

        // **** generate a random key ****
        int randKey = rand.nextInt(K);

        // **** get associated value ****
        int nextValue = getNextValue(randKey, slm);

        // **** display the result ****
        System.out.println("main <<<  randKey: " + randKey + " nextValue: " + nextValue);
        System.out.println();

        // **** remove some values from the list map ****
        for (int i = 0; i < K / 2; i++) {

            // **** get the first key ****
            int key = slm.firstKey();

            // **** remove the key ****
            int removed = slm.remove(key);

            // **** display the key ****
            System.out.println("main <<<  removed: " + removed);
        }

        // **** display the list map ****
        System.out.println("main <<<      slm: " + slm.toString());
        System.out.println();

        // **** remove the rest of the keys in the list map ****
        while (!slm.isEmpty()) {

            // **** key the largest key in the list map ****
            Integer key = slm.lowerKey(K);
            if (key == null)
                break;

            // **** remove the key ****
            int removed = slm.remove(key);

            // **** display the key ****
            System.out.println("main <<<  removed: " + removed);
        }

        // **** display the list map ****
        System.out.println("main <<< slm.size: " + slm.size());
        System.out.println("main <<<      slm: " + slm.toString());
        
    }

}