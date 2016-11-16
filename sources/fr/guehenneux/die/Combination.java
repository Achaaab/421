package fr.guehenneux.die;

/**
 * a combination of dice
 *
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            resting position type, usually a number
 */
public interface Combination<T> {

	/**
	 * @return the combination size, i.e., the number of resting positions
	 */
	int getSize();

	/**
	 * @param index
	 *            the resting position index
	 * @return the resting position at the specified index
	 */
	T getRestingPosition(int index);

	/**
	 * @param index
	 *            the resting position index
	 * @param restingPosition
	 *            the resting position
	 */
	void setRestingPosition(int index, T restingPosition);
}