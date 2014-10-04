import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class Explanations extends Actor
{
    boolean pro, pBpro, nBpro, key;
    NextButton nB;
    PreviousButton pB;
    Page p;
    protected static final int LIMIT = 7;
    protected int now_Num;
    public static int target = 0;
    protected static final int TIME_LIMIT = 5;
    public static boolean expFin = false;
    Color t;
    int delayTime;
    int x, y;
    { target = 0; expFin = false; } //인스턴스 초기화.
    /**
     * GreenfootImage 를 배열로 만들어서 각각에 대한 설명을 객체로 만든다.
     * 이때 베이스가 될 이미지는 Board에서 썼던 것으로 하는 게 좋을 듯하다.
     * 아마 Board에서는 이미지를 쓰지 않았던 것으로 기억하므로, 500px*250px 크기 정도의 사각형 이미지를 만들어서
     * 일괄적으로 이용하도록 하자.
     */
    public Explanations()
    {
        pro = true;
        x=y=0;
        t = new Color(0,0,0,0);
    }
    public void act()
    {
        if(pro)
        {
            pro = !pro;
            nBpro = true;
            pBpro = false;
            now_Num = 1;
            p = new Page();
            nB = new NextButton();
            pB = new PreviousButton();
            getWorld().addObject(p, getWorld().getWidth()/2, getWorld().getHeight()-60);
            if(nBpro)   getWorld().addObject(nB, getWorld().getWidth()/2+50, getWorld().getHeight()-60);
        }
        if(expFin)
        {
            //초기화 및 제거 과정.
            getWorld().removeObject(nB);
            getWorld().removeObject(pB);
            getWorld().removeObject(p);
            p = null;
            nB = null;
            pB = null;
            target = 1;
            getWorld().removeObject(this);
        }
        else
        {
            if(!nBpro && nB!=null)
            {
                getWorld().removeObject(nB);
                nB = null;
            }
            else if(nBpro && nB==null)
            {
                nB = new NextButton();
                getWorld().addObject(nB, getWorld().getWidth()/2+50, getWorld().getHeight()-60);
            }
            if(!pBpro && pB!=null)
            {
                try
                {
                    getWorld().removeObject(pB); //addObject 된 적 없다면 이 부분은 저절로 skip.
                } catch(Exception e) {};
                pB = null;
            }
            else if(pBpro && pB==null)
            {
                pB = new PreviousButton();
                getWorld().addObject(pB, getWorld().getWidth()/2-50, getWorld().getHeight()-60);
            }
            checkButton();
        }
    }
    void checkButton()
    {
        now_Num += target;
        target=0;
        if(now_Num<1)   now_Num=1;
        else if(now_Num>LIMIT)  now_Num=LIMIT;
        if(now_Num == LIMIT)
        {
            nBpro = false;
        } else  nBpro = true;
        if(now_Num == 1)
        {
            pBpro = false;
        } else  pBpro = true;
    }
    public void targetPP()
    {
        target=1;
    }
    public void targetMM()
    {
        target=-1;
    }
}
