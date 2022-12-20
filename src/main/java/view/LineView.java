
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author mrofa
 */
public class LineView extends JDialog{
    private JTextField itemNameValue;
    private JTextField itemCountValue;
    private JTextField itemPricevalue;
    private JLabel itemName;
    private JLabel itemCount;
    private JLabel itemPrice;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public LineView(MainFrame frame) {
        itemNameValue = new JTextField(20);
        itemName = new JLabel("Item Name");
        
        itemCountValue = new JTextField(20);
        itemCount = new JLabel("Item Count");
        
        itemPricevalue = new JTextField(20);
        itemPrice = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("line OK");
        cancelBtn.setActionCommand("line Cancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemName);
        add(itemNameValue);
        add(itemCount);
        add(itemCountValue);
        add(itemPrice);
        add(itemPricevalue);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameValue;
    }

    public JTextField getItemCountField() {
        return itemCountValue;
    }

    public JTextField getItemPriceField() {
        return itemPricevalue;
    }
}