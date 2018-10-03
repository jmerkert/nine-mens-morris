package com.merkert.morris.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class MillsTest {

    Board board = mock(Board.class);
    Mills sut = new Mills(board);

    @Test
    void hasMill_horizontalTrue_EdgeCase() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.BLACK, new Position(0, 1));
        Man blackThree = new Man(Color.BLACK, new Position(0, 2));
        when(board.getMan(0, 0)).thenReturn(blackOne);
        when(board.getMan(0, 1)).thenReturn(blackTwo);

        assertTrue(sut.hasMill(blackThree));
    }

    @Test
    void hasMill_horizontalFalseBecauseWhite() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.WHITE, new Position(0, 1));
        Man blackThree = new Man(Color.BLACK, new Position(0, 2));
        when(board.getMan(0, 0)).thenReturn(blackOne);
        when(board.getMan(0, 1)).thenReturn(blackTwo);

        assertFalse(sut.hasMill(blackThree));
    }

    @Test
    void hasMill_horizontalFalseBecauseOnlyTwo() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.BLACK, new Position(0, 2));
        when(board.getMan(0, 0)).thenReturn(blackOne);

        assertFalse(sut.hasMill(blackTwo));
    }

    @Test
    void hasMill_horizontalTrue_MiddleCase() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.BLACK, new Position(0, 2));
        Man blackThree = new Man(Color.BLACK, new Position(0, 1));
        when(board.getMan(0, 0)).thenReturn(blackOne);
        when(board.getMan(0, 2)).thenReturn(blackTwo);

        assertTrue(sut.hasMill(blackThree));
    }

    @Test
    void hasMill_horizontalFalse_becauseWrongDistricts() {
        Man blackOne = new Man(Color.BLACK, new Position(0, 0));
        Man blackTwo = new Man(Color.BLACK, new Position(0, 2));
        Man blackThree = new Man(Color.BLACK, new Position(1, 1));
        when(board.getMan(0, 0)).thenReturn(blackOne);
        when(board.getMan(0, 2)).thenReturn(blackTwo);

        assertFalse(sut.hasMill(blackThree));
    }

}
