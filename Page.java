import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class Page extends Explanations
{
    GreenfootImage img = new GreenfootImage(getImage());
    int pageNum, temp;
    BfE bfe;
    Page()
    {
        pageNum = 1;
        pro = true;
        temp = pageNum;
        bfe = new BfE(pageNum);
    }
    public void act() 
    {
        if(pro)
        {
            pro = !pro;
            getWorld().addObject(bfe, getWorld().getWidth()/2, getWorld().getHeight()*2/5);
        }
        if(!expFin)
            printPageNum();
    }    
    
    private void printPageNum()
    {
        pageNum = ((Board)getWorld()).exp.now_Num;
        if(temp!=pageNum)
        {
            temp = pageNum;
            bfe.changeNum(temp);
        }
        GreenfootImage txt = new GreenfootImage(""+pageNum, 25, Color.BLACK, t);
        GreenfootImage image = new GreenfootImage(img);
        image.drawImage(txt, (image.getWidth()-txt.getWidth())/2, (image.getHeight()-txt.getHeight())/2);
        setImage(image);
    }
}
