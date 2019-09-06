
/**
 * Enemy for Player (Ends game)
 * 
 * Nate Geschwill
 * 
 */
public class Enemy extends Character
{
    int eDir, rewardPoints;
    boolean death, getPlayer;
    long attackDelay, attackDelayRate;
    
    public Enemy(int xx, int yy, int hlth, int wdth, int hght, int hX, int hY, long attcktmr, int rP){   //Constructor creates Alien object
        super(xx,yy,hlth,wdth,hght,hX,hY);
        eDir = 0;
        attackDelay = 0;
        attackDelayRate = attcktmr*1000;
        rewardPoints = rP;
        getPlayer = false;
    }
    
    public void setAttackDelay(){
        attackDelay = System.currentTimeMillis() + attackDelayRate;
    }
    
    public long getAttackDelay(){
        return attackDelay;
    }
    
    public int getRewardPoints(){
        return rewardPoints;
    }
    
    public void stop(){
        eDir = 0;
    }
    
    public void moveEnemy(int px, int py, int speed){
        int ReDir = (int) (Math.random() * 50);
       
        if(x<0*Const.wRatio){
            x+=speed;
        }
        if(x>500*Const.wRatio){
            x-=speed;
        }
        if(y<0*Const.hRatio){
            y+=speed;
        }
        if(y>400*Const.hRatio){
            y-=speed;
        }
        if(x<0*Const.wRatio&&y<0*Const.hRatio){
            reposition(0,0);
        }
        if(x>500*Const.wRatio&&y<0*Const.hRatio){
            reposition((int)(500*Const.wRatio),0);
        }
        if(y>400*Const.hRatio&&x<0*Const.wRatio){
            reposition(0,(int)(400*Const.hRatio));
        }
        if(y>400*Const.hRatio&&x>500*Const.wRatio){
            reposition((int)(500*Const.wRatio),(int)(400*Const.hRatio));
        }
        
       
            if(ReDir == 5){
                eDir = (int) (Math.random() * 8 + 1);
            }
            
            if(ReDir == 1 || ReDir == 2 || ReDir == 3 || ReDir == 4)
                getPlayer = true;
            else if(ReDir == 5)
                getPlayer=false;
                
            //when enemies health is low
            if(px<x && py<y && health<=5){
                x+=speed;
                y+=speed;
            }
            else if(px>x && health<=5){
                x-=speed;
                y-=speed;
            }
            else if(px>x && health<=5){
                x-=speed;
                y+=speed;
            }
            else if(px<x && health<=5){
                x+=speed;
                y-=speed;
            }
            else if(px<x && health<=5){
                x+=speed;
            }
            else if(px>x && health<=5){
                x-=speed;
            }
            else if(py<y && health<=5){
                y+=speed;
            }
            else if(py>y && health<=5){
                y-=speed;
            }
            
            //goes towards you
            else if(px<x && py<y && getPlayer || eDir == 1){
                x-=speed;
                y-=speed;
            }
            else if(px>x && py>y && getPlayer || eDir == 2){
                x+=speed;
                y+=speed;
            }
            else if(px>x && py<y && getPlayer || eDir == 3){
                x+=speed;
                y-=speed;
            }
            else if(px<x && py>y && getPlayer || eDir == 4){
                x-=speed;
                y+=speed;
            }
            else if(px<x && getPlayer || eDir == 6){
                x-=speed;
            }
            else if(px>x && getPlayer || eDir == 5){
                x+=speed;
            }
            else if(py<y && getPlayer || eDir == 7){
                y-=speed;
            }
            else if(py>y && getPlayer || eDir == 8){
                y+=speed;
            }
            else{
                x = x;
                y = y;
            }
        
    }
}
