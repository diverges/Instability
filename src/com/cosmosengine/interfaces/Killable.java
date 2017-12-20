package com.cosmosengine.interfaces;

/**
 * Implemented by all entities that can die.
 */
public interface Killable {

	/**
	 * Confirm if the object has passed its death point
	 * 
	 * @return
	 */
	boolean checkIfAlive();

	/**
	 * On death event
	 */
	void onDeath(); // event to execute upon death

	/**
	 * Deals damage to current entity and can result in its destruction.
	 * 
	 * @param damage
	 *            intended damage
	 * @return actual damage dealt
	 */
	int dealDamage(int damage);

}
