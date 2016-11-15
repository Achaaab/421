package fr.guehenneux.game421;

import java.util.Set;

import fr.guehenneux.die.Die6;

/**
 * a board
 *
 * @author Jonathan Guéhenneux
 */
public interface Board {

	/**
	 * @return the 3 dice 6
	 */
	Set<Die6> getDice();
}