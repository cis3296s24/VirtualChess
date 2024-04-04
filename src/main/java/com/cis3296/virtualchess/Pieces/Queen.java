package com.cis3296.virtualchess.Pieces;

public class Queen extends Piece {

    /**
     * Constructor for a piece in the board
     * @param x - The X position of the square in the board
     * @param y - The X position of the square in the board
     */
    public Queen(int x, int y, String color){
        super(x, y, color);
        this.type = "queen";
        setImage();
    }

}
