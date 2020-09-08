package com.example.gads2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_skill.*
import kotlinx.android.synthetic.main.skill_iq_item.view.*

class SkillFragment : Fragment(R.layout.fragment_skill) {

    private val viewModel: LeaderBoardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skillerRcycler.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadSkillIQLeaders()
            .observe(viewLifecycleOwner, { listOfLearnerLeaders ->
                listOfLearnerLeaders?.let { skillIQList ->
                    skillerRcycler.adapter = SkillIQAdapter(skillIQList.sortedBy { skillItem ->
                        -skillItem.score
                    })
                } ?: handleError()
            })
    }

    private fun handleError() {
        TODO("Not yet implemented")
    }

    class SkillIQAdapter(private val skillList: List<SkillIQResponseItem>) :
        RecyclerView.Adapter<SkillIQAdapter.SkillIQViewHolder>() {
        class SkillIQViewHolder(skillIQView: View) : RecyclerView.ViewHolder(skillIQView) {
            fun bindItem(learnerResponseItem: SkillIQResponseItem) {
                itemView.skillerNameTxt.text = learnerResponseItem.name
                itemView.skillerSummaryTxt.text =
                    "${learnerResponseItem.score} skill IQ Score, ${learnerResponseItem.country} "
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.skill_iq_item, parent, false)
            return SkillIQViewHolder(view)
        }

        override fun onBindViewHolder(holder: SkillIQViewHolder, position: Int) {
            val item = skillList[position]
            holder.bindItem(item)
        }

        override fun getItemCount() = skillList.size
    }
}