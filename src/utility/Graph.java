/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Shreyas
 */
public class Graph {
    
    public static void createStat(ArrayList<Integer> stats) {
        String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        for (int i = 0; i < stats.size(); i++){
            int val = stats.get(i);
            String s = month[i%12];
            dcd.setValue(val,s,s);
        }
        JFreeChart jChart = ChartFactory.createBarChart("Statistics", "Months", "Products", dcd, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot plot = jChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        
        ChartFrame chartFrame = new ChartFrame("Statistics", jChart, true);
        chartFrame.setVisible(true);
        chartFrame.setSize(700, 500);
    }
    public static void createStat(String header[],ArrayList<Integer> stats) {
        
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        for (int i = 0; i < stats.size(); i++){
            int val = stats.get(i);
            String s = header[i%12];
            dcd.setValue(val,s,s);
        }
        JFreeChart jChart = ChartFactory.createBarChart("Statistics", "Months", "Products", dcd, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot plot = jChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        
        ChartFrame chartFrame = new ChartFrame("Statistics", jChart, true);
        chartFrame.setVisible(true);
        chartFrame.setSize(700, 500);
    }
}
