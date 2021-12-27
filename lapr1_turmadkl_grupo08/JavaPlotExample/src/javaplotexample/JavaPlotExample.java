/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaplotexample;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;


public class JavaPlotExample{

   
   public static void main(String[] args) {
        // TODO code application logic here
        Test1_Basic();
  
   }

public static void Test1_Basic()
{
       JavaPlot p = new JavaPlot();
	
       PlotStyle myPlotStyle = new PlotStyle();
       myPlotStyle.setStyle(Style.LINESPOINTS);
       myPlotStyle.setLineWidth(1);
       //RgbPlotColor = new RgbPlotColor(
       myPlotStyle.setLineType(NamedPlotColor.BLUE);
       myPlotStyle.setPointType(7);
       myPlotStyle.setPointSize(1);
       
       double tab[][];
       tab = new double[3][2];
       tab[0][0] = 1.0000;
       tab[0][1] = 1.0000;
       tab[1][0] = 2.0000;
       tab[1][1] = 2.0000;
       tab[2][0] = 3.0000;
       tab[2][1] = 1.0000;
       DataSetPlot s = new DataSetPlot(tab);
       s.setTitle("Teste");
       s.setPlotStyle(myPlotStyle);
       
       //p.newGraph();
       p.addPlot(s);
      
       p.newGraph();
       p.plot();
}
    
}
