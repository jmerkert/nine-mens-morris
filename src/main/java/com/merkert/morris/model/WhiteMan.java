package com.merkert.morris.model;

/**
 * Represents a white token
 *
 */
public final class WhiteMan implements Man {

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
