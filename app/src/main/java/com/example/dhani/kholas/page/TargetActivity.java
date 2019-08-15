package com.example.dhani.kholas.page;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.dhani.kholas.R;
import com.example.dhani.kholas.adapter.AlarmReceiver;
import com.example.dhani.kholas.dao.entity.Bookmark;
import com.example.dhani.kholas.dao.service.BookmarkService;

public class TargetActivity extends Activity{
    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    EditText target;
    Button mulai;
    String jml_target;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        textAlarmPrompt = findViewById(R.id.alarmprompt);
        mulai = (Button) findViewById(R.id.bt_target);
        target = (EditText) findViewById(R.id.jml_hlm);

        buttonstartSetDialog = findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });

        mulai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jml_target = target.getText().toString();
//                Intent intent = new Intent(TargetActivity.this, MainActivity.class);
//                intent.putExtra("TARGET", jml_target);
//                startActivity(intent);
//                finish();

                Bookmark bookmark = new Bookmark();
                BookmarkService bookmarkService = new BookmarkService();

                bookmark.setPage(Integer.parseInt(jml_target));
                bookmark.setTarget(Integer.parseInt(jml_target));
                bookmarkService.createBookmark(bookmark);


            }
        });

    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(TargetActivity.this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
        timePickerDialog.setTitle("Set Reminder Time");

        timePickerDialog.show();

    }

    OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
                Log.i("hasil", " =<0");
            } else if (calSet.compareTo(calNow) > 0) {
                Log.i("hasil", " > 0");
            } else {
                Log.i("hasil", " else ");
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarmPrompt.setText("Reminder akan muncul pada : " + targetCal.getTime());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

}