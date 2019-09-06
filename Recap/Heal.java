
/**
 * Write a description of class Heal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heal extends Item
{
    int healAmount;
    public Heal(String n, int xx, int yy, boolean tkn, int wdth, int hght, int rP, int hA){
        super(n,xx,yy,tkn,'h',wdth,hght,rP);
        
        healAmount = hA;
    }
    public int getHealAmount(){
        return healAmount;
    }
}
