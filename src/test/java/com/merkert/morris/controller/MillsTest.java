package com.merkert.morris.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.merkert.morris.controller.Mills;
import com.merkert.morris.model.Board;
import com.merkert.morris.model.Color;
import com.merkert.morris.model.Man;
import com.merkert.morris.model.Position;

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
        Man whiteOne = new Man(Color.WHITE, new Position(0, 1));
        Man blackThree = new Man(Color.BLACK, new Position(0, 2));
        when(board.getMan(0, 0)).thenReturn(blackOne);
        when(board.getMan(0, 1)).thenReturn(whiteOne);

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

    @Test
    void hasMill_verticalTrue() {
        Man whiteOne = new Man(Color.WHITE, new Position(0, 1));
        Man whiteTwo = new Man(Color.WHITE, new Position(1, 1));
        Man whiteThree = new Man(Color.WHITE, new Position(2, 1));
        when(board.getMan(0, 1)).thenReturn(whiteOne);
        when(board.getMan(1, 1)).thenReturn(whiteTwo);
        when(board.getMan(2, 1)).thenReturn(whiteThree);

        assertTrue(sut.hasMill(whiteTwo));
    }

    @Test
    void hasMill_verticalFalse_becauseBlack() {
        Man whiteOne = new Man(Color.WHITE, new Position(0, 1));
        Man blackOne = new Man(Color.BLACK, new Position(1, 1));
        Man whiteTwo = new Man(Color.WHITE, new Position(2, 1));
        when(board.getMan(0, 1)).thenReturn(whiteOne);
        when(board.getMan(1, 1)).thenReturn(blackOne);
        when(board.getMan(2, 1)).thenReturn(whiteTwo);

        assertFalse(sut.hasMill(whiteTwo));
    }

    @Test
    void hasMill_verticalFalse_becauseEmpty() {
        Man whiteOne = new Man(Color.WHITE, new Position(0, 1));
        Man whiteTwo = new Man(Color.WHITE, new Position(2, 1));
        when(board.getMan(0, 1)).thenReturn(whiteOne);
        when(board.getMan(2, 1)).thenReturn(whiteTwo);

        assertFalse(sut.hasMill(whiteTwo));
    }

}
