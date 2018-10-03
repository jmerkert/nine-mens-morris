package com.merkert.morris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ManTest {

    Man sut;

    @Test
    void construct_positionNull() {
        assertThrows(NullPointerException.class, () -> {
            new Man(Color.BLACK, null);
        });
    }

    @Test
    void construct_colorNull() {
        assertThrows(NullPointerException.class, () -> {
            new Man(null, new Position(0, 0));
        });
    }

    @Test
    void setPosition_Null() {
        sut = new Man(Color.BLACK, new Position(0, 0));

        assertThrows(NullPointerException.class, () -> {
            sut.setPosition(null);
        });
    }

    @Test
    void getPosition() {
        Position initial = new Position(0, 0);
        Position moved = new Position(0, 1);

        sut = new Man(Color.BLACK, initial);

        assertEquals(sut.getPosition(), initial);

        sut.setPosition(moved);

        assertEquals(sut.getPosition(), moved);
    }

}
