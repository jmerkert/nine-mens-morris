package com.merkert.morris.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Board {

    private Collection<Man> blackMen = new ArrayList<Man>();
    private Collection<Man> whiteMen = new ArrayList<Man>();
    private final Man[][] positions = new Man[3][8];

    /**
     * Returns a man on a distinct position.
     * 
     * @param district
     * @param position
     * @return may be {@code null]
     */
    public Man getMan(int district, int position) {
        return positions[district][position];
    }

    /**
     * Sets a man on his position if empty. This move is only valid in setup phase.
     * 
     * @param must not be {@code null}
     * @return {@code true} if successful, {@code false} else.
     */
    public boolean setMan(Man man) {
        Objects.requireNonNull(man, "man");
        if (isOccupied(man.getPosition())) {
            return false;
        }
        positions[man.getPosition().getDistrict()][man.getPosition().getPosition()] = man;
        addToCollection(man);
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
        Objects.requireNonNull(man, "man");
        Objects.requireNonNull(target, "target");
        if (isOccupied(target) || !isValidMove(man, target)) {
            return false;
        }
        positions[man.getPosition().getDistrict()][man.getPosition().getPosition()] = null;
        positions[target.getDistrict()][target.getPosition()] = man;
        return true;
    }

    /**
     * Removes a given man from the board. This results from closing a mill.
     * 
     * @param must not be {@code null}
     */
    public void removeMan(Man man) {
        Objects.requireNonNull(man, "man");
        positions[man.getPosition().getDistrict()][man.getPosition().getPosition()] = null;
        removeFromCollection(man);
    }

    private boolean isOccupied(Position position) {
        return positions[position.getDistrict()][position.getPosition()] == null ? false : true;
    }

    private boolean isValidMove(Man man, Position target) {
        if (springingIsPossible(man)) {
            return true;
        }
        Position start = man.getPosition();
        if (start.getDistrict() == target.getDistrict()) {
            return isValidHorizontal(start.getPosition(), target.getPosition());
        } else {
            return isValidVertical(start, target);
        }
    }

    private boolean springingIsPossible(Man man) {
        if (man.getColor() == Color.BLACK) {
            return blackMen.size() == 2;
        } else {
            return whiteMen.size() == 2;
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

    private boolean isOddNumber(int num) {
        return (num & 1) != 0;
    }

    private void addToCollection(Man man) {
        if (man.getColor() == Color.BLACK) {
            this.blackMen.add(man);
        } else {
            this.whiteMen.add(man);
        }
    }

    private void removeFromCollection(Man man) {
        this.blackMen.remove(man);
        this.whiteMen.remove(man);
    }

    Collection<Man> getBlackMen() {
        return this.blackMen;
    }

    Collection<Man> getWhiteMen() {
        return this.whiteMen;
    }

}
