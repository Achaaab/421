package fr.guehenneux.die;

import java.util.stream.Stream;

/**
 * wikipedia: "A small throwable object with multiple resting positions, used for generating random numbers."
 *
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            resting position type, usually a number
 */
public interface Die<T> {

	/**
	 * throw the die, setting a new resting position
	 */
	void roll();

	/**
	 * @return the resting position
	 */
	T getRestingPosition();

	/**
	 * @param restingPosition
	 *            the resting position
	 */
	void setRestingPosition(T restingPosition);

	/**
	 * @return the possible resting positions
	 */
	Stream<T> getRestingPositions();
}