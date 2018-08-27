package com.pegboard;

public class PegBoardState {

	private static final char OPEN = '0';
	private static final char OCCUPIED = '1';

	private final String m_state;

	private PegBoardState(String state) {
		m_state = state;
	}

	public static PegBoardState create(int emptyPosition) {

		StringBuilder sb = new StringBuilder();
		for (int position = 0; position < 15; ++position) {
			if (position == emptyPosition) {
				sb.append(OPEN);
			} else {
				sb.append(OCCUPIED);
			}
		}

		String state = sb.toString();

		return new PegBoardState(state);
	}

	public boolean isOccupied(int position) {
		return m_state.charAt(position) == OCCUPIED;
	}

	public boolean isOpen(int position) {
		return m_state.charAt(position) == OPEN;
	}

	public boolean isValid(Move move) {
		return m_state.charAt(move.getPositionFrom()) == OCCUPIED && m_state.charAt(move.getPositionOver()) == OCCUPIED
				&& m_state.charAt(move.getPositionTo()) == OPEN;
	}

	public PegBoardState apply(Move move) {

		StringBuilder sb = new StringBuilder();
		for (int position = 0; position < 15; ++position) {
			if (position == move.getPositionFrom()) {
				if (m_state.charAt(position) != OCCUPIED) {
					throw new IllegalStateException("Invalid move - positionFrom");
				}
				sb.append(OPEN);
			} else if (position == move.getPositionOver()) {
				if (m_state.charAt(position) != OCCUPIED) {
					throw new IllegalStateException("Invalid move - positionOver");
				}
				sb.append(OPEN);
			} else if (position == move.getPositionTo()) {
				if (m_state.charAt(position) != OPEN) {
					throw new IllegalStateException("Invalid move - positionTo");
				}
				sb.append(OCCUPIED);
			} else {
				sb.append(m_state.charAt(position));
			}
		}

		String state = sb.toString();

		return new PegBoardState(state);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_state == null) ? 0 : m_state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PegBoardState other = (PegBoardState) obj;
		if (m_state == null) {
			if (other.m_state != null)
				return false;
		} else if (!m_state.equals(other.m_state))
			return false;
		return true;
	}

}
