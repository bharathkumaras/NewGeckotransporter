package com.example.sandy.newgeckotransporter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Sendmessage extends AppCompatActivity {
    EditText message;
    Button send;
    MesssageContentprovider messsageContentprovider;
    RecyclerView recyclerView;
    MessgeRecyclerview adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);
        message = findViewById(R.id.messages);
        send = findViewById(R.id.sendmessage);
        recyclerView = findViewById(R.id.recyclerview);
        messsageContentprovider = new MesssageContentprovider();
        Myasynictask myasynictask = new Myasynictask();
        myasynictask.execute();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Message",message.getText().toString());

                Uri cursor = getContentResolver().insert(MesssageContentprovider.uri, contentValues);

                Toast.makeText(getBaseContext(), cursor.toString(), Toast.LENGTH_LONG).show();
                message.setText("");


            }
        });
    }
    class Myasynictask extends AsyncTask<Void,Void,List<MessageModelclass>>
    {

        @Override
        protected List<MessageModelclass> doInBackground(Void... voids) {

            List<MessageModelclass> mm = new ArrayList<>();
            Cursor cursor = getContentResolver().query(MesssageContentprovider.uri,new String[]{Messagedatabase.Message},null,null,null);
            if (cursor!=null)
            {
                while (cursor.moveToNext())
                {
                    MessageModelclass m1 = new MessageModelclass();
                    m1.setMessage(cursor.getString(cursor.getColumnIndex(Messagedatabase.Message)));
                    mm.add(m1);

                }
            }

            return mm;
        }

        @Override
        protected void onPostExecute(List<MessageModelclass> messageModelclasses) {

            adapter = new MessgeRecyclerview(messageModelclasses,getApplicationContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        }
    }
}
