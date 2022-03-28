package de.franckyoumbi.pflanzensammlung.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.franckyoumbi.pflanzensammlung.*

class PlantAdapter(
     val context: MainActivity,
     private val platList: List<PlantModel>,
     private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    //box to store all the components to be checked
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDescription: TextView?= view.findViewById(R.id.description_item)
        val satrIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
         return ViewHolder(view)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {


         val currentPlant = platList[position]
         val repo = PlantRepository()
         Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)
         holder.plantName?.text = currentPlant.name
         holder.plantDescription?.text = currentPlant.description
         if(currentPlant.liked){
             holder.satrIcon.setImageResource(R.drawable.ic_star)

         }else{
             holder.satrIcon.setImageResource(R.drawable.ic_unstar)
         }
         holder.satrIcon.setOnClickListener{
             currentPlant.liked = !currentPlant.liked
             repo.updatePlant(currentPlant)
         }
         holder.itemView.setOnClickListener{
             PlantPopup(this, currentPlant).show()
         }

     }

     override fun getItemCount(): Int {

         return platList.size
     }
 }