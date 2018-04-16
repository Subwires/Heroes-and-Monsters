
/**
 * Hero class.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Hero extends Character // Inherits from Character class
{
    private int level;
    private int gold;
    private int exp;
    private int maxExp;
    private String classType;
    private List<String> inventory = new ArrayList<String>(); // ArrayList holds Hero's inventory information.
    private Room currentRoom; // a Room reference that keeps track of what room the hero is in.
    private boolean inBattle; // Checks whether Hero is in battle or not.
    

    public Hero(String heroName, String heroWeapon, String cType) // Constructor for Hero object
    {
        super.name = heroName;
        super.weapon = heroWeapon;
        super.isAlive = true;
        super.health = 100;
        super.maxHealth = 100;
        super.strength = 5;
        super.speed = 5;
        super.dexterity = 5;
        super.defense = 5;
        classType = cType;
        level = 1;
        gold = 0;
        exp = 0;
        maxExp = 100;
        inBattle = false;
    }
    
    public Hero(String heroName, String heroWeapon, boolean status, int hp, int maxHp, int lvl, int xp, int maxXp, int gd, 
        int str, int spe, int dex, int def, boolean battle, String cType, List<String> inv){ // Constructor overloading
        super.name = heroName;
        super.isAlive = status;
        super.health = hp;
        super.maxHealth = maxHp;
        super.weapon = heroWeapon;
        classType = cType;
        level = lvl;
        gold = gd;
        exp = xp;
        maxExp = maxXp;
        List<String> inventory = inv;
    }
    
    public Hero(){
        
    }
    
    // Getter methods for Hero class.
    public List<String> getInventory(){ // Gets the inventory of the Hero.
        return inventory;
    }
    
    public boolean getInBattle(){
        return inBattle;
    }
    
    public int getLevel (){
        return level;
    }
    
    public int getGold(){
        return gold;
    }
    
    public String getClassType(){
        return classType;
    }
    
    public int getMaxExp(){ // Gets max exp of the Hero.
        return maxExp;
    }
    
    public int getExp(){ // Gets the current exp of the Hero.
        return exp;
    }
    
    public boolean move (Direction direction){ // Moves a hero to a room using direction parameter.
        boolean moveHero = false; // checks whether Hero has moved or not.
        Optional<Room> next = currentRoom.getNextRoom(direction); 
        if (next.isPresent()){
            currentRoom = next.get();
            moveHero = true;
        }
        return moveHero;
        
    }
    
    public Room getCurrentRoom(){ // Gets the current room of the Hero
        return currentRoom;
    }
    
    public String printCurrentRoomName(){ // prints out current Room of the Hero.
        return currentRoom.getRoomName();
    }
    
    public String printCurrentRoomDescription(){
        return currentRoom.getRoomDescription();
    }
    
    public String printCurrentRoomPosition(){
        return currentRoom.getRoomPositionX() + "," + currentRoom.getRoomPositionY();
    }
    
    // Setter methods for Hero class.
    public void addToInventory (String inv){  // Adds an item to the Hero inventory.
        inventory.add(inv);
    }
    
    public void setCurrentRoom(Room roomName){ // Sets the current room for the Hero.
        currentRoom = roomName;
    }
    
    public void setClassType(String type){
        classType = type;
    }
    
    public void setExp(int experience){ // Sets the experience for a Hero.
        exp = experience;
    }
    
    public void setGold(int g){
        gold = g;
    }
    
    public void setInBattle(boolean check){
        inBattle = check;
    }
    
    public void setLevel(int lvl){
        level = lvl;
    }
    
    public void setMaxExp(int experience){
        maxExp = experience;
    }
    
    public void setInventory(List<String> inv){
        inventory = inv;
    }
    
}
