/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author Shreyas
 */
public class CustomJTable extends JTable{

    public CustomJTable(String data[][],String header[]) {
        super(data,header);
    }
            public CustomJTable() {
        super();
    }
    public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer,int row,int col) {
        Component comp = super.prepareRenderer(renderer, row, col);
        if (row%2==0 && !isCellSelected(row, col)) {
            comp.setBackground(new java.awt.Color(235,235,235));
        }
        else if (!isCellSelected(row, col)) {
            comp.setBackground(Color.white);
        }
        else {
            comp.setBackground(new java.awt.Color(51,153,255));
        }
        return comp;
    }
}