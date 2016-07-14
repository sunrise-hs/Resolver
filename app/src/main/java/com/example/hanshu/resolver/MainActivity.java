package com.example.hanshu.resolver;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button b1;
    ListView lv;
    List<Person> list_infor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b_query);
        lv=(ListView)findViewById(R.id.listView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path="content://com.example.hanshu.first.contentResolver/query";
                Uri uri=Uri.parse(path);
                ContentResolver resolver=getContentResolver();
                Cursor cursor=resolver.query(uri,null,null,null,null);
                if(cursor==null){
                    System.out.println("游标为空0000000000000");
                    return;
                }
                list_infor=new ArrayList<Person>();
                while(cursor.moveToNext()){
//
//                    int id=cursor.getInt(cursor.getColumnIndex("id"));
//                    String name=cursor.getString(1);
//                    String number=cursor.getString(2);
//                    System.out.println("编号"+id+"   姓名"+name+"  电话"+number);
                    Person infor=new Person();
                    int id=cursor.getInt(cursor.getColumnIndex("id"));
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String number=cursor.getString(cursor.getColumnIndex("number"));
                    infor.setId(id);
                    infor.setName(name);
                    infor.setNumber(number);
                    list_infor.add(infor);
                }
                cursor.close();
                lv.setAdapter(new MyAdapter());
            }
        });
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list_infor.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv=new TextView(MainActivity.this);
            tv.setText(list_infor.get(i).toString());
            return tv;
        }
    }
}
