package pl.pollub.s95408.lab2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import java.math.BigDecimal
import java.math.RoundingMode

class MarksActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var marksAdapter: MarksAdapter
    private var state: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marks2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val marksRecyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.marksRecyclerView)
        marksRecyclerView.layoutManager = linearLayoutManager

        val rowCount = intent.extras?.getInt("marks") ?: 0
        val subjects = resources.getStringArray(R.array.subjects)
        var marks = ArrayList<MarksModel>()
        for (i in 0 until rowCount) {
            marks.add(MarksModel(subjects[i]))
        }
        val marksAdapter = MarksAdapter(marks)
        marksRecyclerView.adapter = marksAdapter

        val saveButton = findViewById<android.widget.Button>(R.id.sredniaButton)
        saveButton.setOnClickListener() {
            var sum : Double = 0.0
            var count = 0
            for (i in 0 until rowCount) {
                val mark = marksAdapter.marks[i].mark
                if (mark != 0) {
                    sum += mark
                    count++
                }
            }
            val average = BigDecimal(sum / count).setScale(2, RoundingMode.HALF_EVEN)
            Toast.makeText(this, "Średnia ocen: $average", Toast.LENGTH_SHORT).show()
            val backIntent = android.content.Intent(this, MainActivity::class.java)
            backIntent.putExtra("average", average.toString())
            backIntent.putExtra("marksAmount", rowCount.toString())
            backIntent.putExtra("name", intent.extras?.getString("name"))
            backIntent.putExtra("surname", intent.extras?.getString("surname"))
            setResult(RESULT_OK, backIntent)
            startActivity(backIntent)
        }
    }
    override fun onPause()
    {
        super.onPause()
        state = linearLayoutManager.onSaveInstanceState()!!
    }
    override fun onResume()
    {
        super.onResume()
        linearLayoutManager.onRestoreInstanceState(state)
    }
}