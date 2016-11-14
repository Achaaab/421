package fr.guehenneux.die;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A die with enumerated resting positions.
 *
 * @author Jonathan Guéhenneux
 *
 * @param <T>
 *            resting position type, should implement properly hashcode and equals methods
 */
public class EnumerativeDie<T> extends AbstractDie<T> {

	private List<T> restingPositions;
	private Set<T> distinctRestingPositions;

	/**
	 * Create a new die with specified resting positions and throw it to set the first resting position.
	 *
	 * @param restingPositions
	 *            the resting positions
	 */
	public EnumerativeDie(Collection<T> restingPositions) {

		this.restingPositions = new ArrayList<>(restingPositions);

		facesCount = restingPositions.size();
		distinctRestingPositions = new HashSet<>(restingPositions);

		doThrow();
	}

	@Override
	public void doThrow() {

		int restingPositionIndex = RANDOM.nextInt(facesCount);
		restingPosition = restingPositions.get(restingPositionIndex);
	}

	@Override
	public void setRestingPosition(T restingPosition) {

		if (!distinctRestingPositions.contains(restingPosition)) {
			throw new InvalidParameterException("specified resting position does not exist");
		}

		super.setRestingPosition(restingPosition);
	}
}