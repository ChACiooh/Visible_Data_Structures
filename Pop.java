import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Pop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pop extends Stack
{
    Color tr = new Color(0,0,0,0);
    private static final int K = 40;//간격 덧셈 상수
    GreenfootImage n;
    int xxx, yyy;
    /**
     * Act - do whatever the Pop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Pop()
    {
        xxx = yyy = 0;
        pro = true;
        popClicked = false;
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
                /*GreenfootImage text = new GreenfootImage("POP", 30, Color.RED, tr);
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
            if(xxx > getX()-60 && xxx<getX()+60 && yyy>getY()-24 && yyy<getY()+24 && !popClicked && !pushClicked)
            {
                GreenfootImage img = new GreenfootImage("button-round-eject-icon.png");
                /*GreenfootImage txt = new GreenfootImage("POP", 30, Color.RED, tr);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                setImage(img);
                removeStacken();
            }
            else    setImage(n);
            
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
    
    private void removeStacken()
    {
        if(Greenfoot.mouseClicked(this) && sp>0 && !pushClicked && !popClicked && !StackGameCleared)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            y-=K;
            try
            {
                if( ( ((Board)getWorld()).SM.dataType==1 && ((Board)getWorld()).stack.push.stacken[sp-1].printSp == AP.AnswerAP[sp-1])
                || ( ((Board)getWorld()).SM.dataType==2 && ((Board)getWorld()).stack.push.stacken[sp-1].printSp == GP.AnswerGP[sp-1]) )
                check = 2;
            }catch(Exception e){};
            popClicked = true;
        }
        else if(sp == 0)
        {
            if(Greenfoot.mouseClicked(this) && !pushClicked && !popClicked)
            {
                getWorld().addObject(new Empty(), getWorld().getWidth()/2, 100);
            }
        }
    }
}
