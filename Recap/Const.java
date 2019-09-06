
/**
 * Write a description of class Const here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class Const
{
        public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public static double w = screenSize.getWidth();
        public static double h = screenSize.getHeight();
        
        public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        public static int widthG = gd.getDisplayMode().getWidth();
        public static int heightG = gd.getDisplayMode().getHeight();
        
        public static int width = (int)w;
        public static int height = (int)h;
        public static double wRatio = (widthG/800.0);
        public static double hRatio = (heightG/500.0);
        
        public static final int playerWidth = (int)(20*wRatio);
        public static final int playerHeight = (int)(20*hRatio);
        public static final int playerHealth = 50;
        public static final int playerHealthX = (int)(2*wRatio);//from player location
        public static final int playerHealthY = (int)(18*hRatio);//from player location
        public static final int playerHealthFont = 15;
        public static final int playerStartX = (int)(10*wRatio);
        public static final int playerStartY = (int)(10*hRatio);
        public static final int playerInventorySlots = 5;
        
        public static final int playerHealthBoxX = (int)(565*wRatio);
        public static final int playerHealthBoxY = (int)(360*hRatio);
        public static final int playerHealthBoxHeight = (int)(20*hRatio);
        public static final int playerHealthRate = 1;
        public static final int playerHealthSymbolX = (int)(540*wRatio);
        public static final int playerHealthSymbolY = (int)(380*hRatio);
        public static final int symbolFont = 25;
        
        public static final int storedItemStartX = (int)(560*wRatio);
        public static final int storedItemStartY = (int)(105*hRatio);
        public static final int storedItemNext = 50;
        public static final int storedItemFont = 35;
        
        public static final int arrowItemStartX = (int)(530*wRatio);
        public static final int arrowItemStartY = (int)(105*hRatio);
        
        public static final int mainMenu = 0;
        public static final int descMenu = 1;
        public static final int maxMenu = 1;
        
        public static final int descArrowStartX = (int)(530*wRatio);
        public static final int descArrowStartY = (int)(105*hRatio);
        public static final int descMenuSlots = 2;
        public static final int descMenuRate = 150;
        
        public static final int mainMenuRate = 50;
        
        
        public static final int enemyWidth = (int)(20*wRatio);
        public static final int enemyHeight = (int)(20*hRatio);
        public static final int enemyHealth = 50;
        public static final int enemyHealthX = (int)(2*wRatio);//from enemy location
        public static final int enemyHealthY = (int)(18*hRatio);//from enemy location
        public static final int enemyHealthFont = 15;
        public static final int enemyRewardPoints = 20;
        
        public static final int areaOrbWidth = (int)(20*wRatio);
        public static final int areaOrbHeight = (int)(20*hRatio);
        public static final int areaOrbDamage = 25;
        public static final int areaOrbStartX = (int)(50*wRatio);
        public static final int areaOrbStartY = (int)(50*hRatio);
        public static final int areaOrbRewardPoints = 5;
        public static final int areaOrbEquippedWidth = (int)(60*wRatio);
        public static final int areaOrbEquippedHeight = (int)(60*hRatio);
        public static final int areaOrbEquippedX = (int)(-20*wRatio);//from player location
        public static final int areaOrbEquippedY = (int)(-20*hRatio);//from player location
        public static final int areaOrbAttackDelayRate = 1;
        
        public static final int iceBallWidth = (int)(20*wRatio);
        public static final int iceBallHeight = (int)(20*hRatio);
        public static final int iceBallDamage = 1;
        public static final int iceBallStartX = (int)(400*wRatio);
        public static final int iceBallStartY = (int)(50*hRatio);
        public static final int iceBallRewardPoints = 5;
        public static final int iceBallEquippedWidth = (int)(20*wRatio);
        public static final int iceBallEquippedHeight = (int)(20*hRatio);
        public static final int iceBallEquippedXR = (int)(20*wRatio);//from player location
        public static final int iceBallEquippedYR = (int)(0*hRatio);//from player location
        public static final int iceBallEquippedXL = (int)(-20*wRatio);//from player location
        public static final int iceBallEquippedYL = (int)(0*hRatio);//from player location
        public static final int iceBallEquippedXU = (int)(0*wRatio);//from player location
        public static final int iceBallEquippedYU = (int)(-20*hRatio);//from player location
        public static final int iceBallEquippedXD = (int)(0*wRatio);//from player location
        public static final int iceBallEquippedYD = (int)(20*hRatio);//from player location
        public static final int iceBallAttackDelayRate = 2;
        
        public static final int potionWidth = (int)(20*wRatio);
        public static final int potionHeight = (int)(20*hRatio);
        public static final int potionHealAmount = 5;
        public static final int potionStartX = (int)(50*wRatio);
        public static final int potionStartY = (int)(90*hRatio);
        public static final int potionRewardPoints = 10;
        public static final int potionEquippedWidth = (int)(20*wRatio);
        public static final int potionEquippedHeight = (int)(20*hRatio);
        public static final int potionEquippedX = (int)(20*wRatio);//from player location
        public static final int potionEquippedY = (int)(0*hRatio);//from player location
        
        public static final int scoreX = (int)(560*wRatio);
        public static final int scoreY = (int)(45*hRatio);
        public static final int scoreFont = 30;
        
        public static final int pressStartX = (int)(565*wRatio);
        public static final int pressStartY = (int)(410*hRatio);
        
        public static final int pauseX = (int)(565*wRatio);
        public static final int pauseY = (int)(410*hRatio);
        
        
        
        public static int screenWidth = width;
        public static int screenHeight = height;
        
        public static final int menuBoxX = (int)(525*wRatio);
        public static final int menuBoxY = (int)(0*hRatio);
        public static final int menuBoxWidth = (int)(300*wRatio);
        public static final int menuBoxHeight = (int)(500*hRatio);
        
        public static final int lowerXBound = (int)(0*wRatio);
        public static final int lowerYBound = (int)(0*hRatio);
        public static final int upperXBound = (int)(500*wRatio);
        public static final int upperYBound = (int)(450*hRatio);
        
        public static final int heartEdgeFont = (int)((symbolFont+3)*wRatio);
        public static final int heartFont = (int)((symbolFont)*wRatio);
        
        
        public static final int enemieAttackDelayRate = 3;
        public static final int pickUpDelayRate = 1;
}
