package fr.guehenneux.die;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @see fr.guehenneux.die.Die6
 *
 * @author Jonathan Guéhenneux
 */
public class TestDie6 {

	private Die6 die;

	@Before
	public void before() {
		die = new Die6();
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition0() {
		die.setRestingPosition(-100);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition1() {
		die.setRestingPosition(0);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition2() {
		die.setRestingPosition(7);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition3() {
		die.setRestingPosition(Integer.MIN_VALUE);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition4() {
		die.setRestingPosition(Integer.MAX_VALUE);
	}

	@Test
	public void setRestingPosition() {

		die.setRestingPosition(3);
		int restingPosition = die.getRestingPosition();
		Assert.assertEquals(restingPosition, 3);
	}

	@Test
	public void getRestingPosititions() {

		Set<Integer> restingPositions = die.getRestingPositions().collect(Collectors.toSet());

		Assert.assertEquals(restingPositions.size(), 6);

		Assert.assertTrue(restingPositions.contains(1));
		Assert.assertTrue(restingPositions.contains(2));
		Assert.assertTrue(restingPositions.contains(3));
		Assert.assertTrue(restingPositions.contains(4));
		Assert.assertTrue(restingPositions.contains(5));
		Assert.assertTrue(restingPositions.contains(6));
	}

	@Test
	public void validRestingPosition() {

		Set<Integer> restingPositions = die.getRestingPositions().collect(Collectors.toSet());

		Assert.assertTrue(restingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(restingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(restingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(restingPositions.contains(die.getRestingPosition()));
	}
}