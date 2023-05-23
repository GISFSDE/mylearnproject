package com.gisfsde.activitilearn;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LakerProcessDiagramCanvas extends DefaultProcessDiagramCanvas {
    public LakerProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
        HIGHLIGHT_COLOR = Color.cyan;
        THICK_TASK_BORDER_STROKE = new BasicStroke(6.0f);
    }

    public LakerProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType) {
        super(width, height, minX, minY, imageType);
        HIGHLIGHT_COLOR = Color.cyan;
        THICK_TASK_BORDER_STROKE = new BasicStroke(6.0f);
    }

    public void drawHighLightColor(int x, int y, int width, int height, Color color) {
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();

        g.setPaint(color);
        g.setStroke(THICK_TASK_BORDER_STROKE);

        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
        g.draw(rect);

        g.setPaint(originalPaint);
        g.setStroke(originalStroke);
    }

}