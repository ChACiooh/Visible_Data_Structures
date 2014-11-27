import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class HeapSort extends Heap
{
    GreenfootImage img = new GreenfootImage(base);
    GreenfootImage txt = new GreenfootImage("힙 정렬", 27, Color.RED, t);
    public HeapSort()
    {
        //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        n = img;
    }
    public void act() 
    {
        if(HeapEnded)
        {
            getWorld().removeObject(this);
        }
        else
        {
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
                if(mx>getX()-60 && mx<getX()+60 && my>getY()-24 && my<getY()+24 && !HeapSorting && sortComplete && !HeapSortCompleted)
                {
                    img = new GreenfootImage("button-round-random-icon.png");
                    //txt = new GreenfootImage("시작", 27, Color.RED, t);
                    //img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                    setImage(img);
                    checkClicked();
                }
                else    setImage(n);
            }
            catch(NullPointerException npe){};
        }
    }    
    
    private void checkClicked()
    {
        if(Greenfoot.mouseClicked(this)  && !HeapSortCompleted && sortComplete && !HeapSorting && !HeapSortCan && !HeapSortComplete && !HeapSortCompleted)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            HeapSorting = true;
            HeapSortCan = true;
        }
    }
}
