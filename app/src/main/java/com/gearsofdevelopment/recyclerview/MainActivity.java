package com.gearsofdevelopment.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //data example
    private List<String> loadData() {
        List<String> data = new ArrayList<>();
        data.add("Black-crowned crane");
        data.add("Gemsbok");
        data.add("Malagasy ground boa");
        data.add("Northern phalarope");
        data.add("Spectacled caiman");
        data.add("Common langur");
        data.add("Eurasian badger");
        data.add("Cape Barren goose");
        data.add("Little grebe");
        data.add("Caribou");
        data.add("Greater rhea");
        data.add("Steenbok");
        data.add("Swallow (unidentified)");
        data.add("Gila monster");
        data.add("Cormorant (unidentified)");
        data.add("Carpet python");
        data.add("Common long-nosed armadillo");
        data.add("Lapwing (unidentified)");
        data.add("Rhesus monkey");
        data.add("Savannah deer (unidentified)");
        data.add("Blue-tongued lizard");
        data.add("Tree porcupine");
        data.add("Blesbok");
        data.add("Water monitor");
        data.add("American bighorn sheep");
        data.add("Springhare");
        data.add("Tiger");
        data.add("Neotropic cormorant");
        data.add("Bonnet macaque");
        data.add("White-faced whistling duck");
        data.add("Steller sea lion");
        data.add("Klipspringer");
        data.add("Red-breasted nuthatch");
        data.add("Grison");
        data.add("Fairy penguin");
        data.add("Stone sheep");
        data.add("Sportive lemur");
        data.add("White-fronted capuchin");
        data.add("Brush-tailed phascogale");
        data.add("Azara's zorro");
        data.add("Black-tailed prairie dog");
        data.add("Kinkajou");
        data.add("Blue crane");
        data.add("Black curlew");
        data.add("Greater roadrunner");
        data.add("Dingo");
        data.add("Common zebra");
        data.add("Little brown dove");
        data.add("Llama");
        data.add("Common wolf");
        data.add("Tiger cat");
        data.add("Pale white-eye");
        data.add("Gecko (unidentified)");
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> data = loadData();
        RecyclerView recyclerView = findViewById(R.id.rv);
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Holder(parent);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.update(data);
    }

    private static class Holder extends RecyclerView.ViewHolder<String> {

        private TextView textView;

        public Holder(@NonNull ViewGroup parent) {
            super(parent);
        }

        @Override
        public int loadView() {
            return R.layout.example_item;
        }

        @Override
        public void init(View itemView) {
            textView = itemView.findViewById(R.id.txt);
        }

        @Override
        public void setData(String s) {
            textView.setText(s);
        }
    }
}