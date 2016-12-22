package suitmedia.screening;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Fadhil on 21/12/2016.
 */
public class EventAdapter extends BaseAdapter {
    Context context;
    List<Event> eventItems;

    public EventAdapter(Context context, List<Event> eventItems){
        this.context = context;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int i) {
        return eventItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return eventItems.indexOf(getItem(i));
    }

    private class ViewHolder {
        ImageView imageEvent;
        TextView txtNamaEvent;
        TextView txtTanggalEvent;
        TextView txtDesc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = mInflater.inflate(R.layout.event_list_item,null);
            holder = new ViewHolder();
            holder.imageEvent = (ImageView) view.findViewById(R.id.iv_foto_event);
            holder.txtNamaEvent = (TextView) view.findViewById(R.id.tv_nama_event);
            holder.txtTanggalEvent = (TextView) view.findViewById(R.id.tv_tanggal_event);
            holder.txtDesc = (TextView) view.findViewById(R.id.tv_desc);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Event eventItem = (Event) getItem(i);

        holder.imageEvent.setImageResource(eventItem.imageId);
        holder.txtNamaEvent.setText(eventItem.nama);
        DateFormat df = new SimpleDateFormat("MMM dd yyyy");
        holder.txtTanggalEvent.setText(df.format(eventItem.tanggal));
        String diskripsi = (eventItem.desc.length() > 100) ? eventItem.desc.substring(0,100)+"..." : eventItem.desc;
        holder.txtDesc.setText(diskripsi);


        return view;
    }
}
