package com.leitordehoras;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "MainActivity";
    private TextToSpeech textToSpeech;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        handler.post(runnable);

        // Inicializa o TextToSpeech
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Libera os recursos do TextToSpeech
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Define o idioma do TextToSpeech como português do Brasil
            int result = textToSpeech.setLanguage(new Locale("pt", "BR"));
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "O idioma selecionado não é suportado pelo TextToSpeech");
            } else {
                Log.i(TAG, "TextToSpeech inicializado com sucesso");

                // Aguarda até o segundo atual ser igual a 0 e lê as horas
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        int second = calendar.get(Calendar.SECOND);
                        if (second == 0) {
                            String time = String.format("São %d horas e %d minutos", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                            textToSpeech.speak(time, TextToSpeech.QUEUE_FLUSH, null, null);
                        }

                        // Aguarda até o próximo segundo
                        handler.postDelayed(this, 1000 - (System.currentTimeMillis() % 1000));
                    }
                }, 1000 - (System.currentTimeMillis() % 1000));
            }
        } else {
            Log.e(TAG, "Falha ao inicializar o TextToSpeech");
        }
    }

    private final Runnable runnable = new Runnable() {
        public void run() {
            atualizarHorario();
            handler.postDelayed(this, 1000); // atualiza a cada segundo
        }
    };

    private void atualizarHorario() {
        TextView horarioTextView = findViewById(R.id.horarioTextView);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String horaAtual = sdf.format(new Date());
        horarioTextView.setText(horaAtual);
    }
}