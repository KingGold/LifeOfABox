
/**
 * Nate Geschwill
 * Mover Animation Graphic
 */
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
@SuppressWarnings("serial")
public class GraphicGame extends JPanel

{
    int moveSpeed=0;
    int pLastX=0;
    int pLastY=0;
   int x, y,score;
   
   boolean gameState,pause,inventorySelect,con;
   
   boolean[] ptdeath;
   
   MainCharacter player;
   
   ArrayList<Enemy> enemy;
   
   ArrayList<Item> items;
   Menu mainMenu,descMenu,equipItemMenu;
    public GraphicGame(MainCharacter pm,ArrayList<Enemy> gho,ArrayList<Item> it,Menu m,Menu d,Menu eIM, boolean c){   
        player = pm;
        enemy = gho;
        items = it;
        mainMenu = m;
        setBackground(Color.black);
        descMenu = d;
        equipItemMenu = eIM;
        con = c;
    }
    
    public void move(int s, boolean gS, boolean p, boolean iS,boolean c){
        gameState = gS;
      
        score = s;
      
        pause = p;
      
        inventorySelect = iS;
      
        con = c;
    }
    
    public void drawMainC(Graphics g){
       g.setColor(Color.white);
       g.drawRect(player.getX(),player.getY(),player.getWidth(),player.getHeight());
       g.setFont(new Font("Impact",Font.BOLD,Const.playerHealthFont));
       g.drawString("" + player.getHealth(),player.getX()+Const.playerHealthX,player.getY()+
       Const.playerHealthY);
    }
    
    public void drawMenu(Graphics g){
       g.setColor(Color.gray);
       g.fillRect(Const.menuBoxX,Const.menuBoxY,Const.menuBoxWidth,Const.menuBoxHeight);
        
       g.setColor(Color.white);
       g.setFont(new Font("Impact",Font.ITALIC,Const.scoreFont));
       g.drawString("Score: " + score,Const.scoreX,Const.scoreY);
       
       if(mainMenu.isInUse()){
           for(int i=0; i<Const.playerInventorySlots; i++){
               g.setFont(new Font("Webdings_3",Font.BOLD,Const.storedItemFont));
               g.setColor(Color.white);
               if(player.getStoredItem(i)==null){
                   g.drawString("———",mainMenu.getXStart(),
                   mainMenu.getYStart()+(mainMenu.getRate()*i));
               }
               else{
                    g.drawString("" + player.getStoredItem(i).getName(),mainMenu.getXStart(),
                    mainMenu.getYStart()+(mainMenu.getRate()*i));
               }
               g.drawString("Equipped Items",mainMenu.getXStart(),mainMenu.getYStart()+(mainMenu.getRate()*5));
               g.setColor(Color.black);
               g.setFont(new Font("Webdings_3",Font.BOLD,Const.heartEdgeFont));
               g.drawString("♥", Const.playerHealthSymbolX-4, Const.playerHealthSymbolY+1);
               g.setColor(Color.red);
               g.fillRect(Const.playerHealthBoxX, Const.playerHealthBoxY, Const.playerHealthRate*player.getHealth(), Const.playerHealthBoxHeight);
               g.setFont(new Font("Webdings_3",Font.BOLD,Const.heartFont));
               g.drawString("♥", Const.playerHealthSymbolX, Const.playerHealthSymbolY-1);
               g.setColor(Color.black);
               g.drawRect(Const.playerHealthBoxX, Const.playerHealthBoxY, Const.playerHealthRate*player.getHealth(), Const.playerHealthBoxHeight);
               /**
               if(player.getStoredItem(i)==null){
                   g.drawString("Null",Const.storedItemStartX,
                   Const.storedItemStartY+(50*i));
                }
                else{
                    g.drawString("" + player.getStoredItem(i).getName(),Const.storedItemStartX,
                    Const.storedItemStartY+(50*i));
                }
                */
            }
       }
       else if(descMenu.isInUse()){
           g.setFont(new Font("Webdings_3",Font.BOLD,Const.storedItemFont));
           g.setColor(Color.white);
           if(player.getStoredItem(mainMenu.getSlotNum())==null && player.getEquippedItem() != null){
                   g.drawString(player.getEquippedItem().getName(),Const.storedItemStartX,
                   Const.storedItemStartY);
                   g.drawString("Unequip?" ,
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*2);
                   g.drawString("Yes         No",
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*3);
           }
           else if(player.getStoredItem(mainMenu.getSlotNum())!=null){
               g.drawString("" + player.getStoredItem(mainMenu.getSlotNum()).getName(),Const.storedItemStartX,
               Const.storedItemStartY);
               if(player.getStoredItem(mainMenu.getSlotNum()).getClassID() == 'h'){
                   Heal h = (Heal)player.getStoredItem(mainMenu.getSlotNum());
                   g.drawString("Heals: " + h.getHealAmount(),
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext);
                   g.drawString("Equip?" ,
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*2);
                   g.drawString("Yes         No",
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*3);
               }
               if(player.getStoredItem(mainMenu.getSlotNum()).getClassID() == 'w'){
                   Weapon w = (Weapon)player.getStoredItem(mainMenu.getSlotNum());
                   g.drawString("Damage: " + w.getDamage(),
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext);
                   g.drawString("Equip?" ,
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*2);
                   g.drawString("Yes         No",
                   Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext*3);
               }
           }
       }
       else if(equipItemMenu.isInUse() && player.getEquippedItem()!=null){
            g.setFont(new Font("Webdings_3",Font.BOLD,Const.storedItemFont));
            g.setColor(Color.white);
            g.drawString("" + player.getEquippedItem().getName(),Const.storedItemStartX,
            Const.storedItemStartY);
            if(player.getEquippedItem().getClassID() == 'h'){
                Heal h = (Heal)player.getEquippedItem();
                g.drawString("Heals: " + h.getHealAmount(),
                Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext);
            }
            if(player.getEquippedItem().getClassID() == 'w'){
                Weapon w = (Weapon)player.getEquippedItem();
                g.drawString("Damage: " + w.getDamage(),
                Const.storedItemStartX,Const.storedItemStartY+Const.storedItemNext);
            }
       }
       if(pause || inventorySelect){
           g.setFont(new Font("Impact",Font.ITALIC,Const.scoreFont));
            
           g.setColor(Color.white);
            
           g.drawString("Paused", Const.pauseX, Const.pauseY);
           
           if(inventorySelect){
               if(mainMenu.isInUse()){
                   g.setFont(new Font("Webdings_3",Font.BOLD,Const.storedItemFont));
                   g.drawString("➤",Const.arrowItemStartX,
                   Const.arrowItemStartY+(mainMenu.getRate()*mainMenu.getSlotNum()));
                }
               else if(descMenu.isInUse()){
                   g.setFont(new Font("Webdings_3",Font.BOLD,Const.storedItemFont));
                   g.drawString("➤",Const.descArrowStartX+(descMenu.getRate()*descMenu.getSlotNum()),
                   Const.descArrowStartY+Const.storedItemNext*3);
                }
            }
        }
       if(!gameState && con){
           g.setFont(new Font("Impact",Font.ITALIC,Const.scoreFont));
            
            g.setColor(Color.white);
            
            g.drawString("Press Start", Const.pressStartX, Const.pressStartY);
        }
       else if(!gameState && !con){
           g.setFont(new Font("Impact",Font.ITALIC,Const.scoreFont));
            
            g.setColor(Color.white);
            
            g.drawString("Game Over", Const.pressStartX, Const.pressStartY);
        }
    }
    
    public void drawEnemy(Graphics g){
       
        for(int index = 0; index < enemy.size(); index++)
        {
            g.setColor(Color.white);
            g.setFont(new Font("Impact",Font.BOLD,Const.playerHealthFont));
            g.drawString("" + enemy.get(index).getHealth(),
            enemy.get(index).getX()+Const.enemyHealthX,
            enemy.get(index).getY()+Const.enemyHealthY);
            g.setColor(Color.yellow);
            if(!enemy.get(index).getDeath())
                g.drawRect(enemy.get(index).getX(),enemy.get(index).getY(),Const.playerWidth,
                Const.playerHeight);
            else
                enemy.remove(index);
       
       }
    }
    
    public void drawItems(Graphics g){
        for(int iitems = 0; iitems < items.size(); iitems++){
           g.setColor(Color.blue);
           g.fillRect(items.get(iitems).getX(),items.get(iitems).getY(),items.get(iitems).getWidth(),
           items.get(iitems).getHeight());
           if(items.get(iitems).checkTaken()){
               items.remove(iitems);
            }
        }
       
       if(player.getEquippedItem() != null){
            if(player.getEquippedItem().getClassID() == 'w'){
                    Weapon w = (Weapon)player.getEquippedItem();
                    g.setColor(Color.red);
                    if(w.isWActive()&&w.getName().equals("✶ Area Orb"))
                        g.fillRect(player.getX()+Const.areaOrbEquippedX,player.getY()+
                        Const.areaOrbEquippedY,
                        Const.areaOrbEquippedWidth,Const.areaOrbEquippedHeight);
                    else if(w.getName().equals("✶ Area Orb")){
                        g.drawRect(player.getX()+Const.areaOrbEquippedX,player.getY()+
                        Const.areaOrbEquippedY,
                        Const.areaOrbEquippedWidth,Const.areaOrbEquippedHeight);
                    }
                    if(w.isWActive()&&w.getName().equals("❄ Ice Ball")){
                        g.setColor(Color.blue);
                        g.fillOval(w.getX(),w.getY(),w.getWidth(),w.getHeight());
                    }
                    else if(w.getName().equals("❄ Ice Ball")){
                        g.setColor(Color.blue);
                        g.fillOval(w.getX(),w.getY(),w.getWidth(),w.getHeight());
                    }
             }
            if(player.getEquippedItem().getClassID() == 'h'){
                    
                    g.setColor(Color.red);
                    g.fillOval(player.getX()+Const.potionEquippedX,player.getY()+Const.potionEquippedY,
                    Const.potionEquippedWidth,Const.potionEquippedHeight);
                    
                    
             }
       }
    }
    
    public void paint(Graphics g){
       super.paint(g);
       
       drawItems(g);
       drawEnemy(g);
       drawMainC(g);
       drawMenu(g);
       g.setColor(Color.white);

    }

}


