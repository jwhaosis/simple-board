package com.models.factory;

import com.models.TileBoard;

public class TileBoardFactory {
    public TileBoardFactory tileBoardFactory() {
        return new TileBoardFactory();
    }
    
    public TileBoard generateBasicBoard() {
        return new TileBoard(20, 5, 5, 5);
    }
    
    public TileBoard generateCustomBoard(int boardSize, int maxRooms, int maxRoomWidth, int maxRoomHeight) {
        return new TileBoard(boardSize, maxRooms, maxRoomWidth, maxRoomHeight);
    }
}
