package vn.ntq.android.projecttest4.network;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Allen on 19-Apr-16.
 */
public interface CatService {
    @Root(name = "response")
    public class Response {
        @Element(name = "data")
        ListCat listCat;

        public ListCat getListCat() {
            return listCat;
        }
    }

    class ListCat {
        @ElementList(name = "images")
        List<CatEntity> listCat;

        public List<CatEntity> getListCat() {
            return listCat;
        }
    }

    @Root(name = "image")
    class CatEntity implements Parcelable {
        @Element(name = "url")
        private String url;

        @Element(name = "id")
        private String id;

        @Element(name = "source_url")
        private String source_url;

        public CatEntity() {
        }

        public CatEntity(String url, String id, String source_url) {
            this.url = url;
            this.id = id;
            this.source_url = source_url;
        }

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


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeString(this.id);
            dest.writeString(this.source_url);
        }

        protected CatEntity(Parcel in) {
            this.url = in.readString();
            this.id = in.readString();
            this.source_url = in.readString();
        }

        public static final Creator<CatEntity> CREATOR = new Creator<CatEntity>() {
            @Override
            public CatEntity createFromParcel(Parcel source) {
                return new CatEntity(source);
            }

            @Override
            public CatEntity[] newArray(int size) {
                return new CatEntity[size];
            }
        };
    }


    @GET("images/get?format=xml&results_per_page=20&size=small")
    Call<Response> getListCat();

}
