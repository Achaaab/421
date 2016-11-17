package fr.guehenneux.die;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            resting position type, usually a number
 */
public abstract class AbstractCombination<T> extends ArrayList<T> implements Combination<T> {

	/**
	 * create an empty combination
	 */
	public AbstractCombination() {

	}

	/**
	 * create a combination of specified length with {@code null} resting positions
	 *
	 * @param length
	 *            the length of the combination
	 */
	public AbstractCombination(int length) {

		super(length);

		while (size() < length) {
			add(null);
		}
	}

	/**
	 * Create a combination from the specified resting positions.
	 *
	 * @param restingPositions
	 *            the resting positions
	 */
	public AbstractCombination(Collection<? extends T> restingPositions) {
		super(restingPositions);
	}

	@Override
	public int getSize() {
		return size();
	}

	@Override
	public T getRestingPosition(int index) {
		return get(index);
	}

	@Override
	public void setRestingPosition(int index, T restingPosition) {
		set(index, restingPosition);
	}
}