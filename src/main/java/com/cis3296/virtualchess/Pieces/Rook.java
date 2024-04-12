package com.cis3296.virtualchess.Pieces;

import com.cis3296.virtualchess.Coordinates;

import java.util.ArrayList;

public class Rook extends Piece {

    /**
     * Constructor for a Rook type piece
     * @param coordinates are the coordinates of the Rook on the board
     * @param color chooses the color of the piece
     */
    public Rook(Coordinates coordinates, String color){
        super(coordinates, color);
        this.type = "rook";
        setImage();
    }

    @Override
    public ArrayList<Coordinates> getMoveSet() {
        return null;
    }


}
