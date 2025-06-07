package com.example.hamburgueriaz;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQuantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mTextViewQuantidade = findViewById(R.id.textViewQuantidade);

    }

    public void somar(View view) {
        String valorQuantidadeString = mTextViewQuantidade.getText().toString();
        int  valorQuantidade = Integer.parseInt(valorQuantidadeString);

        mTextViewQuantidade.setText(String.valueOf(valorQuantidade + 1));

    }

    public void subtrair(View view) {

    }

}