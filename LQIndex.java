import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class LQIndex extends LinearQueue
{
    int No;
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt;
    LQIndex(){}
    
    LQIndex(int num)
    {
        No = num;
        pro = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(!QueueEnded)
        {
            if(pro)
            {
                pro = false;
                txt = new GreenfootImage(""+No, 22, Color.BLACK, new Color(0,0,0,0));
                base.drawImage(txt, (base.getWidth()-txt.getWidth())/2, (base.getHeight()-txt.getHeight())/2);
                setImage(base);
            }
        }
        else    getWorld().removeObject(this);
    }    
}
