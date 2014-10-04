import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GotoRoottest extends SearchBinaryTreetest
{
    GreenfootImage n;
    int mx, my, thisXSize, thisYSize;
    
    GotoRoottest()
    {
        n = new GreenfootImage("button-round-dark-arrow-up-icon.png");
        mx = my = 0;
        thisXSize = getImage().getWidth();
        thisYSize = getImage().getHeight();
    }
    
    public void act() 
    {
        // Add your action code here.
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
            if(mx>getX()-thisXSize/2 && mx<getX()+thisXSize/2 && my>getY()-thisYSize/2 && my<getY()+thisYSize && !rclick && !click)
            {
                setImage(n);
                checkClick();
            } else  setImage(base);
        }
    }    
    
    private void checkClick()
    {
        if(Greenfoot.mouseClicked(this) && !rclick && !click)
        {
            try
            {
                Greenfoot.playSound("click.wav");
            }catch(Exception e){};
            rclick = !rclick;
            //rtime = 0;
            ((Board)getWorld()).rtimeZeroForSBTtest();
        }
    }
}
