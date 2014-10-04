import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class SelectQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectLinearQueue extends Select
{
    SelectLinearQueue()
    {
        f=true;
        g=true;
        b= new GreenfootImage("lqueue1.png");
    }
    /**
     * Act - do whatever the SelectQueue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(f)
        {
            f=false;
            n = express("lqueue1.png", "", 27);
        }
        if(g)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24)
            {
                //GreenfootImage txt = new GreenfootImage("선형 큐", 27, Color.RED, t);
                //b.drawImage(txt, 10, (b.getHeight()-txt.getHeight())/2);
                setImage(b);
            }
            else    setImage(n);
            checkClicked(Greenfoot.mouseClicked(this));
        }
        else    getWorld().removeObject(this);
    }    
    
    public void checkClicked(boolean a)
    {
        if(a)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            g=false;
            ((Board)getWorld()).Lq.init();
            //Reset 버튼을 누를 경우, static 인스턴스 초기화가 실행되지 않아 init() 메서드를 호출시켜준다.
            ((Board)getWorld()).newLinearQueue();
            for(int i=0;i<((Board)getWorld()).main.pannel.length;i++)
                    getWorld().removeObject(((Board)getWorld()).main.pannel[i]);
            getWorld().removeObject(((Board)getWorld()).main);
            getWorld().addObject(((Board)getWorld()).Lq, getWorld().getWidth()/2, getWorld().getHeight()/2);
            ((Board)getWorld()).GM.setSituation(2);
            getWorld().removeObject(this);
        }
    }
}
