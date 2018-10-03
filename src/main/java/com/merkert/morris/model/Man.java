package com.merkert.morris.model;

/**
 * This interface represents a token.
 *
 */
public interface Man {

    /**
     * Returns the the actual position of the man.
     * 
     * @return never {@code null}
     */
    Position getPosition();

    /**
     * Moves the man to the given position if possible and returns result.
     * 
     * @param target must not be {@code null}
     * @return true if successful, false else
     */
    boolean moveTo(Position target);

    /**
     * Removes the man from the board.
     */
    void remove();

}
