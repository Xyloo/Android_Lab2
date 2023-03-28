package pl.pollub.s95408.lab2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarksAdapter(val marks: ArrayList<MarksModel>) : RecyclerView.Adapter<MarksAdapter.MarksViewHolder>() {

    class MarksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mark: MarksModel) {
            itemView.findViewById<TextView>(R.id.marksText).text = mark.subject
            itemView.id = mark.subject.hashCode()
            when (mark.mark)
            {
                2 -> itemView.findViewById<RadioButton>(R.id.marksRadioButton2).isChecked = true
                3 -> itemView.findViewById<RadioButton>(R.id.marksRadioButton3).isChecked = true
                4 -> itemView.findViewById<RadioButton>(R.id.marksRadioButton4).isChecked = true
                5 -> itemView.findViewById<RadioButton>(R.id.marksRadioButton5).isChecked = true
            }
            itemView.findViewById<RadioButton>(R.id.marksRadioButton2).setOnClickListener {
                mark.mark = 2
            }
            itemView.findViewById<RadioButton>(R.id.marksRadioButton3).setOnClickListener {
                mark.mark = 3
            }
            itemView.findViewById<RadioButton>(R.id.marksRadioButton4).setOnClickListener {
                mark.mark = 4
            }
            itemView.findViewById<RadioButton>(R.id.marksRadioButton5).setOnClickListener {
                mark.mark = 5
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.marks_row, parent, false)
        return MarksViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarksViewHolder, position: Int) {
        holder.bind(marks[position])
    }

    override fun getItemCount(): Int {
        return marks.size
    }
}
