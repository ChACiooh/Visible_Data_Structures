import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.io.IOException;
public class EB extends Select
{
    int num, thisXSize, thisYSize;
    BfE bfe;
    int time;
    public EB(int No)
    {
        num = No;
        time = 0;
        GreenfootImage img = new GreenfootImage(getImage());
        /*GreenfootImage txt = new GreenfootImage("설명 보기", 30, Color.RED, t);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);*/
        setImage(img);
        n = img;
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
        b = new GreenfootImage("Toolbar-Regular-Get-Info-icon2.png");
    }
    public void act() 
    {
        // Add your action code here.
        mouseOn();
    }    
   
    
    private void mouseOn()
    {
        try
        {
            x = Greenfoot.getMouseInfo().getX();
            y = Greenfoot.getMouseInfo().getY();
        } catch(NullPointerException npe){};
        if(x>getX()-thisXSize/2 && x<getX()+thisXSize/2 && y>getY()-thisYSize/2 && y<getY()+thisYSize/2)
        {/*
            if(bfe==null)
            {
                bfe = new BfE(num);
                getWorld().addObject(bfe, getWorld().getWidth()/2, getWorld().getHeight()/2-80);
            }*/
            /*GreenfootImage txt = new GreenfootImage("설명 보기", 30, Color.RED, t);
            b.drawImage(txt, (b.getWidth()-txt.getWidth())/2, (b.getHeight()-txt.getHeight())/2);*/
            setImage(b);
            if(time == 0)
                checkClicked(Greenfoot.mouseClicked(this));
            else if(time>0)
            {
                time++;
                if(time == 7)   time = 0;
            }
        }
        else
        {
            setImage(n);
        }
        /*else if(bfe!=null)
        {
            setImage(n);
            getWorld().removeObject(bfe);
            bfe = null;
        }*/
        
    }
    
    public void checkClicked(boolean a)
    {
        if(a)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            time++;
            switch(num)
            {
                case 1:
                break;
                case 2:
                break;
                case 3:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\StackHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
                case 4:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\QueueHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
                case 5:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\QueueHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
                case 6:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\ListHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
                case 7:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\HeapHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
                case 8:
                try 
                {
                    Process pc = Runtime.getRuntime().exec("cmd.exe /c start iexplore.exe "+System.getProperty("user.dir")+"\\html\\BinarySearchTreeHtml.html");
                } catch (IOException ex) 
                {
                    System.out.println(ex.getMessage());
                    System.out.println();
                }
                break;
            }
        }
    }
}
