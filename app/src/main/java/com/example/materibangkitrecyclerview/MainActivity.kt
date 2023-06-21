package com.example.materibangkitrecyclerview;
import ListDriverAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvDrivers: RecyclerView
    private val list = ArrayList<Driver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDrivers = findViewById(R.id.rv_drivers)
        rvDrivers.setHasFixedSize(true)
        list.addAll(getListHeroes())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListHeroes(): ArrayList<Driver> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataTeam = resources.getStringArray(R.array.data_team)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Driver>()
        for (i in dataName.indices) {
            val hero = Driver(dataName[i], dataDescription[i], dataTeam[i],  dataPhoto.getResourceId(i,-1))
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvDrivers.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListDriverAdapter(list)
        rvDrivers.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListDriverAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Driver) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(driver: Driver) {
        val driver = Driver(driver.name, driver.description, driver.team, driver.photo)
        val detailActivity = Intent(this@MainActivity, DetailActivity::class.java)
        detailActivity.putExtra(DetailActivity.EXTRA_DRIVER, driver)
        startActivity(detailActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvDrivers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvDrivers.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val toAboutPage = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(toAboutPage)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


