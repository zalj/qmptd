package ui;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class LineChartByThroughPut {
	
	private static final String TIME_DELAY_X_AXIS = "吞吐量";
	
	private ChartPanel[] panels = new ChartPanel[4];
	public LineChartByThroughPut(String[][] temp, boolean[] isFindTarget) {
		if(isFindTarget[0] && isFindTarget[1]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {0, 1});
			JFreeChart jfreechart1 = ChartFactory.createXYLineChart("时延变化", TIME_DELAY_X_AXIS, "时延", 
					xyDataset, PlotOrientation.VERTICAL, 
					true, true, true);
			setProperties(jfreechart1);
	        panels[0] = new ChartPanel(jfreechart1, true);
		}else if (isFindTarget[0]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {0});
			JFreeChart jfreechart1 = ChartFactory.createXYLineChart("时延变化", TIME_DELAY_X_AXIS, "时延", 
					xyDataset, PlotOrientation.VERTICAL, 
					true, true, true);
			setProperties(jfreechart1);
	        panels[0] = new ChartPanel(jfreechart1, true);
		}else if(isFindTarget[1]){
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {1});
			JFreeChart jfreechart1 = ChartFactory.createXYLineChart("时延变化", TIME_DELAY_X_AXIS, "时延", 
					xyDataset, PlotOrientation.VERTICAL, 
					true, true, true);
			setProperties(jfreechart1);
	        panels[0] = new ChartPanel(jfreechart1, true);
		}
		
		if(isFindTarget[2] && isFindTarget[3]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {2, 3});
			JFreeChart jfreechart2 = ChartFactory.createXYLineChart("抖动变化", TIME_DELAY_X_AXIS, "抖动", 
													xyDataset, PlotOrientation.VERTICAL, 
													true, true, true);
			setProperties(jfreechart2);
	        panels[1] = new ChartPanel(jfreechart2, true);
		}else if (isFindTarget[2]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {2});
			JFreeChart jfreechart2 = ChartFactory.createXYLineChart("抖动变化", TIME_DELAY_X_AXIS, "抖动", 
													xyDataset, PlotOrientation.VERTICAL, 
													true, true, true);
			setProperties(jfreechart2);
	        panels[1] = new ChartPanel(jfreechart2, true);
		}else if (isFindTarget[3]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {3});
			JFreeChart jfreechart2 = ChartFactory.createXYLineChart("抖动变化", TIME_DELAY_X_AXIS, "抖动", 
													xyDataset, PlotOrientation.VERTICAL, 
													true, true, true);
			setProperties(jfreechart2);
	        panels[1] = new ChartPanel(jfreechart2, true);
		}
		
		if(isFindTarget[4]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {4});
			JFreeChart jfreechart3 = ChartFactory.createXYLineChart("丢包数变化", TIME_DELAY_X_AXIS, "丢包数", 
													xyDataset, PlotOrientation.VERTICAL, 
													true, true, true);
			setProperties(jfreechart3);
	        panels[2] = new ChartPanel(jfreechart3, true);
		}
		
		if(isFindTarget[5]) {
			XYDataset xyDataset = getXYDataSet(temp, isFindTarget, new int[] {5});
			JFreeChart jfreechart4 = ChartFactory.createXYLineChart("丢包率变化", TIME_DELAY_X_AXIS, "丢包率", 
													xyDataset, PlotOrientation.VERTICAL, 
													true, true, true);
			setProperties(jfreechart4);
	        panels[3] = new ChartPanel(jfreechart4, true);
		}
	}
	
	private static final String[] TITLE_NAME = {"Time Delay", "Average Time Delay", "Good Jitter", "Bad Jitter", "Miss Count", "Miss Rate"};
	private XYDataset getXYDataSet(String[][] temp, boolean[] isFindTarget, int[] ls) {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		if(ls.length == 2) {
			if(ls[0] == 0) {
				XYSeries timeDelaySeries = new XYSeries(TITLE_NAME[0]);
				XYSeries averageTimeDelaySeries = new XYSeries(TITLE_NAME[1]);
				for(int i = 0; i < temp.length; i++) {
					double x = Double.parseDouble(temp[i][6]);
					double y1 = Double.parseDouble(temp[i][0]);
					double y2 = Double.parseDouble(temp[i][1]);
					timeDelaySeries.add(x, y1);
					averageTimeDelaySeries.add(x, y2);
				}
				xySeriesCollection.addSeries(timeDelaySeries);
				xySeriesCollection.addSeries(averageTimeDelaySeries);
			}
			if(ls[0] == 2) {
				XYSeries goodJitterSeries = new XYSeries(TITLE_NAME[2]);
				XYSeries badJitterSeries = new XYSeries(TITLE_NAME[3]);
				for(int i = 0; i < temp.length; i++) {
					double x = Double.parseDouble(temp[i][6]);
					double y1 = Double.parseDouble(temp[i][2]);
					double y2 = Double.parseDouble(temp[i][3]);
					goodJitterSeries.add(x, y1);
					badJitterSeries.add(x, y2);
				}
				xySeriesCollection.addSeries(goodJitterSeries);
				xySeriesCollection.addSeries(badJitterSeries);
			}
		}
		else if(ls[0] != 5){
			XYSeries xySeries = new XYSeries(TITLE_NAME[ls[0]]);
			for(int i = 0; i < temp.length; i++) {
				double x = Double.parseDouble(temp[i][6]); 
				double y = Double.parseDouble(temp[i][ls[0]]);
				xySeries.add(x, y);
			}
			xySeriesCollection.addSeries(xySeries);
		}else {
			XYSeries xySeries = new XYSeries(TITLE_NAME[ls[0]]);
			for(int i = 0; i < temp.length; i++) {
				double x = Double.parseDouble(temp[i][6]); 
				double y = Double.parseDouble(temp[i][ls[0]].substring(0, temp[i][ls[0]].length() - 1));
				xySeries.add(x, y);
			}
			xySeriesCollection.addSeries(xySeries);
		}
        return xySeriesCollection;
	}
	
	public void setProperties(JFreeChart jfreechart) {
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		
		ValueAxis throughputAxis =  xyplot.getDomainAxis();
		
		//水平底部标题
        throughputAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));     
        
        //垂直标题
        throughputAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  
        
        //获取纵轴
        ValueAxis rangeAxis = xyplot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        
        //设置图例
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        
        //设置标题字体
        jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));
	}
	
	public ChartPanel[] getChartPanel() {
		return panels;
	}
}
