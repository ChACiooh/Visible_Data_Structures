import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class OverFlow extends Actor implements Delay
{
    int time;
    GreenfootImage img = new GreenfootImage("LifeText.png");
    Color t = new Color(0,0,0,0);
    OverFlow()
    {
        time=0;
    }
    OverFlow(int type)
    {
        time = 0;
        if(type == 1)
        {
            GreenfootImage txt = new GreenfootImage("가득 찼습니다.", 25, Color.BLACK, t);
            img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
        }
    }
    /**
     * Act - do whatever the OverFlow wants to do. This method is called whenever
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
