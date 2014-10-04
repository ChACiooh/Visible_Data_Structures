import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class PopLQ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PushLQ extends LinearQueue
{
    int K;
    Color tr = new Color(0,0,0,0);
    Barrel[] barrel = new Barrel[18];
    int x,y;
    GreenfootImage n;
    PushLQ()
    {
        for(int i=0;i<barrel.length;i++)    barrel[i] = null;
        pro = true;
        x = y = K = 0;
        pushEnd = true;
    }
    /**
     * Act - do whatever the PopLQ wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro=!pro;
            GreenfootImage image = new GreenfootImage(getImage());
            /*GreenfootImage text = new GreenfootImage("EnQueue", 30, Color.RED, tr);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);*/
            setImage(image);
            n=image;
        }
        if(QueueEnded)//큐가 종료되면 자동으로 사라진다.
        {
            getWorld().removeObject(this);
        }
        else if(pushEnd)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(x > getX()-60 && x<getX()+60 && y>getY()-24 && y<getY()+24 && pushEnd)
            {
                GreenfootImage img = new GreenfootImage("button-round-plus-icon.png");
                /*GreenfootImage txt = new GreenfootImage("EnQueue", 30, Color.RED, tr);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
                setImage(img);
            }
            else    setImage(n);
            addBarrel(Greenfoot.mouseClicked(this));
        }
        else    setImage(n);
    }    
    
    private void addBarrel(boolean a)
    {
        if(a && pushEnd)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            //K = 40*(tail-head+1); // 마지막 요소의 위치 X값
            boolean key = false;
            int data = 0;
            String input;
            do
            {
                key = false;
                input = JOptionPane.showInputDialog(null, "정수를 입력해 주세요.\n(-99~999 권장)");
                if(input == null)
                {
                    return;
                }
                try
                {
                    data = Integer.parseInt(input);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "정확히 입력해 주세요.");
                    key = true;
                }
            }while(key);
            
            K = 40*(tail+1);
            
            if(K+20<=getWorld().getHeight()*19/20)//마지막 위치가 큐의 크기에 내에 적합한다면
            {
                pushEnd = false;
                barrel[tail%barrel.length] = new Barrel(K, data);
                sp++;//들어오는 요소의 번호에 관여한다.
                getWorld().addObject(barrel[tail%barrel.length], getWorld().getWidth()-10, getWorld().getHeight()/2);
                KQ[tail] = data;
                tail++;//생성시킨 후 tail을 증가시킨다.
                
            }
            else    getWorld().addObject(new OverFlow(), getWorld().getWidth()/2, getWorld().getHeight()/3);//이외에는 overflow
        }
    }
}
