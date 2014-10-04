import greenfoot.*;
import java.awt.*;

public abstract class Select extends Actor
{
    Color t = new Color(0,0,0,0);
    boolean f;//pro와 동일한 역할
    protected static boolean g=false;//다른 게임 종류 버튼 제거 확인 여부용 변수
    protected static boolean delete; // Start, How to play 버튼 제거 확인 여부용 변수
    GreenfootImage b;
    GreenfootImage n;
    int x = 0, y = 0;
    int thisXSize, thisYSize;
    
    protected GreenfootImage express(String file_name, String s, int a)
    {
        GreenfootImage image = new GreenfootImage(file_name);
        GreenfootImage text = new GreenfootImage(""+s, a, Color.BLACK, t);
        image.drawImage(text, 10/*(image.getWidth()-text.getWidth())/2*/, 
            (image.getHeight()-text.getHeight())/2);
        //setImage(image);
        return image;
    }
    
    public abstract void checkClicked(boolean a);
}