package com.pegboard;

import java.util.ArrayList;
import java.util.List;

public class PegBoardController {

	private static final int NULL_POSITION = -1;

	private MoveSet m_allPossibleMoves;

	private PegBoardState m_pegBoardState;
	private int m_selectedPosition;

	private List<PositionStateListener> m_positionStateListeners = new ArrayList<>();

	public PegBoardController() {
		m_allPossibleMoves = MoveSet.createAllPossibleMoves();
	}

	public void initialize() {
		m_pegBoardState = PegBoardState.create(0);
		m_selectedPosition = NULL_POSITION;

		for (int position = 0; position < 15; ++position) {
			notifyPositionStateListeners(position);
		}
	}

	public PositionState getPegState(int position) {
		if (m_selectedPosition == position) {
			return PositionState.SELECTED;
		} else if (m_pegBoardState.isOccupied(position)) {
			return PositionState.OCCUPIED;
		} else {
			return PositionState.UNOCCUPIED;
		}
	}

	public void click(int position) {

		if (m_pegBoardState.isOccupied(position)) {
			moveTo(position);
		} else
			jumpTo(position);
	}

	public void regsterPositionStateListener(PositionStateListener listener) {
		m_positionStateListeners.add(listener);
	}

	public void unregsterPositionStateListener(PositionStateListener listener) {
		m_positionStateListeners.remove(listener);
	}

	private void notifyPositionStateListeners(int position) {
		m_positionStateListeners.forEach(listener -> listener.onPegStateUpdate(position, getPegState(position)));
	}

	private void moveTo(int position) {
		if (position != m_selectedPosition) {
			int previousSelectedPosition = m_selectedPosition;
			m_selectedPosition = position;

			if (previousSelectedPosition != NULL_POSITION) {
				notifyPositionStateListeners(previousSelectedPosition);
			}

			if (position != NULL_POSITION) {
				notifyPositionStateListeners(position);
			}
		}
	}

	private void jumpTo(int position) {
		if (m_selectedPosition != NULL_POSITION) {
			Move move = m_allPossibleMoves.getMove(m_selectedPosition, position);
			if (move != null) {
				if (m_pegBoardState.isValid(move)) {
					m_pegBoardState = m_pegBoardState.apply(move);
					m_selectedPosition = NULL_POSITION;
					
					notifyPositionStateListeners(move.getPositionFrom());
					notifyPositionStateListeners(move.getPositionTo());
				}
			}
		}
	}

}
