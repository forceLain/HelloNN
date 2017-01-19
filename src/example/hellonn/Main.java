package example.hellonn;

import java.util.*;

public class Main {

    /**
     * Simple bitmap 3x5 px representation of every digit composed into a single row with MOD = 3
     * For instance, 0 is<br/>
     * <code>
     * 111<br/>
     * 101<br/>
     * 101<br/>
     * 101<br/>
     * 111<br/>
     * </code>
     * <br/>
     * 4 is<br/>
     * <code>
     * 101<br/>
     * 101<br/>
     * 111<br/>
     * 101<br/>
     * 001<br/>
     * </code>
     */
    private static final Map<Integer, int[]> bitmaps = new HashMap<>();

    static {
        bitmaps.put(0, new int[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1});
        bitmaps.put(1, new int[]{0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1});
        bitmaps.put(2, new int[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1});
        bitmaps.put(3, new int[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1});
        bitmaps.put(4, new int[]{1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1});
        bitmaps.put(5, new int[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1});
        bitmaps.put(6, new int[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1});
        bitmaps.put(7, new int[]{1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1});
        bitmaps.put(8, new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1});
        bitmaps.put(9, new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1});
    }

    public static void main(String[] args) {

        //LEARNING
        //Make 10 neurons and teach each of them a single digit
        List<Neuron> network = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Neuron neuron = new Neuron();
            learnForDigit(neuron, i);
            network.add(neuron);
        }

        //USING
        for (int i = 0; i < 10; i++) {
            int[] bitmap = bitmaps.get(i);
            int result = -1;
            //ask every neuron if it can detect a digit
            for (Neuron neuron : network) {
                int proceed = neuron.proceed(bitmap);
                if (proceed == 1) {
                    result = network.indexOf(neuron);
                    break;
                }
            }
            System.out.println("Number is: " + i + "; Answer is: " + result);
        }

        //TRY A CORRUPTED BITMAP
        int[] corrupted5 = new int[]{1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1};
        int result = -1;
        for (Neuron neuron : network) {
            int proceed = neuron.proceed(corrupted5);
            if (proceed == 1) {
                result = network.indexOf(neuron);
                break;
            }
        }
        System.out.println("Corrupted number is: 5; Answer is: " + result);
    }

    private static void learnForDigit(Neuron neuron, int digit) {
        Random random = new Random();
        int size = bitmaps.size();
        for (int i = 0; i < 100000; i++) {
            int randomNumber = random.nextInt(size);
            int[] bitmap = bitmaps.get(randomNumber);
            int proceed = neuron.proceed(bitmap);
            if (proceed == 1 && randomNumber != digit) {
                neuron.learn(bitmap, false);
            } else if (proceed == 0 && randomNumber == digit) {
                neuron.learn(bitmap, true);
            }
        }

    }
}
