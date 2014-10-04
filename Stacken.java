import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Stacken here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stacken extends Stack
{
    int lastHeight, B;
    private boolean first;
    private static final int ACCELERATION = 1;
    int speed;
    private int spt;//sp와 비교하는 변수
    private boolean pushend = false, effectOn = false;
    Color tr = new Color(0,0,0,0);
    public int printSp;
    GreenfootImage effect;
    Stacken(){}
    Stacken(int a, int b)
    {
        GreenfootImage img = new GreenfootImage(getImage());
        effect = new GreenfootImage("EffectBrick.png");
        effect.scale((int)(effect.getWidth()*0.6), (int)(effect.getHeight()*0.6));
        B = a;
        speed = 2;
        spt = b;
        img.scale(150, 30);
        sp2++;
        printSp=sp2;
        setImage(img);
        first = true;
    }
    public void act() 
    {
        // Add your action code here.
        if(!StackEnded)//끝나지 않았다면
        {
            updateImage();
            if(first)
            {
                initializeHeight();
                movePush();
            }
            movePop();
            
            if(!effectOn && StackGameCleared)
            {
                effectImage();
            }
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
    
    void initializeHeight()
    {
        lastHeight = getWorld().getHeight()-B;
    }
    
    void movePush()
    {
        if(getY()<=lastHeight-30)
        {
            pushend = false;
            speed += ACCELERATION;
            setLocation(getX(), getY()+speed);
        }
        else if(getY()>lastHeight-30)
        {
            while(getY()<lastHeight)
            {
                setLocation(getX(), getY()+1);
            }
            if(!pushend)
            {
                speed = 2;
                pushend = true;
                time = 1;
                //pushClicked = false;
                first = false;
            }
        }
    }
    
    void movePop()
    {
        if(popClicked && spt == sp-1)
        {
            if(getY()>100)
            {
                speed += ACCELERATION;
                setLocation(getX(), getY()-speed);
            }
            else if(getY()<=100)
            {
                popClicked = false;
                sp--;
                time = 1;
                getWorld().removeObject(this);
            }
        }
    }
    
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(getImage());
        GreenfootImage text = new GreenfootImage(""+printSp, 22, Color.WHITE, tr);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    
    private void effectImage()
    {
        if(effect!=null)
        {
            effectOn = !effectOn;//바꿔준다.
            GreenfootImage txt = new GreenfootImage(""+printSp, 22, Color.WHITE, tr);
            effect.drawImage(txt, (effect.getWidth()-txt.getWidth())/2, (effect.getHeight()-txt.getHeight())/2);
            setImage(effect);
        }
    }
}
