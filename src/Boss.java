
/**
 * Boss class.
 */
public class Boss extends Character
{
    // instance variables - replace the example below with your own
    private int rewardExp;
    private int rewardGold;
    private String itemDrop;
    private String itemName;
    private String bossDescription;
    private Room currentRoom;

    public Boss(String bossName, String bossWeapon, int health, int maxHealth, int str, int spe, int dex, int def, String desc, String drop)
    {
        super.name = bossName;
        super.weapon = bossWeapon;
        super.isAlive = true;
        super.health = health;
        super.maxHealth = maxHealth;
        super.strength = str;
        super.speed = spe;
        super.dexterity = dex;
        super.defense = def;
        bossDescription = desc;
        itemDrop = drop;
    }

    public void setRewardExp(int Exp){
        rewardExp = Exp;
    }

    public void setRewardGold(int Gold){
        rewardGold = Gold;
    }

    public void setItemDrop(String value){ 
        itemDrop = value;
    }

    public void setItemName(String item){
        itemName = item;
    }

    public void setCurrentRoom(Room roomName){ // Sets the current room for the Boss.
        currentRoom = roomName;
    }

    public void setBossDescription(String bossDesc){
        bossDescription = bossDesc;
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

    public String getBossDescription(){
        return bossDescription;
    }
    
        public Room getCurrentRoom(){ // Gets the current room of the Boss
        return currentRoom;
    }
    
        public String printCurrentRoomName(){ // prints out current Room of the Boss.
        return currentRoom.getRoomName();
    }

}
