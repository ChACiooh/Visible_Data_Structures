import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class Main extends Actor
{
    boolean pro;
    GreenfootImage img = new GreenfootImage(getImage());
    GreenfootImage txt = new GreenfootImage("Projected by Ch A Ciooh", 22, Color.BLACK, new Color(0,0,0,0));
    Pannel[] pannel = new Pannel[3];
    int[] OriginX = new int[3];
    int[] OriginY = new int[3];
    private int time;
    Color parlet = new Color(170,160,150);
    public Main()
    {
        pro = true;
        time = 0;
        for(int i=0;i<pannel.length;i++)    pannel[i] = new Pannel();
        pannel[0].setImage("Visible.png");
        OriginX[0] = pannel[0].getImage().getWidth();
        OriginY[0] = pannel[0].getImage().getHeight();
        pannel[1].setImage("DATA.png");
        OriginX[1] = pannel[1].getImage().getWidth();
        OriginY[1] = pannel[1].getImage().getHeight();
        pannel[2].setImage("Structures.png");
        OriginX[2] = pannel[2].getImage().getWidth();
        OriginY[2] = pannel[2].getImage().getHeight();
        img.drawImage(txt, 800-170, 800-50);
        setImage(img);
    }
    
    public void addPannels()
    {
        pannel[0].setImage("Visible.png");
        pannel[1].setImage("DATA.png");
        pannel[2].setImage("Structures.png");
        getWorld().addObject(pannel[0], getWorld().getWidth()/4, 100);
        pannel[0].setRotation(351);
        getWorld().addObject(pannel[1], getWorld().getWidth()/2+20, 100);
        getWorld().addObject(pannel[2], getWorld().getWidth()*3/5, 157);
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = false;
            time = 0;
            //getImage().setColor(parlet);
            //getImage().fillRect(0, 0, 800, 800);
            /*GreenfootImage img = new GreenfootImage(getImage());
            img.setTransparency(100);
            setImage(img);*/
            if(!Board.first)//맨 처음이 아니라면
            {/*
               ((Board)getWorld()).newStart();//Start 버튼 생성.
               ((Board)getWorld()).newHowToPlay();//How to play 버튼 생성.
               addPannels();*/
               Board.first = true;
            }
        }
        try
        {
            if(time < 150 && Board.first)
            {
                Title();
            }
            if(time == 150)
            {
               ((Board)getWorld()).newStart();//Start 버튼 생성.
               ((Board)getWorld()).newHowToPlay();//How to play 버튼 생성.
               time++;
            }
            if(time>150 && time <=180)
            {
                //((Board)getWorld()).start.getImage().setTransparency((int)(time-150)*255/(180-150));
                //((Board)getWorld()).HTP.getImage().setTransparency((int)(time-150)*255/(180-150));
                //System.out.println("time : "+time);
                ((Board)getWorld()).start.updateImage("Button-Turn-On-icon.png");
                ((Board)getWorld()).HTP.updateImage("Sign-Info-icon.png");
                ((Board)getWorld()).start.getImage().scale(((Board)getWorld()).start.thisXSize, (int)((time-150)*( ((Board)getWorld()).start.thisYSize/30)));
                ((Board)getWorld()).HTP.getImage().scale(((Board)getWorld()).HTP.thisXSize, (int)((time-150)*(63/30)));
                time++;
            }
            if(time==181)
            {
                time++;
                ((Board)getWorld()).start.produced = true;
                ((Board)getWorld()).HTP.produced = true;
            }
        }
        catch (Exception e)
        {
        };
    }    
    
    private void Title()
    {
        if(time==0)
        {
            getWorld().addObject(pannel[0], getWorld().getWidth()/4, 100);
            
            //System.out.println(""+OriginX[0]+" "+OriginY[0]);
            pannel[0].getImage().scale(4, 3);
        }
        if(time>=1 && time <40)
        {
            pannel[0].setImage("Visible.png");
            pannel[0].setRotation(time*9);
            pannel[0].getImage().scale((int)(time*5.2*1.5), (int)(time*2.6*1.5));
        }
        if(time == 40)
        {
            getWorld().addObject(pannel[1], getWorld().getWidth()/2+20, 100);
            
            //System.out.println(""+OriginX[1]+" "+OriginY[1]);
            pannel[1].getImage().scale(OriginX[1], 3);
        }
        if(time >=41 && time <70)
        {
            pannel[1].setImage("DATA.png");
            pannel[1].getImage().scale( OriginX[1], (int)((time-40)*5.2));
        }
        if(time == 70)
        {
            getWorld().addObject(pannel[2], getWorld().getWidth()*3/5, 157);
            
            //System.out.println(""+OriginX[2]+" "+OriginY[2]);
            pannel[2].getImage().setTransparency(0);
        }
        if(time >70 && time<150)
        {
            pannel[2].getImage().setTransparency((int)((time-70)*255/(149-70)));
        }
        time++;
    }
}
















