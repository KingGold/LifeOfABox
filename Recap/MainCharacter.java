
/**
 *(Main Character)
 * 
 * Nate Geschwill
 * 
 */
public class MainCharacter extends Character
{
    Item equippedItem;
    Item[] inventory;
    boolean death;
    public MainCharacter(int xx, int yy, Item i, int hlth, int wdth, int hght, int hX, int hY
    , Item[] invntry){
      //Constructor creates spaceship object
      super(xx,yy,hlth,wdth,hght,hX,hY);
      equippedItem = i;
      inventory = invntry;
    }
    
    public void movePlayer(double dirx, double diry){   //moves space in xdir and ydir direction
     x += dirx;
     y += diry;
    }
    
    public void setEquippedItem(Item i){      //sets item to equip slot
        equippedItem = i;
    }
    
    public Item getEquippedItem(){      //Returns item the player has    NOTE:Returns null if there is no item
        return equippedItem;
    }
    
    public Item getStoredItem(int num){
        return inventory[num];
    }
    
    public boolean checkForRoom(){
        for(int i=0; i<inventory.length; i++){
            if(inventory[i] == null){
                return true;
            }
        }
        return false;
    }
    
    public void setItem(Item it,int slot){    //Sets item into inventory slot
        inventory[slot] = it;
    }
    
    public void addItem(Item it){    //Sets item into EMPTY inventory slot
        for(int i=0; i<inventory.length; i++){
            if(inventory[i] == null){
                inventory[i] = it;
                break;
            }
        }
    }
    
    public void clearInventory(){    //Clears inventory spaces and equipped item
        for(int i=0; i<inventory.length; i++){
            inventory[i] = null;
        }
        equippedItem = null;
    }
}
