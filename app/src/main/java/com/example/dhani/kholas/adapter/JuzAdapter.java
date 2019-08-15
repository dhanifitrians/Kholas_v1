package com.example.dhani.kholas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.model.BaseJuz;
import com.example.dhani.kholas.page.JuzActivity;
import com.example.dhani.kholas.page.SlideShowFragment;

import java.util.List;

public class JuzAdapter extends BaseAdapter {
    Context context;
    List<BaseJuz> listJuz;

    private View vi;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater = null;

    public JuzAdapter(Context context, List<BaseJuz> listJuz) {
        this.context = context;
        this.listJuz = listJuz;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return listJuz.size();}

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        vi = view;
        //Populate the Listview
        final int pos = position;
        BaseJuz baseJuz = listJuz.get(pos);
        if(vi == null) {
            vi = layoutInflater.inflate(R.layout.list_juz, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = vi.findViewById(R.id.surat_juz);
            viewHolder.no = vi.findViewById(R.id.no_juz);
            vi.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();

        viewHolder.name.setText(baseJuz.getJuz());
        viewHolder.no.setText(baseJuz.getJuzKe());

        viewHolder.layout_juz= vi.findViewById(R.id.layout_juz);
        viewHolder.layout_juz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(vi.getContext(), SlideShowFragment.class);
//                intent.putExtra("posisi", pos);
//                vi.getContext().startActivity(intent);
//                Toast.makeText(vi.getContext(),"tes"+pos,Toast.LENGTH_SHORT).show();
            }
        });

        return vi;
    }

    public class ViewHolder{
        TextView name;
        TextView no;
        LinearLayout layout_juz;
    }



}
