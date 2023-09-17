package com.example.semoc_bes_droid_builders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<Item>();

        //name, eventType, local, date, image
        items.add(new Item("Generative AI", "Palestra", "Auditório", "05/10/2023", R.drawable.placeholder));
        items.add(new Item("Programação Competitiva", "Debate", "Sala 414", "05/10/2023", R.drawable.placeholder));
        items.add(new Item("Segurança de Sistemas", "Palestra", "Auditório", "05/10/2023", R.drawable.placeholder));
        items.add(new Item("Análise de Dados", "Debate", "Sala 412", "06/10/2023", R.drawable.placeholder));
        items.add(new Item("Testes Automatizados", "Palestra", "Auditório", "06/10/2023", R.drawable.placeholder));
        items.add(new Item("Governança de TI", "Palestra", "Auditório", "06/10/2023", R.drawable.placeholder));
        items.add(new Item("Building Tech Leaders", "Palestra", "Auditório", "06/10/2023", R.drawable.placeholder));
        items.add(new Item("Gerenciamento de Projetos", "Palestra", "Auditório", "07/10/2023", R.drawable.placeholder));
        items.add(new Item("Metodologias Ágeis", "Palestra", "Auditório", "07/10/2023", R.drawable.placeholder));
        items.add(new Item("Machine Learning", "Palestra", "Auditório", "07/10/2023", R.drawable.placeholder));
        items.add(new Item("Natural Language Processing", "Palestra", "Auditório", "07/10/2023", R.drawable.placeholder));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), items));
    }
}