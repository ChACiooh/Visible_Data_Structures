import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallOfList here.
 * 그저 벽이다. 퀄리티를 높여 보이게끔 함.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallOfList extends List
{
    /**
     * Act - do whatever the WallOfList wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(ListEnded)   getWorld().removeObject(this);
    }    
}
