package com.example.period.myprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int Contactdir=0;
    public static final int Contactitem=1;
    public static final  String Authority="com.example.period.myprovider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper myhelper;

    static {
        uriMatcher =new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Authority,"Contact",Contactdir);
        uriMatcher.addURI(Authority,"Contact/#",Contactitem);
    }
    @Override
    public boolean onCreate(){
        myhelper=new MyDatabaseHelper(getContext(),"Contact.db",null,2);
//        myhelper.getInstance(getContext());
        return true;
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match((uri))){
            case Contactdir:
                return "vnd.android.cursor.dir/vnd.com.example.period.myprovider.Contact";
            case Contactitem:
                return "vnd.android.cursor.item/vnd.com.example.period.myprovider.Contact";
        }
        return  null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=myhelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case Contactdir:
                cursor=db.query("Contact",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Contactitem:
                String name=uri.getPathSegments().get(1);
                cursor=db.query("Contact",projection,"name=",new String[]{name},null,null,sortOrder);
                default:
                    break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet impleme" +
                "nted");
    }
}
