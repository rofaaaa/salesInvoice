
package model;
    
public class Line {
    private int invoiceNum;
    private String itemName;
    private double itemPrice;
    private int itemcount;
    private Invoice invoice;

    
    
    public Line(int invoiceNum, String itemName, double itemPrice, int itemcount, Invoice invoice) {
        this.invoiceNum = invoiceNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemcount = itemcount;
        this.invoice = invoice;
    }

    public Line(int invoiceNum, String itemName, double itemPrice, int itemcount) {
        this.invoiceNum = invoiceNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemcount = itemcount;
    }

    public Line(String itemName, double itemPrice, int itemcount, Invoice invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemcount = itemcount;
        this.invoice = invoice;
    }
    

    public double getLineTotal() {
        return itemPrice * itemcount;
    }
   
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
   
    public String getAsCSV() {
        return invoice.getNum() + "," + itemName + "," + itemPrice + "," + itemcount;
    }
    
}


