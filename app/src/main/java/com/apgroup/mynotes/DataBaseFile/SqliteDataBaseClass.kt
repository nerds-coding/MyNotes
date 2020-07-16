package com.apgroup.mynotes.DataBaseFile

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class SqliteDataBaseClass(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE ${FeedEntryInDatabase.FeedEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "${FeedEntryInDatabase.FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                "${FeedEntryInDatabase.FeedEntry.COLUMN_NAME_BODY} TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyNotes.db"
    }

}

object FeedEntryInDatabase{
    object FeedEntry:BaseColumns{
        const val TABLE_NAME = "notes"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_BODY = "body"

    }
}