import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectNode extends List
{
    GreenfootImage img = new GreenfootImage(getImage());
    SelectNode()
    {
        img.scale(img.getWidth()-1, img.getHeight()-1);
        setImage(img);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
