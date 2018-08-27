package com.pegboard;

/**
 * 
 * Defines a listener to handle changes to position state changes.
 * 
 * @author rtodd
 *
 */
public interface PositionStateListener {

	public void onPositionStateUpdate(int position, PositionState pegState);

}
