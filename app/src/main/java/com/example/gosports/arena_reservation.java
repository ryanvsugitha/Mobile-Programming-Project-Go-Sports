package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class arena_reservation extends AppCompatActivity{

    int userID;
    ImageView arenaImg;
    TextView arenaName, arenaDesc;
    Button btn_submit;
    private DatabaseHandler db;
    private reservedarena reservedarena;

    String[] timeArray = {"Select Time",
                            "08.00 - 09.00",
                            "09.00 - 10.00",
                            "10.00 - 11.00",
                            "11.00 - 12.00",
                            "12.00 - 13.00",
                            "13.00 - 14.00",
                            "14.00 - 15.00",
                            "15.00 - 16.00",
                            "16.00 - 17.00",
                            "17.00 - 18.00",
                            "18.00 - 19.00",
                            "19.00 - 20.00",
                            "20.00 - 21.00"};

    String[] dateArray = {"Select Date",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""};

    String selectedDate = "";
    String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_reservation);

        arenaImg = findViewById(R.id.arenaImg);
        arenaName = findViewById(R.id.arenaName);
        arenaDesc = findViewById(R.id.arenaDesc);
        btn_submit = findViewById(R.id.btn_submit);

        userID = getIntent().getIntExtra("userID", 0);

        db = new DatabaseHandler(this);
        reservedarena = new reservedarena();

        Spinner date_picker = (Spinner) findViewById(R.id.date_picker);
        Spinner time_picker = (Spinner) findViewById(R.id.time_picker);

        date_picker.setOnItemSelectedListener(new dateSpinner());
        time_picker.setOnItemSelectedListener(new timeSpinner());

        String name = getIntent().getStringExtra("arenaName");
        String desc = getIntent().getStringExtra("arenaDesc");
        int img = getIntent().getIntExtra("arenaImg", 0);
        int userID = getIntent().getIntExtra("userID", 0);

        arenaImg.setImageResource(img);
        arenaName.setText(name);
        arenaDesc.setText(desc);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        Calendar date = Calendar.getInstance();

        try{
            date.setTime(sdf.parse(currentDate));
        }catch(ParseException e){
            e.printStackTrace();
        }

        for (int i = 1; i < 8; i++){
            date.add(Calendar.DAY_OF_MONTH, 1);
            String newDate = sdf.format(date.getTime());
            dateArray[i] = newDate;
        }

        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeArray);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_picker.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dateArray);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date_picker.setAdapter(aa2);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reservedarena.setBookerID(userID);
                reservedarena.setArenaname(name);
                reservedarena.setTime(selectedTime);
                reservedarena.setDate(selectedDate);

                if (selectedDate.equals("Select Date") || selectedTime.equals("Select Time")){
                    Toast.makeText(arena_reservation.this, "Please select Date or Time", Toast.LENGTH_SHORT).show();
                }
                else if (db.checkAvailable(reservedarena)){
                    Toast.makeText(arena_reservation.this, "Court not available at this Date and Time", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.createBooking(reservedarena);
                    db.close();
                    Toast.makeText(arena_reservation.this, "Court order success!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(arena_reservation.this, sportmenu.class);
                    i.putExtra("userID", userID);
                    startActivity(i);
                }
            }
        });
    }

    class dateSpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            selectedDate = parent.getItemAtPosition(pos).toString();
        }
        @Override
        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    class timeSpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            selectedTime = parent.getItemAtPosition(pos).toString();
        }
        @Override
        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
}