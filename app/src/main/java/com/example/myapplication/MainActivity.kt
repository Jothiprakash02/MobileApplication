package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

     lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Toolbar / ActionBar title
        supportActionBar?.title = "XYZ"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Drawer
        drawerLayout = findViewById(R.id.draw)
        navigationView = findViewById(R.id.nav)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        // Spinner
        val sview = findViewById<Spinner>(R.id.sview)

        val arr1 = arrayOf(
            arrayOf("a", "1"),
            arrayOf("b", "2")
        )

        val arr = arr1.flatMap { it.toList() }

        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arr
        )

        sview.adapter = adapter1

        // GridView
        val gview = findViewById<GridView>(R.id.gview)

        val adapter0 = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arr
        )

        gview.adapter = adapter0

        gview.setOnItemClickListener { _, _, position, _ ->
            val x = arr[position]

            Toast.makeText(
                this,
                x,
                Toast.LENGTH_SHORT
            ).show()
        }

        // Button
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val textview = findViewById<TextView>(R.id.dy)

        button1.setOnClickListener {
            val intent= Intent(this, MyService::class.java)
            startService(intent)
        }
        button2.setOnClickListener {
            val intent= Intent(this, MyService::class.java)
            stopService(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // IMPORTANT:
        // Let ActionBarDrawerToggle handle the hamburger button
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return when (item.itemId) {

            R.id.X -> {
                Toast.makeText(
                    this,
                    " Chooses me",
                    Toast.LENGTH_SHORT
                ).show()

                true
            }

            R.id.Y -> {
                Toast.makeText(
                    this,
                    " Chooses me too",
                    Toast.LENGTH_SHORT
                ).show()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.mainNav -> {
                Toast.makeText(
                    this,
                    " Chooses me",
                    Toast.LENGTH_SHORT
                ).show()
            }

            R.id.logoutNav -> {
                Toast.makeText(
                    this,
                    "Chooses me too",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        drawerLayout.closeDrawers()

        return true
    }
}