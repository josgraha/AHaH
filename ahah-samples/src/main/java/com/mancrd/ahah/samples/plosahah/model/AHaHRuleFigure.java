/**
 * Copyright (c) 2013 M. Alexander Nugent Consulting <i@alexnugent.name>
 *
 * M. Alexander Nugent Consulting Research License Agreement
 * Non-Commercial Academic Use Only
 *
 * This Software is proprietary. By installing, copying, or otherwise using this
 * Software, you agree to be bound by the terms of this license. If you do not agree,
 * do not install, copy, or use the Software. The Software is protected by copyright
 * and other intellectual property laws.
 *
 * You may use the Software for non-commercial academic purpose, subject to the following
 * restrictions. You may copy and use the Software for peer-review and methods verification
 * only. You may not create derivative works of the Software. You may not use or distribute
 * the Software or any derivative works in any form for commercial or non-commercial purposes.
 *
 * Violators will be prosecuted to the full extent of the law.
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRßANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.mancrd.ahah.samples.plosahah.model;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.CSVImporter;
import com.xeiam.xchart.CSVImporter.DataOrientation;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesLineStyle;
import com.xeiam.xchart.SeriesMarker;
import com.xeiam.xchart.StyleManager.ChartTheme;
import com.xeiam.xchart.SwingWrapper;

/**
 * @author timmolter
 */
public class AHaHRuleFigure {

  public static void main(String[] args) throws Exception {

    AHaHRuleFigure aHaHRuleFigure = new AHaHRuleFigure();

    Chart chart = CSVImporter.getChartFromCSVDir("./Results/Model/AHaHRule/Functional", DataOrientation.Columns, 300, 300, ChartTheme.Matlab);
    aHaHRuleFigure.go(chart, "Functional");

    chart = CSVImporter.getChartFromCSVDir("./Results/Model/AHaHRule/Circuit", DataOrientation.Columns, 300, 300, ChartTheme.Matlab);
    aHaHRuleFigure.go(chart, "Circuit");

  }

  private void go(Chart chart, String type) throws IOException {

    // import chart from a folder containing CSV files

    chart.setChartTitle("AHaH Rule - " + type);
    chart.setYAxisTitle("dW");
    chart.setXAxisTitle("y");
    chart.getStyleManager().setLegendVisible(false);
    chart.getStyleManager().setPlotGridLinesVisible(false);
    chart.getStyleManager().setAxisTicksVisible(false);

    Map<Integer, Series> seriesMap = chart.getSeriesMap();

    Series circuitBias = seriesMap.get(0);
    circuitBias.setLineStyle(SeriesLineStyle.NONE);
    circuitBias.setMarker(SeriesMarker.CIRCLE);
    circuitBias.setMarkerColor(new Color(255, 0, 0, 10));

    Series circuitInputs = seriesMap.get(1);
    circuitInputs.setLineStyle(SeriesLineStyle.NONE);
    circuitInputs.setMarker(SeriesMarker.CIRCLE);
    circuitInputs.setMarkerColor(new Color(0, 0, 255, 10));

    BitmapEncoder.savePNGWithDPI(chart, "./PLOS_AHAH/Figures/AHaHRule_" + type + ".png", 300);
    // Show it
    new SwingWrapper(chart).displayChart();
  }
}
