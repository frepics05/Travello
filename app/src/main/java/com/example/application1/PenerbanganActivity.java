package com.example.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PenerbanganActivity extends AppCompatActivity {

    String[] JenisPesawat = {"Air Asia", "Garuda Indonesia", "Lion Air", "Batik Air", "Air Asia"};
    String[] JamPenerbangan = {"10:00 Am - 12:00", "07:45 Am - 09:45 Am", "09:45 Am - 11:45 Am", "12:00 Am - 02:00 Pm", "09:00 Am - 12:00 Am"};
    String[] HargaTiket = {"500.000 Rp", "750.000 Rp", "350.000 Rp", "650.000 Rp", "700.000Rp"};
    String[] LamaPerjalanan = {"2 Jam", "2 Jam", "2 Jam", "2 Jam", "3 Jam"};
    String[] TujuanPenerbangan = {"CGK - PDG", "CGK - PDG", "CGK - PDG", "CGK - PDG", "CGK - KUL"};
    int[] gambar = {R.drawable.airasia, R.drawable.garudaidn, R.drawable.lionair, R.drawable.batikair, R.drawable.airasia};

    String idkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerbangan);

        idkey = getIntent().getStringExtra("key");

        ListView listView = findViewById(R.id.list_pesawat);
        CustomLisAdapter adapter = new CustomLisAdapter();
        listView.setAdapter(adapter);
    }

    private class CustomLisAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gambar.length;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.tiketpesawat, null);

            ImageView imageView = convertView.findViewById(R.id.img);
            TextView textView = convertView.findViewById(R.id.txt_Judul);
            TextView textView1 = convertView.findViewById(R.id.txt_Keterangan);
            TextView textView2 = convertView.findViewById(R.id.txt_Keterangan2);
            TextView textView3 = convertView.findViewById(R.id.txt_Keterangan3);
            TextView textView4 = convertView.findViewById(R.id.txt_Keterangan4);

            imageView.setImageResource(gambar[position]);
            textView.setText(JenisPesawat[position]);
            textView1.setText(JamPenerbangan[position]);
            textView2.setText(HargaTiket[position]);
            textView3.setText(LamaPerjalanan[position]);
            textView4.setText(TujuanPenerbangan[position]);
            return convertView;

        }
    }
}