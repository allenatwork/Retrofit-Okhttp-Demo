package vn.ntq.android.projecttest4.network;

import java.util.List;

/**
 * Created by Allen on 19-Apr-16.
 */
public class CatResponse {


    private ResponseEntity response;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public static class ResponseEntity {

        private DataEntity data;

        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public static class DataEntity {
            private ImagesEntity images;

            public ImagesEntity getImages() {
                return images;
            }

            public void setImages(ImagesEntity images) {
                this.images = images;
            }

            public static class ImagesEntity {

                private List<ImageEntity> image;

                public List<ImageEntity> getImage() {
                    return image;
                }

                public void setImage(List<ImageEntity> image) {
                    this.image = image;
                }

                public static class ImageEntity {
                    private String url;
                    private String id;
                    private String source_url;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getSource_url() {
                        return source_url;
                    }

                    public void setSource_url(String source_url) {
                        this.source_url = source_url;
                    }
                }
            }
        }
    }
}
