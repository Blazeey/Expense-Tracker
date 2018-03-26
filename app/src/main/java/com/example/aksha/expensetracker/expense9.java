package com.example.aksha.expensetracker;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class expense9 extends AppCompatActivity {
    private PieChart mChart;
    String income,policy,fixeddeposit,gift,vacation,cable,electricity,grocery,newspaper,water,accessories,clothesA,clothesK;
    String cosmetics,food;
    PieChart pieChart;
    private float[] value = {25.3f,10.6f,44.32f,21.f,12.f};
    private String[] Name = {"Household","Shopping","Bank","Extras","Balance"};
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense9);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        pieChart = (PieChart) findViewById(R.id.chart1);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Expense chart");
        pieChart.setCenterTextSize(20f);
        pieChart.setDrawEntryLabels(true);


       // mChart.setCenterText(generateCenterSpannableText());

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    System.out.println("testing 123");
                    income = dataSnapshot.child("income").getValue().toString();
                    fixeddeposit = dataSnapshot.child("bank").child("fixed deposit").getValue().toString();
                    policy = dataSnapshot.child("bank").child("policy").getValue().toString();
                    gift = dataSnapshot.child("extras").child("gift").getValue().toString();
                    vacation = dataSnapshot.child("extras").child("vacation").getValue().toString();
                    cable = dataSnapshot.child("household").child("cable").getValue().toString();
                    electricity = dataSnapshot.child("household").child("electricity").getValue().toString();
                    grocery = dataSnapshot.child("household").child("grocery").getValue().toString();
                    newspaper = dataSnapshot.child("household").child("newspaper").getValue().toString();
                    water = dataSnapshot.child("household").child("water").getValue().toString();
                    accessories = dataSnapshot.child("shopping").child("accessories").getValue().toString();
                    clothesA = dataSnapshot.child("shopping").child("clothesA").getValue().toString();
                    clothesK = dataSnapshot.child("shopping").child("clothesK").getValue().toString();
                    cosmetics = dataSnapshot.child("shopping").child("cosmetics").getValue().toString();
                    food = dataSnapshot.child("shopping").child("food").getValue().toString();
                    System.out.println("all values"+income+fixeddeposit+policy+gift);
                    int income1,fixeddeposit1,policy1,gift1,vacation1,cable1,electricity1,grocery1,newspaper1,water1,accessories1,clothesA1,clothesK1,cosmetics1,food1;
                    income1 = Integer.parseInt(income);
                    fixeddeposit1 = Integer.parseInt(fixeddeposit);
                    policy1 = Integer.parseInt(policy);
                    gift1 = Integer.parseInt(gift);
                    vacation1 = Integer.parseInt(vacation);
                    cable1 = Integer.parseInt(cable);
                    electricity1 = Integer.parseInt(electricity);
                    grocery1 = Integer.parseInt(grocery);
                    newspaper1 = Integer.parseInt(newspaper);
                    water1 = Integer.parseInt(water);
                    accessories1 = Integer.parseInt(accessories);
                    clothesA1 = Integer.parseInt(clothesA);
                    clothesK1 = Integer.parseInt(clothesK);
                    cosmetics1 = Integer.parseInt(cosmetics);
                    food1 = Integer.parseInt(food);
                    float bal = income1 - (fixeddeposit1+policy1+gift1+vacation1+cable1+electricity1+grocery1+newspaper1+water1+accessories1+clothesA1+clothesK1+cosmetics1+food1);
                    if(flag==0){
                        float bank = fixeddeposit1+policy1;
                        float extras = gift1+vacation1;
                        float house = cable1+electricity1+grocery1+newspaper1+water1;
                        float shopping = accessories1+clothesA1+clothesK1+cosmetics1+food1;
                        System.out.println("testingggggggggg "+house+" "+shopping+" "+bank+" "+extras);
                        value[0]=(house/income1)*100;
                        value[1]=(shopping/income1)*100;
                        value[2]=(bank/income1)*100;
                        value[3]=(extras/income1)*100;
                        value[4]=(bal/income1)*100;
                        System.out.println("valuessssss");
                        for(int i=0;i<4;i++){
                            System.out.println(value[i]);
                        }
                        addDataSet();
                        flag=1;
                    }

                    System.out.println("balance testing"+bal);
                }catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(expense3.this,"database error",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addDataSet() {
        ArrayList<PieEntry> v = new ArrayList<>();
        ArrayList<String> N = new ArrayList<>();
        for (int i = 0; i < Name.length; i++) {
            N.add(Name[i]);
        }
        for (int i = 0; i < value.length; i++) {
            v.add(new PieEntry(value[i], N.get(i)));
        }

        PieDataSet pieDataSet = new PieDataSet(v, "Expense");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colours
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#3cba54"));
        colors.add(Color.parseColor("#4885ed"));
        colors.add(Color.parseColor("#db3236"));
        colors.add(Color.parseColor("#2E4053"));
        colors.add(Color.parseColor("#F1948A"));

        pieDataSet.setColors(colors);

        //Adding legends

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }
}
