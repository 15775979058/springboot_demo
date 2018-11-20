package net.xdclass.demo.domain;

public class Video {
    private int id;

    private String video_url;

    private String thumbnail_url_square;

    private String thumbnail_url_rectangle;

    private String part_in_month;

    private int status;

    private String note;

    private String video_title;

    private String introduce;

    private String openId;

    private int preview_num;

    private int level_award;

    private String equipment;

    private String location;

    private int add_time;

    private int is_delete;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getThumbnail_url_square() {
        return thumbnail_url_square;
    }

    public void setThumbnail_url_square(String thumbnail_url_square) {
        this.thumbnail_url_square = thumbnail_url_square;
    }

    public String getThumbnail_url_rectangle() {
        return thumbnail_url_rectangle;
    }

    public void setThumbnail_url_rectangle(String thumbnail_url_rectangle) {
        this.thumbnail_url_rectangle = thumbnail_url_rectangle;
    }

    public String getPart_in_month() {
        return part_in_month;
    }

    public void setPart_in_month(String part_in_month) {
        this.part_in_month = part_in_month;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getPreview_num() {
        return preview_num;
    }

    public void setPreview_num(int preview_num) {
        this.preview_num = preview_num;
    }

    public int getLevel_award() {
        return level_award;
    }

    public void setLevel_award(int level_award) {
        this.level_award = level_award;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getLocation(String location) {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public Video(){

    }

    public Video(int id, String video_url, String thumbnail_url_square, String thumbnail_url_rectangle, String part_in_month, int status, String note, String video_title, String introduce, String openId, int preview_num, int level_award, String equipment, String location, int add_time, int is_delete) {
        this.id = id;
        this.video_url = video_url;
        this.thumbnail_url_square = thumbnail_url_square;
        this.thumbnail_url_rectangle = thumbnail_url_rectangle;
        this.part_in_month = part_in_month;
        this.status = status;
        this.note = note;
        this.video_title = video_title;
        this.introduce = introduce;
        this.openId = openId;
        this.preview_num = preview_num;
        this.level_award = level_award;
        this.equipment = equipment;
        this.location = location;
        this.add_time = add_time;
        this.is_delete = is_delete;
    }
}
