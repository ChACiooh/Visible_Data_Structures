import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Arrow extends List
{
    //GreenfootImage arrow = new GreenfootImage(600, 600);
    protected int origin_X, origin_Width;
    public int No;
    public boolean decrease = false;
    public boolean sizeComplete = false;
    GreenfootImage img = new GreenfootImage(getImage());
    public static LastArrow2[] LA2 = new LastArrow2[6];
    ArrowSpot AS;
    public Arrow() {}
    
    public Arrow(int num)
    {
        decrease = false;
        sizeComplete = false;
        No = num;
        pro = true;
    }
    
    public void act() 
    {
        if(pro)
        {
            pro = !pro;
            int temp;
            origin_Width = img.getWidth();
            origin_X = getX();
            
            //arrow.setColor(new Color(0,0,0));//Black
            AS = new ArrowSpot();
            getWorld().addObject(AS, getX()-51, getY()+14);
            for(temp = origin_Width; temp<origin_Width*4;temp++);
            img.scale(temp, img.getHeight()*3/4);
            setImage(img);
        }
        if(ListEnded)
        {
            //초기화 및 삭제되는 과정.
            decrease = false;
            origin_Width = 0;
            origin_X = 0;
            No = 0;
            getWorld().removeObject(this);
        }
        else
        {
            decreaseWidth();
            if(origin_X > getWorld().getWidth()) origin_X -= 720;
            if(img.getWidth() == origin_Width)  sizeComplete = true;
        }
    }    
    
    protected void amendArrow()
    {
        if(sizeComplete)
        {
            if( getX()+35 != origin_X )    setLocation(origin_X-35, getY());
        }
    }
    
    public void decreaseChange()
    {
        if(!decrease)   decrease = true;
    }
    
    private void decreaseWidth()//크기를 줄이고 이동하는 메서드
    {
        if(decrease)
        {
            if(getX()>origin_X-35)  setLocation(getX()-5, getY());
            if(img.getWidth()>origin_Width)
            {
                img.scale(img.getWidth()/2, img.getHeight());
                setImage(img);
            }
            else if(getX() == origin_X-35 && img.getWidth() == origin_Width)
            {
                decrease = false;
            }
        }
    }
    
    public void getDistance()
    {
        System.out.println("origin_Width : "+origin_Width+", Now Width : "+img.getWidth());
    }

}
