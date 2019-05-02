package alamsyah.scan_qr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import alamsyah.scan_qr.R;

/**
 * Created by ASUS on 27/09/2018.
 */

public class GridViewAdapter extends BaseAdapter{
    private Context mContext;
    private final String[] string;
    private final int[] Imageid;

    public GridViewAdapter(Context context,String[] string,int[] Imageid){
        mContext = context;
        this.Imageid = Imageid;
        this.string = string;
    }
    @Override
    public int getCount() {
        return string.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.custom, null);
            TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);
            textView.setText(string[i]);
            imageView.setImageResource(Imageid[i]);
        } else {
            grid = (View) view;
        }
        return grid;
    }
}
