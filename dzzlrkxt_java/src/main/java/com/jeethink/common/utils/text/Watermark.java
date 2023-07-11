package com.jeethink.common.utils.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;

/**
 * 通过实现endpage方法实现添加水印
 * 该方法可以在输出pdf内容时实现添加水印
 */
//加水印实现类
public class Watermark extends PdfPageEventHelper {

    private Font FONT = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new GrayColor(0.1f));

    private String waterCont;   //水印内容

    public Watermark() {
    }

    public Watermark(String waterCont) {
        this.waterCont = waterCont;
    }

    public Font getFONT() {
        return FONT;
    }

    public void setFONT(Font FONT) {
        this.FONT = FONT;
    }

    public String getWaterCont() {
        return waterCont;
    }

    public void setWaterCont(String waterCont) {
        this.waterCont = waterCont;
    }

    /**
     * pdf输出事件，在每页pdf内容输出完执行
     * @param writer
     * @param document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfGState pdfGState = new PdfGState();
        pdfGState.setFillOpacity(0.3f);
        pdfGState.setStrokeOpacity(0.4f);
        PdfContentByte contentByte = writer.getDirectContentUnder();
        contentByte.setGState(pdfGState);
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                ColumnText.showTextAligned(contentByte,
                        Element.ALIGN_CENTER,
                        new Phrase((waterCont == null || waterCont == "") ? "hello" : waterCont,FONT),
                        (50.5f+i*350),
                        (40.0f+j*150),
                        writer.getPageNumber()%2 == 1 ? 45 : -45);
            }
        }
    }
}
