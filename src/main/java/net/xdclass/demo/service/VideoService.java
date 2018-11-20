package net.xdclass.demo.service;

import net.xdclass.demo.domain.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {

    //视频上传
    int add(Video video);

    //分页查询
    List<Video> getVideoByPage(Map<String,Object> paramMap);


}
