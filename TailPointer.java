import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class TailPointer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TailPointer extends LinearQueue
{
    Color t = new Color(0,0,0,0);
    GreenfootImage img = new GreenfootImage(getImage());
    GreenfootImage text = new GreenfootImage("Rear↑", 27, Color.BLACK, t);
    private int XTemp;
    /**
     * Act - do whatever the TailPointer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    TailPointer()
    {
        pro = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            if(getX()<60)  setLocation(60, getY());
            XTemp = getX();
            updateImage();
        }
        if(time>0 && time<13)
        {
            time++;
        }
        if(time == 13)
        {
            shiftOfTailPointer();
        }
        if(QueueEnded)
        {
            try
            {
                getWorld().removeObject(this);
            } catch(NullPointerException npe) {};
        }
    }    
    
    private void shiftOfTailPointer()
    {
        if(getX()<XTemp+tail*L)
        {
            setLocation(getX()+8, getY());
        }
        else    pushEnd = true;
        /*else if(getX()>XTemp+(tail-head)*L)
        {
            setLocation(getX()-8, getY());
        }*/
    }
    
    private void updateImage()
    {
        img.drawImage(text, (img.getWidth()-text.getWidth())/2-text.getWidth()/2+5, 
                    (img.getHeight()-text.getHeight())/2);
        setImage(img);
    }
}
