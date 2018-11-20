package net.xdclass.demo.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xdclass.demo.domain.JsonData;

import net.xdclass.demo.domain.Video;
import net.xdclass.demo.service.VideoService;
import net.xdclass.demo.utils.ConverVideoUtils;
import net.xdclass.demo.utils.MakedirUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：用于视频作品上传与提交作品数据
 *	upload:用于视频异步上传
 *
 */
@Controller
@CrossOrigin
public class FileController {

	@RequestMapping(value = "/api/v1/gopage")  
	public Object index() {
		return "index";
	}

	@Autowired
	private VideoService videoService;

    //private static final String filePath = "/opt/video/";
	@Value("${web.upload-path}")
	private String filePath;

	@Value("${web.upload-path-thumbnail}")
	private String filePathThumbnail;

	@Value("${web.server-ip}")
	private String serverIp;

	@Value("${web.ffmpeg-path}")
	private String ffmpegPath;

	/**
	 * 	异步上传图片
	 * @param file
	 * @param request
	 * @param resp
	 * @return
	 */
	 	@PostMapping(value = "/upload")
	    @ResponseBody
		@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {RequestMethod.GET, RequestMethod.POST})
//		@CrossOrigin
	    public JsonData upload(@RequestParam("head_img") MultipartFile file, HttpServletRequest request, HttpServletResponse resp) {
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Methods", "*");
			resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
	 		//file.isEmpty(); 判断图片是否为空
	 		//file.getSize(); 图片大小进行判断

	        
	 		// 获取文件名
	        String fileName = file.getOriginalFilename();	        
	        System.out.println("上传的文件名为：" + fileName);
	        
	        // 获取文件的后缀名,比如图片的jpeg,png
	        String suffixName = fileName.substring(fileName.lastIndexOf("."));
	        System.out.println("上传的后缀名为：" + suffixName);

	        if(!suffixName.equals(".mp4")){
				return  new JsonData(10050, "请上传MP4文件 ", null);
			}

			//创建时间文件夹
			MakedirUtils makedirUtils = new MakedirUtils();
			String filePathFormat = makedirUtils.makeDirVideo(filePath,filePathThumbnail);
	        
	        // 文件上传后的路径
	        fileName = UUID.randomUUID() + suffixName;
	        System.out.println("转换后的名称:"+fileName);
	        
	        File dest = new File(filePathFormat + fileName);

	        try {
	            file.transferTo(dest);
				//上传视频后自动截图
				ConverVideoUtils converVideoUtils = new ConverVideoUtils();
				/*
				windows截图
				 */
				//String img_url = converVideoUtils.processImg(filePath + fileName,"E:/ffmpeg/windows/ffmpeg-20181111-e24a754-win64-static/ffmpeg-20181111-e24a754-win64-static/bin/ffmpeg.exe");
				/*
				liunx截图
				 */
				String img_url = converVideoUtils.processImg(filePathFormat +  fileName,ffmpegPath);
				String[] s = img_url.split(";");			//获取返回链接
				String[] img_url_square = s[0].split("/");	//方形
				String[] img_url_rectangle = s[1].split("/");	//矩形

				System.out.println(img_url_rectangle[3]);
				System.out.println(img_url_rectangle[4]);
				/*
				插入数据库
				 */

//	            return new JsonData(10001, fileName,serverIp+fileName);
				return new JsonData(10001, "上传成功",serverIp+img_url_square[3]+"/"+img_url_square[4]);
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return  new JsonData(10050, "上传失败 ", null);
	    }

		@RequestMapping(value = "showDateDir")
		@ResponseBody
	    public String showMsg(){
	 		//日期 201902
			Date date = new Date();
			String dateForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String dateFormPlace = dateForm.replace("-","");
			//10位时间戳
			long timeStampSec = new Date().getTime()/1000;
			String timestamp = String.format("%010d", timeStampSec);
			//字符串解析文件名
			String url = "http://47.106.183.16:8080/20181114/8de763a9-54bc-4790-bb55-46dfd70b74a2_square.jpg";
			String[] paths = url.split("/");
			/*
			http:

			47.106.183.16:8080
			20181114
			8de763a9-54bc-4790-bb55-46dfd70b74a2_square.jpg
			 */
			String[] pathNames = paths[4].split("_");

			System.out.println("后缀名："+pathNames[1].substring(pathNames[1].lastIndexOf(".")));
			return "";
//			String video_url_mp4 = filePath+paths[3]+"/"+pathNames[0]+".mp4";
//			//长缩略图
//			String video_url_rectangle = filePathThumbnail + paths[3]+"/"+pathNames[0] + "_rectangle."+suffixs[1];
//			System.out.println("视频路径为："+video_url_mp4);
//			System.out.println("长图链接："+video_url_rectangle);
//	 		return "";
		}


	@PostMapping(value = "/uploadVideoSubmit")
	@ResponseBody
	@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {RequestMethod.GET,RequestMethod.POST})
	public JsonData uploadVideoSubmit(HttpServletRequest request, HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		resp.setHeader("Access-Control-Allow-Headers", "content-type, authorization");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");

		//提交的是video_url_square的链接
		String video_url_square = request.getParameter("video_url");
		System.out.println("投稿视频链接："+video_url_square);
		String part_in_month = request.getParameter("part_in_month");
		System.out.println("参赛月份："+part_in_month);
		String video_title = request.getParameter("video_title");
		System.out.println("视频标题："+video_title);
		String introduce = request.getParameter("introduce");
		System.out.println("视频描述："+introduce);
		String equipment = request.getParameter("equipment");
		System.out.println("设备："+equipment);
		String location = request.getParameter("location");
		System.out.println("拍摄地："+location);

		if(video_url_square.equals("")){
			return new JsonData(10050, "投稿视频链接不能为空!", null);
		}else if(part_in_month.equals("")){
			return new JsonData(10050, "参赛月份不能为空!", null);
		}else if(video_title.equals("")){
			return new JsonData(10050, "视频标题不能为空!", null);
		}else if(introduce.equals("")){
			return new JsonData(10050, "视频描述不能为空!", null);
		}else if(equipment.equals("")){
			return new JsonData(10050, "拍摄设备不能为空!", null);
		}else if(location.equals("")){
			return new JsonData(10050, "拍摄地不能为空!", null);
		}

		//解析链接
		//mp4文件
		String[] paths = video_url_square.split("/");
		String[] pathNames = paths[4].split("_");
		String video_url_mp4 = filePath+paths[3]+"/"+pathNames[0]+".mp4";
		//长缩略图
		String suffix = pathNames[1].substring(pathNames[1].lastIndexOf("."));	//后缀名
		String video_url_rectangle = serverIp + paths[3]+"/"+pathNames[0] + "_rectangle"+suffix;

		long timeStampSec = new Date().getTime()/1000;
		int timestamp = Integer.parseInt(String.format("%010d", timeStampSec));

		Video video = new Video();
		video.setVideo_url(video_url_mp4);
		video.setThumbnail_url_square(video_url_square);
		video.setThumbnail_url_rectangle(video_url_rectangle);
		video.setPart_in_month(part_in_month);
		video.setStatus(1);
		video.setNote("参加"+part_in_month+"月份赛季比赛");
		video.setVideo_title(video_title);
		video.setIntroduce(introduce);
		video.setOpenId("ddddssss");
		video.setEquipment(equipment);
		video.setLocation(location);
		video.setAdd_time(timestamp);

		videoService.add(video);

		return new JsonData(10001, "提交成功", video_url_square);
	}


}
