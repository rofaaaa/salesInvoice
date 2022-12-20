
package model;

import java.util.ArrayList;


public class Invoice {
    
    private int num;
    private String date;
    private String customerName;
    private ArrayList<Line> lines;

    
    public double invoiceTotalCount (){
        double totalCount=0.0;
        for(Line eachLine:getLines()){
           totalCount+= eachLine.getItemPrice()*eachLine.getItemcount();
        }
        return totalCount;
    }
    public Invoice() {
    }

    public Invoice(int num, String date, String customerName) {
        this.num = num;
        this.date = date;
        this.customerName = customerName;
    }

    public ArrayList<Line> getLines() {
        if(lines==null){
            lines=new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
   
 public String getAsCSV() {
        return num + "," + date + "," + customerName;
    }    

}


