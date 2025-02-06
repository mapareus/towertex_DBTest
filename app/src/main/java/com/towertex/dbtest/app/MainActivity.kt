package com.towertex.dbtest.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.towertex.dbtest.R
import com.towertex.dbtest.students.room.StudentDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            db = StudentDatabase.buildDatabaseWithData(this@MainActivity)
        }
        val textView = TextView(this)
        textView.text = getString(R.string.app_name)
        setContentView(textView)
    }
}