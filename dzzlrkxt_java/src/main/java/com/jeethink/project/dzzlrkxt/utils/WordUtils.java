package com.jeethink.project.dzzlrkxt.utils;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.deepoove.poi.XWPFTemplate;
import com.jeethink.framework.config.JeeThinkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

public class WordUtils {

    private static Logger logger = LoggerFactory.getLogger(WordUtils.class);

    /**
     * 根据模板填充内容生成word
     * 调用方法参考下面的main方法，详细文档参考官方文档
     * Poi-tl模板引擎官方文档：http://deepoove.com/poi-tl/
     *
     * @param templatePath word模板文件路径
     * @param fileDir      生成的文件存放地址
     * @param fileName     生成的文件名,不带格式。假如要生成abc.docx，则fileName传入abc即可
     * @param paramMap     替换的参数集合
     * @return 生成word成功返回生成的文件的路径，失败返回空字符串
     */
    public static String createWord(String templatePath, String fileDir, String fileName, Map<String, Object> paramMap) {
        Assert.notNull(templatePath, "word模板文件路径不能为空");
        Assert.notNull(fileDir, "生成的文件存放地址不能为空");
        Assert.notNull(fileName, "生成的文件名不能为空");

        // 生成的word格式
        String formatSuffix = ".docx";
        // 拼接后的文件名
        fileName = fileName + formatSuffix;

        // 生成的文件的存放路径
        if (!fileDir.endsWith("/")) {
            fileDir = fileDir + File.separator;
        }

        File dir = new File(fileDir);
        if (!dir.exists()) {
            logger.info("生成word数据时存储文件目录{}不存在,为您创建文件夹!", fileDir);
            dir.mkdirs();
        }

        String filePath = fileDir + fileName;
        // 读取模板templatePath并将paramMap的内容填充进模板，即编辑模板+渲染数据
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(paramMap);
        try {
            // 将填充之后的模板写入filePath
            template.writeToFile(filePath);
            template.close();
        } catch (Exception e) {
            logger.error("生成word异常", e);
            e.printStackTrace();
            return "";
        }
        return filePath;
    }

    /**
     * word转pdf
     *
     * @param wordPath
     * @return
     */
    public static String wordForPdf(String localPath, String wordPath, String fileName) {
        //word的路径
//        File inputWord = new File(wordPath);
        // 获取资源路存储路径
//        String localPath = JeeThinkConfig.getProfile();
        String formatSuffix = ".pdf";
        // 拼接后的文件名
        fileName = fileName + formatSuffix;
        String path = localPath + File.separator + fileName;
        System.out.println("===" + path);
        //pdf文件的路径
//        File outputFile = new File(path);
        try {
//            InputStream docxInputStream = new FileInputStream(inputWord);
//            OutputStream outputStream = new FileOutputStream(outputFile);

            FileInputStream fis = new FileInputStream(JeeThinkConfig.getProfile()+"/license.xml");
            License license=new License();
            license.setLicense(fis);
            File file = new File(path);
            try (FileOutputStream os = new FileOutputStream(file)) {
                Document doc = new Document(wordPath);
                doc.save(os, SaveFormat.PDF);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            IConverter converter = LocalConverter.builder().build();
//            OfficeManager officeManager = LocalOfficeManager.builder()
//                    .install()
//                    .officeHome(getYmlParame("libreoffice"))
//                    .build();
//            officeManager.start();
//            JodConverter.convert(docxInputStream).as(DefaultDocumentFormatRegistry.DOCX).to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
//            XWPFDocument xwpfDocument = new XWPFDocument(OPCPackage.open(inputWord));
//            PdfOptions pdf = PdfOptions.create();
//
//            OutputStream os = new FileOutputStream(outputFile);
//            PdfConverter.getInstance().convert(xwpfDocument,os,pdf);
//            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
//            outputStream.close();
//            officeManager.stop();
            System.out.println("word→pdf is success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;

    }
//    public static void ConvertToPDF(String docPath, String pdfPath) {
//        try {
//            System.out.println("docPath"+docPath);
//            System.out.println("pdfPath"+pdfPath);
//            FileInputStream fis = new FileInputStream(docPath);
//            FileOutputStream fos = new FileOutputStream(pdfPath);
//            XWPFDocument xwpfDocument = new XWPFDocument(fis);
//            PdfOptions pdfOptions = PdfOptions.create();
//            PdfConverter.getInstance().convert(xwpfDocument,fos,pdfOptions);
//            System.out.println("生成成功");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            System.out.println("生成失败");
//        }
//    }
    public static void main(String[] args) throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        // 渲染文本
//        params.put("projectName", "XXX工程");
//
//        // 渲染图片
//        params.put("picture", new PictureRenderData(120, 120, "D:\\wx.png"));
//        // TODO 渲染其他类型的数据请参考官方文档
//        String templatePath = "D:\\zdd.docx";
//        String fileDir = "D:\\template";
//        String fileName = "zdd2";
//
//        String wordPath = WordUtils.createWord(templatePath, fileDir, fileName, params);
//        System.out.println("生成文档路径：" + wordPath);
//        ConvertToPDF("C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414103841《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.docx", "C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414103841《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.pdf");
//        InputStream docxInputStream = Files.newInputStream(Paths.get("C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414103841《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.docx"));
//        OutputStream outputStream = Files.newOutputStream(Paths.get("C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414103841《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.pdf"));
//        OfficeManager officeManager = LocalOfficeManager.builder()
//                .install()
//                .officeHome("D:/SOFT/Installed/libreoffice")
//                .build();
//        officeManager.start();
//        JodConverter.convert(docxInputStream).as(DefaultDocumentFormatRegistry.DOCX).to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
////            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
//        outputStream.close();
//        officeManager.stop();

        FileInputStream fis = new FileInputStream("src/main/resources/license.xml");
        License license=new License();
        license.setLicense(fis);
        File file = new File("C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414094404《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.pdf");
        try (FileOutputStream os = new FileOutputStream(file)) {
            Document doc = new Document("C:\\jeethink\\dtfhxsc\\uploadPath\\download\\20230414094404《富阳区受降南单元详细规划规划》3+X底线合规性审查报告.docx");
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
