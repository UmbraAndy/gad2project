package com.example.gads2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_learner.*
import kotlinx.android.synthetic.main.fragment_skill.*
import kotlinx.android.synthetic.main.learner_item.view.*


class LearnerFragment : Fragment(R.layout.fragment_learner) {

    private val viewModel:LeaderBoardViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learnerRcycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
    override fun onResume() {
        super.onResume()
        viewModel.loadLearnersLeaders().observe(viewLifecycleOwner, { listOfSkillIQLeaders ->
            listOfSkillIQLeaders?.let {
                if(it.isNotEmpty()) {
                    learnerRcycler.adapter = LearnerAdapter(it.sortedBy {learnItem ->
                        -learnItem.hours
                    })
                }
            } ?: handleError()
        })
    }

    private fun handleError() {
        TODO("Not yet implemented")
    }

    class LearnerAdapter(val leanerList:List<LearnerResponseItem>): RecyclerView.Adapter<LearnerAdapter.LeanerViewHolder>(){
        class LeanerViewHolder(learnerView: View): RecyclerView.ViewHolder(learnerView){
            fun bindItem(learnerResponseItem: LearnerResponseItem){
                itemView.learnerNameTxt.text = learnerResponseItem.name
                itemView.learnerSummaryTxt.text = "${learnerResponseItem.hours} learning hours, ${learnerResponseItem.country}"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeanerViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.learner_item,parent,false)
            return LeanerViewHolder(view)
        }

        override fun onBindViewHolder(holder: LeanerViewHolder, position: Int) {
            val item = leanerList[position]
            holder.bindItem(item)
        }

        override fun getItemCount(): Int = leanerList.size
    }
}