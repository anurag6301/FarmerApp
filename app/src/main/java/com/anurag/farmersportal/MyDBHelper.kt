package com.anurag.farmersportal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.DisplayNameSources.EMAIL

class MyDBHelper(context: Context):SQLiteOpenHelper(context,"USERDB",null,1) {
    private val DROP_TABLE="DROP TABLE IF EXISTS USERDB"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE BUYERUSERS(USERSID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL,USERAADHAR TEXT NOT NULL UNIQUE,PHONE TEXT NOT NULL ,EMAIL TEXT NOT NULL UNIQUE,NICKNAME TEXT NOT NULL,PWD TEXT NOT NULL)")
        db?.execSQL("CREATE TABLE PRODUCERUSERS(USERSID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL,USERAADHAR TEXT NOT NULL UNIQUE,PHONE TEXT NOT NULL,EMAIL TEXT NOT NULL UNIQUE,NICKNAME TEXT NOT NULL,PWD TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}