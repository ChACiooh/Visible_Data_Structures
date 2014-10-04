import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;
public class SelectMaxHeap extends Select
{
    protected static final boolean max = true;
    private boolean iErased;
    int temp, time;
    SelectMaxHeap()
    {
        iErased = false;
        temp = 2;
        time = 0;
        f = true;
        g = true;
        b = new GreenfootImage("maxheap1.png");
    }
    public void act() 
    {
        // Add your action code here.
        if(f)
        {
            f=false;
            n = express("maxheap1.png", "", 27);
        }
        if(g)
        {
            if(time >0 && time < 7)
            {
                time++;
            } else time = 0;
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24)
            {
                //GreenfootImage txt = new GreenfootImage("MAX 힙", 27, Color.RED, t);
                //b.drawImage(txt, 10, (b.getHeight()-txt.getHeight())/2);
                setImage(b);
                if(time == 0)
                    checkClicked(Greenfoot.mouseClicked(this));
            }
            else    setImage(n);
            
        }
        else
        {
            sequelWork();
            getWorld().removeObject(this);
        }
    }    
    
    public void checkClicked(boolean a)
    {
        if(a)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            temp = JOptionPane.showConfirmDialog(null, "Shift-Up 과정을 보시겠습니까?");
            if(temp==2)
            {
                time++;
                return;
            }
            g=false;
            ((Board)getWorld()).GM.setSituation(2);
            iErased = true;
        }
    }
    
    private void sequelWork()
    {
        if(iErased)
        {
            getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
            ((Board)getWorld()).newHeap(temp, max);
            for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            //getWorld().addObject(((Board)getWorld()).H, 120, 15);
        }
    }
}
