package com.example.aplikasipelaporaninsiden;

import java.io.Serializable;

public class VideoItem implements Serializable {
    private String title;
    private String duration;
    private int thumbnailResId;
    private int videoRawResId; // To simulate playing a video from raw resources

    public VideoItem(String title, String duration, int thumbnailResId, int videoRawResId) {
        this.title = title;
        this.duration = duration;
        this.thumbnailResId = thumbnailResId;
        this.videoRawResId = videoRawResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public int getThumbnailResId() {
        return thumbnailResId;
    }

    public int getVideoRawResId() {
        return videoRawResId;
    }
}
