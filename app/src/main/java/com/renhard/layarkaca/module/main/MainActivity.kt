package com.renhard.layarkaca.module.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.renhard.layarkaca.R
import com.renhard.layarkaca.databinding.ActivityMainBinding
import com.renhard.layarkaca.module.detail.view.DetailFilmActivity
import com.renhard.layarkaca.module.favorites.view.FavoritesFragment
import com.renhard.layarkaca.module.groups.controller.GroupsActivity
import com.renhard.layarkaca.module.groups.model.GroupModel
import com.renhard.layarkaca.module.groups.model.MemberModel
import com.renhard.layarkaca.module.movies.view.MoviesFragment
import com.renhard.layarkaca.module.tvshows.view.TVShowsFragment

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var binding: ActivityMainBinding
    private val mHandler = Handler(Looper.getMainLooper())

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.movie -> {
                binding.viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.tvshow -> {
                binding.viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                binding.viewpager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.app_name)
        supportActionBar?.elevation = 0f

        binding.bottomMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(MoviesFragment())
        adapter.addFragment(TVShowsFragment())
        adapter.addFragment(FavoritesFragment())
        binding.viewpager.offscreenPageLimit = 3
        binding.viewpager.adapter = adapter
        binding.viewpager.addOnPageChangeListener(this)

        binding.etSearch.doOnTextChanged { _, _, _, _ ->
            val currentText = binding.etSearch.text ?: ""
            binding.btnClearSearch.background = if(currentText.isEmpty()) ContextCompat.getDrawable(this, R.drawable.outline_search_black_24)
                                                else ContextCompat.getDrawable(this, R.drawable.outline_clear_black_24)

            mHandler.removeCallbacksAndMessages(null)
            mHandler.postDelayed({
                val moviesFragment = adapter.getItem(0) as? MoviesFragment
                val tvFragment = adapter.getItem(1) as? TVShowsFragment
                moviesFragment?.viewModel?.query?.value = currentText.toString()
                tvFragment?.viewModel?.query?.value = currentText.toString()
            }, 350)
        }

        binding.btnClearSearch.setOnClickListener {
            binding.etSearch.text.clear()
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val groups = menu.findItem(R.id.groups)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.groups -> {
                val member1 = MemberModel(1, "Budi Siswanto")
                val member2 = MemberModel(2, "Muh Aqsa R")
                val member3 = MemberModel(3, "Renhard Joki Hutajulu")
                val group = GroupModel(listOf(member1, member2, member3))
                val intent = Intent(this, GroupsActivity::class.java)
                intent.putExtra(GroupsActivity.EXTRA_GROUP, Gson().toJson(group))
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                binding.searchLayout.visibility = View.VISIBLE
            }
            1 -> {
                binding.searchLayout.visibility = View.VISIBLE
            }
            2 -> {
                binding.searchLayout.visibility = View.GONE
            }
        }
    }
}