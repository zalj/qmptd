package ui;


import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
 
public class TimeSeriesChart {
	ChartPanel panel1;
	public TimeSeriesChart(){
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Legal & General��λ���л���۸�", "����", "�۸�",xydataset, PlotOrientation.VERTICAL, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		ValueAxis dateaxis =  xyplot.getDomainAxis();
        //dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        panel1 = new ChartPanel(jfreechart, true);
        dateaxis.setLabelFont(new Font("����", Font.BOLD, 14));         //ˮƽ�ײ�����
        dateaxis.setTickLabelFont(new Font("����", Font.BOLD, 12));  //��ֱ����
        ValueAxis rangeAxis = xyplot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
        jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("����", Font.BOLD, 20));//���ñ�������
	} 
	@SuppressWarnings("deprecation")
	private static XYDataset createDataset() {  //������ݼ��е�࣬�����������
		
		TimeSeries timeseries = new TimeSeries("legal & generalŷ��ָ������",
		        org.jfree.data.time.Month.class);
		timeseries.add(new Month(2, 2001), 181.80000000000001D);
		timeseries.add(new Month(3, 2001), 167.30000000000001D);
		timeseries.add(new Month(4, 2001), 153.80000000000001D);
		timeseries.add(new Month(5, 2001), 167.59999999999999D);
		timeseries.add(new Month(6, 2001), 158.80000000000001D);
		timeseries.add(new Month(7, 2001), 148.30000000000001D);
		timeseries.add(new Month(8, 2001), 153.90000000000001D);
		timeseries.add(new Month(9, 2001), 142.69999999999999D);
		timeseries.add(new Month(10, 2001), 123.2D);
		timeseries.add(new Month(11, 2001), 131.80000000000001D);
		timeseries.add(new Month(12, 2001), 139.59999999999999D);
		timeseries.add(new Month(1, 2002), 142.90000000000001D);
		timeseries.add(new Month(2, 2002), 138.69999999999999D);
		timeseries.add(new Month(3, 2002), 137.30000000000001D);
		timeseries.add(new Month(4, 2002), 143.90000000000001D);
		timeseries.add(new Month(5, 2002), 139.80000000000001D);
		timeseries.add(new Month(6, 2002), 137D);
		timeseries.add(new Month(7, 2002), 132.80000000000001D);
		
		TimeSeries timeseries1 = new TimeSeries("legal & generalӢ��ָ������",
		        org.jfree.data.time.Month.class);
		timeseries1.add(new Month(2, 2001), 129.59999999999999D);
		timeseries1.add(new Month(3, 2001), 123.2D);
		timeseries1.add(new Month(4, 2001), 117.2D);
		timeseries1.add(new Month(5, 2001), 124.09999999999999D);
		timeseries1.add(new Month(6, 2001), 122.59999999999999D);
		timeseries1.add(new Month(7, 2001), 119.2D);
		timeseries1.add(new Month(8, 2001), 116.5D);
		timeseries1.add(new Month(9, 2001), 112.7D);
		timeseries1.add(new Month(10, 2001), 101.5D);
		timeseries1.add(new Month(11, 2001), 106.09999999999999D);
		timeseries1.add(new Month(12, 2001), 110.3D);
		timeseries1.add(new Month(1, 2002), 111.7D);
		timeseries1.add(new Month(2, 2002), 111D);
		timeseries1.add(new Month(3, 2002), 109.59999999999999D);
		timeseries1.add(new Month(4, 2002), 113.2D);
		timeseries1.add(new Month(5, 2002), 111.59999999999999D);
		timeseries1.add(new Month(6, 2002), 108.8D);
		timeseries1.add(new Month(7, 2002), 101.59999999999999D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}
	
	public ChartPanel getChartPanel(){
	    return panel1;
	}
	  
	public static void main(String[] args) {
		JFrame frame=new JFrame("Java����ͳ��ͼ");
		frame.setLayout(new GridLayout(2,2,10,10));
		frame.add(new BarChart().getChartPanel());           //�������ͼ
		frame.add(new BarChart1().getChartPanel());          //�������ͼ����һ��Ч��
		frame.add(new PieChart().getChartPanel());           //��ӱ�״ͼ
		frame.add(new TimeSeriesChart().getChartPanel());    //�������ͼ
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
