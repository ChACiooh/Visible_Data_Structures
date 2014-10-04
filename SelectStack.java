import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class SelectStack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectStack extends Select
{
    public SelectStack()
    {
        f=true;
        g=true;
        b = new GreenfootImage("stack1.png");
    }
    /**
     * Act - do whatever the SelectStack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(f)
        {
            f=false;
            n = express("stack1.png", "", 27);
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
                //GreenfootImage txt = new GreenfootImage("스택", 27, Color.RED, t);
                //b.drawImage(txt, 10, (b.getHeight()-txt.getHeight())/2);
                setImage(b);
            }
            else    setImage(n);
            checkClicked(Greenfoot.mouseClicked( this ));
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
            g=false;
            //getWorld().addObject(new StackMission(), getWorld().getWidth()/2, getWorld().getHeight()/2);
            ((Board)getWorld()).stack = new Stack();
            getWorld().addObject(((Board)getWorld()).stack, getWorld().getWidth()/2, getWorld().getHeight()*2/3);
            ((Board)getWorld()).GM.setSituation(2);
            for(int i=0;i< ((Board)getWorld()).main.pannel.length; i++)
                getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            getWorld().removeObject(this);
        }
    }
}
