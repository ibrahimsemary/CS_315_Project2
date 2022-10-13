import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class CostLabel extends JPanel{
    
    CostLabel(int cost) {
        JLabel costlabel = new JLabel("Total Cost: $"+cost);
        costlabel.setFont(new Font(costlabel.getFont().getName(), costlabel.getFont().getStyle(), 20));
    }
}
