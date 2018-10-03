package com.merkert.morris.controller;

import java.util.Objects;

import com.merkert.morris.model.Board;
import com.merkert.morris.model.Man;
import com.merkert.morris.model.Position;

/**
 * This class knows all mills and can check if a moved man caused a mill.
 *
 */
public class Mills {

    private final Board board;

    /**
     * Dedicated constructor, sets the board.
     * 
     * @param board must not be {@code null}
     */
    public Mills(Board board) {
        this.board = Objects.requireNonNull(board, "board");
    }

    /**
     * Returns whether a mill has been created.
     * 
     * @param man must not be {@code null}
     * @return {@code true} if a mill has been built, {@code false] otherwise
     */
    public boolean hasMill(Man man) {
        boolean hasMill = false;
        Objects.requireNonNull(man, "man");
        hasMill = checkHorizontal(man);
        if (hasMill) {
            return true;
        }
        return checkVertical(man);
    }

    private boolean checkHorizontal(Man one) {
        boolean mill = false;

        if (isOddNumber(one.getPosition().getPosition())) {
            // Middle case. Only this side has to be checked
            mill = checkMiddle(one);
        } else {
            // Edge case. Both sides can be a mill
            mill = checkLeft(one);
            if (mill == true) {
                return true;
            } else {
                mill = checkRight(one);
            }
        }
        return mill;
    }

    private boolean checkLeft(Man one) {
        Man two = getLeftOf(one);
        if (two != null) {
            Man three = getLeftOf(two);
            if (three != null) {
                return sameColor(one, two, three);
            }
        }
        return false;
    }

    private boolean checkRight(Man one) {
        Man two = getRightOf(one);
        if (two != null) {
            Man three = getRightOf(two);
            if (three != null) {
                return sameColor(one, two, three);
            }
        }
        return false;
    }

    private boolean checkMiddle(Man one) {
        Man two = getLeftOf(one);
        Man three = getRightOf(one);
        if (two == null || three == null) {
            return false;
        }
        return sameColor(one, two, three);
    }

    private boolean sameColor(Man one, Man two, Man three) {
        return one.getColor() == two.getColor() && one.getColor() == three.getColor();
    }

    private boolean checkVertical(Man man) {
        Man one = board.getMan(0, man.getPosition().getPosition());
        Man two = board.getMan(1, man.getPosition().getPosition());
        Man three = board.getMan(2, man.getPosition().getPosition());
        if (one == null || two == null || three == null) {
            return false;
        }
        return sameColor(one, two, three);
    }

    private boolean isOddNumber(int num) {
        return (num & 1) != 0;
    }

    private Man getLeftOf(Man man) {
        Position position = man.getPosition();
        if (position.getPosition() > 0) {
            return board.getMan(position.getDistrict(), position.getPosition() - 1);
        }
        return board.getMan(position.getDistrict(), 7);
    }

    private Man getRightOf(Man man) {
        Position position = man.getPosition();
        if (position.getPosition() < 7) {
            return board.getMan(position.getDistrict(), position.getPosition() + 1);
        }
        return board.getMan(position.getDistrict(), 0);
    }

}
