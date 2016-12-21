package suitmedia.screening;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fadhil on 21/12/2016.
 */
public class Person {
    int imageId;
    String nama;
    Date birthday;

    public Person(int imageId, String nama, Date birthday) {
        this.imageId = imageId;
        this.nama = nama;
        this.birthday = birthday;
    }

    public String getPhone(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.birthday);
        int tgllahir = cal.get(Calendar.DAY_OF_MONTH);
        String hasil = "feature phone";

        if(tgllahir % 6 == 0){
            hasil = "iOS";
        }else if(tgllahir % 3 == 0){
            hasil = "android";
        }else if(tgllahir % 2 == 0){
            hasil = "blackbarry";
        }

        return hasil;
    }

}
