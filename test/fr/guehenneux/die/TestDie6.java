package fr.guehenneux.die;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	private Set<Integer> validRestingPositions;

	@Before
	public void before() {

		die = new Die6();
		validRestingPositions = IntStream.rangeClosed(1, 6).boxed().collect(Collectors.toSet());
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition1() {
		die.setRestingPosition(-100);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition2() {
		die.setRestingPosition(0);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition3() {
		die.setRestingPosition(7);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition4() {
		die.setRestingPosition(Integer.MIN_VALUE);
	}

	@Test(expected = InvalidParameterException.class)
	public void invalidRestingPosition5() {
		die.setRestingPosition(Integer.MAX_VALUE);
	}

	@Test
	public void setRestingPosition() {

		die.setRestingPosition(3);
		int restingPosition = die.getRestingPosition();
		Assert.assertEquals(restingPosition, 3);
	}

	@Test
	public void validRestingPosition() {

		Assert.assertTrue(validRestingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(validRestingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(validRestingPositions.contains(die.getRestingPosition()));

		die.roll();
		Assert.assertTrue(validRestingPositions.contains(die.getRestingPosition()));
	}
}