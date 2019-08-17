package com.example.dhani.kholas.page;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.adapter.AlarmReceiver;
import com.example.dhani.kholas.base.ObjectBox;
import com.example.dhani.kholas.dao.entity.Bookmark;
import com.example.dhani.kholas.dao.service.BookmarkService;
import com.example.dhani.kholas.utils.Utils;

public class TargetActivity extends Activity{
    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    EditText target;
    EditText start;
    Button mulai;
    String jml_target;
    String start_halaman;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;

    Bookmark bookmark;
    BookmarkService bookmarkService;
    boolean status;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        bookmark  = new Bookmark();
        bookmarkService = new BookmarkService();

        textAlarmPrompt = findViewById(R.id.alarmprompt);
        mulai = (Button) findViewById(R.id.bt_target);
        target = (EditText) findViewById(R.id.jml_hlm);
        start = (EditText) findViewById(R.id.start_hlm);

        buttonstartSetDialog = findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });

        findBookmark();

        if (status == true){
            mulai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkService.deleteBookmark(bookmark);
                    Toast.makeText(getApplicationContext(),"Reset Success",Toast.LENGTH_SHORT).show();
                    start.setEnabled(true);
                    target.setEnabled(true);
                    buttonstartSetDialog.setEnabled(true);

                    start.setText("");
                    target.setText("");
                    mulai.setText("Save");
                    textAlarmPrompt.setText("");
                }
            });
        }else {
            mulai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jml_target = target.getText().toString();
                    start_halaman = start.getText().toString();

                    bookmark.setPage(Integer.parseInt(start_halaman));
                    bookmark.setTarget(Integer.parseInt(jml_target));
                    bookmark.setStatus(true);
                    bookmarkService.createBookmark(bookmark);

                    Intent home = new Intent(TargetActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }
            });
        }

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
            calNow.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calNow.set(Calendar.MINUTE, minute);
            calNow.set(Calendar.SECOND, 0);
            calNow.set(Calendar.MILLISECOND, 0);
            bookmark.setCreatedDate(calNow.getTime().toString());

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
                Log.i("hasil", " else bookmarkService = new BookmarkService();");
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarmPrompt.setText("Reminder akan muncul pada : " + targetCal.getTime());
        bookmark.setTime(targetCal.getTime().toString());

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    public void findBookmark(){
        //get database bookmark by status true
        List<Bookmark> bookmarkList = bookmarkService.findBookmark();
        if(bookmarkList.size() > 0){
            for (int i=0;i < bookmarkList.size() ; i++){
                if (bookmarkList.get(i).getTime() != null){

                    //set value on database
                    textAlarmPrompt.setText(bookmarkList.get(0).getTime());
                    start.setText(Integer.toString(bookmarkList.get(0).getPage()));
                    target.setText(Integer.toString(bookmarkList.get(0).getTarget()));

                    //disable edit
                    start.setEnabled(false);
                    target.setEnabled(false);
                    buttonstartSetDialog.setEnabled(false);

                    mulai.setText("Reset");
                    status = true;
                }
            }
        }
    }

}