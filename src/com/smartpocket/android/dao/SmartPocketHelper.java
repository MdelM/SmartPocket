/**
 * 
 */
package com.smartpocket.android.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.smartpocket.android.model.Article;

/**
 * @author Mishka
 * database Helper
 *
 */
public class SmartPocketHelper  extends SQLiteOpenHelper{

	
	public static final String APP_BD_NAME="smartpocket.db";
	public static final int APP_DB_VERSION=1;
	public static final String APP_DB_TAG=SmartPocketHelper.class.getSimpleName();
	
	public static final String DB_TABLE_ARTICLE="article";
	public static final String DB_TABLE_DEPENSE="depense";
	
	public static final String key_id="_id";
	public static final String key_id_article="idarticle";
	public static final String key_name_article="nomarticle";
	
	
	public static Context context;
	public static SQLiteDatabase smartdb;
	
	public static final String APP_CREATE_TABLE_ARTICLE=
			"CREATE TABLE IF NOT EXISTS "+ DB_TABLE_ARTICLE + "("
			+ key_id + " integer primary key autoincrement,"
			+ key_id_article + " varchar not null,"
			+ key_name_article + " varchar not null);";
	
	public SmartPocketHelper(Context context) {
		super(context, APP_BD_NAME, null, APP_DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(APP_CREATE_TABLE_ARTICLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(APP_DB_TAG, "Upgrade the database from "+ oldVersion + " to version " + newVersion);
		db.execSQL("DROP TABLE IF EXIST " + DB_TABLE_ARTICLE);
	
	}
	
	public void addArticleInCache(Article article){
		smartdb=this.getWritableDatabase();
		ContentValues v=new ContentValues();
		v.put(key_id_article, article.getIdArticle());
		v.put(key_name_article, article.getNomArticle());
		
		smartdb.insert(DB_TABLE_ARTICLE, null, v);
		smartdb.close();
	}
	
	
	public List<Article> getAllArticleFromCache(){
		List<Article> articles=new ArrayList<Article>();
		String sql="SELECT * FROM "+ DB_TABLE_ARTICLE;
		smartdb=this.getWritableDatabase();
		Cursor c=smartdb.rawQuery(sql, null);
		if(c.moveToFirst()){
			do{
				Article A=new Article();
				/*A.setIdArticle(Integer.parseInt(c.getString(0)));*/
				A.setIdArticle(c.getString(1));
				A.setNomArticle(c.getString(2));
				articles.add(A);
			}while(c.moveToNext());
		}
		smartdb.close();
		return articles;
		
	}
	
	
	
	
	
	
	
}
