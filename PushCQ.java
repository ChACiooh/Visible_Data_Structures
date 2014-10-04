import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class PushCQ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PushCQ extends CircularQueue
{
    Color tr = new Color(0,0,0,0);
    int x, y;
    GreenfootImage n;
    /**
     * Act - do whatever the PushCQ wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    PushCQ()
    {
        pro=true;
        x=y=0;
    }
    public void act() 
    {
        if(pro)
        {
            pro=!pro;
            GreenfootImage image = new GreenfootImage(getImage());
            /*GreenfootImage text = new GreenfootImage("EnQueue", 30, Color.RED, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);*/
            setImage(image);
            n=image;
        }
        if(!checkClicked)
        {
            isClicked(Greenfoot.mouseClicked(this));
        }
        if(turnEnd)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24 && turnEnd)
            {
                GreenfootImage img = new GreenfootImage("button-round-plus-icon.png");
                /*GreenfootImage txt = new GreenfootImage("EnQueue", 30, Color.RED, tr);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                setImage(img);
            }
            else    setImage(n);
        }
        else    setImage(n);
        END(CQEnded);
    }    
    
    public void isClicked(boolean a)
    {
        if(a)//돌고 있는 중에는 클릭을 해도 무시하도록 한다.
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            
            if(((Board)getWorld()).Cq.ch.turnE)
                checkClicked = true;
            else    checkClicked = false;
        }
    }
}
