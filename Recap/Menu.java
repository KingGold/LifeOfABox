
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu
{
    boolean pause = false;
    int slotTotal,slotNum,xStart,yStart,rate,trig;
    boolean select,back,inUse;
    
    public Menu(int sltT, int sltN, boolean s, boolean b,boolean iU, int xS, int yS, int rr){
        trig = 0;
        inUse = iU;
        slotTotal = sltT;
        slotNum = sltN;
        xStart = xS;
        yStart = yS;
        rate = rr;
        
    }
    public void useMenu(boolean left,boolean right,boolean up,boolean down){
        if(down && trig == 0){
            if(slotNum == slotTotal)
                slotNum = 0;
            else
                slotNum++;
            trig = 1;
        }
        else if(up && trig == 0){
            if(slotNum == 0)
                slotNum = slotTotal;
            else
                slotNum--;
            trig = 1;
        }
        else if(right && trig == 0){
            if(slotNum == slotTotal)
                slotNum = 0;
            else
                slotNum++;
            trig = 1;
        }
        else if(left && trig == 0){
            if(slotNum == 0)
                slotNum = slotTotal;
            else
                slotNum--;
            trig = 1;
        }
        else if(!right && !left && !up && !down && trig == 1)
            trig = 0;
    }
    public boolean isInUse(){
        return inUse;
    }
    public int getSlotTotal(){
        return slotTotal;
    }
    public int getSlotNum(){
        return slotNum;
    }
    public int getXStart(){
        return xStart;
    }
    public int getYStart(){
        return yStart;
    }
    public int getRate(){
        return rate;
    }
    public boolean select(){
        return select;
    }
    public void setUse(boolean b){
        inUse = b;
    }
}
