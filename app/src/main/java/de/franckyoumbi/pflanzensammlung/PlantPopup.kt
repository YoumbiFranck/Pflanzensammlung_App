package de.franckyoumbi.pflanzensammlung

import android.app.Dialog
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import de.franckyoumbi.pflanzensammlung.adapter.PlantAdapter

class PlantPopup(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plant_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView){

        if (currentPlant.liked){
            button.setImageResource(R.drawable.ic_star)
        }else{
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupStarButton() {
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        //interact
        starButton.setOnClickListener {
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            //delete the plant from the database

            val repo = PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            //close the popup
            dismiss()
        }
    }

    private fun setupComponents() {

        val plantimage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantimage)


        findViewById<TextView>(R.id.poppup_plant_name).text = currentPlant.name


        findViewById<TextView>(R.id.poppup_description_subtitel).text = currentPlant.description


        findViewById<TextView>(R.id.poppup_grow_subtitel).text = currentPlant.grow


        findViewById<TextView>(R.id.poppup_water_subtitel).text = currentPlant.water

    }
}