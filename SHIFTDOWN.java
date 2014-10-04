import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class SHIFTDOWN extends Heap
{
    GreenfootImage base = new GreenfootImage(getImage());
    GreenfootImage txt, img;
    Color t = new Color(0,0,0,0);
    SHIFTDOWN()
    {
        pro = true;
        mx = my = 0;
    }
    public void act() 
    {
        if(pro)
        {
            pro = false;
            //txt = new GreenfootImage("SHIFT DOWN", 24, Color.RED, t);
            img = new GreenfootImage(base);
            //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
            n = img;
        }
        if(!HeapEnded)
        {
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(mx>getX()-60 && mx<getX()+60 && my>getY()-24 && my<getY()+24 && !SHIFTDOWN_Sort && !SHIFTUP_Sort)
            {
                img = new GreenfootImage("button-round-dark-arrow-down-icon.png");
                //txt = new GreenfootImage("시작", 24, Color.RED, t);
                //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
                checkClicked();
            }
            else    setImage(n);
            
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
    
    private void checkClicked()
    {
        if(Greenfoot.mouseClicked(this) && (!SHIFTDOWN_Sort/* || !SHIFTUP_Sort*/))
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            SHIFTDOWN_Sort = true;
        }
    }
}
