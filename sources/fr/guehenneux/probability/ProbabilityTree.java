package fr.guehenneux.probability;

/**
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            the node type, should implement properly hashcode and equals methods
 */
public class ProbabilityTree<T> {

	private ProbabilityNode<T> root;

	/**
	 * @return the root
	 */
	public ProbabilityNode<T> getRoot() {
		return root;
	}
}