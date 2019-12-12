package com.example.yujian.music;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;



public class MyDatabaseHelper extends SQLiteOpenHelper{
    private static final String mName = "Music.db";
    private static final int mVersion = 1;
    private Context mContext;
    private static final String CREATETBLMUSIC="create table tblMusic("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"singer text,"
            +"album text,"
            +"path text,"
            +"sound integer)";

    public MyDatabaseHelper(Context context) {
        super(context, mName, null, mVersion);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETBLMUSIC);
        //0代表了标准品质，1代表了高品质，2代表了无损品质
        db.execSQL("insert into tblMusic(name,singer,album,path,sound) values('DreamItPossible','张靓颖','华为手机歌曲铃声','/storage/emulated/0/Dream_It_Possible.mp3',0)");
        db.execSQL("insert into tblMusic(name,singer,album,path,sound) values('Encounter','Jason','PhoneMusic','/storage/emulated/0/Encounter.mp3',0)");
        db.execSQL("insert into tblMusic(name,singer,album,path,sound) values('Polonaise','网络歌手','未知','/storage/emulated/0/Polonaise.mp3',0)");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Music> query(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("tblMusic",null,null,null,null,null,null);
        List<Music> musicList = new LinkedList<Music>();
        while(cursor.moveToNext()){
            Music music = new Music();
            music.setId(cursor.getInt(cursor.getColumnIndex("id")));
            music.setName(cursor.getString(cursor.getColumnIndex("name")));
            music.setSinger(cursor.getString(cursor.getColumnIndex("singer")));
            music.setAlbum(cursor.getString(cursor.getColumnIndex("album")));
            music.setPath(cursor.getString(cursor.getColumnIndex("path")));
            music.setSound(cursor.getInt(cursor.getColumnIndex("sound")));
            musicList.add(music);
        }
        return musicList;
    }

    public void add(Music music){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="insert into tblMusic(name,singer,album,path,sound) values('"
                +music.getName()
                +"','"
                +music.getSinger()
                +"','"
                +music.getAlbum()
                +"','"
                +music.getPath()
                +"',"
                +music.getSound()
                +")";
        db.execSQL(sql);
    }

    public void update(Music music){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="update tblMusic set name = '"
                +music.getName()
                +"',singer = '"
                +music.getSinger()
                +"',album = '"
                +music.getAlbum()
                +"',path = '"
                +music.getPath()
                +"',sound = "
                +music.getSound()
                +" where id = "
                +music.getId();
        db.execSQL(sql);
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql="delete from tblMusic where id = "+id;
        db.execSQL(sql);
    }
}
