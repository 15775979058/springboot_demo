package net.xdclass.demo.mapper;

import net.xdclass.demo.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface VideoMapper {

    //推荐使用#{}，不要使用${}，会有注入风险
    @Insert("Insert Into au_upload_video(video_url,thumbnail_url_square,thumbnail_url_rectangle,part_in_month,status,note,video_title,introduce,openId,equipment,location,add_time)" +
                                        " VALUES(#{video_url},#{thumbnail_url_square},#{thumbnail_url_rectangle},#{part_in_month},#{status},#{note},#{video_title},#{introduce},#{openId},#{equipment},#{location},#{add_time})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(Video video);

    //视频首页列表数据
    @Select("Select id,thumbnail_url_rectangle,part_in_month,video_title,introduce,preview_num,equipment,location,add_time FROM au_upload_video WHERE is_delete = 0 and status = 1 limit #{startRow},#{pageSize}")
    List<Video> selectVideoByPage(Map<String, Object> paramMap);

}