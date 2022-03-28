package de.franckyoumbi.pflanzensammlung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.franckyoumbi.pflanzensammlung.fragments.AddPlantFragment
import de.franckyoumbi.pflanzensammlung.fragments.CollectionFragment
import de.franckyoumbi.pflanzensammlung.fragments.HomeFragment

/**
 * @author Youmbi Franck
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_titel)


        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener setOnNavigationItemReselectedListener@{
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_titel)
                    return@setOnNavigationItemReselectedListener true
                }

                R.id.collection_page ->{
                    loadFragment(CollectionFragment(this), R.string.collection_page_titel)
                    return@setOnNavigationItemReselectedListener true
                }

                R.id.add_plant_page ->{
                    loadFragment(AddPlantFragment(this),R.string.add_plant_titel)
                    return@setOnNavigationItemReselectedListener true
                }
                else -> false
            }
        }







    }

    private fun loadFragment(fragment: Fragment, string: Int) {

        val repo = PlantRepository()

        findViewById<TextView>(R.id.page_titel).text = resources.getString(string)

        repo.updateData{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}