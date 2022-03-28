package de.franckyoumbi.pflanzensammlung

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.databaseRef
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.downloadUri
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.plantList
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.storageReference
import java.net.URI
import java.util.*
import javax.security.auth.callback.Callback

  //class that manages the database




class PlantRepository {

    object Singleton{
        //give the link to access the bucket
        private val BUCKET_URL: String = "gs://pflanzensammlung.appspot.com"

        //connect to storage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)


        // connect to the "plants" reference
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")


        // create a list that will contain our plants
        val plantList = arrayListOf<PlantModel>()

        //contain the link of the current image
        var downloadUri: Uri? = null

    }

    fun updateData(callback: () -> Unit){
        // ingest data from database Ref --> list of plants
        databaseRef.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // remove old plants
                plantList.clear()

                // collect the list
                for(ds in snapshot.children){
                    // build a plant object

                    val plant = ds.getValue(PlantModel::class.java)

                    // check that the plant is not null
                    if(plant != null){
                        //ajouter la plante à notre liste

                        plantList.add(plant)
                    }
                }
                // operate the collback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    //create a function to send files to storage
    fun uploadImage(file: Uri, callback: () -> Unit){
        //check that this file is not null
        if (file != null){
            val fileName = UUID.randomUUID().toString() + ".jpg"

            //creating a reference for the image
            val ref = storageReference.child(fileName)

            //send the file in a send task
            val uploadTask = ref.putFile(file)

           //---
            callback()
        }
    }

    //update plant object in database
    fun updatePlant(plant: PlantModel){
        databaseRef.child(plant.id).setValue(plant)
    }

    //insert a new plant in the database
    fun insertPlant(plant: PlantModel){
        databaseRef.child(plant.id).setValue(plant)
    }

    //delete a plant from the database
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue() //removeValue() permet de supprimer de la base de d
}