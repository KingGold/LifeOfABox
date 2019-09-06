
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    boolean death;
    int health,width,height,x,y,healthX,healthY;
    public Character(int xx, int yy, int hlth, int wdth, int hght, int hX, int hY) {
      x = xx;
      y = yy;
      health = hlth;
      width = wdth;
      height = hght;
      healthX = hX;
      healthY = hY;
      death = false;
    }
    
    public void reposition(int xx, int yy){     //Move Character to different position
        x = xx;
        y = yy;
    }
    
    public int getX()         // returns x value of spaceship
    {
      return x;
    }
    
    public int getY()         // returns y value of spaceship
    {
      return y;
    }
    
    public void kill(){     //Makes death true
        death = true;
    }
    
    public void resetDeath(){       //Makes death false
        death = false;
    }
    
    public boolean getDeath(){      //returns death value
        return death;
    }
    
    public int getHealth(){     //returns health value
        return health;
    }
    
    public void setHealth(int h){     //sets new health
        health = h;
    }
    
    public int getWidth(){      //returns character's width
        return width;
    }
    
    public int getHeight(){      //returns character's height
        return height;
    }
    
    public int getHealthX(){      //returns character's health's x location from character location
        return healthX;
    }
    
    public int getHealthY(){      //returns character's health's y location from character location
        return healthY;
    }
}
