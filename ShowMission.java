import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class ShowMission here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowMission extends Actor
{
    Color t = new Color(0,0,0,0);
    boolean pro;
    public static boolean ShowMissionButtonEnded;
    public int dataType = 0;
    int x, y;
    GreenfootImage n;
    
    public ShowMission(String Data_Type)
    {
        pro = true;
        x=y=0;
        ShowMissionButtonEnded = false;
        if(Data_Type.toString() == "Stack_AP")
        {
            dataType = 1;
        }
        else if(Data_Type.toString() == "Stack_GP")
        {
            dataType = 2;
        }
        else if(Data_Type.toString() == "LinearQueue")
        {
            dataType = 3;
        }
        else if(Data_Type.toString() == "CicularQueue")
        {
            dataType = 4;
        }
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            GreenfootImage img = new GreenfootImage(getImage());
            GreenfootImage txt = new GreenfootImage("미션 보기\n(Show Mission)", 22, Color.RED, t);
            img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
            n = img;
            img = null;
            txt = null;
        }
        if(!ShowMissionButtonEnded)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24)
            {
                GreenfootImage img = new GreenfootImage("Selected.png");
                GreenfootImage txt = new GreenfootImage("미션 보기\n(Show Mission)", 22, Color.RED, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                checkClicked(Greenfoot.mouseClicked(this));
            }
            else    setImage(n);
            
        }
        else    getWorld().removeObject(this);
    }    
    
    private void checkClicked(boolean a)
    {
        if(a)
        {
            Greenfoot.playSound("click.wav");
            if(dataType == 1)
                AP.printMessageOfAP();
            else if(dataType == 2)
                GP.printMessageOfGP();
        }
    }
}
