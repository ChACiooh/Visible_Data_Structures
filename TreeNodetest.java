import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class TreeNodetest extends SearchBinaryTreetest
{
    int No;
    int font_size = 35;
    
    public boolean selected;
    
    Color t = new Color(0,0,0,0);
    
    Vector p = new Vector();
    
    TreeNodetest t1, t2;
    boolean isNull1, isNull2;
    
    public static final int DELAY_TIME = 12;
    
    int delay;
    
    TreeNodetest()
    { 
        pro = true; 
    }
    
    TreeNodetest(int num)
    {
        //pro = true;
        No = num;
        delay = DELAY_TIME;
        isNull1 = isNull2 = true;
        if(No>=10000 || No<=-10000)    font_size = 25;
        if(No>=100000 || No<=-100000)   font_size = 22;
    }
    
    public void delay()
    {
        if(delay >= 0 && delay < DELAY_TIME)
        {
            delay ++;
            setImage("TreeNodeS.png");
        }
        else
        {
            delay = -1;
            pro = true;
        }
    }
    
    public void setNodePositionVector(int a, int b)
    {
        p.dx = a;
        p.dy = b;
    }
    
    public void setTVector(Vector Tv)
    {
        p = Tv;
    }
    
    public void act() 
    {
        delay();
        if(delay == -1)
        {
            if(pro)
            {
                pro = !pro;
                p.dx = getX();
                p.dy = getY();
                selected = false;
                setImage(base);
            }
            if(!SBTEnded)
            {
                if(getX()>40 && getX()<getWorld().getWidth()-40 && getY()>40 && getY()<getWorld().getHeight()*4/5-40)
                    printNum(selectOrNot());
            }
            else    getWorld().removeObject(this);
        }
    }    
    
    protected void controlTransparency(int t)
    {
        getImage().setTransparency(t);
    }
    
    void printNum(GreenfootImage background)
    {
        txt = new GreenfootImage(""+No, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage(background);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
    }
    
    public GreenfootImage selectOrNot()
    {
        if(selected)    return new GreenfootImage("SH3.png");
        else    return base;
    }
    
    public TreeNodetest getTreeNode()
    {
        return new TreeNodetest(No);
    }
}
