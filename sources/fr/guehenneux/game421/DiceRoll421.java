package fr.guehenneux.game421;

import java.util.LinkedList;

/**
 * A dice roll.
 *
 * @author Jonathan Guéhenneux
 */
public class DiceRoll421 extends LinkedList<Boolean> {

	/**
	 * an empty dice roll
	 */
	public DiceRoll421() {

	}

	/**
	 * create a copy of the specified dice roll
	 *
	 * @param roll
	 *            the dice roll to copy
	 */
	public DiceRoll421(DiceRoll421 roll) {
		super(roll);
	}
}