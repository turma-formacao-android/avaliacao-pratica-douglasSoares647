package turmaformacao.br.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhb_s on 01/10/2015.
 */
public class Telephone implements Parcelable{

private Long id;
    private Long id_contact;
    private String telephone;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId_contact() {
            return id_contact;
        }

        public void setId_contact(Long id_contact) {
            this.id_contact = id_contact;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id==null?-1:this.id);
            dest.writeValue(this.id_contact==null?-1:this.id_contact);
            dest.writeString(this.telephone==null?"":this.telephone);
        }

        public Telephone() {
        }

        protected Telephone(Parcel in) {
            this.id = (Long) in.readValue(Long.class.getClassLoader());
            this.id_contact = (Long) in.readValue(Long.class.getClassLoader());
            this.telephone = in.readString();
        }

public static final Parcelable.Creator<Telephone> CREATOR = new Parcelable.Creator<Telephone>() {
    public Telephone createFromParcel(Parcel source) {
        return new Telephone(source);
    }

    public Telephone[] newArray(int size) {
        return new Telephone[size];
    }
};
}
