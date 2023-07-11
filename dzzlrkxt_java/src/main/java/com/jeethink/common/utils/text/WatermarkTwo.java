package com.jeethink.common.utils.text;

import com.jeethink.common.core.lang.UUID;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 通过showTextAligned方法进行添加水印
 * 该方法实现给已有pdf添加水印
 */
@Repository
public class WatermarkTwo {

    private BaseFont baseFont;

    {
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WatermarkTwo() {
    }

    public WatermarkTwo(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    public BaseFont getBaseFont() {
        return baseFont;
    }

    public void setBaseFont(BaseFont baseFont) {
        this.baseFont = baseFont;
    }

    /**
     * 给已有pdf添加水印     原路径和输出路径可以一致
     * @param pdfFilePath 原pdf路径
     * @param outPdfFilePath 输出pdf路径
     * @param watermark 水印内容
     */
    public void addWatermark(String pdfFilePath , String outPdfFilePath , String watermark){
        PdfReader pdfReader = null;
        PdfStamper stamper = null;
        boolean update = false;
//        输出和输入地址一样时，先将文件输入到随机位置，之后将原文件删掉，输出文件改名
        if (pdfFilePath.equals(outPdfFilePath)){
            update = true;
            String pre = outPdfFilePath.substring(0,outPdfFilePath.lastIndexOf('/')+1);
            String suf = outPdfFilePath.substring(outPdfFilePath.lastIndexOf(".")-1);
            outPdfFilePath = pre + UUID.fastUUID() + suf;
        }
        try {
//            读取原pdf
            pdfReader = new PdfReader(pdfFilePath);
//            获取输出pdf的流
            stamper = new PdfStamper(pdfReader,new FileOutputStream(outPdfFilePath));
//            字体透明度
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.3f);
            pdfGState.setStrokeOpacity(0.4f);
//            获取pdf页码，对每一页加水印
            int totalPage = pdfReader.getNumberOfPages()+1;
            for (int i = 1;i<totalPage;i++){
//                获取每页pdf的内容
                PdfContentByte overContent = stamper.getUnderContent(i);
//                开始编写水印
                overContent.beginText();
//                设置水印内容透明度
                overContent.setGState(pdfGState);
//                设置字体
                overContent.setFontAndSize(baseFont, 30);
                // 添加范围
                overContent.setTextMatrix(70, 200);
                // 具体位置 内容 旋转多少度 共360度
                for (int k=0;k<5;k++){
                    for (int j=0;j<5;j++){
                        overContent.showTextAligned(Element.ALIGN_CENTER, watermark, (50.5f+k*350), (40.0f+j*150), totalPage%2 == 1 ? -45 : 45);
//                        ColumnText.showTextAligned(writer.getDirectContentUnder(),
//                                Element.ALIGN_CENTER,
//                                new Phrase((waterCont == null || waterCont == "") ? "WORLD" : waterCont,FONT),
//                                (50.5f+i*350),
//                                (40.0f+j*150),
//                                writer.getPageNumber()%2 == 1 ? 45 : -45);
                    }
                }
//                overContent.showTextAligned(Element.ALIGN_CENTER, watermark, 300, 350, 300);
//                overContent.showTextAligned(Element.ALIGN_TOP, watermark, 100, 100, 5);
//                overContent.showTextAligned(Element.ALIGN_BOTTOM, watermark, 400, 400, 75);
                overContent.endText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                stamper.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pdfReader.close();
        }
        if (update){
            File file = new File(pdfFilePath);
            file.delete();
            new File(outPdfFilePath).renameTo(new File(pdfFilePath));
        }
    }

    /**
     * 重载添加水印方法
     * @param pdfFilePath 需要添加水印的pdf路径
     * @param watermark 水印内容
     */
    public void addWatermark(String pdfFilePath , String watermark){
        addWatermark(pdfFilePath,pdfFilePath,watermark);
    }
}
