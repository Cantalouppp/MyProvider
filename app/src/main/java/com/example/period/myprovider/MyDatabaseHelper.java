package com.example.period.myprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

/**
 * Created by Period on 2018/6/11.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
   private Context mcontext;
//    private static MyDatabaseHelper mInstance=null;
    public static final String Create_Contact="create table Contact("
            +"name String primary key ,"
            +"number String ,"
            +"sex String )";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory
    ,int version){
        super(context,name,factory,version);
        mcontext=context;
    }
//    static synchronized MyDatabaseHelper getInstance(Context context){
//        if(mInstance==null){
//            mInstance=new MyDatabaseHelper(context,"Contact.db",null,2);
//        }
//                return mInstance;
//
//    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_Contact);

        db.execSQL("insert into Contact values('A','54834248','女')");
        db.execSQL("insert into Contact values('B','13468975932','男')");
        db.execSQL("insert into Contact values('C','13467877887','男')");
        db.execSQL("insert into Contact values('D','15968788216','女')");
        db.execSQL("insert into Contact values('E','17578757894','男')");
        db.execSQL("insert into Contact values('F','17478351692','女')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop  table if exists Contact ");
    }

}
