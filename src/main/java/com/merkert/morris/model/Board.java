package com.merkert.morris.model;

public class Board {

    private final Man[][] positions = new Man[3][8];

    public Man getMan(int district, int position) {
        return positions[district][position];
    }

    /**
     * Sets a man on a position if empty. This move is only valid in setup phase and
     * if the player has only 3 men left.
     * 
     * @param must not be {@code null}
     * @return {@code true} if successful, {@code false} else.
     */
    public boolean setMan(Man man) {
        // TODO: Adds check for count == 3 || initial phase;
        if (isOccupied(man.getPosition())) {
            return false;
        }
        positions[man.getPosition().getDistrict()][man.getPosition().getPosition()] = man;
        return true;
    }

    /**
     * Moves a man from his position to another position if possible.
     * <p>
     * Movement is possible if:
     * <ul>
     * <li>The target position is not occupied</li>
     * <li>The target position a neighbor field of the man's actual position</li>
     * </ul>
     * </p>
     * 
     * @param man    must not be {@code null}
     * @param target must not be {@code null}
     * @return {@code true} if successful, {@code false} else.
     */
    public boolean moveMan(Man man, Position target) {
        // TODO: Adds check for count == 3;
        if (isOccupied(target) || !isValidMove(man.getPosition(), target)) {
            return false;
        }
        positions[man.getPosition().getDistrict()][man.getPosition().getPosition()] = null;
        positions[target.getDistrict()][target.getPosition()] = man;
        return true;
    }

    private boolean isOccupied(Position position) {
        return positions[position.getDistrict()][position.getPosition()] == null ? false : true;
    }

    private boolean isValidMove(Position start, Position target) {
        if (start.getDistrict() == target.getDistrict()) {
            return isValidHorizontal(start.getPosition(), target.getPosition());
        } else {
            return isValidVertical(start, target);
        }
    }

    private boolean isValidHorizontal(int start, int target) {
        int distance = Math.abs(start - target);
        if (distance == 1) {
            return true;
        }
        if (start == 7 && target == 0) {
            return true;
        }
        if (start == 0 && target == 7) {
            return true;
        }
        return false;
    }

    private boolean isValidVertical(Position start, Position target) {
        // Not neighbor district
        if (Math.abs(start.getDistrict() - target.getDistrict()) != 1) {
            return false;
        }
        // Only odd positions have a connection to another district.
        if (!isOddNumber(start.getPosition())) {
            return false;
        }
        // Not neighbor position
        if (start.getPosition() != target.getPosition()) {
            return false;
        }
        return true;
    }

    public boolean isOddNumber(int num) {
        return (num & 1) != 0;
    }

}
