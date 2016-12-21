package suitmedia.screening;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fadhil on 21/12/2016.
 */
public class GuestAdapter extends BaseAdapter {
    Context context;
    List<Person> guestLists;

    public GuestAdapter(Context context, List<Person> guestItems){
        this.context = context;
        this.guestLists = guestItems;
    }

    @Override
    public int getCount() {
        return guestLists.size();
    }

    @Override
    public Object getItem(int i) {
        return guestLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return guestLists.indexOf(getItem(i));
    }

    private class ViewHolder {
        ImageView imgFoto;
        TextView txtNamaGuest;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = mInflater.inflate(R.layout.guest_grid_item,null);
            holder = new ViewHolder();
            holder.imgFoto = (ImageView) view.findViewById(R.id.iv_foto_guest);
            holder.txtNamaGuest = (TextView) view.findViewById(R.id.tv_nama_guest);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Person guestitem = (Person) getItem(i);
        holder.imgFoto.setImageResource(R.mipmap.ic_launcher);
        holder.txtNamaGuest.setText(guestitem.nama);

        return view;
    }
}
