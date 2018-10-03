package com.merkert.morris.model;

import java.util.Objects;

/**
 * This interface represents a token.
 *
 */
public final class Man {

    private final Color color;
    private final Position position;

    /**
     * Dedicated constructor.
     * 
     * @param must not be {@code null]}
     */
    public Man(Color color, Position position) {
        this.color = Objects.requireNonNull(color, "color");
        this.position = Objects.requireNonNull(position, "position");
    }

    /**
     * Returns the the actual position of the man.
     * 
     * @return never {@code null}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the the actual position of the man.
     * 
     * @return never {@code null}
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Moves the man to the given position if possible and returns result.
     * 
     * @param target must not be {@code null}
     * @return true if successful, false else
     */
    public boolean moveTo(Position target) {
        return false;
    }

    /**
     * Removes the man from the board.
     */
    public void remove() {
    }

}
