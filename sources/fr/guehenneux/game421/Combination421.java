package fr.guehenneux.game421;

import java.util.ArrayList;
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
	 * @param diceSet
	 *            a dice set
	 */
	public Combination421(DiceSet421 diceSet) {

		this();

		Die6 die;

		for (int dieIndex = 0; dieIndex < DiceSet421.SIZE; dieIndex++) {

			die = diceSet.get(dieIndex);
			setRestingPosition(dieIndex, die.getRestingPosition());
		}

		sort(Collections.reverseOrder());
	}

	/**
	 * @return the combination value
	 */
	public int getValue() {
		return COMBINATION_VALUE.get(this);
	}

	/**
	 * Sort the combination.
	 */
	public void sort() {
		sort(Collections.reverseOrder());
	}

	/**
	 * @return whether the combination is a 421
	 */
	public boolean is421() {
		return get(0) == 4 && get(1) == 2 && get(2) == 1;
	}

	/**
	 * @return whether the combination is a fiche (aka mac)
	 */
	public boolean isFiche() {
		return get(0) != 1 && get(1) == 1 && get(2) == 1;
	}

	/**
	 * @return whether the combination is a baraque (aka zenzi, triplet or brelan)
	 */
	public boolean isBaraque() {
		return get(0) == get(1) && get(1) == get(2);
	}

	/**
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

		} else if (isFiche()) {

			if (otherCombination.is421()) {

				comparison = -1;

			} else if (otherCombination.isFiche()) {

				comparison = get(0) - otherCombination.get(0);

			} else if (otherCombination.isBaraque()) {

				if (otherCombination.get(0) == 1) {
					comparison = -1;
				} else if (get(0) >= otherCombination.get(0)) {
					comparison = 1;
				} else {
					comparison = -1;
				}

			} else {

				comparison = 1;
			}

		} else if (isBaraque()) {

			if (otherCombination.is421()) {

				comparison = -1;

			} else if (otherCombination.isFiche()) {

				if (get(0) == 1) {
					comparison = 1;
				} else if (get(0) > otherCombination.get(0)) {
					comparison = 1;
				} else {
					comparison = -1;
				}

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

		} else {

			if (otherCombination.is421() || otherCombination.isFiche() || otherCombination.isBaraque()
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
		}

		return comparison;
	}
}