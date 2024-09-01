package com.io.core.moves;

import com.io.core.board.Board;
import com.io.core.board.BoardPosition;

import java.util.ArrayList;
import java.util.List;

public class QueenMove implements Move {

    private final int cost, damage;
    private static final int[] X = {1, 1, -1, -1, 0, 1, -1, 0};
    private static final int[] Y = {1, -1, 1, -1, 1, 0, 0, -1};
    private static final int maxReach = Integer.MAX_VALUE;

    public QueenMove(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    @Override
    public boolean isMoveValid(BoardPosition startPosition, BoardPosition endPosition, Board board) {
        if (startPosition == endPosition) return false;
        int dx = endPosition.x() - startPosition.x();
        int dy = endPosition.y() - startPosition.y();

        return MovesUtils.isValidRayMove(Integer.signum(dx),
                Integer.signum(dy), maxReach, startPosition, endPosition, board);
    }

    @Override
    public List<BoardPosition> getAccessibleCells(BoardPosition position, Board board) {
        var accessibleCells = new ArrayList<BoardPosition>();

        for (int i = 0; i < X.length; i++) {
            accessibleCells.addAll(MovesUtils.getRayAccessibleCells(X[i], Y[i], maxReach, board, position));
        }
        return accessibleCells;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
