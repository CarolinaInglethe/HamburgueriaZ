package com.example.hamburgueriaz;

import android.content.Intent;
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

    private TextView mTextViewQuantidade, mTextViewResumoPedido, mTextViewValorPedido;
    private EditText mEditTextViewNome;
    private CheckBox mCheckBoxViewBacon, mCheckBoxViewQueijo, mCheckBoxViewOrionRings;



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
        String quantidade = mTextViewQuantidade.getText().toString();
        int  quantidadeNumber = Integer.parseInt(quantidade);

        quantidadeNumber++;
        mTextViewQuantidade.setText(String.valueOf(quantidadeNumber));
    }


    public void subtrair(View view) {
        String quantidade = mTextViewQuantidade.getText().toString();
        int  quantidadeNumber = Integer.parseInt(quantidade);

        if (quantidadeNumber > 0 ) {
            quantidadeNumber--;
        }

        mTextViewQuantidade.setText(String.valueOf(quantidadeNumber));
    }


    public int somaValorTotalPedido(Boolean bacon,Boolean queijo, Boolean orionRings, int quantidade) {
        int somaTotal = 0;

        if (quantidade > 1) {
            somaTotal = somaTotal + 20 * quantidade;  // soma valor do hamburguer e adicionais multiplicando pela quantidade
            if (bacon) {
                somaTotal = somaTotal + 2 * quantidade;
            }
            if (queijo) {
                somaTotal = somaTotal + 2 * quantidade;
            }
            if (orionRings) {
                somaTotal = somaTotal + 3 * quantidade;
            }
        } else {
            somaTotal = somaTotal + 20; // apenas soma o valor do hamburguer
            if (bacon) {
                somaTotal = somaTotal + 2;
            }
            if (queijo) {
                somaTotal = somaTotal + 2;
            }
            if (orionRings) {
                somaTotal = somaTotal + 3;
            }
        }

        return somaTotal;
    }


    public void funcaoEnviarEmail( String textoResumoPedido, int valorTotalPedido){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, String.valueOf(textoResumoPedido + "\r\nR$ " + valorTotalPedido));
        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(textoResumoPedido + "\r\nR$ " + valorTotalPedido));
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Resumo do Pedido"));
    }


    public void enviarPedido(View view) {
        String nome = mEditTextViewNome.getText().toString();
        Boolean checkBoxBacon = mCheckBoxViewBacon.isChecked();
        Boolean checkBoxQueijo = mCheckBoxViewQueijo.isChecked();
        Boolean checkBoxOrionRings = mCheckBoxViewOrionRings.isChecked();
        int quantidade = Integer.parseInt(mTextViewQuantidade.getText().toString());

        if (quantidade > 0) {
            int valorTotalPedido = somaValorTotalPedido(checkBoxBacon, checkBoxQueijo, checkBoxOrionRings, quantidade); // função calcular Valor Total R$
            mTextViewValorPedido.setText(String.valueOf("R$ " + valorTotalPedido));

            String textoResumoPedido = "RESUMO DO PEDIDO:" + "\r\n --------------- " + "\r\n Nome Cliente:  " + nome + "\r\n Bacon: " + checkBoxBacon  + "\r\n Queijo: " +
                    checkBoxQueijo + " \r\n Orion Rings: " + checkBoxOrionRings + "\r\n Quantidade: " + quantidade + "\r\n --------------- ";

            mTextViewResumoPedido.setText(String.valueOf(""));  // Limpar Resumo
            mTextViewResumoPedido.setText(String.valueOf(textoResumoPedido));

            funcaoEnviarEmail(textoResumoPedido, valorTotalPedido);

        }
    }

}