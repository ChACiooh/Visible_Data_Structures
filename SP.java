import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class SP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SP extends Stack
{
    Color t = new Color(0,0,0,0);
    /**
     * Act - do whatever the SP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SP()
    {
        printSP();
    }
    
    public void act() 
    {
        // Add your action code here.
        if(time>0 && time<13)   time++;
        if(time==13)
        {
            if(getY()>getWorld().getHeight()-super.y-40)  shift1();
            else if(getY()<getWorld().getHeight()-super.y-40) shift2();
            else
            {
                pushClicked = false;
            }
            
            
        }
        if(StackEnded)  getWorld().removeObject(this);
    }    
    
    void printSP()
    {
        GreenfootImage image = new GreenfootImage(getImage());
        GreenfootImage text = new GreenfootImage("SP", 20, Color.WHITE, t);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    
    protected void shift1()
    {
        //상승
        setLocation(getX(), getY()-4);
    }
    
    protected void shift2()
    {
        //하강
        setLocation(getX(), getY()+4);
    }
}
