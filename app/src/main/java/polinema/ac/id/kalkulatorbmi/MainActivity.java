package polinema.ac.id.kalkulatorbmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText bobot;
    private EditText tinggi;
    private EditText umur;
    private RadioGroup rgGender;
    private RadioButton rbGender;

    int tampilBerat;
    int tampilTinggi;
    int tampilUmur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bobot       = (EditText) findViewById(R.id.inputBerat);
        tinggi      = (EditText) findViewById(R.id.inputTinggi);
        umur        = (EditText) findViewById(R.id.inputUmur);
        rgGender    = (RadioGroup) findViewById(R.id.pilihGender);
        Integer pilihGender = rgGender.getCheckedRadioButtonId();
        rbGender    = (RadioButton) findViewById(pilihGender);
    }

    // Input Berat
    public void kurangBerat(View view) {
        tampilBerat = tampilBerat - 1;
        displayBerat(tampilBerat);
    }

    public void tambahBerat(View view) {
        tampilBerat = tampilBerat + 1;
        displayBerat(tampilBerat);
    }

    private void displayBerat(int tampilBerat) {
        bobot.setText("" + tampilBerat);
    }

    // Input Tinggi
    public void kurangTinggi(View view) {
        tampilTinggi = tampilTinggi - 1;
        displayTinggi(tampilTinggi);
    }

    public void tambahTinggi(View view) {
        tampilTinggi = tampilTinggi + 1;
        displayTinggi(tampilTinggi);
    }

    private void displayTinggi(int tampilTinggi) {
        tinggi.setText("" + tampilTinggi);
    }

    // Input Umur
    public void kurangUmur(View view) {
        tampilUmur = tampilUmur - 1;
        displayUmur(tampilUmur);
    }

    public void tambahUmur(View view) {
        tampilUmur = tampilUmur + 1;
        displayUmur(tampilUmur);
    }

    private void displayUmur(int tampilUmur) {
        umur.setText("" + tampilUmur);
    }

    // Proses Calculate
    public void calculateBMI(View view) {
        String tinggiStr = tinggi.getText().toString();
        String bobotStr  = bobot.getText().toString();

        if (tinggiStr != null && !"".equals(tinggiStr) && bobotStr != null && !"".equals(bobotStr)) {
            float tinggiValue = Float.parseFloat(tinggiStr) / 100;
            float bobotValue  = Float.parseFloat(bobotStr);

            float bmi = bobotValue / (tinggiValue * tinggiValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";
        String infoUmur = umur.getText().toString();

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.terlalu_sangat_kurus);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.sangat_kurus);
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.kurus);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.gemuk);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.cukup_gemuk);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.sangat_gemuk);
        } else {
            bmiLabel = getString(R.string.terlalu_sangat_gemuk);
        }

        bmiLabel = "Jenis kelamin: " + rbGender.getText() + "\n\n"
                + "Hasil Perhitungan BMI: " + bmi + "\n"
                + "Kategori: " + bmiLabel + " \n\n"
                + "Umur: " + infoUmur;

        AlertDialog.Builder tampilBMI = new AlertDialog.Builder(this);
        tampilBMI.setTitle("Hasil Perhitungan BMI");
        tampilBMI.setMessage(bmiLabel).setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = tampilBMI.create();
        alertDialog.show();

    }
}