
/**
 * Monster class.
 */
public class Monster extends Character
{
    private int rewardExp;
    private int rewardGold;
    private String itemDrop;
    private String itemName;
    private Room currentRoom;

    public Monster(String monsterName, String monsterWeapon, int hp, int str, int spe, int def, int dex, String item, int reward, String drop)
    {
        super.name = monsterName;
        super.weapon = monsterWeapon;
        super.isAlive = true;
        super.health = hp;
        super.maxHealth = hp;
        super.strength = str;
        super.speed = spe;
        super.defense = def;
        super.dexterity = dex;
        itemName = item;
        rewardExp = reward;
        rewardGold = reward;
        itemDrop = drop;
    }
    
    public void setRewardExp(int Exp){
        rewardExp = Exp;
    }
    
    public void setRewardGold(int Gold){
        rewardGold = Gold;
    }
    
    public void setItemName(String item){
        itemName = item;
    }
    
    public void setCurrentRoom(Room roomName){ // Sets the current room for the Monster.
        currentRoom = roomName;
    }
    
    public int getRewardExp(){ // Getter methods for Monster class.
        return rewardExp;
    }
    
    public int getRewardGold(){
        return rewardGold;
    }
    
    public String getItemDrop(){
        return itemDrop;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public Room getCurrentRoom(){ // Gets the current room of the Monster
        return currentRoom;
    }
    
        public String printCurrentRoomName(){ // prints out current Room of the Monster.
        return currentRoom.getRoomName();
    }
    

    
    

}
