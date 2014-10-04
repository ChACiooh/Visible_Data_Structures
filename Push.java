import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Push here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Push extends Stack
{
    Color transparent = new Color(0,0,0,0);
    private static final int K = 40;//간격 덧셈 상수
    Stacken[] stacken = new Stacken[13];
    GreenfootImage n;
    int xxx, yyy;
    /**
     * Act - do whatever the Push wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Push()
    {
        pro = true;
        xxx = yyy = 0;
        pushClicked = false;
    }
    
    public void act() 
    {
        // Add your action code here.
        if(!StackEnded)
        {
            if(pro)
            {
                pro=!pro;
                GreenfootImage image = new GreenfootImage(getImage());
                /*GreenfootImage text = new GreenfootImage("PUSH", 30, Color.RED, transparent);
                image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);*/
                n = image;
                setImage(image);
            }
            try
            {
                xxx = Greenfoot.getMouseInfo().getX();
                yyy = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(xxx > getX()-60 && xxx<getX()+60 && yyy>getY()-24 && yyy<getY()+24 && !pushClicked && !popClicked)
            {
                GreenfootImage img = new GreenfootImage("button-round-plus-icon.png");
                /*GreenfootImage txt = new GreenfootImage("PUSH", 30, Color.RED, transparent);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                setImage(img);
                addStacken();
            }
            else    setImage(n);
            
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
    
    public void addStacken()
    {
        if(Greenfoot.mouseClicked(this) && !popClicked && !pushClicked && !StackGameCleared)
        {
            //y=K*(sp+1);
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            pushClicked = true;
            y+=K;
            if(getWorld().getHeight()-y > getWorld().getHeight()/3)
            {
                stacken[sp] = new Stacken(y, sp);
                getWorld().addObject(stacken[sp], getWorld().getWidth()/2, 100);
                sp++;
            }
            else
            {
                y-=K;
                getWorld().addObject(new OverFlow(), getWorld().getWidth()/2, 100);
                pushClicked = false;
            }
        }

    }
}
