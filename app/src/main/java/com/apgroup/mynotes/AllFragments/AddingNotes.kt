package com.apgroup.mynotes.AllFragments

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apgroup.mynotes.DataBaseFile.FeedEntryInDatabase
import com.apgroup.mynotes.DataBaseFile.SqliteDataBaseClass
import com.apgroup.mynotes.R
import kotlinx.android.synthetic.main.fragment_adding_notes.*
import kotlinx.android.synthetic.main.notes_home_page.*


class AddingNotes : Fragment() {

    private lateinit var dbType: SQLiteDatabase

    private var id: Int? = null
    private lateinit var title: String
    private lateinit var body: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_adding_notes, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val database = SqliteDataBaseClass(activity!!.applicationContext)
        dbType = database.writableDatabase

        if (arguments != null) {
            id = (arguments?.getInt("ID") as? Int) as Int
            title = (arguments?.getString("title") as? String).toString()
            body = (arguments?.getString("body") as? String).toString()

            NotesTitle.setText(title)
            NotesBody.setText(body)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_view_of_adding_notes_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {

                if ((id != null) && (title != null) && (body != null)) {
                    val tle = NotesTitle.text.toString()
                    val bdy = NotesBody.text.toString()
                    val contentValues = ContentValues().apply {
                        put(FeedEntryInDatabase.FeedEntry.COLUMN_NAME_TITLE, ""+tle)
                        put(FeedEntryInDatabase.FeedEntry.COLUMN_NAME_BODY,""+bdy)
                    }
                    dbType!!.update(FeedEntryInDatabase.FeedEntry.TABLE_NAME,
                        contentValues,
                        "${BaseColumns._ID} = ?",
                        arrayOf(id.toString()))

                    Log.d("Update", "Updating"+id.toString())


                } else AddNotesIntoDB()
                return true
            }
            else -> {
                return false
            }
        }
        return false
    }

    private fun AddNotesIntoDB() {
        val title = NotesTitle.text.toString()
        val body = NotesBody.text.toString()

        if (body != null) {
            val Values = ContentValues().apply {
                this.put(FeedEntryInDatabase.FeedEntry.COLUMN_NAME_TITLE, title)
                this.put(FeedEntryInDatabase.FeedEntry.COLUMN_NAME_BODY, body)
            }
            dbType.insert(FeedEntryInDatabase.FeedEntry.TABLE_NAME, null, Values)
            NotesTitle.setText("")
            NotesBody.setText("")
            NotesTitle.requestFocus()

        } else {
            NotesBody.error = "Text Required"
            Toast.makeText(
                activity!!.applicationContext,
                "Values can't be Null",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        dbType.close()
    }
}