/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mrofa
 */
public class LinesTableModel extends AbstractTableModel{
    private ArrayList <Line> lines;
    private String [] cols= {"NO.","Item Name","Item Price","Count","Item Total"};

    public LinesTableModel(ArrayList<Line> lines) {
        this.lines=lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();

    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    @Override
    public int getColumnCount() {
        return cols.length;

    }
    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Line line=lines.get(rowIndex);
        switch(columnIndex) {
            case 0: return line.getInvoiceNum();
            case 1: return line.getItemName();
            case 2: return line.getItemPrice();
            case 3: return line.getItemcount();
            case 4: return line.getLineTotal();
            default : return "";
        }

    }
    
    
    
}
