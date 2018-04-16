
/**
 * Character class.
 */
public class Character // Name of the class
{
    protected String name;
    protected String weapon;
    protected boolean isAlive;
    protected int health;
    protected int maxHealth;
    protected int strength;
    protected int speed;
    protected int dexterity;
    protected int defense;
    

    public Character()
    {
        
    }
    
    public void setName (String charName){ // Setter methods
        name = charName;
    }
    
    public void setWeapon(String wep){
        weapon = wep;
    }
    
    public void setHealth (int hp){
        health = hp;
    }
    
    public void setMaxHealth(int hp){
        maxHealth = hp;
    }
    
    public void setIsAlive(boolean status){
        isAlive = status;
    }
    
    public void setStrength(int str){
        strength = str;
    }
    
    public void setSpeed(int spe){
        speed = spe;
    }
    
    public void setDexterity(int dex){
        dexterity = dex;
    }
    
    public void setDefense(int def){
        defense = def;
    }
    
    public String getName(){ // Getter methods
        return name;
    }
    
    public String getWeapon(){
        return weapon;
    }
    
    public boolean getIsAlive(){
        return isAlive;
    }
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getDexterity(){
        return dexterity;
    }
    
    public int getDefense(){
        return defense;
    }
    
    public void addStrength(){
        strength = strength + 1;
    }
    
    public void addSpeed(){
        speed = speed + 1;
    }
    
    public void addDexterity(){
        dexterity = dexterity + 1;
    }
    
    public void addDefense(){
        defense = defense + 1;
    }
    


}
