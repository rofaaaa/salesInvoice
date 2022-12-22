
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvTableModel;
import model.Invoice;
import model.Line;
import model.LinesTableModel;
import view.InvoiceView;
import view.LineView;
import view.MainFrame;

public class controller implements ActionListener, ListSelectionListener {
   private MainFrame frame;
   private InvoiceView invoiceView;
    private LineView lineView;

    public controller(MainFrame frame) {
        this.frame = frame;
    }
   
   

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command=="Load File"){
            try {
                loadFile();
            } catch (IOException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (command=="Save File"){
            savefile();
        }else if (command=="Create New Invoice"){
            System.out.println("create new ");
            createNewInvoice();
        }else if(command=="Delete Invoice"){
            deleteInvoice();
        }else if (command=="create New Item"){
            createNewItem();
        }else if (command=="delete Item"){
            deleteItem();
        } else if (command=="InvoiceOK"){
            InvoiceOK();
        } else if (command=="InvoiceCancel"){
            InvoiceCancel();
        }
        else if (command=="line OK"){
            LineOK();
        } else if (command=="ine Cancel"){
            LineCancel();
        }
        
    }
  @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getInvoicesTable().getSelectedRow();
        if (selectedIndex != -1) {
        System.out.println("You have selected row: " + selectedIndex);
        Invoice currentInvoice = frame.getInvoices().get(selectedIndex);
        System.out.println("nummmmm  "+currentInvoice.getNum());
                frame.getInvoiceNumberValue().setText("bhbjj");
        frame.getInvoiceNumberValue().setText(""+currentInvoice.getNum());
        frame.getCustomerNameValue().setText(currentInvoice.getCustomerName());
        frame.getInvoiceDateValue().setText(currentInvoice.getDate());
        frame.getInvoiceTotalValue().setText(""+currentInvoice.invoiceTotalCount());
        LinesTableModel linesTableModel = new LinesTableModel(currentInvoice.getLines());
        frame.getInvoiceItemsTable().setModel(linesTableModel);
        linesTableModel.fireTableDataChanged();
        }
    }
    private void loadFile() throws IOException {
        JFileChooser f=new JFileChooser();
        try {
        int check=f.showOpenDialog(frame);
        
        if(check==JFileChooser.APPROVE_OPTION) {
            File invoicesFile=f.getSelectedFile();
            Path invoicesFilePath=Paths.get(invoicesFile.getAbsolutePath());
            List<String> invoicesLines=Files.readAllLines(invoicesFilePath);
            
            ArrayList<Invoice> invoices=new ArrayList<>();
            
            for(String invoiceLine:invoicesLines){
                String parts[] = invoiceLine.split(",");
                int num = Integer.parseInt(parts[0]);
                String date= parts[1];
                String name= parts[2];
                Invoice invoice=new Invoice(num,date,name);
                System.out.println(num+"  "+date+" "+name);
                invoices.add(invoice);
            }
            
            check=f.showOpenDialog(frame);
            
            if(check==JFileChooser.APPROVE_OPTION) {
               
            File linesFile=f.getSelectedFile();
            Path linesFilePath=Paths.get(linesFile.getAbsolutePath());
            List<String> linesOfEachinvoice=Files.readAllLines(linesFilePath);
            
           
            
            for(String line:linesOfEachinvoice){
                String parts[] = line.split(",");
                int num = Integer.parseInt(parts[0]);
                String item= parts[1];
                double price = Double.parseDouble(parts[2]);
                int count = Integer.parseInt(parts[3]);
     
                 Invoice lineInvoice = new Invoice();
                  
                        for (Invoice invoice : invoices) {
                          //  System.out.println("invoice num "+invoice.getNum()+" num line "+num);
                            if (invoice.getNum() == num) {
                              //  System.out.println(num);
                                lineInvoice = invoice;
                                break;
                            }
                        }
                  Line newLine=new Line(num,item,price,count,lineInvoice); 
                  lineInvoice.getLines().add(newLine);
                       
            }
            frame.setInvoices(invoices);
            InvTableModel invoicesTablemodel=new InvTableModel (invoices);
            frame.setInvoicesTableModel(invoicesTablemodel);
            frame.getInvoicesTable().setModel(invoicesTablemodel);
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
    }

    private void savefile() {
        ArrayList<Invoice> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for (Invoice invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (Line line : invoice.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        //System.out.println("Check point");
        try {
            JFileChooser f= new JFileChooser();
            int result = f.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = f.getSelectedFile();
                FileWriter hFW = new FileWriter(headerFile);
                hFW.write(headers);
                hFW.flush();
                hFW.close();
                result = f.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = f.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
    }

    private void createNewInvoice() {
        invoiceView = new InvoiceView(frame);
        invoiceView.setVisible(true);
    }
    private void deleteInvoice() {
        int row = frame.getInvoicesTable().getSelectedRow();
        if (row != -1) {
            frame.getInvoices().remove(row);
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

     private void createNewItem() {
        lineView = new LineView(frame);
        lineView.setVisible(true);
    }

    private void deleteItem() {
        int selectedRow = frame.getInvoiceItemsTable().getSelectedRow();

        if (selectedRow != -1) {
            LinesTableModel linesTableModel = (LinesTableModel) frame.getInvoiceItemsTable().getModel();
            linesTableModel.getLines().remove(selectedRow);
            linesTableModel.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    
     private void InvoiceCancel() {
        invoiceView.setVisible(false);
        invoiceView.dispose();
        invoiceView = null;
    }

    private void InvoiceOK() {
        String date = invoiceView.getInvDateField().getText();
        String customer = invoiceView.getCustNameField().getText();
        int num = frame.getNextInvoiceNum();
        try {
            String[] dateParts = date.split("-");  // "22-05-2013" 
            if (dateParts.length < 3) {
                JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Invoice invoice = new Invoice(num, date, customer);
                    frame.getInvoices().add(invoice);
                    frame.getInvoicesTableModel().fireTableDataChanged();
                    invoiceView.setVisible(false);
                    invoiceView.dispose();
                    invoiceView = null;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void LineOK() {
        String item = lineView.getItemNameField().getText();
        String countStr = lineView.getItemCountField().getText();
        String priceStr = lineView.getItemPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getInvoicesTable().getSelectedRow();
        if (selectedInvoice != -1) {
            Invoice invoice = frame.getInvoices().get(selectedInvoice);
            Line line = new Line(item, price, count, invoice);
            invoice.getLines().add(line);
            LinesTableModel linesTableModel = (LinesTableModel) frame.getInvoiceItemsTable().getModel();
            //linesTableModel.getLines().add(line);
            linesTableModel.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
        lineView.setVisible(false);
        lineView.dispose();
        lineView = null;
    }

    private void LineCancel() {
        lineView.setVisible(false);
        lineView.dispose();
        lineView = null;
    }
    
}
