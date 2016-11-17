package fr.guehenneux.game421;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

import fr.guehenneux.die.Die6;

/**
 * A 421 dice set. It contains 3 dice-6.
 *
 * @author Jonathan Guéhenneux
 */
public class DiceSet421 extends ArrayList<Die6> {

	public static final int SIZE = 3;

	private static final List<DiceRoll421> POSSIBLE_ROLLS = getPossibleRolls();

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

	/**
	 * @param rollCount
	 *            the number of times we can roll the dice
	 * @return the expected value after the specified roll count
	 */
	public double getExpectedValue(int rollCount) {

		double expectedValue;

		if (rollCount == 0) {

			expectedValue = getCombination().getValue();

		} else {

			DoubleStream expectedValues = POSSIBLE_ROLLS.stream()
					.mapToDouble(roll -> getExpectedValue(roll, rollCount));

			expectedValue = expectedValues.max().getAsDouble();
		}

		return expectedValue;
	}

	/**
	 * @param rollCount
	 *            the number of times we can roll the dice
	 * @return the dice roll that give the higher expected value after the specified roll count
	 */
	public DiceRoll421 getBestOffensiveRoll(int rollCount) {

		double maximumExpectedValue = Double.MIN_VALUE;
		DiceRoll421 bestRoll = null;
		double expectedValue;

		for (DiceRoll421 roll : POSSIBLE_ROLLS) {

			expectedValue = getExpectedValue(roll, rollCount);

			if (expectedValue > maximumExpectedValue) {

				bestRoll = roll;
				maximumExpectedValue = expectedValue;
			}
		}

		return bestRoll;
	}

	/**
	 * @param valueToBeat
	 * @param rollCount
	 * @return
	 */
	public DiceRoll421 getBestDefensiveRoll(int valueToBeat, int rollCount) {

		double maximumBeatProbability = 0;
		DiceRoll421 bestRoll = null;
		List<Double> expectedValues;
		double beatingRollCount;
		double beatProbability;

		for (DiceRoll421 roll : POSSIBLE_ROLLS) {

			expectedValues = new ArrayList<>();
			populateExpectedValues(roll, 0, rollCount, expectedValues);

			beatingRollCount = expectedValues.stream().filter(expectedValue -> expectedValue > valueToBeat).count();
			beatProbability = beatingRollCount / expectedValues.size();

			if (beatProbability > maximumBeatProbability) {

				bestRoll = roll;
				maximumBeatProbability = beatProbability;
			}
		}

		return bestRoll;
	}

	/**
	 * @param roll
	 * @param rollCount
	 * @return
	 */
	private double getExpectedValue(DiceRoll421 roll, int rollCount) {

		List<Double> expectedValues = new ArrayList<>();
		populateExpectedValues(roll, 0, rollCount, expectedValues);

		double expectedValueSum = 0;

		for (double expectedValue : expectedValues) {
			expectedValueSum += expectedValue;
		}

		double averageExpectedValue = expectedValueSum / expectedValues.size();

		return averageExpectedValue;
	}

	/**
	 * @param roll
	 * @param dieIndex
	 * @param rollCount
	 * @return
	 */
	private void populateExpectedValues(DiceRoll421 roll, int dieIndex, int rollCount, List<Double> expectedValues) {

		if (dieIndex < SIZE) {

			Die6 die = get(dieIndex);

			if (roll.get(dieIndex)) {

				int savedRestingPosition = die.getRestingPosition();

				die.getRestingPositions().forEach(restingPosition -> {

					die.setRestingPosition(restingPosition);
					populateExpectedValues(roll, dieIndex + 1, rollCount, expectedValues);
				});

				die.setRestingPosition(savedRestingPosition);

			} else {

				populateExpectedValues(roll, dieIndex + 1, rollCount, expectedValues);
			}

		} else {

			expectedValues.add(getExpectedValue(rollCount - 1));
		}
	}

	/**
	 * For n dice, we have 2^n possible rolls. For example, for 3 dice, we have 2^3 possible rolls:
	 * <ul>
	 * <li>roll 0 dice</li>
	 * <li>roll die 0</li>
	 * <li>roll die 1</li>
	 * <li>roll die 2</li>
	 * <li>roll dice 0 and 1</li>
	 * <li>roll dice 0 and 2</li>
	 * <li>roll dice 1 and 2</li>
	 * <li>roll dice 0, 1 and 2</li>
	 * </ul>
	 *
	 * @return the possible rolls
	 */
	private static List<DiceRoll421> getPossibleRolls() {

		List<DiceRoll421> possibleRolls = new ArrayList<>();
		DiceRoll421 currentRoll = new DiceRoll421();

		populatePossibleRolls(possibleRolls, currentRoll);

		return possibleRolls;
	}

	/**
	 * Recursive method that generate all possible rolls.
	 *
	 * @param possibleRolls
	 *            the possible rolls being populated
	 * @param currentRoll
	 *            the roll being set
	 */
	private static void populatePossibleRolls(List<DiceRoll421> possibleRolls, DiceRoll421 currentRoll) {

		if (currentRoll.size() == SIZE) {

			possibleRolls.add(new DiceRoll421(currentRoll));

		} else {

			currentRoll.addLast(true);
			populatePossibleRolls(possibleRolls, currentRoll);
			currentRoll.removeLast();

			currentRoll.addLast(false);
			populatePossibleRolls(possibleRolls, currentRoll);
			currentRoll.removeLast();
		}
	}
}