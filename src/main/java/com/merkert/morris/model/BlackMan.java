package com.merkert.morris.model;

/**
 * Represents a black token.
 */
public final class BlackMan implements Man {

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public boolean moveTo(Position target) {
        return false;
    }

    @Override
    public void remove() {
    }

}
