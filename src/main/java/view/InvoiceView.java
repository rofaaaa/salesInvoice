/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class InvoiceView extends JDialog {
    private JTextField custNameValue;
    private JTextField DateValue;
    private JLabel custName;
    private JLabel Date;
    private JButton okBtn;
    private JButton cancelBtn;

    public InvoiceView(MainFrame frame) {
        custName = new JLabel("Customer Name:");
        custNameValue = new JTextField(20);
        Date = new JLabel("Invoice Date:");
        DateValue = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("InvoiceOK");
        cancelBtn.setActionCommand("InvoiceCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(Date);
        add(DateValue);
        add(custName);
        add(custNameValue);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return custNameValue;
    }

    public JTextField getInvDateField() {
        return DateValue;
    }
    
}
