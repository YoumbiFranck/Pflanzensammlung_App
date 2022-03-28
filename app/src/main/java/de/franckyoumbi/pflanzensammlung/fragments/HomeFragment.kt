package de.franckyoumbi.pflanzensammlung.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import de.franckyoumbi.pflanzensammlung.MainActivity
import de.franckyoumbi.pflanzensammlung.PlantModel
import de.franckyoumbi.pflanzensammlung.PlantRepository.Singleton.plantList
import de.franckyoumbi.pflanzensammlung.R
import de.franckyoumbi.pflanzensammlung.adapter.PlantAdapter
import de.franckyoumbi.pflanzensammlung.adapter.PlantitemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    // inject layout fragment_home
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        val horizontalRecyclerview = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerview.adapter = PlantAdapter(context,plantList.filter { !it.liked },R.layout.item_horizontal_plant)


        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        verticalRecyclerView.adapter = PlantAdapter(context,plantList,R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantitemDecoration())

        return view
    }
}