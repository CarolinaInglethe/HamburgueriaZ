package com.example.hamburgueriaz;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQuantidade;
    private EditText mEditTextViewNome;
    private TextView mTextViewResumoPedido;
    private TextView mTextViewValorPedido;
    private CheckBox mCheckBoxViewBacon;
    private CheckBox mCheckBoxViewQueijo;
    private CheckBox mCheckBoxViewOrionRings;


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
        mEditTextViewNome = findViewById(R.id.EditTextViewNome);
        mTextViewResumoPedido = findViewById(R.id.textViewResumoPedido);
        mTextViewValorPedido = findViewById(R.id.textViewValorPedido);
        mCheckBoxViewBacon = findViewById(R.id.checkBoxViewBacon);
        mCheckBoxViewQueijo = findViewById(R.id.checkBoxViewQueijo);
        mCheckBoxViewOrionRings = findViewById(R.id.checkBoxViewOrionRings);
    }

    public void somar(View view) {
        String valorQuantidadeString = mTextViewQuantidade.getText().toString();

        int  valorQuantidade = Integer.parseInt(valorQuantidadeString);
        valorQuantidade++;

        mTextViewQuantidade.setText(String.valueOf(valorQuantidade));
    }

    public void subtrair(View view) {
        String valorQuantidadeString = mTextViewQuantidade.getText().toString();

        int  valorQuantidade = Integer.parseInt(valorQuantidadeString);

        if (valorQuantidade > 0 ) {
            valorQuantidade--;
        }

        mTextViewQuantidade.setText(String.valueOf(valorQuantidade));
    }


    public void enviarPedido(View view) {
        String nomeClienteString = mEditTextViewNome.getText().toString();
        String checkBoxBacon = mCheckBoxViewBacon.getText().toString();
        String checkBoxQueijo = mCheckBoxViewQueijo.getText().toString();
        String checkBoxOrionRings = mCheckBoxViewOrionRings.getText().toString();
        String valorQuantidadeString = mTextViewQuantidade.getText().toString();

        String resumoPedido = mTextViewResumoPedido.getText().toString();
        String valorPedido = mTextViewValorPedido.getText().toString();

        mTextViewResumoPedido.setText(String.valueOf(
                resumoPedido + "Nome Cliente: " + nomeClienteString + "Bacon?: " + checkBoxBacon + "Queijo?: " +
                        checkBoxQueijo + "Orion Rings?: " + checkBoxOrionRings + "Quantidade: " + valorQuantidadeString
        ));


    }

}