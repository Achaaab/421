package fr.guehenneux.die;

import java.security.InvalidParameterException;

/**
 * @author Jonathan Guéhenneux
 */
public class NumericalDie extends AbstractDie<Integer> {

	private int lower;
	private int upper;
	private int step;

	/**
	 * Create a die and throw it to set a first position. Lower value is set to 1. Step is set to 1.
	 *
	 * @param upper
	 */
	public NumericalDie(int upper) {
		this(1, upper, 1);
	}

	/**
	 * Create a new die and throw it to set a first resting position.
	 *
	 * @param lower
	 *            the lower value
	 * @param upper
	 *            the upper value
	 * @param step
	 *            the step between each value
	 */
	public NumericalDie(int lower, int upper, int step) {

		if (lower > upper) {
			throw new InvalidParameterException("upper value must be greater than or equal to lesser value");
		}

		if (step <= 0) {
			throw new InvalidParameterException("step must be strictly positive");
		}

		facesCount = 1 + (upper - lower) / step;

		this.lower = lower;
		this.upper = upper;
		this.step = step;

		doThrow();
	}

	@Override
	public void doThrow() {
		restingPosition = lower + step * RANDOM.nextInt(facesCount);
	}

	@Override
	public void setRestingPosition(Integer restingPosition) {

		if (restingPosition < lower || restingPosition > upper || (restingPosition - lower) % step != 0) {
			throw new InvalidParameterException("specified resting position does not exist");
		}

		super.setRestingPosition(restingPosition);
	}
}