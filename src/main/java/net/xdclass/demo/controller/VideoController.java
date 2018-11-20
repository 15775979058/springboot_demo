package net.xdclass.demo.controller;

import net.xdclass.demo.domain.JsonData;
import net.xdclass.demo.domain.Video;
import net.xdclass.demo.service.VideoService;
import net.xdclass.demo.utils.ConverVideoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：用于视频首页列表数据源
 *
 */
@Controller
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Value("${web.ffmpeg-path}")
    private String ffmpegPath;

    /**
     * 视频首页列表
     * @paramx
     * @return
     */
    @PostMapping(value = "/getVideoList")
    @ResponseBody
    @CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {RequestMethod.GET, RequestMethod.POST})
    public JsonData getVideoList(HttpServletRequest request){
        //提交页数
        int curPage = Integer.parseInt(request.getParameter("curPage"));

        //页数
        int pageSize = 10;
        int startRow = (curPage - 1)*pageSize;
        Map<String,Object> params = new ConcurrentHashMap<>();
        params.put("startRow",startRow);
        params.put("pageSize",pageSize);
        List<Video> videoList = videoService.getVideoByPage(params);

        return new JsonData(10001,"获取成功",videoList);
    }

    @GetMapping(value = "/ffmpeg_fun")
    @ResponseBody
    @CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {RequestMethod.GET, RequestMethod.POST})
    public void ffmpeg_fun(){
        ConverVideoUtils converVideoUtils = new ConverVideoUtils();
        converVideoUtils.processFfmpegSwf("/opt/video_thumbnail/20181115/64c69b1d-bfea-48b3-8fc4-12ffb39e1a8d.mp4","/opt/video_thumbnail/20181115/64c69b1d-bfea-48b3-8fc4-12ffb39e1a8d.swf",ffmpegPath);
    }


    @PostMapping("/play")
    @ResponseBody
    @CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {RequestMethod.GET, RequestMethod.POST})
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("play");
        File file = new File("/opt/video/20181115/64c69b1d-bfea-48b3-8fc4-12ffb39e1a8d.mp4");
//        File file = new File("E:/test.mp4");
        String fileName = file.getName();
        String userAgent = req.getHeader("User-Agent").toLowerCase();

        if (userAgent.indexOf("firefox") != -1) {

            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
        }
        else {

            resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }

        //设置response编码
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Content-Length", "" + file.length());
        //设置输出文件类型
        resp.setContentType("video/mpeg4");

        FileInputStream fis = null;
        OutputStream os = null;

        try {
            //获取response输出流
            os = resp.getOutputStream();

            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 1;
            System.out.println("begin");
            while ((len = fis.read(buffer)) != -1) {
                // 输出文件
                os.write(buffer,0,len);
//                System.out.println(buffer);
            }
            System.out.println("middle");
        } catch (Exception c) {
            System.out.println(c);
            if (null != fis) {
                fis.close();
            }

            if (null != os) {
                os.flush();
                os.close();
            }
        }
        System.out.println("finish");
    }
}
