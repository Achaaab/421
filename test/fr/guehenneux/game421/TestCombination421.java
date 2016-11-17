package fr.guehenneux.game421;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see fr.guehenneux.game421.Combination421
 *
 * @author Jonathan Guéhenneux
 */
public class TestCombination421 {

	@Test
	public void compareTo0() {

		Combination421 combination611 = new Combination421(6, 1, 1);
		Combination421 combination666 = new Combination421(6, 6, 6);

		Assert.assertTrue(combination611.compareTo(combination666) > 0);
		Assert.assertTrue(combination666.compareTo(combination611) < 0);
	}

	@Test
	public void compareTo1() {

		Combination421 combination111 = new Combination421(1, 1, 1);
		Combination421 combination222 = new Combination421(2, 2, 2);

		Assert.assertTrue(combination111.compareTo(combination222) > 0);
		Assert.assertTrue(combination222.compareTo(combination111) < 0);
	}
}