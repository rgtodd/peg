package com.pegboard;

public class Move {

	private int m_positionFrom;
	private int m_positionOver;
	private int m_positionTo;

	public Move(int positionFrom, int positionOver, int positionTo) {
		m_positionFrom = positionFrom;
		m_positionOver = positionOver;
		m_positionTo = positionTo;
	}

	public int getPositionFrom() {
		return m_positionFrom;
	}

	public int getPositionOver() {
		return m_positionOver;
	}

	public int getPositionTo() {
		return m_positionTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m_positionFrom;
		result = prime * result + m_positionOver;
		result = prime * result + m_positionTo;
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
		Move other = (Move) obj;
		if (m_positionFrom != other.m_positionFrom)
			return false;
		if (m_positionOver != other.m_positionOver)
			return false;
		if (m_positionTo != other.m_positionTo)
			return false;
		return true;
	}

}
