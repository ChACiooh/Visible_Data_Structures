import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;

public class FinalPrint extends Actor
{
    private boolean t, pro;
    public static String s;
    
    public FinalPrint(){}
    
    public FinalPrint(boolean isSuccessed, String dataType)
    {
        pro = true;
        t = isSuccessed;
        s = new String(dataType.toString());
    }
    
    public void act() 
    {
        // Add your action code here.
        if(pro)
        {
            pro = !pro;
            if(t)//성공했다면
            {
                printSuccess();
            }
            else
            {
                printFail();
            }
            HomeButton.reference = true;
            getWorld().removeObject(this);
        }
    }    
    
    private void printSuccess()
    {
        JOptionPane.showMessageDialog(null, "Clear!\n당신은 이제 "+s+" 마스터!!");
    }
    
    private void printFail()
    {
        JOptionPane.showMessageDialog(null, "Fail...\n"+s+"을(를) 조금 더 연습해 보세요.");
    }
}
