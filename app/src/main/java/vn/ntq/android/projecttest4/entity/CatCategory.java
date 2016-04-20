package vn.ntq.android.projecttest4.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Allen on 19-Apr-16.
 */
public class CatCategory implements Parcelable {
    private int id;
    private String name;
    private String url;

    public CatCategory(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected CatCategory(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Creator<CatCategory> CREATOR = new Creator<CatCategory>() {
        @Override
        public CatCategory createFromParcel(Parcel source) {
            return new CatCategory(source);
        }

        @Override
        public CatCategory[] newArray(int size) {
            return new CatCategory[size];
        }
    };
}
