package pers.lxl.mylearnproject.javase.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipL {
    public static void main(String[] args) {
        //read
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\lixianglun\\11.zip"))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    int n,i=0;
                    while ((n = zip.read()) != -1) {
                        System.out.println(i);
                        i++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //write
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\lixianglun\\11.zip"))) {
            File[] files = new File[0];
            for (File file : files) {
                zip.putNextEntry(new ZipEntry(file.getName()));
               // zip.write(getFileDataAsBytes(file));
                zip.closeEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
