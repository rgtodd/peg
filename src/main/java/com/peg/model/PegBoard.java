package com.peg.model;

import java.util.ArrayList;
import java.util.List;

public class PegBoard {

	private static final int NULL_POSITION = -1;

	private MoveSet m_allPossibleMoves;

	private PegBoardState m_pegBoardState;
	private int m_selectedPosition;

	private List<PegStateListener> m_pegStateListeners = new ArrayList<>();

	public PegBoard() {
		m_allPossibleMoves = MoveSet.createAllPossibleMoves();
	}

	public void initialize() {
		m_pegBoardState = PegBoardState.create(0);
		m_selectedPosition = NULL_POSITION;

		for (int position = 0; position < 15; ++position) {
			notifyPegStateListeners(position);
		}
	}

	public PegState getPegState(int position) {
		if (m_selectedPosition == position) {
			return PegState.SELECTED;
		} else if (m_pegBoardState.isOccupied(position)) {
			return PegState.OCCUPIED;
		} else {
			return PegState.UNOCCUPIED;
		}
	}

	public void click(int position) {

		if (m_pegBoardState.isOccupied(position)) {
			moveTo(position);
		} else
			jumpTo(position);
	}

	public void regsterPegStateListener(PegStateListener listener) {
		m_pegStateListeners.add(listener);
	}

	public void unregsterPegStateListener(PegStateListener listener) {
		m_pegStateListeners.remove(listener);
	}

	private void notifyPegStateListeners(int position) {
		m_pegStateListeners.forEach(listener -> listener.onPegStateUpdate(position, getPegState(position)));
	}

	private void moveTo(int position) {
		if (position != m_selectedPosition) {
			int previousSelectedPosition = m_selectedPosition;
			m_selectedPosition = position;

			if (previousSelectedPosition != NULL_POSITION) {
				notifyPegStateListeners(previousSelectedPosition);
			}

			if (m_selectedPosition != NULL_POSITION) {
				notifyPegStateListeners(m_selectedPosition);
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
					notifyPegStateListeners(move.getPositionFrom());
					notifyPegStateListeners(move.getPositionTo());
				}
			}
		}
	}

}
