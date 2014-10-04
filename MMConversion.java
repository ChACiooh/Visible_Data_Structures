import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class MMConversion extends Heap
{
    GreenfootImage txt = new GreenfootImage("MIN/\nMAX", 19, Color.WHITE, t);
    GreenfootImage img;
    int thisXSize, thisYSize;
    MMConversion()
    {
        pro = true;
        img = new GreenfootImage(base);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        n = img;
    }
    public void act() 
    {
        if(!HeapEnded)
        {
            if(pro)
            {
                thisXSize = getImage().getWidth();
                thisYSize = getImage().getHeight();
                pro = false;
            }
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
                img = new GreenfootImage(base);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                if(mx>getX()-thisXSize/2 && mx<getX()+thisXSize/2 && my>getY()-thisYSize/2 && my<getY()+thisYSize/2 && ConverseCan)
                {
                    img = new GreenfootImage("SH2.png");
                    GreenfootImage txt = new GreenfootImage("MIN/\nMAX", 19, Color.YELLOW, t);
                    img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                    setImage(img);
                }
                else    setImage(n);
                if(Greenfoot.mouseClicked(this) && ConverseCan )
                {
                    Greenfoot.playSound("click.wav");
                    mmConversion();
                }
            }catch(NullPointerException npe){};
        }
        else    getWorld().removeObject(this);
        
    }    
}
