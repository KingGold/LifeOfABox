
/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Item
{
    int damage,aoeX,aoeY;
    long attackDelay, attackDelayRate,duration,durationRate;
    boolean wActive;
    public Weapon(String n, int xx, int yy, boolean tkn, int dam, int wdth, int hght, long attcktmr, int rP, boolean wA,int aeX,int aeY,long dR){
        super(n,xx,yy,tkn,'w',wdth,hght, rP);
        damage = dam;
        attackDelay = 0;
        attackDelayRate = attcktmr*1000;
        duration = 0;
        durationRate = dR*1000;
        wActive = wA;
        aoeX = aeX;
        aoeY = aeY;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public void setAttackDelay(){
        attackDelay = System.currentTimeMillis() + attackDelayRate;
    }
    
    public long getAttackDelay(){
        return attackDelay;
    }
    
    public void setDuration(){
        duration = System.currentTimeMillis() + durationRate;
    }
    
    public long getDurationLeft(){
        return duration;
    }
    
    public boolean isWActive(){
        return wActive;
    }
    
    public void setWActive(boolean wA){
        wActive = wA;
    }
    
    public int getAOEX(){
        return aoeX;
    }
    public int getAOEY(){
        return aoeY;
    }
}
