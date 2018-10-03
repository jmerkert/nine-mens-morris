package com.merkert.morris.model;

/**
 * Represents a position on the board. The board consists of 3 districts, each
 * district has 8 positions.
 */
public final class Position {

    final int district;
    final int position;

    public Position(int district, int position) {
        this.district = district;
        this.position = position;
    }

    public int getDistrict() {
        return district;
    }

    public int getPosition() {
        return position;
    }

}
