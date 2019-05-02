package alamsyah.scan_qr.model;

/**
 * Created by ASUS on 18/09/2018.
 */

public class User {
    String id,nama;

    public User(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
