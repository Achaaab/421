package fr.guehenneux.game421;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.guehenneux.die.Die6;

/**
 * A 421 dice set. It contains 3 dice-6.
 *
 * @author Jonathan Guéhenneux
 */
public class DiceSet421 extends ArrayList<Die6> {

	public static final int SIZE = 3;

	/**
	 * Create a 421 dice set and roll each die once.
	 */
	public DiceSet421() {

		while (size() < SIZE) {
			add(new Die6());
		}
	}

	/**
	 * @return the possible combinations
	 */
	public List<Combination421> getPossibleCombinations() {

		List<Combination421> possibleCombinations = new ArrayList<>();
		populatePossibleCombinations(possibleCombinations, 0);
		return possibleCombinations;
	}

	/**
	 * recursive method that iterate over current die possible resting positions add populate the possible combinations
	 *
	 * @param possibleCombinations
	 *            the possible combinations to populate
	 * @param dieIndex
	 *            the index of the next die to set
	 */
	private void populatePossibleCombinations(List<Combination421> possibleCombinations, int dieIndex) {

		if (dieIndex < size()) {

			final Die6 die = get(dieIndex);

			die.getRestingPositions().forEach(restingPosition -> {

				die.setRestingPosition(restingPosition);
				populatePossibleCombinations(possibleCombinations, dieIndex + 1);
			});

		} else {

			possibleCombinations.add(getCombination());
		}
	}

	/**
	 * @return the distinct possible combinations
	 */
	public Set<Combination421> getDistinctPossibleCombinations() {

		List<Combination421> possibleCombinations = getPossibleCombinations();
		return new HashSet<>(possibleCombinations);
	}

	/**
	 * @return the current combination formed by the dice set
	 */
	public Combination421 getCombination() {
		return new Combination421(this);
	}
}