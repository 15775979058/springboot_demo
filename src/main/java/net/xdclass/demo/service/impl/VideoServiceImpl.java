package net.xdclass.demo.service.impl;

import net.xdclass.demo.domain.Video;
import net.xdclass.demo.mapper.VideoMapper;
import net.xdclass.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int add(Video video) {
        videoMapper.insert(video);
        int id = video.getId();
        return 0;
    }

    @Override
    public List<Video> getVideoByPage(Map<String, Object> paramMap) {
        return videoMapper.selectVideoByPage(paramMap);
    }
}
