package com.pegboard;

import java.util.HashSet;
import java.util.Set;

public class MoveSet {

	private Set<Move> m_moves;

	private MoveSet(Set<Move> moves) {
		m_moves = moves;
	}

	public static MoveSet createAllPossibleMoves() {
		Set<Move> moves = new HashSet<>();

		moves.add(new Move(0, 1, 3));
		moves.add(new Move(0, 2, 5));

		moves.add(new Move(1, 3, 6));
		moves.add(new Move(1, 4, 8));

		moves.add(new Move(2, 4, 7));
		moves.add(new Move(2, 5, 9));

		moves.add(new Move(3, 1, 0));
		moves.add(new Move(3, 4, 5));
		moves.add(new Move(3, 7, 12));
		moves.add(new Move(3, 6, 10));

		moves.add(new Move(4, 7, 11));
		moves.add(new Move(4, 8, 13));

		moves.add(new Move(5, 2, 0));
		moves.add(new Move(5, 4, 3));
		moves.add(new Move(5, 8, 12));
		moves.add(new Move(5, 9, 14));

		moves.add(new Move(6, 3, 1));
		moves.add(new Move(6, 7, 8));

		moves.add(new Move(7, 4, 2));
		moves.add(new Move(7, 8, 9));

		moves.add(new Move(8, 4, 1));
		moves.add(new Move(8, 7, 6));

		moves.add(new Move(9, 5, 2));
		moves.add(new Move(9, 8, 7));

		moves.add(new Move(10, 6, 3));
		moves.add(new Move(10, 11, 12));

		moves.add(new Move(11, 7, 4));
		moves.add(new Move(11, 12, 13));

		moves.add(new Move(12, 7, 3));
		moves.add(new Move(12, 8, 5));
		moves.add(new Move(12, 11, 10));
		moves.add(new Move(12, 13, 14));

		moves.add(new Move(13, 8, 4));
		moves.add(new Move(13, 12, 11));

		moves.add(new Move(14, 9, 5));
		moves.add(new Move(14, 13, 12));

		return new MoveSet(moves);
	}

	public Move getMove(int positionFrom, int positionTo) {
		for (Move move : m_moves) {
			if (move.getPositionFrom() == positionFrom && move.getPositionTo() == positionTo) {
				return move;
			}
		}

		return null;
	}
}
