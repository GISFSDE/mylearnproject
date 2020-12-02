package pers.lxl.mylearnproject.javase.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javafx.scene.input.DataFormat.URL;

public class Picture {
    public static void main(String[] args) throws IOException {
        /*读取*/
//      一: 通过 java.awt.Toolkit 工具类来读取本地、网络 或 内存中 的 图片（支持 GIF、JPEG 或 PNG）
//        Image image = Toolkit.getDefaultToolkit().getImage(String filename);
//        Image image1 = Toolkit.getDefaultToolkit().getImage(URL url);
//        Image image2 = Toolkit.getDefaultToolkit().createImage(byte[] imageData);
//      二: 通过 javax.imageio.ImageIO 工具类读取本地、网络 或 内存中 的 图片（BufferedImage 继承自 Image）,建议使用api更多
//        BufferedImage bufImage = ImageIO.read(File input
        BufferedImage bufImage = ImageIO.read(new File("F:\\PROJECT\\IDEA\\mylearnproject\\src\\main\\resources\\pictures\\线程状态转换.jpg"));
//        BufferedImage bufImage = ImageIO.read(URL input);
//        BufferedImage bufImage = ImageIO.read(InputStream input);
// 创建空图片
//        BufferedImage bufImage = new BufferedImage(int width, int height, int imageType);

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        // 把修改过的 bufImage 保存到本地
        ImageIO.write(bufImage, "JPEG", new File("F:\\PROJECT\\IDEA\\mylearnproject\\src\\main\\resources\\pictures\\bb.jpg"));



    }
}
