import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class SelectSBTtest extends Select
{
    public SelectSBTtest()
    {
        f = true;
        g = true;
        b = new GreenfootImage("btree2.png");
    }
    public void act() 
    {
        if(f)
        {
            f = false;
            n = express("btree2.png", "", 22);
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
                //GreenfootImage txt = new GreenfootImage("이진\n탐색트리", 22, Color.RED, t);
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
            } catch(Exception e){};
            g = false;
            getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
            ((Board)getWorld()).newSBTtest();
            for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            ((Board)getWorld()).GM.setSituation(2);
            getWorld().removeObject(this);
        }
    }
}
