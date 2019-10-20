package com.example.emicalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button emiCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText P = (EditText)findViewById(R.id.principal);
        final EditText I = (EditText)findViewById(R.id.interest);
        final EditText Y = (EditText)findViewById(R.id.years);
        final EditText result = (EditText)findViewById(R.id.emi);
        final EditText TO = (EditText)findViewById(R.id.interest_total);

        emiCalc = (Button)findViewById(R.id.calc);
        emiCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String st1 = P.getText().toString();
                String st2 = I.getText().toString();
                String st3 = Y.getText().toString();

                if(TextUtils.isEmpty(st1)) {
                    P.setError("Amount Not Entered");
                    P.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(st2)) {
                    I.setError("Interest Not Entered");
                    I.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(st3)) {
                    Y.setError("Year Not Entered");
                    Y.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);

                float Principal = calPrinc(p);
                float Rate = calInt(i);
                float Months = calMnth(y);
                float Dvdnt = calDvnt(Rate, Months);
                float FD = calFinalDvdnt(Principal,Rate,Dvdnt);
                float D = calDivider(Dvdnt);
                float emi = calEmi(FD, D);
                float TA = calTa(emi, Months);
                float TI = calTi(TA,Principal);
                result.setText(String.valueOf(emi));
                TO.setText(String.valueOf((TI)));



            }

        });

    }
    public float calPrinc(float p){
        return (float)(p);


    }

    public float calInt(float i){
        return (float)(i/12/100);
    }
    public float calMnth(float y){
        return (float)(y*12);
    }
    public float calDvnt(float Rate,float Months){
        return (float)(Math.pow(1+Rate,Months));
    }
    public float calFinalDvdnt(float Principal,float Rate,float Dvdnt){
        return (float)(Principal * Rate * Dvdnt);
    }
    public float calDivider(float Dvdnt){
        return (float)(Dvdnt-1);
    }
    public float calEmi(float FD,float D){
        return (float)(FD/D);
    }
    public float calTa(float emi, float Months){
        return (float)(emi*Months);
    }
    public float calTi(float TA,float Principal){
        return (float)(TA- Principal);
    }
}
