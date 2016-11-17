package fr.guehenneux.game421;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @see fr.guehenneux.game421.DiceSet421
 *
 * @author Jonathan Guéhenneux
 */
public class TestDiceSet421 {

	private DiceSet421 diceSet421;

	@Before
	public void before() {
		diceSet421 = new DiceSet421();
	}

	@Test
	public void getPossibleCombinations() {

		List<Combination421> combinations = diceSet421.getPossibleCombinations();
		Assert.assertEquals(combinations.size(), 216);

		for (Combination421 combination : combinations) {
			Assert.assertEquals(combination.size(), DiceSet421.SIZE);
		}

		Set<Combination421> distinctCombinations = diceSet421.getDistinctPossibleCombinations();
		Assert.assertEquals(distinctCombinations.size(), 56);
	}

	@Test
	public void getExpectedValue0() {

		System.out.println("OFFENSIVE");

		DiceSet421 diceSet = new DiceSet421();

		diceSet.get(0).setRestingPosition(3);
		diceSet.get(1).setRestingPosition(5);
		diceSet.get(2).setRestingPosition(3);

		System.out.println("1 : " + diceSet.getBestOffensiveRoll(1));
		System.out.println("2 : " + diceSet.getBestOffensiveRoll(2));
		System.out.println(diceSet.getCombination().getValue());
		System.out.println(diceSet.getExpectedValue(0));
		System.out.println(diceSet.getExpectedValue(1));
		System.out.println(diceSet.getExpectedValue(2));
	}

	@Test
	public void getExpectedValue1() {

		System.out.println("DEFENSIVE");

		Combination421 combinationToBeat = new Combination421(6, 2, 1);
		int valueToBeat = combinationToBeat.getValue();

		DiceSet421 diceSet = new DiceSet421();

		diceSet.get(0).setRestingPosition(6);
		diceSet.get(1).setRestingPosition(1);
		diceSet.get(2).setRestingPosition(2);

		System.out.println("en 1 : " + diceSet.getBestDefensiveRoll(valueToBeat, 1));
		System.out.println("en 2 : " + diceSet.getBestDefensiveRoll(valueToBeat, 2));
	}
}