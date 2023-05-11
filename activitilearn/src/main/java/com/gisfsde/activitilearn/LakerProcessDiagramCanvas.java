package com.gisfsde.activitilearn;

import org.activiti.bpmn.model.AssociationDirection;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class LakerProcessDiagramCanvas extends DefaultProcessDiagramCanvas {


    //定义连线颜色为绿色
    protected static Color HIGHLIGHT_SequenceFlow_COLOR = Color.green;

    public LakerProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }

    /**
     * 重写绘制连线的方式,设置绘制颜色
     */
    @Override
    public void drawConnection(int[] xPoints, int[] yPoints, boolean conditional, boolean isDefault, String connectionType, AssociationDirection associationDirection, boolean highLighted, double scaleFactor) {
        Paint originalPaint = this.g.getPaint();
        Stroke originalStroke = this.g.getStroke();
        this.g.setPaint(CONNECTION_COLOR);
        if (connectionType.equals("association")) {
            this.g.setStroke(ASSOCIATION_STROKE);
        } else if (highLighted) {
            this.g.setPaint(HIGHLIGHT_SequenceFlow_COLOR);
            this.g.setStroke(HIGHLIGHT_FLOW_STROKE);
        }

        for (int i = 1; i < xPoints.length; ++i) {
            Integer sourceX = xPoints[i - 1];
            Integer sourceY = yPoints[i - 1];
            Integer targetX = xPoints[i];
            Integer targetY = yPoints[i];
            java.awt.geom.Line2D.Double line = new java.awt.geom.Line2D.Double((double) sourceX, (double) sourceY, (double) targetX, (double) targetY);
            this.g.draw(line);
        }

        java.awt.geom.Line2D.Double line;
        if (isDefault) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[0], (double) yPoints[0], (double) xPoints[1], (double) yPoints[1]);
            this.drawDefaultSequenceFlowIndicator(line, scaleFactor);
        }

        if (conditional) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[0], (double) yPoints[0], (double) xPoints[1], (double) yPoints[1]);
            this.drawConditionalSequenceFlowIndicator(line, scaleFactor);
        }

        if (associationDirection.equals(AssociationDirection.ONE) || associationDirection.equals(AssociationDirection.BOTH)) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[xPoints.length - 2], (double) yPoints[xPoints.length - 2], (double) xPoints[xPoints.length - 1], (double) yPoints[xPoints.length - 1]);
            this.drawArrowHead(line, scaleFactor);
        }

        if (associationDirection.equals(AssociationDirection.BOTH)) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[1], (double) yPoints[1], (double) xPoints[0], (double) yPoints[0]);
            this.drawArrowHead(line, scaleFactor);
        }

        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    @Override
    public void drawHighLight(int x, int y, int width, int height) {
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();
        this.g.setPaint(Color.GREEN);
        this.g.setStroke(THICK_TASK_BORDER_STROKE);

        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
        this.g.draw(rect);

        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    public void drawHighLight(int x, int y, int width, int height, Color color) {
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();
        this.g.setPaint(color);
        this.g.setStroke(THICK_TASK_BORDER_STROKE);

        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
        this.g.draw(rect);

        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    @Override
    public void drawLabel(String text, GraphicInfo graphicInfo, boolean centered) {
        float interline = 1.0f;

        // text
        if (text != null && text.length() > 0) {
            Paint originalPaint = g.getPaint();
            Font originalFont = g.getFont();

            g.setPaint(Color.BLACK);
            LABEL_FONT = new Font(labelFontName, Font.ITALIC, 12);
            g.setFont(LABEL_FONT);
            int wrapWidth = 200;
            int textY = (int) graphicInfo.getY();

            // TODO: use drawMultilineText()
            AttributedString as = new AttributedString(text);
            as.addAttribute(TextAttribute.FOREGROUND, g.getPaint());
            as.addAttribute(TextAttribute.FONT, g.getFont());
            AttributedCharacterIterator aci = as.getIterator();
            FontRenderContext frc = new FontRenderContext(null, true, false);
            LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);

            while (lbm.getPosition() < text.length()) {
                TextLayout tl = lbm.nextLayout(wrapWidth);
                textY += tl.getAscent();
                Rectangle2D bb = tl.getBounds();
                double tX = graphicInfo.getX();
                if (centered) {
                    tX += (int) (graphicInfo.getWidth() / 2 - bb.getWidth() / 2);
                }
                tl.draw(g, (float) tX, textY);
                textY += tl.getDescent() + tl.getLeading() + (interline - 1.0f) * tl.getAscent();
            }

            // restore originals
            g.setFont(originalFont);
            g.setPaint(originalPaint);
        }
    }
}