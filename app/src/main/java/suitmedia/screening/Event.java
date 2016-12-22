package suitmedia.screening;
import java.util.Date;

/**
 * Created by Fadhil on 21/12/2016.
 */
public class Event {
    String nama;
    int imageId;
    Date tanggal;
    String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.";
    Double latitude = -6.890554;
    Double longtitude = 107.609981;

    public Event(int imageId, String nama, Date tanggal) {
        this.imageId = imageId;
        this.nama = nama;
        this.tanggal = tanggal;
    }
    public Event(int imageId, String nama, Date tanggal, Double longt, Double lat) {
        this.imageId = imageId;
        this.nama = nama;
        this.tanggal = tanggal;
        this.latitude = lat;
        this.longtitude = longt;
    }
}
