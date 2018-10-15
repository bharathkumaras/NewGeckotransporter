package com.example.sandy.newgeckotransporter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sqlite extends AppCompatActivity {
    EditText name,phone;
    Button save;
    customdatabase customdatabase;

    Cursor cursor;

    ListView list;
    int Itemposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        name = findViewById(R.id.Name);
        phone = findViewById(R.id.Phonenumber);
        list = findViewById(R.id.listview);
        save= findViewById(R.id.psave);
        customdatabase = new customdatabase(getApplicationContext());

        getvalues();
        registerForContextMenu(list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Itemposition = position;
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customdatabase.opendb();
                customdatabase.insertdadata(name.getText().toString(),phone.getText().toString());
                Toast.makeText(Sqlite.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                customdatabase.closedb();

              finish();

            }
        });
    }
    public void getvalues()
    {
        customdatabase.opendb();
        cursor = customdatabase.getcursor();
        Listview listview = new Listview();
        list.setAdapter(listview);
    }

    class Listview extends BaseAdapter
    {

        @Override
        public int getCount() {
            return cursor.getCount();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_list,null);
            TextView id_textView = (TextView)convertView.findViewById(R.id.id);
            TextView name_textView = (TextView)convertView.findViewById(R.id.CNAme);
            TextView phone_textView = (TextView)convertView.findViewById(R.id.CPhone);

            cursor.moveToPosition(position);
            id_textView.setText(cursor.getString(0));
            name_textView.setText(cursor.getString(1));
            phone_textView.setText(cursor.getString(2));
            return convertView;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getvalues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Delete all");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Sqlite.this);
        alert.setTitle("Delete All");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                customdatabase.delete();
                Toast.makeText(Sqlite.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                getvalues();
            }
        });
       alert.setNegativeButton("NO",null);
       alert.show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        cursor.moveToPosition(Itemposition);

        return super.onContextItemSelected(item);
    }
}
