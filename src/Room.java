
/**
Room class.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Room 
{
    private String roomName;
    private String roomDescription;
    private Map<Direction,Room> exits = new HashMap<>();
    private int x;
    private int y;


    public Room(String rName, String rDesc, int posX, int posY) 
    {
        roomName = rName;
        roomDescription = rDesc;
        x = posX;
        y = posY;
    }
    
    public String getRoomName(){ // Gets the room name.
        return roomName;
    }
    
    public String getRoomDescription(){ // Gets the room description.
        return roomDescription;
    }
    
    public int getRoomPositionX(){
        return x;
    }
    
    public int getRoomPositionY(){
        return y;
    }
    
    public Optional<Room> getNextRoom(Direction direction){ // Gets the nextRoom.
        return Optional.ofNullable(exits.get(direction));
    }
    
    public void addExit(Direction direction, Room room){ // Adds an exit to an existing room.
        exits.put(direction,room);
    }
    
    public void setRoomName(String rName){ // Sets a room name.
        roomName = rName;
    }
    
    public void setRoomDescription(String rDesc){ // Sets room description.
        roomDescription = rDesc;
    }
    
    public void roomString(Hero currentRoom){
        System.out.println("You are currently standing at " + roomName + ".\n" + "Description : " + roomDescription + "Current position " + x + "," + y);
    }
    

}
