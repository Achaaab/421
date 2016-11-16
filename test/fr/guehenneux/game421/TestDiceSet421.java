package fr.guehenneux.game421;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @see fr.guehenneux.die.Die6
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
}