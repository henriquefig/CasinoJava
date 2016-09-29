package roulete;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SlotChoice
{
    private short rank;
    private String a= new String();
    private static String[] ranks  = { "Watermelon", "Bell", "Seven", "Lime", "Cherry", "Blackberry", "Grapes", "Orange", "Bar"};
     JPanel imgcard = new JPanel();
   
    public static String rankAsString( int __rank ) {
        return ranks[__rank];
    }

    SlotChoice(short rank)
    {
        
        a="./img/slot/"+rank+".png";
         Icon image=new ImageIcon(a);


        JLabel label = new JLabel(image);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        imgcard.setVisible(true);
        imgcard.add(label); 
        this.rank=rank;
    }

    public @Override String toString()
    {
          return ranks[rank];
    }

    public short getRank() {
         return rank;
    }
}
