package com.lesson;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

public class Test {

    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径
     * @throws Exception
     */
    public static void fetchFrame(String videofile, String framefile)
            throws Exception {
        long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
        int lenght = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            System.out.println(lenght);
            if ((i > 5000) && (f.image != null)) {
                break;
            }
            i++;
        }
        IplImage img = f.image;
        int owidth = img.width();
        int oheight = img.height();
        // 对截取的帧进行等比例缩放
        int width = 800;
        int height = (int) (((double) width / owidth) * oheight);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
        ImageIO.write(bi, "jpg", targetFile);
        ff.stop();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        try {
            Test.fetchFrame("http://vali-dns.cp31.ott.cibntv.net/69749FF87034971D5DEC153BF/03000A0A005BBB1942AA6531329A3C7A2FC98A-7558-4630-9342-5B0CD7223BF7.mp4?ccode=0502&duration=391&expire=18000&psid=df19de3b347b8e864618ac703cccec36&ups_client_netip=3b29a284&ups_ts=1539670469&ups_userid=&utid=TEEOFOYtVSwCAbckU2WyNlLy&vid=XMzg1MTk3MTQzMg%3D%3D&vkey=A60c0523842249b6ed176c5984111c1ec&s=99972f83b42a4edabfd8&sp=", "C:\\Users\\mayn\\Documents\\Tencent Files\\809005117\\FileRecv\\test5.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
