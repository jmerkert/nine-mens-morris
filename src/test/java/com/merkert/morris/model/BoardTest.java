package com.merkert.morris.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    Board sut;

    @BeforeEach
    void init() {
        sut = new Board();
    }

    @Test
    void setMan_null() {
        assertThrows(NullPointerException.class, () -> {
            sut.setMan(null);
        });
    }

    @Test
    void setMan_occupied() {
        Man man = new Man(Color.BLACK, new Position(0, 0));

        sut.setMan(man);

        assertFalse(sut.setMan(man));
    }

    @Test
    void setMan_free() {
        Man man = new Man(Color.BLACK, new Position(0, 0));

        sut.setMan(new Man(Color.BLACK, new Position(1, 0)));

        assertTrue(sut.setMan(man));
    }

    @Test
    void setBlackMen_addsToCollection() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.BLACK, new Position(0, 1));

        sut.setMan(blackOne);
        assertThat(sut.getBlackMen().size(), is(1));
        assertThat(sut.getBlackMen(), contains(blackOne));

        sut.setMan(blackTwo);
        assertThat(sut.getBlackMen().size(), is(2));
        assertThat(sut.getBlackMen(), containsInAnyOrder(blackOne, blackTwo));

        assertThat(sut.getWhiteMen().size(), is(0));
    }

    @Test
    void setWhiteMen_addsToCollection() {
        Man whiteOne = new Man(Color.WHITE, new Position(0, 0));
        Man whiteTwo = new Man(Color.WHITE, new Position(0, 1));

        sut.setMan(whiteOne);
        assertThat(sut.getWhiteMen().size(), is(1));
        assertThat(sut.getWhiteMen(), contains(whiteOne));

        sut.setMan(whiteTwo);
        assertThat(sut.getWhiteMen().size(), is(2));
        assertThat(sut.getWhiteMen(), containsInAnyOrder(whiteOne, whiteTwo));

        assertThat(sut.getBlackMen().size(), is(0));
    }

    @Test
    void moveMan_manIsNull() {
        assertThrows(NullPointerException.class, () -> {
            sut.moveMan(null, new Position(0, 0));
        });
    }

    @Test
    void moveMan_positionIsNull() {
        assertThrows(NullPointerException.class, () -> {
            sut.moveMan(new Man(Color.BLACK, new Position(0, 0)), null);
        });
    }

    @Test
    void moveMan_inDistrictAndFree() {
        Man man = new Man(Color.BLACK, new Position(0, 0));
        sut.setMan(man);

        assertTrue(sut.moveMan(man, new Position(0, 1)));
    }

    @Test
    void moveMan_inDistrictAndFreeAndEdge() {
        Man man = new Man(Color.BLACK, new Position(0, 0));
        sut.setMan(man);

        assertTrue(sut.moveMan(man, new Position(0, 7)));
    }

    @Test
    void moveMan_inDistrictAndOccupied() {
        Man man = new Man(Color.BLACK, new Position(0, 1));
        sut.setMan(man);
        sut.setMan(new Man(Color.BLACK, new Position(0, 0)));

        assertFalse(sut.moveMan(man, new Position(0, 0)));
    }

    @Test
    void moveMan_otherDistrictAndFree() {
        Man man = new Man(Color.BLACK, new Position(0, 1));
        sut.setMan(man);

        assertTrue(sut.moveMan(man, new Position(1, 1)));
        assertFalse(sut.moveMan(man, new Position(2, 1)));
    }

    @Test
    void moveMan_otherDistrictAndOccupied() {
        Man man = new Man(Color.BLACK, new Position(0, 1));
        sut.setMan(man);
        sut.setMan(new Man(Color.BLACK, new Position(1, 1)));

        assertFalse(sut.moveMan(man, new Position(1, 1)));
    }

    @Test
    void renmoveMan_null() {
        assertThrows(NullPointerException.class, () -> {
            sut.removeMan(null);
        });
    }

    @Test
    void renmoveMan_removesFromCollection() {
        Man black = new Man(Color.BLACK, new Position(0, 1));
        Man white = new Man(Color.WHITE, new Position(0, 1));
        sut.setMan(black);
        sut.setMan(white);

        sut.removeMan(black);
        sut.removeMan(white);

        assertThat(sut.getBlackMen(), not(contains(black)));
        assertThat(sut.getWhiteMen(), not(contains(white)));
    }

    @Test
    void getMan() {
        Man black = new Man(Color.BLACK, new Position(0, 1));
        sut.setMan(black);

        assertThat(sut.getMan(0, 1), is(black));
    }

}
