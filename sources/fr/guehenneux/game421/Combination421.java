package fr.guehenneux.game421;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.guehenneux.die.AbstractCombination;
import fr.guehenneux.die.Die6;

/**
 * A combination generated with a 421 dice set.
 *
 * @author Jonathan Guéhenneux
 */
public class Combination421 extends AbstractCombination<Integer> implements Comparable<Combination421> {

	private static final int PENALTY_421 = 10;
	private static final int PENALTY_111 = 7;
	private static final int PENALTY_TIERCE = 2;
	private static final int DEFAULT_PENALTY = 1;

	private static final Map<Combination421, Integer> COMBINATION_VALUE;

	static {

		COMBINATION_VALUE = new HashMap<>();

		DiceSet421 diceSet = new DiceSet421();
		List<Combination421> combinations = new ArrayList<>(diceSet.getDistinctPossibleCombinations());
		combinations.sort(null);
		int combinationCount = combinations.size();

		for (int combinationIndex = 0; combinationIndex < combinationCount; combinationIndex++) {
			COMBINATION_VALUE.put(combinations.get(combinationIndex), combinationIndex);
		}
	}

	/**
	 * create a combination of 3 integers
	 */
	public Combination421() {
		super(DiceSet421.SIZE);
	}

	/**
	 * create a combination from the current resting positions of a dice set
	 *
	 * @param diceSet
	 *            the dice set
	 */
	public Combination421(DiceSet421 diceSet) {

		this();

		Die6 die;

		for (int dieIndex = 0; dieIndex < DiceSet421.SIZE; dieIndex++) {

			die = diceSet.get(dieIndex);
			setRestingPosition(dieIndex, die.getRestingPosition());
		}

		sort();
	}

	/**
	 * create a combination from a collection of resting positions
	 *
	 * @param restingPositions
	 *            the resting positions
	 */
	public Combination421(Collection<? extends Integer> restingPositions) {
		super(restingPositions);
	}

	/**
	 * create a combination from specified resting positions
	 *
	 * @param restingPosition0
	 * @param restingPosition1
	 * @param restingPosition2
	 */
	public Combination421(int restingPosition0, int restingPosition1, int restingPosition2) {

		super(DiceSet421.SIZE);

		set(0, restingPosition0);
		set(1, restingPosition1);
		set(2, restingPosition2);

		sort();
	}

	/**
	 *
	 */
	private void sort() {
		sort(Collections.reverseOrder());
	}

	/**
	 * @return the combination value
	 */
	public int getValue() {
		return COMBINATION_VALUE.get(this);
	}

	/**
	 * @return the number of token to give to the player with lower combination
	 */
	public int getPenalty() {

		int penalty;

		if (is421()) {

			penalty = PENALTY_421;

		} else if (is111()) {

			penalty = PENALTY_111;

		} else if (isFiche() || isBaraque()) {

			penalty = get(0);

		} else if (isTierce()) {

			penalty = PENALTY_TIERCE;

		} else {

			penalty = DEFAULT_PENALTY;
		}

		return penalty;
	}

	/**
	 * (4, 2, 1) is the strongest combination in 421 game
	 *
	 * @return whether the combination is (4, 2, 1)
	 */
	public boolean is421() {
		return get(0) == 4 && get(1) == 2 && get(2) == 1;
	}

	/**
	 * (1, 1, 1) is a particular baraque
	 *
	 * @return whether the combination is (1, 1, 1)
	 */
	public boolean is111() {
		return get(0) == 1 && get(1) == 1 && get(2) == 1;
	}

	/**
	 * A fiche is a combination of the form (x, 1, 1) with x != 1. There are 5 possible fiches:
	 * <ul>
	 * <li>(6, 1, 1)</li>
	 * <li>(5, 1, 1)</li>
	 * <li>(4, 1, 1)</li>
	 * <li>(3, 1, 1)</li>
	 * <li>(2, 1, 1)</li>
	 * </ul>
	 *
	 * @return whether the combination is a fiche (aka mac)
	 */
	public boolean isFiche() {
		return get(0) != 1 && get(1) == 1 && get(2) == 1;
	}

	/**
	 * A baraque is a combination of the form (x, x, x). There are 6 possible baraques:
	 * <ul>
	 * <li>(1, 1, 1)</li>
	 * <li>(6, 6, 6)</li>
	 * <li>(5, 5, 5)</li>
	 * <li>(4, 4, 4)</li>
	 * <li>(3, 3, 3)</li>
	 * <li>(2, 2, 2)</li>
	 * </ul>
	 *
	 * @return whether the combination is a baraque (aka zenzi, triplet or brelan)
	 */
	public boolean isBaraque() {
		return get(0) == get(1) && get(1) == get(2);
	}

	/**
	 * A tierce is a combination of the form (x, x-1, x-2). There are 4 possible tierces:
	 * <ul>
	 * <li>(6, 5, 4)</li>
	 * <li>(5, 4, 3)</li>
	 * <li>(4, 3, 2)</li>
	 * <li>(3, 2, 1)</li>
	 * </ul>
	 *
	 * @return whether the combination is a tierce (aka suite)
	 */
	public boolean isTierce() {
		return get(0) == get(1) + 1 && get(1) == get(2) + 1;
	}

	@Override
	public int compareTo(Combination421 otherCombination) {

		int comparison;

		if (is421()) {

			if (otherCombination.is421()) {
				comparison = 0;
			} else {
				comparison = 1;
			}

		} else if (is111()) {

			if (otherCombination.is421()) {
				comparison = -1;
			} else if (otherCombination.is111()) {
				comparison = 0;
			} else {
				comparison = 1;
			}

		} else if (isFiche()) {

			if (otherCombination.is421() || otherCombination.is111()) {
				comparison = -1;
			} else if (otherCombination.isFiche()) {
				comparison = get(0) - otherCombination.get(0);
			} else if (otherCombination.isBaraque()) {
				comparison = get(0) >= otherCombination.get(0) ? 1 : -1;
			} else {
				comparison = 1;
			}

		} else if (isBaraque()) {

			if (otherCombination.is421() || otherCombination.is111()) {
				comparison = -1;
			} else if (otherCombination.isFiche()) {
				comparison = get(0) > otherCombination.get(0) ? 1 : -1;
			} else if (otherCombination.isBaraque()) {
				comparison = get(0) - otherCombination.get(0);
			} else {
				comparison = 1;
			}

		} else if (isTierce()) {

			if (otherCombination.is421() || otherCombination.isFiche() || otherCombination.isBaraque()) {
				comparison = -1;
			} else if (otherCombination.isTierce()) {
				comparison = get(0) - otherCombination.get(0);
			} else {
				comparison = 1;
			}

		} else if (otherCombination.is421() || otherCombination.isFiche() || otherCombination.isBaraque()
				|| otherCombination.isTierce()) {

			comparison = -1;

		} else {

			if (get(0) == otherCombination.get(0)) {

				if (get(1) == otherCombination.get(1)) {

					if (get(2) == otherCombination.get(2)) {
						comparison = 0;
					} else {
						comparison = get(2) - otherCombination.get(2);
					}

				} else {

					comparison = get(1) - otherCombination.get(1);
				}

			} else {

				comparison = get(0) - otherCombination.get(0);
			}
		}

		return comparison;
	}
}