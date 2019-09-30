import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** This class allows us to build linear charts from a set of 2D double arrays. */
public class LinearChartBuilder {
    private String chartTitle;
    private String YAxis;
    private String XAxis;

    /**
     * Creates a LinearChartBuilder with the specified title, YAxis and XAxis name.
     * @param chartTitle The chart title.
     * @param YAxis The YAxis name.
     * @param XAxis The XAxis name.
     */
    public LinearChartBuilder(String chartTitle, String YAxis, String XAxis){
        this.chartTitle = chartTitle;
        this.YAxis = YAxis;
        this.XAxis = XAxis;
    }

    /**
     * Creates the linear chart and creates a .png image that contains it.
     * @param bubbleSortData Contains the data to plot the bubble sort results.
     * @param insertionSortData Contains the data to plot the insertion sort results.
     * @param selectionSortData Contains the data to plot the selection sort results.
     * @param qsData Contains the data to plot the quick sort results.
     */
    public void buildLinearChart(double[][] bubbleSortData,
                                 double[][] insertionSortData,
                                 double[][] selectionSortData,
                                 double[][] qsData,
                                 double[][] rsData) {

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Bubble sort", bubbleSortData);
        dataset.addSeries("Insertion sort", insertionSortData);
        dataset.addSeries("Selection sort", selectionSortData);
        dataset.addSeries("Quick sort", qsData);
        dataset.addSeries("Radix sort", rsData);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,  false);
        setRenderSettings(renderer);
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, XAxis, YAxis, dataset);
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(1200, 700);
        buildImage(image, chartTitle);
    }

    private void buildImage(BufferedImage image, String chartTitle){
        try {
            ImageIO.write(image, "png", new File(chartTitle +".png"));
        } catch (IOException exc) {
            System.out.println("Error creating image.");
        }
    }

    private void setRenderSettings(XYLineAndShapeRenderer renderer){
        setSeriesPaint(renderer);
        setSeriesStroke(renderer);
    }

    private void setSeriesPaint(XYLineAndShapeRenderer renderer){
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.RED);
        renderer.setSeriesPaint(4, Color.CYAN);
    }

    private void setSeriesStroke(XYLineAndShapeRenderer renderer){
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));
        renderer.setSeriesStroke(3, new BasicStroke(2));
        renderer.setSeriesStroke(4, new BasicStroke(2));
    }
}
