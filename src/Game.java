
/**
Heroes and Monsters 
Game class.

 */

import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Game
{

    public static void main(String[] args){
        mainMenu();
    }

    public static void mainMenu(){
        String input;
        input = stringInput("--! Heroes and Monsters Adventure\nPlease select one of the following options.\n[1] New game\t[2] Load game\n[3] Help\t[4] Quit");
        if (input.equals("1")){
            final Character[] playerHero = new Hero[3];
            String[] heroTypes = {"Warrior", "Magician", "Archer"};
            String[] heroItems = {"Sword", "Staff", "Bow"};
            String[] tracker = {"first", "second", "third"};
            info("--! A new game has been created.\nYou will be able to create 3 heroes.");

            for (int i =0; i < playerHero.length; i++){
                input = stringInput("Your " + tracker[i] + " hero will be a " + heroTypes[i] + ".\n[1] Name Hero \t[2] About Hero ");
                if (input.equals("2") && heroTypes[i].equals("Warrior")){
                    info("Hero type: Warrior\nWarriors are strong, armoured fighters they excel at fighting enemies in close range combat.\nTheir primary attributes are Strength and Defense.\nBase stats: \nHP: 100/100\nLevel: 1\tWeapon: Sword\nStrength: 5 \tSpeed: 5\nDexterity: 5 \tDefense: 5");
                }
                else if (input.equals("2") && heroTypes[i].equals("Magician")){
                    info("Hero type: Magician\nMagicians are powerful sorcerors that use magic powers to take down their foes.\nTheir primary attributes are Strength and Speed.\nBase stats: \nHP: 100/100\tWeapon: Staff\nStrength: 5 \tSpeed: 5\nDexterity: 5 \tDefense: 5");
                }
                else if (input.equals("2") && heroTypes[i].equals("Archer")){
                    info("Hero type: Archer\nArchers are quick and cunning heroes, they are skilled with the bow.\nTheir primary attributes are Speed and Dexterity.\nBase stats: \nHP: 100/100\tWeapon: Bow\nStrength: 5 \tSpeed: 5\nDexterity: 5 \tDefense: 5");
                }

                input = stringInput("What is your Hero: " + heroTypes[i] + "'s name?");
                playerHero[i] = new Hero(input, heroItems[i],heroTypes[i]);
                info("--! You have created a Hero called " + playerHero[i].getName() + ".");

                int statCounter = 10;

                input = stringInput("Attributes will be randomly selected and applied to this Hero.\nEnter any key to continue.");
                info("--! Adding random attributes to " + playerHero[i].getName());
                for (int k = 1; k <= 10; k++){
                    int j = randomInt(1,4);
                    if (j == 1){
                        playerHero[i].addStrength();
                        statCounter--;
                    }
                    else if (j == 2){
                        playerHero[i].addSpeed();
                        statCounter--;
                    }
                    else if (j == 3){
                        playerHero[i].addDexterity();
                        statCounter--;
                    }
                    else {
                        playerHero[i].addDefense();
                        statCounter--;
                    }
                }

                info("--! Attributes have been randomly applied to " + playerHero[i].getName() + ".");
                info("Hero Sheet: " + playerHero[i].getName() + ", the " + heroTypes[i]);
                info("Strength: " + playerHero[i].getStrength() + "\tSpeed: " + playerHero[i].getSpeed());
                info("Dexterity: " + playerHero[i].getDefense() + "\tDefense: " + playerHero[i].getDefense());
                input = stringInput("Enter any key to continue.");
            }

            System.out.println("--! Character creation is now completed.\nThis is a summary of the heroes in your game.");
            for (int i = 0; i < playerHero.length; i++){
                info("Your " + tracker[i] + " hero is called " + playerHero[i].getName() + ", the " + heroTypes[i] + ".");
            }
            input = stringInput("Press any key to continue.");
            boolean newGame = true; // A new game was created.
            generateMap(playerHero, newGame);
        }
        else if (input.equals("2")){
            try {
                BufferedReader inStream = new BufferedReader(new FileReader("data.txt")); // Reads from a file
                Hero[] gameHero = new Hero[3];
                String line; // Used to filter ArrayList
                for (int i =0; i<gameHero.length; i++){

                    gameHero[i] = new Hero();    
                    line = inStream.readLine();
                    gameHero[i].setName(inStream.readLine());
                    gameHero[i].setWeapon(inStream.readLine());
                    gameHero[i].setIsAlive(Boolean.parseBoolean(inStream.readLine()));
                    gameHero[i].setHealth(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setMaxHealth(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setLevel(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setExp(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setMaxExp(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setGold(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setStrength(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setSpeed(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setDexterity(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setDefense(Integer.parseInt(inStream.readLine()));
                    gameHero[i].setInBattle(Boolean.parseBoolean(inStream.readLine()));
                    gameHero[i].setClassType(inStream.readLine());
                    line = inStream.readLine(); // Holds the ArrayList string inside.
                    line = line.replace("[", "").replace("]","").replace(",",""); // Replaces specific characters from the String.
                    List<String> inv = new ArrayList<String>(Arrays.asList(line.split(" "))); // Splits the space
                    gameHero[i].setInventory(inv);
                }
                inStream.close();
                Character[] heroes = (Character[]) gameHero; // Cast into Character types
                for (int i = 0; i< heroes.length; i++){
                    info(heroes[i].getName() + "'s data successfully loaded.");
                }
                line = stringInput("Press any key to continue.");
                boolean newGame = false; // Changed to saved game state.
                generateMap(heroes, newGame); 
            }
            catch(IOException e){ // Catches an IOException if found, will print an error message.
                info("File not found!");
                mainMenu();
            }
        }
        else if (input.equals("3")){
            input = stringInput("--! Welcome to Heroes and Monsters Adventure!\nThe aim of the game is to explore the map, fight various monsters including powerful bosses.\nYou can also collect unique items which can be sold or used. \nThe game will end once all heroes have died.\nUse the in-game controls to interact.\nGood Luck!\n[1] Main menu \t[2] Quit");
            if (input.equals("1")){
                mainMenu();
            }
            else {
                return;
            }
        }
        else if (input.equals("4")){
            return;
        }
        else {
            System.out.println("You did not select an option.");
            mainMenu();
        }

    }

    public static Room[] createRooms(Room[] room){

        room[0] = new Room("Abandoned Castle","--! You can travel North/South from here.", 0, 0); // Constructs new instance of Room
        room[1] = new Room("Castle Courtyard", "--! You can travel North/South from here." ,-1, 0);
        room[2] = new Room("Castle Gate", "--! You can travel North/South/East from here.", -2, 0);
        room[3] = new Room("Great Eastern Valley", "--! You can travel North/East/West from here.", -2, 1);
        room[4] = new Room("Great Southern Valley", "--! You can travel North/East/South/West from here.", -3,0); 
        room[5] = new Room("Grand Plains", "--! You can travel North/East/South from here.", -1,1);
        room[6] = new Room("Grand Mountains", "--! You can travel North/South from here.", 0,1); 
        room[7] = new Room("Grand Summit", "--! You can travel South from here.", 1,1);
        room[8] = new Room("Fort Grey", "--! You can travel West from here.", -1,2);
        room[9] = new Room("Troll Camp", "--! You can travel South/West from here.", -2,2); 
        room[10] = new Room("Troll Cave Entry", "--! You can travel North/South/West from here.", -3,2);
        room[11] = new Room("Troll Cave ", "--! You can travel North from here.", -4,2);
        room[12] = new Room("Redwater River", "--! You can travel East/South/West from here.", -3,1);
        room[13] = new Room("Redwater Lane", "--! You can travel North/West from here.", -4,1);
        room[14] = new Room("Southern Hills", "--! You can travel North/East from here.", -4,0);
        room[15] = new Room("Throne Room", "--! You can travel North/South from here.", 1,0); //*
        room[16] = new Room("Hidden Passage", "--! You can travel North/South from here.", 2,0);
        room[17] = new Room("Lower Crypt", "--! You can travel North/South from here.", 3,0);
        room[18] = new Room("The Tomb", "--! You can travel South from here.", 4,0);
        room[19] = new Room("Steep Road", "--! You can travel North/South/East from here.", -4,-1);
        room[20] = new Room("Town", "--! You can travel North/West from here.", -5,-1);
        room[21] = new Room("Marketplace", "--! You can travel East from here.", -5,-2);
        room[22] = new Room("Ancient Quarry", "--! You can travel East/South from here.", -3,-1);
        // Room exits ** Adds all possible exits to each room.
        room[0].addExit(Direction.S, room[1]); 
        room[0].addExit(Direction.N, room[15]);
        room[1].addExit(Direction.N, room[0]); 
        room[1].addExit(Direction.S, room[2]); 
        room[2].addExit(Direction.N, room[1]); 
        room[2].addExit(Direction.S, room[4]); 
        room[2].addExit(Direction.E, room[3]); 
        room[3].addExit(Direction.W, room[2]); 
        room[3].addExit(Direction.E, room[9]);
        room[3].addExit(Direction.N, room[5]); 
        room[4].addExit(Direction.N, room[2]);
        room[4].addExit(Direction.E, room[12]);
        room[4].addExit(Direction.S, room[14]);
        room[4].addExit(Direction.W, room[22]);
        room[5].addExit(Direction.N, room[6]);
        room[5].addExit(Direction.S, room[3]); 
        room[5].addExit(Direction.E, room[8]);
        room[6].addExit(Direction.N, room[7]);
        room[6].addExit(Direction.S, room[5]);
        room[7].addExit(Direction.S, room[6]);
        room[8].addExit(Direction.W, room[5]);
        room[9].addExit(Direction.W, room[3]);
        room[9].addExit(Direction.S, room[10]);
        room[10].addExit(Direction.N, room[9]);
        room[10].addExit(Direction.S, room[11]);
        room[10].addExit(Direction.W, room[12]);
        room[11].addExit(Direction.N, room[10]);
        room[12].addExit(Direction.E, room[10]);
        room[12].addExit(Direction.S, room[13]);
        room[12].addExit(Direction.W, room[4]);
        room[13].addExit(Direction.N, room[12]);
        room[13].addExit(Direction.W, room[14]);
        room[14].addExit(Direction.N, room[4]);
        room[14].addExit(Direction.E, room[13]);
        room[15].addExit(Direction.N, room[16]);
        room[15].addExit(Direction.S, room[0]);
        room[16].addExit(Direction.S, room[15]);
        room[16].addExit(Direction.N, room[17]);
        room[17].addExit(Direction.S, room[16]);
        room[17].addExit(Direction.N, room[18]);
        room[18].addExit(Direction.S, room[17]);
        room[19].addExit(Direction.S, room[20]);
        room[19].addExit(Direction.N, room[22]);
        room[19].addExit(Direction.E, room[14]);
        room[20].addExit(Direction.N, room[19]);
        room[20].addExit(Direction.W, room[21]);
        room[21].addExit(Direction.E, room[20]);
        room[22].addExit(Direction.E, room[4]);
        room[22].addExit(Direction.S, room[19]);

        return room;
    }

    public static Monster[] createMonsters(Character[] monster, Room[] room, String[] item){
        Monster[] monsters = (Monster[]) monster; // Casting Character type into Monster.
        String[] monsterName = {"Troll", "Bandit", "Wolf", "Skeleton", "Dark Knight", "Thief"};
        String[] monsterWeapon = {"Club","Knife", "Claws", "Skeleton Hand", "Sword", "Knife"};
        int[] monsterStr = {5,7,12,10,13,8};
        int[] monsterSpe = {8,5,8,3,10,6};
        int[] monsterDex = {2,3,5,5,12,3};
        int[] monsterDef = {3,5,2,4,14,5};
        int[] monsterHP = {50,70,40,60,100,75};
        int[] monsterReward = {15,25,50,70,100,20};
        int[][] monsterRoom = { {9,10,9} , {12,13,19} , {5,6,7} , {1,1,2} , {0, 15, 15} , {3,4,5}}; // A monster's is generated into a specific room.
        for (int i =0; i<monster.length;i++){
            int number = randomInt(0,6);
            int pickRoom = randomInt(0,3);
            int randomItem = randomInt(0,11);
            // info("Number : " + number); // See what number was chosen randomly.
            monsters[i] = new Monster(monsterName[number], monsterWeapon[number], monsterHP[number], monsterStr[number], monsterSpe[number], monsterDex[number], monsterDef[number], item[number], monsterReward[number], item[randomItem]);
            monsters[i].setCurrentRoom(room[monsterRoom[number][pickRoom]]); // Randomly sets a monster's room.
        }

        for(int i = 0; i<monster.length;i++){ // Check monster locations
            info(monsters[i].getName() + " is in " + monsters[i].printCurrentRoomName());
        }    
        return monsters;
    }

    public static Boss[] createBosses(Room[] room){ // Creates a list of Boss objects and assigns them to a room.
        Character[] boss = new Boss[2];
        Boss[] bosses = (Boss[]) boss;
        String[] bossName = {"Troll King", "Bandit Leader"};
        String[] bossWeapon = {"Great Club", "Deadly Knife"};
        int[] bossStr = {20,10};
        int[] bossSpe  = {15,5};
        int[] bossDex = {4,5};
        int[] bossDef = {5,5};
        int[] bossHP = {120,100};
        int[] bossReward = {120,70}; // 8 ,11
        String[] bossDesc = {"--! Boss Encounter\nThis is the cave of the Troll King. There is no escape!", "--! Boss Encounter\nThis is the fortress of the Bandit Leader. There is no escape!"};
        int[][] bossRoom = {{11},{8}};
        int zero = 0;

        for(int i=0;i<boss.length;i++){
            bosses[i] = new Boss(bossName[i], bossWeapon[i], bossHP[i], bossHP[i], bossStr[i], bossSpe[i], bossDex[i],bossDef[i], bossDesc[i], bossWeapon[i]);
            bosses[i].setCurrentRoom(room[bossRoom[i][0]]);
        }

        return bosses;
    }    

    public static void generateMap(Character[] playerHero, boolean newGame) { // A method that generates the entire world (contains information)

        // Rooms ** Creates a list of rooms for the characters to explore.
        Room rooms[] = new Room[25]; // Number of rooms.     
        Room room[] = createRooms(rooms); // Creates the rooms.

        // Items ** A list of items that can be collected in-game.
        String[] item = {"Potion", "Bread", "Berries", "Apple", "Grand Potion", 
                "Potato", "Salmon" , "Pebbles", "Artifact", "Relic", "Stone", "Shard"};

        // Monster ** Monster objects for the Hero to fight.
        Character[] monster = new Monster[50]; 
        Monster[] monsters = createMonsters(monster, room, item);

        // Bosses ** Add bosses to the map for the Hero to fight.
        Boss[] boss = createBosses(room);
        Hero[] gameHero = (Hero[]) playerHero; 

        // Sets up Hero room.
        for(int i = 0; i < playerHero.length; i++){
            gameHero[i].setCurrentRoom(room[20]); // Sets the current room position of a Hero.
        }

        if (newGame == true){ // Check if a new game was created.
            for (int i=0; i < playerHero.length; i++){
                gameHero[i].addToInventory(item[0]); // Give a specific item to each Hero, E.g. Potion and Bread.
                gameHero[i].addToInventory(item[1]);
            }
        }

        MainInterface menu = new MainInterface("Heroes and Monsters Adventure - Menu", gameHero[0], gameHero[1], gameHero[2]);
        menu.addWindowListener(new WindowAdapter(){ // Close window option
                public void windowClosing(WindowEvent ev){
                    System.exit(0); // Quits the game.
                }
            });
        runMap(gameHero, item, monsters, boss, room); // Passes the casted types into the runMap method.
    }

    public static void runMap(Hero[] gameHero, String[] item, Monster[] monster, Boss[] boss, Room[] rooms){ // All Heroes movement across map
        boolean running = true;
        int heroSelect = 0;
        String input;
        for (int i=0; i <gameHero.length;i++){ // Checks if a hero is dead, places them in a specified room.
            if (gameHero[i].getIsAlive() == false){
                gameHero[i].setCurrentRoom(rooms[20]); // Dead Heroes cannot be moved.
            }
        }

        input = stringInput("--! Which Hero do you want to control?" + "\n[1] " + gameHero[0].getName() + ", the " + gameHero[0].getClassType() + "\n[2] " + gameHero[1].getName() + ", the " + gameHero[1].getClassType() + "\n[3] " + gameHero[2].getName()+ ", the " + gameHero[2].getClassType());
        if (!gameHero[0].getIsAlive() && !gameHero[1].getIsAlive() && !gameHero[2].getIsAlive()){
            info("--! All Heroes have died.");
            info("The adventure is over.");
        }

        if (input.equals("1") && gameHero[0].getIsAlive()){
            heroSelect = 0;
            info("--!" + " You have selected " + gameHero[heroSelect].getName());
        }
        else if (input.equals("2") && gameHero[1].getIsAlive()){
            heroSelect = 1;
            info("--!" + " You have selected " + gameHero[heroSelect].getName());
        }
        else if (input.equals("3") && gameHero[2].getIsAlive()){
            heroSelect = 2;
            info("--!" + " You have selected " + gameHero[heroSelect].getName());
        }
        else if (input.equals("1") && !gameHero[0].getIsAlive() || input.equals("2") && !gameHero[1].getIsAlive() || input.equals("3") && !gameHero[2].getIsAlive()){
            info("--! You cannot select that Hero. That Hero has died!");
        }
        else {
            info("You did not select an option.");
            runMap(gameHero, item, monster, boss, rooms);
        }
        while(running){
            if (gameHero[heroSelect].getIsAlive() == false){
                info(gameHero[heroSelect].getName() + " has died in battle, you will need to select another Hero to continue.");
                runMap(gameHero, item, monster, boss, rooms);
            }
            info((gameHero[heroSelect].getName() + " is currently standing at " + gameHero[heroSelect].printCurrentRoomPosition() + " (" + gameHero[heroSelect].printCurrentRoomName()+ ")"));
            info((gameHero[heroSelect].printCurrentRoomDescription()));
            input = stringInput("Controls: \nMove Hero - 'N','S','E','W'\nExamine Area - 'Examine'\nCheck Hero Inventory - 'Inventory'\nCheck Hero Status - 'Status'\nSwitch Hero - 'Switch'\nQuit Game - 'Quit'");
            input = input.toLowerCase();
            switch(input){
                case "quit": // Quits the game.
                input = stringInput("Are you sure you want to quit the game? You will lose all your progress.\n[1] Quit game\n[2] Back to game");
                if (input.equals("1")){
                    running = false;
                    break;
                }
                break;
                case "status": // Checks Hero status and used to save game progress.
                info("--! Hero Sheet: "+ gameHero[heroSelect].getName() + ", the " + gameHero[heroSelect].getClassType());
                info("Current Health: " + gameHero[heroSelect].getHealth() + "/" + gameHero[heroSelect].getMaxHealth());
                info("Current Level: " + gameHero[heroSelect].getLevel());
                info("Current Exp: " +gameHero[heroSelect].getExp() + "/" + gameHero[heroSelect].getMaxExp() + " (" + (gameHero[heroSelect].getMaxExp()-gameHero[heroSelect].getExp()) + " experience needed to level up).");
                info("Current Gold: " + gameHero[heroSelect].getGold());

                info("Strength: " + gameHero[heroSelect].getStrength() + "\tSpeed: " + gameHero[heroSelect].getSpeed());
                info("Dexterity: " + gameHero[heroSelect].getDefense() + "\tDefense: " + gameHero[heroSelect].getDefense());
                info("You can save the game progress by visiting the 'Town' using all your alive Heroes.");

                if (gameHero[0].printCurrentRoomName().equals("Town") && gameHero[1].printCurrentRoomName().equals("Town") && gameHero[2].printCurrentRoomName().equals("Town")){
                    input = stringInput("All alive Heroes are currently in the 'Town'. Would you like to save game progress? \n[1] Save\t[2] Go back");
                    if (input.equals("1")){
                        saveGame(gameHero);
                    }
                }
                input = stringInput("Press any key to continue.");
                break;
                case "inventory": // Shows Hero inventory.
                heroInventory(gameHero[heroSelect], item, input);
                input = stringInput("Enter any key to continue.");
                break;
                case "switch": // Switches Hero
                runMap(gameHero, item, monster, boss, rooms);
                break;
                case "examine": // Examines area in more detail
                info(gameHero[heroSelect].getName()+ ", the " + gameHero[0].getClassType() + " is examining " + gameHero[heroSelect].printCurrentRoomName());
                boolean found = false;
                for(int i=0;i<boss.length;i++){
                    if (gameHero[heroSelect].getCurrentRoom().equals(boss[i].getCurrentRoom()) && boss[i].getIsAlive()){
                        found = true;
                        int identified = i;
                        info(boss[identified].getBossDescription());
                        bossBattle(gameHero[heroSelect],boss[identified], item);
                    }
                }

                boolean searched = false;
                for (int i = 0; i < monster.length;i++){
                    if (gameHero[heroSelect].getCurrentRoom().equals(monster[i].getCurrentRoom()) && (monster[i].getIsAlive() && !searched)){ // keep looping until a monster is found, also checks if monster is alive/dead
                        int identified = i; // Once found identify it using an int
                        searched = true; 
                        info("While examining the area, " + (gameHero[heroSelect].getName()+ ", the " + gameHero[0].getClassType() + " found a " + monster[identified].getName()) + ".");
                        input = stringInput("What would you like to do? \n[1] Fight\n[2] Run away");
                        if (input.equals("1")){
                            battle(gameHero[heroSelect], monster[identified], item);
                        }
                        else {
                            int runChance = randomInt(0, gameHero[heroSelect].getSpeed());
                            if (runChance > monster[identified].getSpeed()){
                                info(gameHero[heroSelect].getName() + " successfully ran away from the " + monster[identified].getName() + ".");
                                input = stringInput("Enter any key to continue.");
                                break;
                            }
                            else {
                                info(gameHero[heroSelect].getName() + " failed to run away from the " + monster[identified].getName() + ".");
                                input = stringInput("Enter any key to continue.");
                                battle(gameHero[heroSelect], monster[identified], item);
                            }
                        }
                    }

                }
                if (!searched && !found){
                    info(gameHero[heroSelect].getName() + " examined " + gameHero[heroSelect].printCurrentRoomName() + " and found nothing!");
                    input = stringInput("Enter any key to continue.");
                }
                break;
                case "n": // Hero moves North.
                if (gameHero[heroSelect].move(Direction.N)){
                    System.out.println("--! " + gameHero[heroSelect].getName() + " has travelled North to " + gameHero[heroSelect].printCurrentRoomPosition() + " (" + gameHero[heroSelect].printCurrentRoomName()+ ")");
                }
                else {
                    System.out.println("--! You are trying to move in a direction that is blocked. Try another direction.");
                }
                input = stringInput("Enter any key to continue.");
                break;
                case "e": // Hero moves East.
                if (gameHero[heroSelect].move(Direction.E)){
                    System.out.println("--! " +gameHero[heroSelect].getName() + " has travelled East to " + gameHero[heroSelect].printCurrentRoomPosition() + " (" + gameHero[heroSelect].printCurrentRoomName()+ ")");
                }
                else {
                    System.out.println("--! You are trying to move in a direction that is blocked. Try another direction.");
                }
                input = stringInput("Enter any key to continue.");
                break;
                case "s": // Hero moves South.
                if (gameHero[heroSelect].move(Direction.S)){
                    System.out.println("--! " +gameHero[heroSelect].getName() + " has travelled South to " + gameHero[heroSelect].printCurrentRoomPosition() + " (" + gameHero[heroSelect].printCurrentRoomName()+ ")");
                }
                else {
                    System.out.println("--! You are trying to move in a direction that is blocked. Try another direction.");
                }
                input = stringInput("Enter any key to continue.");
                break;
                case "w": // Hero moves West.
                if (gameHero[heroSelect].move(Direction.W)){
                    System.out.println("--! " +gameHero[heroSelect].getName() + " has travelled West to " + gameHero[heroSelect].printCurrentRoomPosition() + " (" + gameHero[heroSelect].printCurrentRoomName()+ ")");
                }
                else {
                    System.out.println("--! You are trying to move in a direction that is blocked. Try another direction.");
                }
                input = stringInput("Enter any key to continue.");
                break;
            }

        }

    }

    public static void heroInventory(Hero gameHero, String[] item, String input){ // Inventory management.
        info("--! " + gameHero.getName() + "'s Inventory: ");
        int inventoryPosition = 1;
        for (int i =0; i<gameHero.getInventory().size();i++){
            System.out.println(inventoryPosition +" - " +gameHero.getInventory().get(i));
            inventoryPosition = inventoryPosition + 1;
        }
        input = stringInput("--! " +"What would you like to do?\n[1] Use an item\n[2] Sell an item\n[3] Destroy an item\n[4] Back to game");
        if (input.equals("1")){
            inventoryPosition = 1;
            info("--! " + gameHero.getName() + "'s Inventory: ");
            for (int i =0; i<gameHero.getInventory().size();i++){ // Print hero inventory
                System.out.println(inventoryPosition +" - " +gameHero.getInventory().get(i));
                inventoryPosition = inventoryPosition + 1; // accumulator
            }
            input = stringInput("Type the name of the item that you want to use.");
            int gainedHealth;
            if (input.equals("potion") || input.equals("Potion") && gameHero.getInventory().contains("Potion")){
                gameHero.getInventory().remove("Potion");
                gainedHealth = 100;
                addHealth(gameHero, gainedHealth);
                info("--! " + gameHero.getName() + " drank Potion.");
                info(gameHero.getName() + " has gained " + gainedHealth + " Health.");
                info(gameHero.getName() + " current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + ".");
            }
            else if (input.equals("bread") || input.equals("Bread") && gameHero.getInventory().contains("Bread")){
                gameHero.getInventory().remove("Bread");
                gainedHealth = 35;
                addHealth(gameHero, gainedHealth);
                info("--! " + gameHero.getName() + " ate Bread.");
                info(gameHero.getName() + " has gained " + gainedHealth + " Health.");
                info(gameHero.getName() + " current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + ".");
            }
            else if (input.equals("apple") || input.equals("Apple") && gameHero.getInventory().contains(input)){
                gameHero.getInventory().remove("Apple");
                gainedHealth = 15;
                addHealth(gameHero, gainedHealth);
                info("--! " + gameHero.getName() + " ate Apple.");
                info(gameHero.getName() + " has gained " + gainedHealth + " Health.");
                info(gameHero.getName() + " current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + ".");
            }
            else if (input.equals("berries") || input.equals("Berries") && gameHero.getInventory().contains("Berries")){
                gameHero.getInventory().remove("Berries");
                gainedHealth = 15;
                addHealth(gameHero, gainedHealth);
                info("--! " + gameHero.getName() + " ate Berries.");
                info(gameHero.getName() + " has gained " + gainedHealth + " Health.");
                info(gameHero.getName() + " current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + ".");
            }
            else if (input.equals("salmon") || input.equals("Salmon") && gameHero.getInventory().contains("Salmon")){
                gameHero.getInventory().remove("Salmon");
                gainedHealth = 75;
                addHealth(gameHero, gainedHealth);
                info("--! " + gameHero.getName() + " ate Salmon.");
                info(gameHero.getName() + " has gained " + gainedHealth + " Health.");
                info(gameHero.getName() + " current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + ".");
            }
            else if (gameHero.getInventory().contains(input)){
                info("--! You cannot use " + input + ", that item can only be sold or dropped.");
            }
            else {
                info("--! You do not have " + input + " in your Inventory.");
            }

        }
        else if(input.equals("2")){
            if (gameHero.printCurrentRoomName().equals("Marketplace") && gameHero.getInBattle() == false){
                inventoryPosition = 1;
                info("--! " + gameHero.getName() + "'s Inventory: ");
                for (int i =0; i<gameHero.getInventory().size();i++){ // print hero inventory
                    System.out.println(inventoryPosition +" - " +gameHero.getInventory().get(i));
                    inventoryPosition = inventoryPosition + 1; // accumulator
                }
                input = stringInput("Type the name of the item that you want to sell.");
                int gainedGold;
                if (input.equals("pebbles") || input.equals("Pebbles") && gameHero.getInventory().contains("Pebbles") || input.equals("stone") && gameHero.getInventory().contains("Stone")){
                    gameHero.getInventory().remove(input);
                    gainedGold = 5;
                    gameHero.setGold(gameHero.getGold() + gainedGold);
                    info("--!" + gameHero.getName() + " sold " + input +  " for " + gainedGold);
                    info(gameHero.getName() + " current Gold: " + gameHero.getGold() + ".");
                }
                else if (input.equals("artifact") || input.equals("Artifact") && gameHero.getInventory().contains("Artifact")){
                    gameHero.getInventory().remove("Artifact");
                    gainedGold = 100;
                    gameHero.setGold(gameHero.getGold() + gainedGold);
                    info("--!" + gameHero.getName() + " sold Artifact for " + gainedGold);
                    info(gameHero.getName() + " current Gold: " + gameHero.getGold() + ".");
                }
                else if (input.equals("relic") || input.equals("Relic") && gameHero.getInventory().contains("Relic") || input.equals("shard") && gameHero.getInventory().contains("Shard")){
                    gameHero.getInventory().remove(input);
                    gainedGold = 25;
                    gameHero.setGold(gameHero.getGold() + gainedGold);
                    info("--!" + gameHero.getName() + " sold " + input + " for " + gainedGold);
                    info(gameHero.getName() + " current Gold: " + gameHero.getGold() + ".");
                }
                else if (input.equals(input) && gameHero.getInventory().contains(input)){

                    gameHero.getInventory().remove(input);
                    gainedGold = 50;
                    gameHero.setGold(gameHero.getGold() + gainedGold);
                    info("--!" + gameHero.getName() + " sold " + input + " for " + gainedGold);
                    info(gameHero.getName() + " current Gold: " + gameHero.getGold() + ".");
                }
            }
            else if (gameHero.getInBattle() == true){
                info("--! You cannot sell your items while in a battle!");
            }
            else {
                info("--! You cannot sell your items in this area.\nVisit the Marketplace to sell your items.");
            }
        }
        else if (input.equals("3")){
            inventoryPosition = 1;
            info("--! " + gameHero.getName() + "'s Inventory: ");
            for (int i =0; i<gameHero.getInventory().size();i++){ // Prints hero inventory.
                System.out.println(inventoryPosition +" - " +gameHero.getInventory().get(i));
                inventoryPosition = inventoryPosition + 1; // Accumulator.
            }

            input = stringInput("Type the name of the item that you want to destroy.");

            for (int i =0; i<gameHero.getInventory().size();i++){ // Cycles through hero inventory.
                if (input.equals(gameHero.getInventory().get(i))){ // Checks if item matches.
                    String removeItem = gameHero.getInventory().get(i); 
                    gameHero.getInventory().remove(removeItem); 
                    info("--! " + gameHero.getName() + " destroyed the item " + removeItem);
                }
                else {
                    info("--! Item not found in Inventory.");
                }
            }
        }
        else {
            return;
        }
    }

    public static void battle(Hero gameHero, Monster monster, String[] items){ // Monster battle scenario.
        boolean inBattle = true;
        gameHero.setInBattle(inBattle);
        String input;
        info("" + gameHero.getName() + " is in battle with " + monster.getName() + "!");
        while (inBattle && gameHero.getIsAlive() && monster.getIsAlive()){
            info("--! Battle Log: ");
            info(gameHero.getName() + "'s Current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + "\t- " + monster.getName() + "'s Current Health: " + monster.getHealth() + "/" + monster.getMaxHealth());
            input = stringInput("What would you like to do? \n[1] Attack\n[2] View inventory [3] Run away");
            if (input.equals("1")){
                int BONUS = randomInt(0,10); // A random bonus applied to battle formula.
                int heroHitChance = randomInt(0, gameHero.getSpeed() + BONUS);
                if (heroHitChance > monster.getDexterity()){ // Chance of hero attack. 
                    int damage = (randomInt(0,(gameHero.getStrength() + BONUS)) - monster.getDefense());
                    if (damage < 0){
                        damage = 0;
                    }
                    monster.setHealth(monster.getHealth()-damage);
                    info(gameHero.getName() + " attacks the " +  monster.getName() + " using their " + gameHero.getWeapon() + " dealing " + damage + " damage.");
                }
                else {
                    info(gameHero.getName() + " misses an attack on the " + monster.getName() + " using their " + gameHero.getWeapon() + ".");
                }
                int monsterHitChance = randomInt(0, monster.getSpeed() + BONUS);
                if (monsterHitChance > gameHero.getDexterity()){ // Chance of monster attack.
                    int damage = (randomInt(0,(monster.getStrength() + BONUS)) - gameHero.getDefense());
                    if (damage < 0){
                        damage = 0;
                    }
                    gameHero.setHealth(gameHero.getHealth()-damage);
                    info(monster.getName() + " attacks " +  gameHero.getName() + " using their " + monster.getWeapon() + " dealing " + damage + " damage.");
                }
                else {
                    info(monster.getName() + " misses an attack on " + gameHero.getName() + " using their " + monster.getWeapon() + ".");
                }
                if (monster.getHealth() <= 0){
                    monster.setIsAlive(false);
                    gameHero.setInBattle(false);
                    inBattle = false;
                    info(monster.getName() + " has died.");
                    info(gameHero.getName() + " has gained: ");
                    info(" + " + monster.getRewardExp() + " Experience.");
                    info(" + " + monster.getRewardGold() + " Gold.");
                    gameHero.setExp(gameHero.getExp() + monster.getRewardExp());
                    gameHero.setGold(gameHero.getGold() + monster.getRewardGold());
                    if (gameHero.getExp() >= gameHero.getMaxExp()){
                        gameHero.setLevel(gameHero.getLevel() + 1);
                        gameHero.setMaxExp(gameHero.getMaxExp()*2);
                        info(gameHero.getName() + " has levelled up! \nThe Hero is now Level " + gameHero.getLevel());
                        addAttributes(gameHero);
                    }

                    input = stringInput("Would you like to loot the " +  monster.getName() + "?\n[1] Loot \t[2] Leave");
                    if (input.equals("1") && gameHero.getInventory().size() <= 10 && !monster.getItemDrop().equals("Nothing")){
                        info(gameHero.getName() + " finds " + monster.getItemDrop() + ".");
                        gameHero.getInventory().add(monster.getItemDrop());
                    }
                    else if (input.equals("2")){
                        info(gameHero.getName() + " leaves the battle zone.");
                    }
                    else if (gameHero.getInventory().size() > 10){
                        info("You cannot loot any more items! The inventory item limit is 10.\nYou can destroy, use or sell your items to gain more inventory space.");
                    }
                    input = stringInput("Enter any key to continue.");

                }
                else if (gameHero.getHealth() <= 0){
                    gameHero.setIsAlive(false);
                    inBattle = false;
                    info(gameHero.getName() + " has died.");
                    input =stringInput("Enter any key to continue.");
                }
            }
            else if (input.equals("2")){
                heroInventory(gameHero, items, input);
            }
            else if (input.equals("3")){
                int runChance = randomInt(0, gameHero.getSpeed());
                if (runChance > monster.getSpeed()){
                    info(gameHero.getName() + " successfully ran away from the " + monster.getName() + ".");
                    inBattle = false;
                    gameHero.setInBattle(false);
                    input = stringInput("Enter any key to continue.");
                }
                else {
                    info(gameHero.getName() + " failed to run away from the " + monster.getName() + ".");
                    
                    int BONUS = randomInt(0,10);
                    int monsterHitChance = randomInt(0, monster.getSpeed() + BONUS);
                    if (monsterHitChance > gameHero.getDexterity()){ // Chance of monster attack
                        int damage = (randomInt(0,(monster.getStrength() + BONUS)) - gameHero.getDefense());
                        if (damage < 0){
                            damage = 0;
                        }
                        gameHero.setHealth(gameHero.getHealth()-damage);
                        info(monster.getName() + " attacks " +  gameHero.getName() + " using their " + monster.getWeapon() + " dealing " + damage + " damage.");
                    }
                    else {
                        info(monster.getName() + " misses an attack on " + gameHero.getName() + " using their " + monster.getWeapon() + ".");
                    }
                    input = stringInput("Enter any key to continue.");
                    battle(gameHero, monster, items);
                }
            }
            else {
                info("You did not select an option.");
            }

        }
        return;
    }

    public static void addHealth(Hero gameHero, int addedHealth){ // Adds Health to the Hero.
        if (addedHealth + gameHero.getHealth() > gameHero.getMaxHealth()){
            gameHero.setHealth(gameHero.getMaxHealth());
        }
        else {
            gameHero.setHealth(gameHero.getHealth() + addedHealth);
        }
    }

    public static void addAttributes(Hero gameHero){ // Level up reward.
        int statCounter = 10;
        String input = stringInput("--! You have been awarded " + statCounter + " attribute points for levelling up.\nEnter any key to continue.");
        info("--! Adding random attributes to " + gameHero.getName());
        for (int k = 1; k <= 10; k++){
            int j = randomInt(1,4);
            if (j == 1){
                gameHero.addStrength();
                statCounter--;
            }
            else if (j == 2){
                gameHero.addSpeed();
                statCounter--;
            }
            else if (j == 3){
                gameHero.addDexterity();
                statCounter--;
            }
            else {
                gameHero.addDefense();
                statCounter--;
            }
        }

        info("--!" + gameHero.getName() + " attributes have been upgraded.");
        info(gameHero.getName() + " Current Attributes: ");
        info("Strength: " + gameHero.getStrength() + "\tSpeed:" + gameHero.getSpeed() + "\nDexterity: " + gameHero.getDexterity() + "\tDefense: " + gameHero.getDefense());
    }

    public static void bossBattle(Hero gameHero, Boss boss, String[] items){ // Boss battle scenario.
        boolean inBattle = true;
        gameHero.setInBattle(inBattle);
        String input;
        info("" + gameHero.getName() + " is in battle with " + boss.getName() + "!");
        while (inBattle && gameHero.getIsAlive() && boss.getIsAlive()){
            info("--! Battle Log: ");
            info(gameHero.getName() + "'s Current Health: " + gameHero.getHealth() + "/" + gameHero.getMaxHealth() + "\t " + boss.getName() + "'s Current Health: " + boss.getHealth() + "/" + boss.getMaxHealth());
            input = stringInput("What would you like to do? \n[1] Attack\n[2] View inventory [3] Run away");
            if (input.equals("1")){
                int BONUS = randomInt(0,10); // A random bonus applied to battle formula.
                int heroHitChance = randomInt(0, gameHero.getSpeed() + BONUS);
                if (heroHitChance > boss.getDexterity()){ // Chance of hero attack.
                    int damage = (randomInt(0,(gameHero.getStrength() + BONUS)) - boss.getDefense());
                    if (damage < 0){
                        damage = 0;
                    }
                    boss.setHealth(boss.getHealth()-damage);
                    info(gameHero.getName() + " attacks the " +  boss.getName() + " using their " + gameHero.getWeapon() + " dealing " + damage + " damage.");
                }
                else {
                    info(gameHero.getName() + " misses an attack on the " + boss.getName() + " using their " + gameHero.getWeapon() + ".");
                }
                int monsterHitChance = randomInt(0,boss.getSpeed() + BONUS);
                if (monsterHitChance > gameHero.getDexterity()){ // Chance of monster attack.
                    int damage = (randomInt(0,(boss.getStrength() + BONUS)) - gameHero.getDefense());
                    if (damage < 0){
                        damage = 0;
                    }
                    gameHero.setHealth(gameHero.getHealth()-damage);
                    info(boss.getName() + " attacks " +  gameHero.getName() + " using their " + boss.getWeapon() + " dealing " + damage + " damage.");
                }
                else {
                    info(boss.getName() + " misses an attack on " + gameHero.getName() + " using their " + boss.getWeapon() + ".");
                }
                if (boss.getHealth() <= 0){
                    boss.setIsAlive(false);
                    gameHero.setInBattle(false);
                    inBattle = false;
                    info(boss.getName() + " has died.");
                    info(gameHero.getName() + " has gained: ");
                    info(" + " + boss.getRewardExp() + " Experience.");
                    info(" + " + boss.getRewardGold() + " Gold.");
                    gameHero.setExp(gameHero.getExp() + boss.getRewardExp());
                    gameHero.setGold(gameHero.getGold() + boss.getRewardGold());
                    if (gameHero.getExp() >= gameHero.getMaxExp()){
                        gameHero.setLevel(gameHero.getLevel() + 1);
                        gameHero.setMaxExp(gameHero.getMaxExp()*2);
                        info(gameHero.getName() + " has levelled up! \nThe Hero is now Level " + gameHero.getLevel());
                        addAttributes(gameHero);
                    }

                    input = stringInput("Would you like to loot the " +  boss.getName() + "?\n[1] Loot \t[2] Leave");
                    if (input.equals("1") && gameHero.getInventory().size() <= 10 && !boss.getItemDrop().equals("Nothing")){
                        info(gameHero.getName() + " finds " + boss.getItemDrop() + ".");
                        gameHero.getInventory().add(boss.getItemDrop());
                    }
                    else if (input.equals("2")){
                        info(gameHero.getName() + " leaves the battle zone.");

                    }
                    else if (gameHero.getInventory().size() > 10){
                        info("You cannot loot any more items! The inventory item limit is 10.\nYou can destroy, use or sell your items to gain more inventory space.");
                    }
                    input = stringInput("Enter any key to continue.");
                    inBattle = false;
                    gameHero.setInBattle(inBattle);
                    break;
                }
                else if (gameHero.getHealth() <= 0){
                    gameHero.setIsAlive(false);
                    inBattle = false;
                    info(gameHero.getName() + " has died.");
                    input =stringInput("Enter any key to continue.");
                }
            }
            else if (input.equals("2")){
                heroInventory(gameHero, items, input);
            }
            else if (input.equals("3")){
                int runChance = randomInt(0, gameHero.getSpeed());
                if (runChance > boss.getSpeed()){
                    info(gameHero.getName() + " successfully ran away from the " + boss.getName() + ".");
                    input = stringInput("Enter any key to continue.");
                    inBattle = false;
                    gameHero.setInBattle(inBattle);
                    break;
                }
                else {
                    info(gameHero.getName() + " failed to run away from the " + boss.getName() + ".");
                    int BONUS = randomInt(0,10);
                    int monsterHitChance = randomInt(0,boss.getSpeed() + BONUS);
                    if (monsterHitChance > gameHero.getDexterity()){ // Chance of monster attack.
                        int damage = (randomInt(0,(boss.getStrength() + BONUS)) - gameHero.getDefense());
                        if (damage < 0){
                            damage = 0;
                        }
                        gameHero.setHealth(gameHero.getHealth()-damage);
                        info(boss.getName() + " attacks " +  gameHero.getName() + " using their " + boss.getWeapon() + " dealing " + damage + " damage.");
                    }
                    else {
                        info(boss.getName() + " misses an attack on " + gameHero.getName() + " using their " + boss.getWeapon() + ".");
                    }
                    input = stringInput("Enter any key to continue.");
                    bossBattle(gameHero, boss, items);
                }
            }
            else {
                info("You did not select an option.");
            }

        }
        return;
    }

    public static void saveGame(Hero[] gameHero){ // Saves the current state of the game to a file.
        try{
            PrintWriter outputStream = new PrintWriter(new FileWriter("data.txt"));
            for (int i=0; i < gameHero.length; i++){
                outputStream.println("Hero Sheet");
                outputStream.println(gameHero[i].getName());
                outputStream.println(gameHero[i].getWeapon());
                outputStream.println(gameHero[i].getIsAlive());
                outputStream.println(gameHero[i].getHealth());
                outputStream.println(gameHero[i].getMaxHealth());
                outputStream.println(gameHero[i].getLevel());
                outputStream.println(gameHero[i].getExp());
                outputStream.println(gameHero[i].getMaxExp());
                outputStream.println(gameHero[i].getGold());
                outputStream.println(gameHero[i].getStrength());
                outputStream.println(gameHero[i].getSpeed());
                outputStream.println(gameHero[i].getDexterity());
                outputStream.println(gameHero[i].getDefense());
                outputStream.println(gameHero[i].getInBattle());
                outputStream.println(gameHero[i].getClassType());
                outputStream.println(gameHero[i].getInventory());
            }
            outputStream.close();
            info("--! The game has successfully saved.");
        }
        catch(IOException e){
            info("An error occured while attempting to save the game.");
        }

    }

    public static String stringInput(String message){ // Gets String input from User.
        String input;
        Scanner scanner = new Scanner (System.in);
        System.out.println(message);
        input = scanner.nextLine();
        return input;
    }

    public static int intInput(int message){ // Gets int input from User.
        int input;
        Scanner scanner = new Scanner (System.in);
        System.out.println(message);
        input = scanner.nextInt();
        return input;
    }

    public static int randomInt (int min, int max){ // Returns a random int.
        Random rand = new Random();
        int number = rand.nextInt(max) + min;
        return number;
    }

    public static void info(String message){ // Basic print.
        System.out.println(message);
        return;
    }
}
