package com.example.calendrappconcpt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class ColorPaterns(
    val primery: Long,
    val liness: Long,
    val margines: Long,
    val textColor: Long
)

//fun ColorsOfNotes.toLong(): ColorPaterns {
//    return when (this) {
//        ColorsOfNotes.Yellow -> 0xFFFFF59D
//        ColorsOfNotes.Red -> 0xFFFF0000
//        ColorsOfNotes.Green -> 0xFF00FF00
//        ColorsOfNotes.Blue -> 0xFF0000FF
//        ColorsOfNotes.Black -> 0xFF000000
//        ColorsOfNotes.White -> 0xFFB4B4B4
//
//    }
//}





fun ColorsOfNotes.toColorPaterns(): ColorPaterns {
    return when (this) {

        ColorsOfNotes.Yellow -> ColorPaterns( // Classic Legal Pad
            primery = 0xFFFFF8B0,
            liness = 0xFF81D4FA,
            margines = 0xFFE57373,
            textColor = 0xFF212121  // dark charcoal for high contrast
        )

        ColorsOfNotes.Red -> ColorPaterns( // Rose/Coral Note
            primery = 0xFFFFEBEE,
            liness = 0xFF880E4F,
            margines = 0xFFFF8A65,
            textColor = 0xFF37474F  // deep blue-gray, softer than pure black
        )

        ColorsOfNotes.Green -> ColorPaterns( // Mint/Sage Paper
            primery = 0xFFE8F5E9,
            liness = 0xFF2E7D32,
            margines = 0xFFF57C00,
            textColor = 0xFF1B5E20  // very dark green for cohesive readability
        )

        ColorsOfNotes.Blue -> ColorPaterns( // Ice Blue Note
            primery = 0xFFE3F2FD,
            liness = 0xFF1565C0,
            margines = 0xFFFBC02D,
            textColor = 0xFF0D47A1  // dark navy blue
        )

        ColorsOfNotes.Black -> ColorPaterns( // Dark Mode / Slate
            primery = 0xFF1E1E1E,
            liness = 0xFF424242,
            margines = 0xFFC62828,
            textColor = 0xFFE0E0E0  // light gray (prevents eye strain in dark mode compared to pure white)
        )

        ColorsOfNotes.White -> ColorPaterns( // Classic Composition Book
            primery = 0xFFFAFAFA,
            liness = 0xFF90CAF9,
            margines = 0xFFEF5350,
            textColor = 0xFF121212  // near black for standard paper contrast
        )

        ColorsOfNotes.Kraft -> ColorPaterns( // Recycled Cardboard
            primery = 0xFFD7CCC8,
            liness = 0xFF8D6E63,
            margines = 0xFF5D4037,
            textColor = 0xFF3E2723  // deep espresso brown
        )

        ColorsOfNotes.Lavender -> ColorPaterns( // Pastel Purple
            primery = 0xFFF3E5F5,
            liness = 0xFF6A1B9A,
            margines = 0xFFFF4081,
            textColor = 0xFF311B92  // extremely dark purple
        )

        ColorsOfNotes.Blueprint -> ColorPaterns( // Technical Blueprint
            primery = 0xFF1565C0,
            liness = 0xFF64B5F6,
            margines = 0xFFFFFFFF,
            textColor = 0xFFF5F5F5  // soft off-white to stand out on solid blue
        )

        ColorsOfNotes.Midnight -> ColorPaterns( // High-contrast Dark Mode
            primery = 0xFF121212,
            liness = 0xFF333333,
            margines = 0xFF00E676,
            textColor = 0xFFBDBDBD  // muted gray, allows the margin to be the main focal point
        )
    }
}
