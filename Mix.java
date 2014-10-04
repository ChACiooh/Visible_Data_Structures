import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class Mix extends Heap
{
    GreenfootImage txt;
    GreenfootImage img;
    int thisXSize, thisYSize;
    Mix()
    {
        pro = true;
    }
    public void act() 
    {
        if(pro)
        {
            pro = false;
            txt = new GreenfootImage("섞기", 20, Color.WHITE, t);
            img = new GreenfootImage(base);
            img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
            thisXSize = getImage().getWidth();
            thisYSize = getImage().getHeight();
            n = img;
        }
        if(!HeapEnded)
        {
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if((/*SHIFTDOWN_Sort || SHIFTUP_Sort ||*/ mixCan) && mx>getX()-thisXSize/2 && mx<getX()+thisXSize/2 && my>getY()-thisYSize/2 && my<getY()+thisYSize/2)
            {
                img = new GreenfootImage("SH2.png");
                txt = new GreenfootImage("섞기", 20, Color.YELLOW, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
            }
            else    setImage(n);
            checkClicked();
        }
        else
        {
            getWorld().removeObject(this);
        }
    }        
    
    private void checkClicked()
    {
        if(Greenfoot.mouseClicked(this) && mixCan)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            ((Board)getWorld()).H.init();
            mixCan = false;
        }
    }
}
