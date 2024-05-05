package com.example.matchingpicture

import androidx.annotation.DrawableRes


//data class WordItem(val word: String, val manisi: String)

object Constants {

    val saykes = listOf(
        listOf(
            saykesModel(R.drawable.image1, R.drawable.image2),
            saykesModel(R.drawable.image3, R.drawable.image4),
            saykesModel(R.drawable.image5, R.drawable.image6),
            saykesModel(R.drawable.image7, R.drawable.image8)
        )
    )
}

// Tiyme
class saykesModel(
    @DrawableRes val variant1: Int,
    @DrawableRes val variant2: Int
)