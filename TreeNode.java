import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class TreeNode extends SearchBinaryTree
{
    int No;
    //GreenfootImage base = new GreenfootImage(getImage());
    public boolean selected;
    int font_size = 23;
    Color t = new Color(0,0,0,0);
    //GreenfootImage txt;
    TreeNode(){ pro = true; }
    
    TreeNode(int num)
    {
        pro = true;
        No = num;
        if(No>=10000 || No<=-10000)    font_size = 19;
        if(No>=100000 || No<=-100000)   font_size = 16;
    }
    
    public void act() 
    {
        if(pro)
        {
            pro = !pro;
            selected = false;
        }
        if(!SBTEnded)
        {
            printNum(selectOrNot());
        }
    }    
    
    void printNum(GreenfootImage background)
    {
        txt = new GreenfootImage(""+No, font_size, Color.WHITE, t);
        GreenfootImage img = new GreenfootImage(background);
        img.drawImage(txt, (img.getWidth()-txt.getWidth())/2, (img.getHeight()-txt.getHeight())/2);
        setImage(img);
    }
    
    public GreenfootImage selectOrNot()
    {
        if(selected)    return new GreenfootImage("SH3.png");
        else    return base;
    }
}
