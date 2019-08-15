package com.example.dhani.kholas.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.model.BaseSurat;
import com.example.dhani.kholas.page.MainActivity;

import java.util.List;


public class SuratAdapter extends BaseAdapter {
    Context context;
    List<BaseSurat> listSurah;

    private View vi;
    private ViewHolder viewHolder;
    private LayoutInflater layoutInflater = null;

    public SuratAdapter(Context context, List<BaseSurat> listSurah) {
        this.context = context;
        this.listSurah = listSurah;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listSurah.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, final View view, ViewGroup viewGroup) {
        vi = view;
        //Populate the Listview
        final int pos = position;
        BaseSurat baseSurat = listSurah.get(pos);
        if(vi == null) {
            vi = layoutInflater.inflate(R.layout.list_item_surat, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = vi.findViewById(R.id.nama_surat);
            viewHolder.nomer = vi.findViewById(R.id.no);
            viewHolder.jml = vi.findViewById(R.id.jml_ayat);
            vi.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) view.getTag();

        viewHolder.name.setText(baseSurat.getNama());
        viewHolder.nomer.setText(baseSurat.getNomor());
        viewHolder.jml.setText("Turun di "+baseSurat.getType()+", "+baseSurat.getAyat()+" ayat");
        viewHolder.linearLayout = vi.findViewById(R.id.item_surah);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(vi.getContext(),SlidingActivity.class);
//                intent.putExtra("posisi",position);
//                vi.getContext().startActivity(intent);
                ((MainActivity)context).onItemSelect(position);

            }
        });

        return vi;
    }

    public class ViewHolder{
        TextView name,nomer,jml;
        LinearLayout linearLayout;
    }
}
