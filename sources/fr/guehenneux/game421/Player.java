package fr.guehenneux.game421;

import java.util.Set;

import fr.guehenneux.die.Die6;

/**
 * a player
 *
 * @author Jonathan Guéhenneux
 */
public interface Player {

	/**
	 * @return the number of tokens the player have
	 */
	int getTokenCount();

	/**
	 * @param dice
	 * @param selectionIndex
	 * @param maximumSelectionIndex
	 */
	void roll(Set<Die6> dice, int selectionIndex, int maximumSelectionIndex);
}