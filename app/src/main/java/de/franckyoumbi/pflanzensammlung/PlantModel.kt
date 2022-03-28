package de.franckyoumbi.pflanzensammlung

class PlantModel(
        val id: String = "plant0",
        val name: String = "Tulipe",
        val description: String = "description",
        val imageUrl : String = "https://cdn.pixabay.com/photo/2021/12/27/14/39/tulips-6897351_1280.jpg",
        val grow: String = "klein",
        val water: String = "viel",
        var liked: Boolean = false
)