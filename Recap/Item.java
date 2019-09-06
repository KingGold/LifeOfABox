
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    int width,height,rewardPoints,x,y;
    boolean taken;
    String name;
    char classID;
    public Item(String n, int xx, int yy, boolean tkn, char cI, int wdth, int hght, int rP){
        name = n;
        
        x = xx;
        y = yy;
        
        taken = tkn;
        
        classID = cI;
        width = wdth;
        height = hght;
        rewardPoints = rP;
    }
    
    public void take(){
        taken = true;
    }
    
    public void drop(){
        taken = false;
    }
    
    public boolean checkTaken(){
        return taken;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void move(int xxx, int yyy){
        x = xxx;
        y = yyy;
    }
    
    public char getClassID(){
        return classID;
    }
    
    public String getName(){
        return name;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getRewardPoints(){
        return rewardPoints;
    }
}
