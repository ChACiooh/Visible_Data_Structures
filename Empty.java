import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Empty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Empty extends Actor implements Delay
{
    int time;
    
    Empty()
    {
        time = 0;
    }
    /**
     * Act - do whatever the Empty wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        delay();
    }    
    
    public void delay()
    {
        time++;
        if(time==40)
        {
            getWorld().removeObject(this);
        }
    }
}
