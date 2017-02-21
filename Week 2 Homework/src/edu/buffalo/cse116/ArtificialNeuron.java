package edu.buffalo.cse116;

/**
 * Class that defines the functions for a single neuron in an artificial neural
 * network (e.g., an AI algorithm). Since the number of inputs can never change,
 * it is faster (and more logical) for this class to use array than an
 * ArrayList.
 *
 * @author Matthew Hertz
 */
public class ArtificialNeuron {

	/**
	 * Input values for this neuron. For purposes of this homework, we store
	 * these input values directly.
	 */
	private double[] inputs;

	/**
	 * Weights assigned to each of the inputs to this neuron. "Learning"
	 * typically occurs by
	 */
	private double[] weights;

	/**
	 * Creates a new artificial neuron. This will save the provided weights
	 * array and create a new inputs array so that the input array has the same
	 * length as the weights array. Following common practice, the initial value
	 * in the inputs array will be set to 1.
	 *
	 * @param weightings
	 *            Array of weightings to be used by this neuron
	 */
	public ArtificialNeuron(double[] weightings) {
		weights = weightings;
		inputs = new double[weightings.length];
		inputs[0] = 1;
	}

	/**
	 * Assign the input at the index stored in {@code index} to have the value
	 * {@code newValue}.
	 *
	 * @param index
	 *            Index of the input whose value will be updated
	 * @param newValue
	 *            New value for the input being updated.
	 */
	public void setInput(int index, double newValue) {
		inputs[index] = newValue;
	}

	/**
	 * Calculates the "output" of this artificial neuron. This is done by
	 * calculating the weighted sum of the inputs. This is done by looping
	 * through each entry in the input array. At each index, multiply the values
	 * of the input and weighting array at that index and add the result into
	 * the sum. Once this sum is completed, compared that sum to
	 * {@code threshold}. If the sum is greater then return 1. If the sum is
	 * smaller or equal then return -1.
	 *
	 * @param threshold
	 *            Threshold value over which this neuron should "fire".
	 * @return 1 if the neuron fires and -1 if it does not.
	 */
	public int activate(double threshold) {
		double sum = 0.0;
		for (int i = 0; i < inputs.length; i++) {
			sum += inputs[i] * weights[i];
		}
		if (sum > threshold) {
			return 1;
		} else {
			return -1;
		}
	}

}