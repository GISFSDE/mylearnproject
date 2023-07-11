package com.jeethink.project.dzzlrkxt.common.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lxl
 * @Date: 2022/10/20/18:40
 * @Description:
 */

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * 压缩文件
     */
    public static String zipShapeFile(List<String> files, String path, String name) {
        try {

//            String zipPath = path + File.separator + name + ".zip";
            String zipPath = path + File.separator + name + ".zip";
            File zipFile = new File(zipPath);
            InputStream input = null;
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            // zip的名称为
            zipOut.setComment(name);

            for (String s : files) {
                File file = new File(s);
                input = new FileInputStream(file);
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
            }
            zipOut.close();
            return zipPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void stringToTxt(String content, String filePath) {
//        String filePath = filePath;"E:/" + "这就是文件名.txt";
//        String content = "这是txt文件内容，随便拼装";
        FileWriter fw = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                file.createNewFile();
            }
            fw = new FileWriter(filePath);
            fw.write(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }
}

