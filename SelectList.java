import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class SelectList extends Select
{
 
    public SelectList()
    {
        f = true;
        g = true;
        b = new GreenfootImage("linkedlist1.png");
    }
    
    public void act() 
    {
        // Add your action code here.
        if(f)
        {
            f = false;
            n = express("linkedlist1.png", "", 27);
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
                //GreenfootImage txt = new GreenfootImage("리스트", 27, Color.RED, t);
                //b.drawImage(txt, 10, (b.getHeight()-txt.getHeight())/2);
                setImage(b);
                checkClicked(Greenfoot.mouseClicked(this));
            }
            else    setImage(n);
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
            getWorld().addObject(new HomeButton(2), 80, getWorld().getHeight()-80);//LIST = 2
            ((Board)getWorld()).newList();
            for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            ((Board)getWorld()).GM.setSituation(2);
            getWorld().removeObject(this);
        }
    } 
}
