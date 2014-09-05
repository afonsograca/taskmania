/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Statistics.DayStats;
import Statistics.Statistics;
import Tasks.Component;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author ei07128
 */
public class StatsChartPanel {

    public static JFreeChart buildGraphic (Component node){
        Statistics stats = Statistics.getInstance();
        CategoryDataset dataset = createDataset(stats, node);
        return createChart(dataset);
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
        JFreeChart chart = ChartFactory.createBarChart3D(null, "Days", "Hours", dataset, PlotOrientation.VERTICAL, false, false, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();

        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();

        DecimalFormat decimalFormat = new DecimalFormat("##.###");
        br.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalFormat));
        br.setPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        br.setItemLabelsVisible(true);

        br.setMaximumBarWidth(.35); // set maximum width to 35% of chart
        chart.getCategoryPlot().setRenderer(br);

        return chart;
    }

    private static CategoryDataset createDataset(Statistics stats, Component node) {
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (node == null)
            return dataset;
                
        stats.build(node);
        DayStats nodeStats = stats.getStats(node);

        Set<Calendar> keys = nodeStats.getStats().keySet();
        TreeSet<Calendar> orderedKeys = new TreeSet<Calendar>(keys);

        for (Calendar cal:orderedKeys){
            String day = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH);

            long coco = (Long)nodeStats.getStats().get(cal);
            double seconds = coco/1000;
            double hours = seconds/3600;

            dataset.addValue(hours, "", day);
        }


        return dataset;
    }
}
