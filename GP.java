import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class GP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GP extends StackMission
{
    int a, b;
    public static String stringGP;
    public static int[] AnswerGP = new int[13];
    GreenfootImage n;
    int xxx, yyy;
    Color t = new Color(0,0,0,0);
    /**
     * Act - do whatever the GP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public GP()
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
            GreenfootImage text = new GreenfootImage("등비수열", 30, Color.BLACK, t);
            image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                (image.getHeight()-text.getHeight())/2);
            n = image;
            setImage(image);
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
                GreenfootImage txt = new GreenfootImage("등비수열", 30, Color.RED, t);
                img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
                setImage(img);
            }
            else    setImage(n);
        }
    }    
    
    protected void printGP()
    {
        a = Greenfoot.getRandomNumber(5)+1;//초항
        b = Greenfoot.getRandomNumber(3)+2;//공비
        stringGP = new String("첫 항이 "+a+"이고, 공비가 "+b+"인 등비수열을 만드세요.");
        AnswerGP[0] = a;
        for(int i=1;i<AnswerGP.length;i++)
        {
            AnswerGP[i] = b*AnswerGP[i-1];
        }
        StackMissionEnded = true;
        printMessageOfGP();
    }
    
    public static void printMessageOfGP()
    {
        //static으로 함으로써, 나중에 미션이 기억나지 않을 경우 언제든지 호출할 수 있도록 한다.
        JOptionPane.showMessageDialog(null, stringGP);
    }
}
