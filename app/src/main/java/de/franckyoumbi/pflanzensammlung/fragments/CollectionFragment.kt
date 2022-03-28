package de.franckyoumbi.pflanzensammlung.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.franckyoumbi.pflanzensammlung.MainActivity
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.plantList
import de.franckyoumbi.pflanzensammlung.R
import de.franckyoumbi.pflanzensammlung.adapter.PlantAdapter
import de.franckyoumbi.pflanzensammlung.adapter.PlantitemDecoration
import java.security.AccessControlContext

class CollectionFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)


        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = PlantAdapter(context, plantList.filter { it.liked }, R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlantitemDecoration())

        return view
    }
}