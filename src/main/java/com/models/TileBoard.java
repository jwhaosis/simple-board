package com.models;

import java.util.LinkedList;
import java.util.Random;

public class TileBoard {
    
    private int[][] tileBoard;
    
    private LinkedList<Room> rooms;
    
    public TileBoard(int boardSize, int maxRooms, int maxRoomWidth, int maxRoomHeight) {
        rooms = new LinkedList<Room>();
        tileBoard = generateBoard(boardSize, maxRooms, maxRoomWidth, maxRoomHeight);
    }
 
    /**
     * Method to generate a 2D int array that represents a board.
     * 
     * @param boardSize the dimensions of the board (always a square).
     * @param maxRooms the max number of rooms to be created.
     * @param maxRoomWidth the maximum width of a room.
     * @param maxRoomHeight the maximum height of a room.
     * 
     * @return the 2D int array with rooms and corridors
     */
    private int[][] generateBoard(int boardSize, int maxRooms, int maxRoomWidth, int maxRoomHeight) {        
        int[][] baseBoard = new int[boardSize][boardSize];
        for(int i = 0; i < maxRooms; i++) {
            Room tempRoom = new Room(boardSize - 1, maxRoomWidth, maxRoomHeight);
            for(Room room : rooms) {
                if(tempRoom.intersects(room)) {
                    tempRoom = null;
                    i--;
                    break;
                }
            }
            if(tempRoom != null) {
                rooms.add(tempRoom);
                for(int w = tempRoom.getUpLeftX(); w <= tempRoom.getLowRightX(); w++) {
                    for(int h = tempRoom.getUpLeftY(); h <= tempRoom.getLowRightY(); h++) {
                        baseBoard[w][h] = 1;
                    }
                }
            }
        }
        
        baseBoard = generateCorridors(baseBoard);
        
        //for testing only
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(baseBoard[i][j]);
            }
            System.out.println();
        }
        return baseBoard;
    }
    
    /**
     * Method to generate corridors between all rooms in the board, uses the instance variable rooms.
     * 
     * @param baseBoard the array to be edited
     * 
     * @return an int[][] with corridors marked as "2"
     */
    private int[][] generateCorridors(int[][] baseBoard){
        Room roomOne, roomTwo;
        roomOne = rooms.pollFirst();
        while(!rooms.isEmpty()) {
            roomTwo = rooms.pollLast();
            int direction = new Random().nextInt(1);
            Room startRoom, endRoom;
            if(direction == 0) {
                startRoom = roomOne;
                endRoom = roomTwo;
            } else {
                startRoom = roomTwo;
                endRoom = roomOne;
            }
            //setting up variables beforehand so we know how to adjust our loop variable
            int meetX, meetY, startX, startY, endX, endY;
            meetX = startRoom.getCenterX();
            meetY = endRoom.getCenterY();
            startX = Math.min(meetX, endRoom.getCenterX());
            startY = Math.min(meetY, startRoom.getCenterY());
            endX = Math.max(meetX, endRoom.getCenterX());
            endY = Math.max(meetY, startRoom.getCenterY());
            for(int i = startX; i <= endX; i++) {
                baseBoard[i][meetY] = 2;
            }
            for(int j = startY; j <= endY; j++) {
                baseBoard[meetX][j] = 2;
            }
            roomOne = roomTwo;
        }
        return baseBoard;
    }
    
    @Override
    public String toString() {
        int size = tileBoard.length;
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j< size; j++) {
                result.append(tileBoard[i][j]);
            }
        }
        return result.toString();
    }
}
