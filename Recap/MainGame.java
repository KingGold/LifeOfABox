

/**
 * Nate Geschwill
 * Mover animation
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainGame implements ActionListener, KeyListener
{
     JFrame f1;
     JPanel p1,p3; 
     JButton quit,start,restart;
     GraphicGame g1;
     int dirx,diry,score,ienemyx,ienemyy,enemyNum,roundNum;
     boolean endprogram,continue1,flipG,starter,left,right,up,down,rF,pause,inventorySelect,select,back,allDead;
     MainCharacter player = new MainCharacter(Const.playerStartX,Const.playerStartY,
     null,Const.playerHealth,Const.playerWidth,Const.playerHeight,Const.playerHealthX,
     Const.playerHealthY,new Item[Const.playerInventorySlots]);
     ArrayList<Enemy> enemy = new ArrayList<Enemy>();
     int max;
     int wDir;
     ArrayList<Item> items = new ArrayList<Item>();
     Menu mainMenu,descMenu,equipItemMenu;
    @SuppressWarnings("deprecation")
    public MainGame()
    {
        endprogram = false;
        continue1 = true;
        starter = false;
        enemyNum = 5;
        score = 0;
        dirx = 0;
        max = 5;
        diry = 0;
        flipG = false;
        left = false;
        right = false;
        up = false;
        down = false;
        pause = false;
        inventorySelect = false;
        select = false;
        back = false;
        allDead = false;
        mainMenu = new Menu(Const.playerInventorySlots,0,select,back,true,Const.storedItemStartX, Const.storedItemStartY, Const.mainMenuRate);
        descMenu = new Menu(Const.descMenuSlots-1,0,select,back,false,Const.storedItemStartX, Const.storedItemStartY, Const.descMenuRate);
        equipItemMenu = new Menu(0,0,select,back,false,0,0,0);
        
        items.add(new Weapon("✶ Area Orb",Const.areaOrbStartX,Const.areaOrbStartY,
        false,Const.areaOrbDamage,Const.areaOrbWidth,Const.areaOrbHeight,Const.areaOrbAttackDelayRate,Const.areaOrbRewardPoints,true,40,40,-1));
        items.add(new Weapon("❄ Ice Ball",Const.iceBallStartX+50,Const.iceBallStartY,
        false,Const.iceBallDamage,Const.iceBallWidth,Const.iceBallHeight,Const.iceBallAttackDelayRate,Const.iceBallRewardPoints,true,20,20,2));
        
        spawnPotion();
        
        f1 = new JFrame("Life of a Box V0.0.5");
            f1.setSize(Const.screenWidth,Const.screenHeight);
            
        f1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        f1.setUndecorated(true);
        f1.setVisible(true);
        
        Container c1 = f1.getContentPane();
        makeEnemy();
        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        
        g1 = new GraphicGame(player,enemy,items,mainMenu,descMenu,equipItemMenu,continue1);
        g1.addKeyListener(this);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        p3 = new JPanel();
        
        restart = new JButton("Restart");
        quit = new JButton ("Quit");

        restart.addActionListener(this);
        quit.addActionListener(this);
        
        start = new JButton("Start");
        start.addActionListener(this);

        p3.add(start);
        p3.add(restart);
        p3.add(quit);
        p1.add(p3,BorderLayout.SOUTH); 
        p1.add(g1,BorderLayout.CENTER);
        c1.add(p1);
        
        f1.show();
        
        getDirection();
     }
    
     
    public void makeEnemy()
     {
        ienemyx = 160;
        ienemyy = 130;
          for(int index = 0; index < enemyNum; index++)
        {
           enemy.add(new Enemy(ienemyx,ienemyy,Const.enemyHealth,Const.enemyWidth,Const.enemyHeight,
           Const.enemyHealthX,Const.enemyHealthY,Const.enemieAttackDelayRate,Const.enemyRewardPoints));
            ienemyx = ienemyx + 100;
            if(ienemyx >= 450 && flipG){
                ienemyx = 130;
                
                ienemyy = ienemyy + 50;
                flipG = false;
            }
            if(ienemyx >= 450 && !flipG){
                ienemyx = 160;
                
                ienemyy = ienemyy + 50;
                flipG = true;
            } 
        }
        }
     
    public void moveChar(){
        diry=0;
        dirx=0;
        if(up)
            diry-=2;
        if(down)
            diry+=2;
        if(left)
            dirx-=2;
        if(right)
            dirx+=2;
        
    }
    
    public void reset(){
        enemy.clear();
        enemyNum = 1;
        makeEnemy();
        
        continue1 = true;
        
        player.reposition(10,10);
        player.setHealth(Const.playerHealth);
        player.clearInventory();
        items.clear();
        items.add(new Weapon("✶ Area Orb",Const.areaOrbStartX,Const.areaOrbStartY,
        false,Const.areaOrbDamage,Const.areaOrbWidth,Const.areaOrbHeight,Const.areaOrbAttackDelayRate,Const.areaOrbRewardPoints,true,45,45,-1));
        spawnPotion();
        
        score = 0;
        
        left = false;
        right = false;
        up = false;
        down = false;
        
        stayInAreaChar();
        moveChar();
        
        player.movePlayer(dirx,diry);
        player.resetDeath();
        for(int ii = 0; ii < enemy.size(); ii++){
            enemy.get(ii).stop();
        }
        g1.move(score,starter,pause,inventorySelect,continue1);
        g1.repaint();
        
    }
     
    public void stayInAreaChar(){
        if(player.getX()<Const.lowerXBound)
            player.reposition(Const.lowerXBound,player.getY());
        else if(player.getX() > Const.upperXBound)
            player.reposition(Const.upperXBound,player.getY());
        else if(player.getY() > Const.upperYBound)
            player.reposition(player.getX(),Const.upperYBound);
        else if( player.getY() < Const.lowerYBound)
            player.reposition(player.getX(),Const.lowerYBound);
    }
     
    public void playerEnemyContact(){
        for(int ii = 0; ii < enemy.size(); ii++){       //player-enemy contact
                    if (player.getX()>=enemy.get(ii).getX() - 20*Const.wRatio && player.getX()<=enemy.get(ii).getX() + 20*Const.wRatio 
                    && player.getY()>=enemy.get(ii).getY() - 20*Const.hRatio && player.getY()<=enemy.get(ii).getY() + 20*Const.hRatio 
                    && System.currentTimeMillis() >= enemy.get(ii).getAttackDelay()){
                       if(player.getHealth() == 0){
                           player.kill();
                           continue1 = false;
                           starter = false;
                        }
                       else{
                          player.setHealth(player.getHealth()-5);
                          if(player.getHealth() == 0){
                           player.kill();
                           continue1 = false;
                           starter = false;
                        }
                        }
                       enemy.get(ii).setAttackDelay();
                    }
                }
    }
    public void playerItemContact(){
        for(int ii = 0; ii < items.size(); ii++){       //player-item contact
                   if (player.getX()>=items.get(ii).getX() - 20*Const.wRatio && player.getX()<=items.get(ii).getX() + 20*Const.wRatio 
                    && player.getY()>=items.get(ii).getY() - 20*Const.hRatio && player.getY()<=items.get(ii).getY() + 20*Const.hRatio
                    && player.checkForRoom()){
                        items.get(ii).take();
                        player.addItem(items.get(ii));
                        items.remove(ii);
                   }
                }
    }
    public void playerHealUse(){
        if(player.getEquippedItem() != null){
                    if(player.getEquippedItem().getClassID() == 'h'){
                        if(select){
                            Heal h = (Heal)player.getEquippedItem();
                            player.setHealth(player.getHealth()+h.getHealAmount());
                            score += player.getEquippedItem().getRewardPoints();
                            player.setEquippedItem(null);
                        }
                    }
                }
    }
    public void weaponEnemyContact(){
        if(player.getEquippedItem() != null && player.getEquippedItem().getClassID() == 'w'){ //equipped item-enemy contact
            boolean hit = false;
            Weapon w = (Weapon)player.getEquippedItem();
            if(select){
                    for(int ii = 0; ii < enemy.size(); ii++){
                        
                        
                        if(enemy.get(ii).getX()>=w.getX() - (int)(w.getAOEX()*Const.wRatio) && enemy.get(ii).getX()<=w.getX() + (int)(w.getAOEX()*Const.wRatio) 
                        && enemy.get(ii).getY()>=w.getY() - (int)(w.getAOEY()*Const.hRatio) && enemy.get(ii).getY()<=w.getY() + (int)(w.getAOEY()*Const.hRatio)
                        && System.currentTimeMillis() >= w.getAttackDelay()){
                            
                            if(enemy.get(ii).getHealth() <= 0){
                                enemy.get(ii).kill();
                                score += enemy.get(ii).getRewardPoints();
                            }
                            else{
                                enemy.get(ii).setHealth(enemy.get(ii).getHealth()-w.getDamage());
                                score += player.getEquippedItem().getRewardPoints();
                                if(enemy.get(ii).getHealth() <= 0){
                                    enemy.get(ii).kill();
                                    score += enemy.get(ii).getRewardPoints();
                                }
                            }
                            
                        }
                    }
                    w.setAttackDelay();
                    w.setDuration();
            }
            for(int ii = 0; ii < enemy.size(); ii++){
                if(enemy.get(ii).getX()>=w.getX() - w.getAOEX()*Const.wRatio && enemy.get(ii).getX()<=w.getX() + w.getAOEX()*Const.wRatio 
                        && enemy.get(ii).getY()>=w.getY() - w.getAOEY()*Const.hRatio && enemy.get(ii).getY()<=w.getY() + w.getAOEY()*Const.hRatio
                 && System.currentTimeMillis() <= w.getDurationLeft()){
                    if(enemy.get(ii).getHealth() <= 0){
                        enemy.get(ii).kill();
                        score += enemy.get(ii).getRewardPoints();
                    }
                    else{
                        enemy.get(ii).setHealth(enemy.get(ii).getHealth()-w.getDamage());
                        score += player.getEquippedItem().getRewardPoints();
                        if(enemy.get(ii).getHealth() <= 0){
                            enemy.get(ii).kill();
                            score += enemy.get(ii).getRewardPoints();
                        }
                    }
                }
            }
            if(System.currentTimeMillis() < w.getAttackDelay())
                w.setWActive(false);
            if(System.currentTimeMillis() >= w.getAttackDelay())
                w.setWActive(true);
                }
    }
    public void moveEnemy(){
        for(int i = 0; i < enemy.size(); i++){  //move enemy
                        enemy.get(i).moveEnemy(player.getX(),player.getY(),1);
                    } 
    }
    public void moveEquippedItem(){
        if(player.getEquippedItem() != null){
            if(player.getEquippedItem().getClassID() == 'w'){
                    Weapon w = (Weapon)player.getEquippedItem();
                    
                    if(w.isWActive()&&w.getName().equals("❄ Ice Ball")){
                        if(left){
                            w.move(player.getX()+Const.iceBallEquippedXL,player.getY()+Const.iceBallEquippedYL);
                            wDir = 1;
                        }
                        else if(right){
                            w.move(player.getX()+Const.iceBallEquippedXR,player.getY()+Const.iceBallEquippedYR);
                            wDir = 2;
                        }
                        else if(down){
                            w.move(player.getX()+Const.iceBallEquippedXD,player.getY()+Const.iceBallEquippedYD);
                            wDir = 3;
                        }
                        else if(up){
                            w.move(player.getX()+Const.iceBallEquippedXU,player.getY()+Const.iceBallEquippedYU);
                            wDir = 4;
                        }
                        else if(wDir == 0){
                            w.move(player.getX()+Const.iceBallEquippedXL,player.getY()+Const.iceBallEquippedYL);
                        }
                        
                    }
                    else if(w.getName().equals("❄ Ice Ball")){
                        if(wDir==1)
                            w.move(w.getX()-(int)(2*Const.wRatio),w.getY());
                        else if(wDir==2)
                            w.move(w.getX()+(int)(2*Const.wRatio),w.getY());
                        else if(wDir==3)
                            w.move(w.getX(),w.getY()+(int)(2*Const.hRatio));
                        else if(wDir==4)
                            w.move(w.getX(),w.getY()-(int)(2*Const.hRatio));
                        else
                            w.move(w.getX()-(int)(2*Const.wRatio),w.getY());
                    }
                    if(w.isWActive()&&w.getName().equals("✶ Area Orb")){
                        player.getEquippedItem().move(player.getX(),player.getY());
                    }
            }
        }
    }
    public boolean allEnemiesDead(){
        allDead=true;
        for(int i=0; i<enemy.size(); i++){
            if(!enemy.get(i).getDeath()){
                allDead = false;
                i=enemy.size();
            }
        }
        return allDead;
    }
    
    public void spawnPotion(){
        items.add(new Heal("✚ Potion",Const.potionStartX,Const.potionStartY,
        false,Const.potionWidth,Const.potionHeight,Const.potionRewardPoints,Const.potionHealAmount));
    }
    
    /** This method moves the shape*/
      @SuppressWarnings("static-access")
    public void getDirection(){
          Thread runner = new Thread();  
          
        while (!endprogram){
            if(continue1 && starter && !pause && !inventorySelect){
                try { 
                    runner.sleep(10);              /**sleep a little to slow it down */
                }
                catch (InterruptedException e) {}
                descMenu.setUse(false);
                mainMenu.setUse(true);
                if(allEnemiesDead()){
                    enemyNum++;
                    makeEnemy();
                    spawnPotion();
                }
                stayInAreaChar();
                moveChar();
                player.movePlayer(dirx,diry);
                
                playerEnemyContact();
                playerItemContact();
                playerHealUse();
                weaponEnemyContact();
                moveEnemy();
                moveEquippedItem();
                if(player.getEquippedItem()!=null)
                System.out.println("" + player.getEquippedItem().getX());
                g1.move(score,starter,pause,inventorySelect,continue1);
                g1.repaint();
            }
            else if(pause || inventorySelect){ //pause section/inventory section
                if(mainMenu.isInUse()){
                    mainMenu.useMenu(left,right,up,down);
                    if(mainMenu.getSlotNum()!=Const.playerInventorySlots && select && player.getStoredItem(mainMenu.getSlotNum())==null && player.getEquippedItem() != null || 
                    mainMenu.getSlotNum()!=Const.playerInventorySlots && select && player.getStoredItem(mainMenu.getSlotNum())!=null){
                        mainMenu.setUse(false);
                        descMenu.setUse(true);
                        select = false;
                    }
                    else if(mainMenu.getSlotNum()==Const.playerInventorySlots && select){
                        mainMenu.setUse(false);
                        equipItemMenu.setUse(true);
                        select = false;
                    }
                }
                else if(descMenu.isInUse()){
                    descMenu.useMenu(left,right,up,down);
                    if(back || select && descMenu.getSlotNum() == 1){
                        descMenu.setUse(false);
                        mainMenu.setUse(true);
                        select = false;
                    }
                    if(select && descMenu.getSlotNum() == 0){
                        Item it = player.getEquippedItem();
                        player.setEquippedItem(player.getStoredItem(mainMenu.getSlotNum()));
                        player.setItem(it, mainMenu.getSlotNum());
                        descMenu.setUse(false);
                        mainMenu.setUse(true);
                        select = false;
                        inventorySelect = false;
                    }
                }
                else if(equipItemMenu.isInUse()){
                    if(back){
                        equipItemMenu.setUse(false);
                        mainMenu.setUse(true);
                    }
                }
                else
                    pause = false;
                
                g1.move(score,starter,pause,inventorySelect,continue1);
                g1.repaint();
            }
            else if(!starter && continue1){
                g1.repaint();
            }
            
        }
        }
    
    
    public void keyPressed(KeyEvent evt){ //System.out.println("" + evt.getKeyCode());          
           if (evt.getKeyCode() == 37 && starter == true){           // left arrow         
                 left = true;
                }
           if (evt.getKeyCode() == 38 && starter == true){            //up    
                 up = true;
                }
           if (evt.getKeyCode() == 40 && starter == true){                   //down
               down = true;
            }
           if (evt.getKeyCode() == 39 && starter == true){          //right arrow   
                 right = true;
                }
           if (evt.getKeyCode() == 27 && starter == true){          //pause  
                  if(!pause)
                    pause = true;
                  else if(pause)
                    pause = false;
                }
           if (evt.getKeyCode() == 67 && starter == true){          //pause  
                  if(!inventorySelect)
                    inventorySelect = true;
                  else if(inventorySelect)
                    inventorySelect = false;
                }
           if (evt.getKeyCode() == 90 && starter == true){         //z key
             select = true;
            }
           if (evt.getKeyCode() == 88 && starter == true){         //x key
             back = true;
            }
        }
               
    public void keyReleased(KeyEvent evt){
         if (evt.getKeyCode() == 37 && starter == true){          // left arrow       
             left = false;
            }
         if (evt.getKeyCode() == 38 && starter == true){         //up     
             up = false;
            }
         if (evt.getKeyCode() == 40 && starter == true){                   //down
             down = false;
            }
         if (evt.getKeyCode() == 39 && starter == true){          //right arrow   
              right = false;
             }
         if (evt.getKeyCode() == 90 && starter == true){         //z key
             select = false;
            }
         if (evt.getKeyCode() == 88 && starter == true){         //x key
             back = false;
            }
        }
        
    public void keyTyped(KeyEvent evt){}  
    
    public void actionPerformed (ActionEvent event){  
        if (event.getSource() == start){
            g1.requestFocus();
            starter = true;
           }
        if (event.getSource() == restart){
           reset();
           starter = false;
        }
        if (event.getSource() == quit){
           endprogram = true;
           f1.dispose();
        }
    }  
}
