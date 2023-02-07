package pers.lxl.mylearnproject.javase.io.io.others;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipL {
    public static void main(String[] args) throws IOException {
//        //read
//        try (ZipInputStream zip = new ZipInputStream(new FileInputStream("C:\\Users\\Dcjczx\\Desktop\\dist.zip"))) {
//            ZipEntry entry;
//            while ((entry = zip.getNextEntry()) != null) {
//                if (!entry.isDirectory()) {
//                    int n,i=0;
//                    while ((n = zip.read()) != -1) {
//                        System.out.println(i);
//                        i++;
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //write
//        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("C:\\Users\\Dcjczx\\Desktop\\dist.zip"))) {
//            File[] files = new File[0];
//            for (File file : files) {
//                zip.putNextEntry(new ZipEntry(file.getName()));
//               // zip.write(getFileDataAsBytes(file));
//                zip.closeEntry();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        fileToZip("D:\\PROJECTS\\MY\\mylearnproject\\src\\main\\java\\pers\\lxl\\mylearnproject\\javase\\io\\io\\IO.jpg", "D:\\CaptureTest\\123.zip");
    }

    public static void fileToZip(String srcFile, String zipFile) throws IOException {
            File file = new File(srcFile);
            //取出文件名
            String name = file.getName();
            //读取文件
            FileInputStream inputStream = new FileInputStream(file);
            //输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            //ZipEnter:表示压缩文件的条目(文件目录)
            zipOutputStream.putNextEntry(new ZipEntry("Image\\01.jpg"));

            int temp = 0;
            while ((temp = inputStream.read()) != -1) {
                zipOutputStream.write(temp);
            }
            zipOutputStream.close();
            inputStream.close();
    }



}
