package fr.guehenneux.die;

import java.util.Random;

/**
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            resting position type, usually a number
 */
public abstract class AbstractDie<T> implements Die<T> {

	protected static final Random RANDOM = new Random();

	protected int facesCount;
	protected T restingPosition;

	@Override
	public T getRestingPosition() {
		return restingPosition;
	}

	@Override
	public void setRestingPosition(T restingPosition) {
		this.restingPosition = restingPosition;
	}
}