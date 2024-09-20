package com.io.core.character;

import com.io.core.board.Board;
import com.io.core.board.BoardPosition;
import com.io.core.moves.KingMove;
import com.io.core.moves.KnightMove;
import com.io.core.moves.MoveDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MeleeEnemy extends Enemy {
    static int maxMana = 2, maxHealth = 5;

    public MeleeEnemy(BoardPosition position) {
        super(maxMana, maxHealth, position);
    }

    @Override
    public MoveDTO makeNextMove(Board board) {
        if (currentMana <= 0)
            return null;

        List<BoardPosition> playerTeamPosition = board.getTeamPosition(0);
        if (playerTeamPosition.isEmpty()) {
            System.err.println("No player found on the board");
            return null;
        }
        BoardPosition playerPosition = playerTeamPosition.get(0);

        var knightMove = new KnightMove(1, 1);
        var move = new KingMove(1, 1);
        if (knightMove.isMoveValid(this, playerPosition, board)) {
            return new MoveDTO(knightMove, playerPosition, this);
        } else {
            var movePositionArr = Arrays.asList(new BoardPosition[]{
                    new BoardPosition(position.x() - 1, position.y()),
                    new BoardPosition(position.x() + 1, position.y()),
                    new BoardPosition(position.x(), position.y() - 1),
                    new BoardPosition(position.x(), position.y() + 1)
            });
            Collections.shuffle(movePositionArr);

            var curDistance = distance(playerPosition, position);
            for (var newPosition : movePositionArr) {
                if (distance(playerPosition, newPosition) < curDistance && move.isMoveValid(this, newPosition, board)) {
                    return new MoveDTO(move, newPosition, this);
                }
            }
            return null;
        }
    }

    private int distance(BoardPosition p, BoardPosition q) {
        return Math.abs(p.x() - q.x()) + Math.abs(p.y() - q.y());
    }
}
