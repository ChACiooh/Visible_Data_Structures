import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class AP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AP extends StackMission
{
    /**
     * Act - do whatever the AP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int a, b;
    public static int[] AnswerAP = new int[13];
    public static String stringAP;
    Color t = new Color(0,0,0,0);
    int xxx, yyy;
    GreenfootImage n;
    public AP()
    {
        pro = true;
        xxx = yyy = 0;
    }
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            GreenfootImage image = new GreenfootImage(getImage());
                GreenfootImage text = new GreenfootImage("등차수열", 30, Color.RED, t);
                image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                    (image.getHeight()-text.getHeight())/2);
                setImage(image);
                n = image;
        }
        if(!StackMissionEnded)
        {
            try
            {
                xxx = Greenfoot.getMouseInfo().getX();
                yyy = Greenfoot.getMouseInfo().getY();
            } catch(NullPointerException npe){};
            if(xxx > getX()-60 && xxx<getX()+60 && yyy>getY()-24 && yyy<getY()+24)
            {
                GreenfootImage img = new GreenfootImage("Selected.png");
                GreenfootImage txt = new GreenfootImage("등차수열", 30, Color.RED, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
            }
            else    setImage(n);
        }
    }    
    
    protected void printAP()
    {
        a = Greenfoot.getRandomNumber(5)+1;//초항
        b = 1;//Greenfoot.getRandomNumber(10)+1;//공차
        stringAP = new String("첫 항이 "+a+"이고, 공차가 "+b+"인 등차수열을 만드세요.");
        for(int i=0;i<AnswerAP.length;i++)
            AnswerAP[i] = a + b*i; // 답 생성.
        
        StackMissionEnded = true;//제거해도 됨.
        printMessageOfAP();//출력
    }
    
   
    
    public static void printMessageOfAP()
    {
        //static으로 함으로써, 나중에 미션이 기억나지 않을 경우 언제든지 호출할 수 있도록 한다.
        JOptionPane.showMessageDialog(null, stringAP);
    }
}
