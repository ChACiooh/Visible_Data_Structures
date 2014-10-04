import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class HeadPointer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeadPointer extends LinearQueue
{
    Color t = new Color(0,0,0,0);
    GreenfootImage img = new GreenfootImage(getImage());
    GreenfootImage text = new GreenfootImage("Front↓", 27, Color.BLACK, t);
    private int XTemp; // 초기 x축 위치 좌표.
    HeadPointer(){}
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            XTemp = getX();
            updateImage();
        }
        if(QueueEnded)
        {
            init();
            getWorld().removeObject(this);
        }
        else
        {
            shiftOfHeadPointer();
        }
    }   
    
    private void shiftOfHeadPointer()
    {
        if(head!=0 && clicked)//pop이 클릭되었다면
        {
            if(getX()<XTemp+head*L)
            {
                setLocation(getX()+8, getY());
            }
            //이 작업이 끝난 후 다른 객체들을 이동시킴으로써 상대적으로 무한한 길이의 Queue를 만들었음을 보인다.
        }
        /*else if(!clicked && getX()>XTemp)
        {
            setLocation(getX()-10, getY());
        }*/
    }
    
    private void updateImage()
    {
        img.drawImage(text, (img.getWidth()-text.getWidth())/2-text.getWidth()/2+5, 
                    (img.getHeight()-text.getHeight())/2);
        setImage(img);
    }
}
