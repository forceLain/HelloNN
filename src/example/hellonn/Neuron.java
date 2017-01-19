package example.hellonn;

public class Neuron {

    private static final int INPUT_COUNT = 15;
    private static final int BIAS = 7;

    private final int[] weights = new int[INPUT_COUNT];

    public int proceed(int[] input) {
        int net = adder(input);
        return fun(net);
    }

    public void learn(int[] input, boolean increase) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 1) {
                if (increase) weights[i]++;
                else weights[i]--;
            }
        }
    }

    private int adder(int[] input) {
        int net = 0;
        for (int i = 0; i < input.length; i++) {
            int wi = input[i] * weights[i];
            net += wi;
        }
        return net;
    }

    private int fun(int net) {
        return net > BIAS ? 1 : 0;
    }

}
