package fr.guehenneux.probability;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            the node type, should implement properly hashcode and equals methods
 */
public class ProbabilityNode<T> {

	private T data;
	private Map<ProbabilityNode<T>, Double> children;

	/**
	 * @param data
	 *            the node data
	 */
	public ProbabilityNode(T data) {

		this.data = data;
		children = new HashMap<>();
	}

	/**
	 * @return the node inner value
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the node children with its probability
	 */
	public Map<ProbabilityNode<T>, Double> getChildren() {
		return children;
	}

	/**
	 * @param child
	 *            a child to add
	 * @param probability
	 *            the probability to reach the child from this node
	 */
	public void addChild(ProbabilityNode<T> child, double probability) {
		children.put(child, probability);
	}

	/**
	 * @param data
	 *            a data
	 * @return the probability to reach the given data from this node
	 */
	public double getProbability(T data) {

		double probability;

		if (this.data.equals(data)) {

			probability = 1;

		} else {

			probability = 0;

			Set<ProbabilityNode<T>> childCollection = children.keySet();

			for (ProbabilityNode<T> child : childCollection) {
				probability += children.get(child) * child.getProbability(data);
			}
		}

		return probability;
	}

	/**
	 * @param valueFunction
	 *            a function that returns a value for a node
	 * @param depth
	 *            the maximum search depth
	 * @return the expected value
	 */
	public double getExpectedValue(Function<T, Double> valueFunction, int depth) {

		double expectedValue;

		if (depth == 0) {

			expectedValue = valueFunction.apply(data);

		} else {

			expectedValue = 0;

			Set<ProbabilityNode<T>> childCollection = children.keySet();

			for (ProbabilityNode<T> child : childCollection) {
				expectedValue += children.get(child) * child.getExpectedValue(valueFunction, depth - 1);
			}
		}

		return expectedValue;
	}
}