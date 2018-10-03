package com.merkert.morris.controller;

import com.merkert.morris.model.Board;
import com.merkert.morris.model.Color;
import com.merkert.morris.model.Man;
import com.merkert.morris.model.Position;

public class Game {

    private static Board board;
    private static Mills mills;
    private int round = 1;

    public Game() {
        this.board = new Board();
        this.mills = new Mills(board);
    }

    private void turn(Color color) {
        if (round <= 9) {
            // get new Position
            // setMan(color, district, position);
            // check if mill and
        } else {
            // get a Man
            // get a new Position
            // moveMan
        }
        // if no success: try again
        // if success: check mills
        // if mill: remove stone
        round++;
    }

    private boolean setMan(Color color, int district, int position) {
        return board.setMan(new Man(color, new Position(district, position)));
    }

}
