package com.models;

import java.util.Random;

public class Room {

    private int centerX;
    private int centerY;
    private int upLeftX;
    private int upLeftY;
    private int lowRightX;
    private int lowRightY;
    
    public Room(int maxMapIndex, int maxWidth, int maxHeight) {
        Random roomGenerator = new Random();
        centerX = roomGenerator.nextInt(maxMapIndex);
        centerY = roomGenerator.nextInt(maxMapIndex);

        //make sure minimum room width has a lower bound, do the floor first to make sure that a size of 1 will always give 1.
        int roomWidth = roomGenerator.nextInt(maxWidth/2) + (int) Math.ceil(maxWidth/2.0);
        int roomHeight = roomGenerator.nextInt(maxHeight/2) + (int) Math.ceil(maxHeight/2.0);
        
        //integer division same as math.floor
        upLeftX = adjustRoomBound(maxMapIndex, centerX - roomWidth/2);
        upLeftY = adjustRoomBound(maxMapIndex, centerY - roomHeight/2);
        lowRightX = adjustRoomBound(maxMapIndex, centerX + (int) Math.ceil(roomWidth/2.0));
        lowRightY = adjustRoomBound(maxMapIndex, centerY + (int) Math.ceil(roomHeight/2.0));
        //only case where this happens is on the max index edges, only need to edit the X coordinate
        if(upLeftX == lowRightX) {
            upLeftX = adjustRoomBound(maxMapIndex, upLeftX - (int) Math.ceil(roomWidth/2.0));
        } else if (upLeftY == lowRightY) {
            upLeftY = adjustRoomBound(maxMapIndex, upLeftY - (int) Math.ceil(roomHeight/2.0));
        }
    }
    
    /**
     * Method to take a coordinate and bring it into the current map bounds
     * 
     * @param maxMapIndex map size - 1
     * @param currentCoord the coordinate to adjust
     * 
     * @return the adjusted coordinate
     */
    private int adjustRoomBound(int maxMapIndex, int currentCoord) {
        if(currentCoord < 0) {
            return 0;
        } else if (currentCoord > maxMapIndex) {
            return maxMapIndex;
        } else {
            return currentCoord;
        }
    }
    
    //basic intersect method
    public boolean intersects(Room room) {
        return (upLeftX <= room.getLowRightX() && lowRightX >= room.getUpLeftX() &&
                upLeftY <= room.getLowRightY() && lowRightY >= room.getUpLeftY());
    }
    
    //getters
    public int getCenterX() {
        return centerX;
    }
    public int getCenterY() {
        return centerY;
    }
    public int getUpLeftX() {
        return upLeftX;
    }
    public int getUpLeftY() {
        return upLeftY;
    }
    public int getLowRightX() {
        return lowRightX;
    }
    public int getLowRightY() {
        return lowRightY;
    }
    public int getHeight() {
        return lowRightY - upLeftY;
    }
    public int getWidth() {
        return lowRightX - upLeftX;
    }
}
