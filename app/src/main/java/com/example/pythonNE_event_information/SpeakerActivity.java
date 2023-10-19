package com.example.pythonNE_event_information;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpeakerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SpeakerAdapter adapter; // Create an adapter for your RecyclerView
    private List<SpeakerItem> speakerList; // Create a list of speakers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewSpeakers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list of speakers
        speakerList = new ArrayList<>();

        // Create and set the adapter
        adapter = new SpeakerAdapter(speakerList);
        recyclerView.setAdapter(adapter);

        // Find the returnToMainActivity ImageView and set a click listener to finish the activity
        ImageView closeSpeakerActivityImageView = findViewById(R.id.returnToMainActivity);
        closeSpeakerActivityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the SpeakerActivity
                finish();
            }
        });

        // Initialize data structures for speaker information
        Set<String> uniqueSpeakerNames = new HashSet<>();

        Map<String, String> speakerBios = createSpeakerBios();

        // Create a list of SpeakerItem objects containing names and bios
        List<SpeakerItem> speakerInfoList = new ArrayList<>();
        for (Item information : MainActivity.information) {
            String speakerName = information.getPersonName();
            String speakerBio = speakerBios.getOrDefault(speakerName, "Biografia não disponível");
            if (!speakerName.isEmpty() && uniqueSpeakerNames.add(speakerName)) {
                speakerInfoList.add(new SpeakerItem(speakerName, speakerBio));
            }
        }

        // Add the speakerInfoList to your RecyclerView adapter
        adapter.addAll(speakerInfoList);
    }

    private Map<String, String> createSpeakerBios() {
        Map<String, String> speakerBios = new HashMap<>();
        // Populate the speakerBios map with speaker names and bios
        speakerBios.put("Lidiane Monteiro", "Desenvolvedora de Software | Fundadora da InspirAda na Computação | Professora de Computação | Comunicadora");
        speakerBios.put("Jerson Brito", "Líder técnico Splan na Ambev Tech");
        speakerBios.put("Bruno Cabral", "Founder at Escavador");
        speakerBios.put("Rebeca Almeida, Giulio Carvalho", "Open Source Community Manager | Data Journalist | Visual Artist, Manager of the Data Science for Civic Innovation Program");
        speakerBios.put("Rebeca Almeida", "Open Source Community Manager | Data Journalist | Visual Artist");
        speakerBios.put("Ryllari Costa", "Software Developer | Computer Engineer at Jusbrasil");
        speakerBios.put("Higor", "Biografia não disponível");
        speakerBios.put("Lucas Emanuel", "Desenvolvedor de Software Full Stack | JavaScript | React | Node.js | Java | Spring | MySQL | MongoDB | Git");
        speakerBios.put("Pedro Assis", "Gestor de Tráfego Pago | Growth Marketing | SEO");
        speakerBios.put("Isaac Silva", "Data Engineer | SQL | BigQuery | GCP | Python | Terraform | Tekton | Qlik Sense");
        speakerBios.put("Mayara Machado", "Data Engineer at Mercado Livre");
        speakerBios.put("Gabi Cavalcante, Raquel Oliveira", "Biografia não disponível");
        speakerBios.put("Letícia Cena", "Project Manager | Cloud | Data-Driven PMO | Software Development & Integration");
        speakerBios.put("Dandara Sousa", "Senior Data Analyst at iFood | Co-Founder @PyLadiesPB");
        speakerBios.put("Ruan Cardoso Comelli", "Biografia não disponível");
        speakerBios.put("Alyne Ferreira", "Biografia não disponível");
        speakerBios.put("Felipe", "Biografia não disponível");
        speakerBios.put("Ítalo Epifânio", "RnD Python Developer");
        speakerBios.put("Maria Fernanda Souza, Rafaela Torres", "Data Engineer @ JusBrasil, Tech Talent Acquisition at Jusbrasil");
        return speakerBios;
    }
}