package lab08;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Charter {

	public void createChart(String dataFile, String filename) {
		JFreeChart chart = ChartFactory.createXYLineChart("Contains on sorted set", "Size (N)", "Time (ms)", createDataSet(dataFile));
		try {
			ChartUtilities.saveChartAsPNG(new File(filename), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showChart(chart);
	}
	
	public JFreeChart buildHistogram(String title, List<Number> data, int bins) {
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Histogram", convertData(data), bins);
		
		dataset.setType(HistogramType.FREQUENCY);
		
		JFreeChart chart = ChartFactory.createHistogram(title, "Bins", "Count", dataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
	
	public JFreeChart buildBarChart(String title, DefaultCategoryDataset data) {
		return ChartFactory.createBarChart(title, "Bins", "Count", data);
	}

	private double[] convertData(List<Number> data) {
		double[] returnValues = new double[data.size()];
		for(int dataIdx = 0; dataIdx < data.size(); dataIdx++) {
			returnValues[dataIdx] = data.get(dataIdx).doubleValue();
		}
		return returnValues;
	}

	public void showChart(JFreeChart chart) {
		JFrame frame = new JFrame();
	    frame.setTitle(chart.getTitle().toString());
	    ChartPanel chartPanel = new ChartPanel(chart);
	    frame.setPreferredSize(new Dimension(800, 400));
	    frame.add(chartPanel);
	    frame.pack();
	    frame.setVisible(true);
	}

	private XYDataset createDataSet(String dataFile) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		try(FileReader reader = new FileReader(dataFile);
				BufferedReader br = new BufferedReader(reader)) {
			String line;
			XYSeries series = new XYSeries("remove");
			while((line = br.readLine()) != null) {
				String[] split = line.split("\t");
				series.add(new XYDataItem(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
			}
			dataset.addSeries(series);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataset;
	}
}
