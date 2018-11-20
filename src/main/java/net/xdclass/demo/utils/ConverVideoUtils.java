package net.xdclass.demo.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.xdclass.demo.domain.Contants;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ConverVideoUtils {

    private String sourceVideoPath;                         //源视频路径
    private String filerealname;                            //文件名不包括后缀名
    private String filename;                                //包括后缀名

    private String ffmpegPath = Contants.ffmpegpath;         // ffmpeg.exe的目录
    private String imageRealPath = Contants.imageRealPath;   // 截图的存放目录

    @Value("${web.video_icon}")
    private static String video_icon;

    //视频截图
    public static String processImg(String video_path,String ffmpeg_path){

        File file = new File(video_path);
        if (!file.exists()) {
            System.err.println("路径[" + video_path + "]对应的视频文件不存在!");
            return "not exist";
        }
        //正方形截图
        List<String> commands_square = new java.util.ArrayList<String>();
        commands_square.add(ffmpeg_path);
        commands_square.add("-i");
        commands_square.add(video_path);
        commands_square.add("-y");
        commands_square.add("-f");
        commands_square.add("image2");
        commands_square.add("-ss");
        commands_square.add("5");//这个参数是设置截取视频多少秒时的画面

        commands_square.add("-s");
        commands_square.add("1080x760");
        commands_square.add(video_path.substring(0, video_path.lastIndexOf(".")).replaceFirst("video", "video_thumbnail") + "_square.jpg");
        String img_url_commands_square = video_path.substring(0, video_path.lastIndexOf(".")).replaceFirst("video", "video_thumbnail") + "_square.jpg";

        //长方形截图
        List<String> commands_rectangle = new java.util.ArrayList<String>();
        commands_rectangle.add(ffmpeg_path);
        commands_rectangle.add("-i");
        commands_rectangle.add(video_path);
        commands_rectangle.add("-y");
        commands_rectangle.add("-f");
        commands_rectangle.add("image2");
        commands_rectangle.add("-ss");
        commands_rectangle.add("5");//这个参数是设置截取视频多少秒时的画面

        commands_rectangle.add("-s");
        commands_rectangle.add("1080x560");
        commands_rectangle.add(video_path.substring(0, video_path.lastIndexOf(".")).replaceFirst("video", "video_thumbnail") + "_rectangle.jpg");
        String img_url_commands_rectangle = video_path.substring(0, video_path.lastIndexOf(".")).replaceFirst("video", "video_thumbnail") + "_rectangle.jpg";

        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands_square);
            builder.start();
            System.out.println("截取成功");

            builder.command(commands_rectangle);
            builder.start();

            return img_url_commands_square+";"+img_url_commands_rectangle;
        } catch (Exception e) {
            e.printStackTrace();
            return "upload error";
        }
    }

    //ffmpeg转成swf文件
    public static String processFfmpegSwf(String srcVideoPath,String tarVideoPath,String ffmpeg_path) {
        File file = new File(srcVideoPath);
        if (!file.exists()) {
            System.err.println("路径[" + srcVideoPath + "]对应的视频文件不存在!");
            return "not exist";
        }
        List<String> commend = new java.util.ArrayList<String>();

        commend.add(ffmpeg_path);

        commend.add("-y");

        commend.add("-i");

        commend.add(srcVideoPath);

        commend.add("-b");

        commend.add("360");

        commend.add("-r");

        commend.add("25");

        commend.add("-s");

        commend.add("640x480");

        commend.add("-ab");

        commend.add("56");

        commend.add("-ar");

        commend.add("22050");

        commend.add("-ac");

        commend.add("1");

        commend.add(tarVideoPath);

        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process process = builder.start();
            process.destroy();

            return "transform success";
        } catch (Exception e) {
            System.err.println("【" + srcVideoPath + "】 processFfmpegSwf  转换不成功 !");
            return "transform error";
        }
    }



}
