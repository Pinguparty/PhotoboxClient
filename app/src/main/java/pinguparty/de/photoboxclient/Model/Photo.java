package pinguparty.de.photoboxclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import pinguparty.de.photoboxclient.R;

public class Photo implements Parcelable {

    private String url;
    private String title;

    public Photo(String url, String title){
        this.url = url;
        this.title = title;
    }
    public Photo(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int i) {
            return new Photo[i];
        }
    };

    public static  Photo[] getPhotos() {

        return new Photo[]{
                new Photo("http://192.168.4.1/FLASH_DRIVE/1.jpg","LOCAL_TEST_1"),
                new Photo("http://192.168.4.1/FLASH_DRIVE/2.jpg","LOCAL_TEST_2"),
                new Photo("http://192.168.4.1/FLASH_DRIVE/3.jpg","LOCAL_TEST_3")
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
