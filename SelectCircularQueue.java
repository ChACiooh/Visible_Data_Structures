import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class SelectCircularQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectCircularQueue extends Select
{

    /**
     * Act - do whatever the SelectCircularQueue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SelectCircularQueue()
    {
        f = true;
        g = true;
        b = new GreenfootImage("cqueue1.png");
    }
    
    public void act() 
    {
        // Add your action code here.
        if(f)
        {
            f = false;
            n = express("cqueue1.png", "", 27);
            setImage(n);
        }
        if(g)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24)
            {
                //GreenfootImage img = new GreenfootImage("Selected.png");
                //GreenfootImage txt = new GreenfootImage("원형 큐", 27, Color.RED, t);
                //b.drawImage(txt, 10, (b.getHeight()-txt.getHeight())/2);
                setImage(b);
            }
            else    setImage(n);
            checkClicked(Greenfoot.mouseClicked(this));
        }
        else    getWorld().removeObject(this);        
    }    
    
    public void checkClicked(boolean a)
    {
        if(a)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            g = false;
            ((Board)getWorld()).newCircularQueue();
            for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            getWorld().addObject(((Board)getWorld()).Cq, getWorld().getWidth()/2, getWorld().getHeight()/2);
            ((Board)getWorld()).GM.setSituation(2);
            getWorld().removeObject(this);
        }
    }
}
