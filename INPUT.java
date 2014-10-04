import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class INPUT extends SearchBinaryTree
{
    GreenfootImage n;
    int mx, my, thisXSize, thisYSize;
    
    INPUT()
    {
        n = new GreenfootImage("button-round-plus-icon.png");
        mx = my = 0;
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
    }
    
    public void act() 
    {
        if(SBTEnded)
        {
            getWorld().removeObject(this);
        }
        else
        {
            try
            {
                mx = Greenfoot.getMouseInfo().getX();
                my = Greenfoot.getMouseInfo().getY();
            }
            catch(NullPointerException npe){}
            if(mx>getX()-thisXSize/2 && mx<getX()+thisXSize/2 && my>getY()-thisYSize/2 && my<getY()+thisYSize)
            {
                setImage(n);
                checkClick();
            } else  setImage(base);
        }
    }    
    
    private void checkClick()
    {
        if(Greenfoot.mouseClicked(this) && !click)
        {
            Greenfoot.playSound("click.wav");
            click = !click;
        }
    }
}
