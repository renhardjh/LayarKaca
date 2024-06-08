package com.renhard.layarkaca.module.groups.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.renhard.layarkaca.R
import com.renhard.layarkaca.databinding.ActivityGroupsBinding
import com.renhard.layarkaca.module.groups.model.GroupModel

class GroupsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityGroupsBinding
    private lateinit var group: GroupModel

    companion object {
        const val EXTRA_GROUP = "extra_group"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.about_us)

        fetchModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun fetchModel() {
        group = Gson().fromJson(intent.getStringExtra(EXTRA_GROUP), GroupModel::class.java)
        binding.tvMember1.text = group.members.get(0).name
        binding.tvMember2.text = group.members.get(1).name
        binding.tvMember3.text = group.members.get(2).name
    }
}