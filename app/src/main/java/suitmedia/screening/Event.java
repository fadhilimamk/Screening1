package suitmedia.screening;
import java.util.Date;

/**
 * Created by Fadhil on 21/12/2016.
 */
public class Event {
    String nama;
    int imageId;
    Date tanggal;
    public Event(int imageId, String nama, Date tanggal) {
        this.imageId = imageId;
        this.nama = nama;
        this.tanggal = tanggal;
    }
}
