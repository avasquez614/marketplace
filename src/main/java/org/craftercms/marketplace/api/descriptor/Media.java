package org.craftercms.marketplace.api.descriptor;

import java.util.List;

/**
 * Created by alfonso on 5/19/17.
 */
public class Media {

    private List<Asset> screenshots;
    private List<Asset> videos;

    public List<Asset> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<Asset> screenshots) {
        this.screenshots = screenshots;
    }

    public List<Asset> getVideos() {
        return videos;
    }

    public void setVideos(List<Asset> videos) {
        this.videos = videos;
    }

}
