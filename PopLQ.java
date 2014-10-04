import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class PopLQ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PopLQ extends LinearQueue
{
    Color tr = new Color(0,0,0,0);
    GreenfootImage n;
    int x,y;
    /**
     * Act - do whatever the PopLQ wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public PopLQ()
    {
        pro = true;
        clicked = false;
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
        if(QueueEnded)
        {
            init();
            getWorld().removeObject(this);
        }
        else if(pushEnd)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24)
            {
                GreenfootImage img = new GreenfootImage("button-round-eject-icon.png");
                /*GreenfootImage txt = new GreenfootImage("DeQueue", 30, Color.RED, tr);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                setImage(img);
            }
            else    setImage(n);
            popBarrel();
        }
        else if(!pushEnd)   setImage(n);
    }    
    
    private void popBarrel()
    {
        if(Greenfoot.mouseClicked(this) && !clicked)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            if(tail>head)
            {
                clicked = true;
                head++;//empty 또는 overflow가 아닌 경우, head를 증가시킨다.
            }
            else if(head == tail)//tail == head => empty
                getWorld().addObject(new Empty(), getWorld().getWidth()/2, getWorld().getHeight()/3);
        }
    }
}
