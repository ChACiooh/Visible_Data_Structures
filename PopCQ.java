import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.*;
/**
 * Write a description of class PopCQ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PopCQ extends CircularQueue
{
    Color tr = new Color(0,0,0,0);
    int x, y;
    GreenfootImage n;
    /**
     * Act - do whatever the PopCQ wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    PopCQ()
    {
        pro = true;
        click = false;
        x=y=0;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro=!pro;
            GreenfootImage image = new GreenfootImage(getImage());
            /*GreenfootImage text = new GreenfootImage("DeQueue", 30, Color.RED, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);*/
            setImage(image);
            n=image;
        }
        if(!click)
        {
            if(((Board)getWorld()).Cq.ch.turnE)
            {
                try
                {
                    x = Greenfoot.getMouseInfo().getX();
                    y = Greenfoot.getMouseInfo().getY();
                } catch(NullPointerException npe){};
                if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24 && turnEnd)
                {
                    GreenfootImage img = new GreenfootImage("button-round-eject-icon.png");
                    /*GreenfootImage txt = new GreenfootImage("DeQueue", 30, Color.RED, tr);
                    img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                    setImage(img);
                }
                else    setImage(n);
            }
            else    setImage(n);
            isClicked(Greenfoot.mouseClicked(this));
        } else  setImage(n);
        END(CQEnded);
    }    
    
    private void isClicked(boolean a)
    {
        if(a)//돌고 있는 중에는 클릭을 해도 무시하도록 한다.
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            if(((Board)getWorld()).Cq.TE)
                click = true;
            else
                click = false;
            turnEnd = false;
        }
    }
}
