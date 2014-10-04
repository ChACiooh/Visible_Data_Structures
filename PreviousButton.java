import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class PreviousButton extends Explanations
{
    GreenfootImage n;
    PreviousButton()
    {
        delayTime = TIME_LIMIT;
        GreenfootImage img = new GreenfootImage(getImage());
        GreenfootImage txt = new GreenfootImage("이전", 25, Color.WHITE, t);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2+10, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
        n = img;
        key = false;
    }
    public void act() 
    {
        try
        {
            x = Greenfoot.getMouseInfo().getX();
            y = Greenfoot.getMouseInfo().getY();
        }catch(NullPointerException npe){};
        if(x>getX()-30&&x<getX()+30&&y>getY()-30&&y<getY()+30)
        {
            GreenfootImage img = new GreenfootImage("Previous2.png");
            GreenfootImage txt = new GreenfootImage("이전", 25, Color.WHITE, t);
            img.drawImage(txt, (img.getWidth()-txt.getWidth())/2+10, (img.getHeight()-txt.getHeight())/2);
            setImage(img);
        }
        else    setImage(n);
        if(Greenfoot.mouseClicked(this) && delayTime == TIME_LIMIT && !key)
        {
            targetMM();
            key = true;
        }
        if(delayTime <=TIME_LIMIT && delayTime>0 && key)
        {
            delayTime--;
        }
        else if(delayTime == 0)
        {
            delayTime = TIME_LIMIT;
            key = false;
        }
    }    
}
