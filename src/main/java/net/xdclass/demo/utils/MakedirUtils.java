package net.xdclass.demo.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakedirUtils {
    /**
     *  创建文件夹工具类
     * @param filePath  视频文件路径
     * @return
     */
    public String makeDirVideo(String filePath,String filePathThumbnail){
        Date date = new Date();
        String dateForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dateFormPlace = dateForm.replace("-","");
        //视频
        String path = filePath+dateFormPlace + "/";

        //如果不存在，创建文件
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }

        //视频缩略图
        String pathThumbnail = filePathThumbnail+dateFormPlace + "/";
        //如果不存在，创建文件
        File ft = new File(pathThumbnail);
        if(!ft.exists()){
            ft.mkdirs();
        }

        return path;
    }


}
