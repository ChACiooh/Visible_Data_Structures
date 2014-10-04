import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.io.IOException;
import java.lang.*;
/**
 * Write a description of class HowToPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HowToPlay extends Select
{
    public int thisXSize, thisYSize;
    public boolean produced;
    int time;
    { delete = false; }
    HowToPlay()
    {
        delete = false;
        time = 0;
        produced = false;
        GreenfootImage img = new GreenfootImage(getImage());
        /*GreenfootImage txt = new GreenfootImage("설명 보기", 30, Color.RED, t);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
        setImage(img);
        n = img;
        b = new GreenfootImage("Toolbar-Regular-Get-Info-icon2.png");
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
    }
    
    public void updateImage(String file_name)
    {
        GreenfootImage image = new GreenfootImage(file_name);
        /*GreenfootImage text = new GreenfootImage("설명 보기", 30, Color.RED, t);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);*/
        setImage(image);
    }
    
    public void act() 
    {
        if(produced)
        {
            try
            {
                x = Greenfoot.getMouseInfo().getX();
                y = Greenfoot.getMouseInfo().getY();
                if(x > getX()-thisXSize/2 && x<getX()+thisXSize/2 && y>getY()-thisYSize/2 && y<getY()+thisYSize/2)
                {
                    /*GreenfootImage txt = new GreenfootImage("설명 보기", 30, Color.RED, t);
                    b.drawImage(txt, (b.getWidth()-txt.getWidth())/2, (b.getHeight()-txt.getHeight())/2-1);*/
                    setImage(b);
                    if(time == 0)
                        checkClicked(Greenfoot.mouseClicked(this));
                    else if(time>0)
                    {
                        time++;
                        if(time == 7)   time = 0;
                    }
                }
                else    setImage(n);
                
            } catch(NullPointerException npe){};
        }
        try
        {
            if(delete)  getWorld().removeObject(this);
        }catch(NullPointerException npe){};
    }    
    
    public void checkClicked(boolean a)
    {
        if(a || delete)
        {
            //설명창을 띄우고 사라지는 과정. start 버튼이 눌려도 이것은 제거되어야 한다.
            if(delete)
                getWorld().removeObject(this);
            else
            {
                time++;
                try
                {
                    Greenfoot.playSound("click.wav");
                }catch(Exception iae)
                {
                    
                }
                /*
                //설명
                //((Board)getWorld()).deleteSelected();
                ((Board)getWorld()).start.delete = true;
                getWorld().addObject(new HomeButton(1), 80, getWorld().getHeight()-80);
                getWorld().removeObject(((Board)getWorld()).main);
                ((Board)getWorld()).main = null;
                //이 위치에 뭔가를 띄워야 할 듯.
                ((Board)getWorld()).newExplanation();
                getWorld().removeObject(this);*/
                
                try 
                {
                    //Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe http://javacodespot.blogspot.com");
                    //System.out.println("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\ExplanationHtml.html");
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\ExplanationHtml.html");                    
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                /*
                try{
                    AppletContext appletURL = Applet.getAppletContext();
                    URL u = new URL("\\html\\ExplanationHtml.html");
                    appletURL.showDocument(u, "_self");
                }catch(MalformedURLException e){
                    System.out.println(e.getMessage());
                }*/
            }
        }
    }
}
