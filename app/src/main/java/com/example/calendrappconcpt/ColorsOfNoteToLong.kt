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
    val primeryDark: Long,

    val liness: Long,
    val linessDark: Long,

    val margines: Long,
    val marginesDark: Long,

    val textColor: Long,
    val textColorDark: Long
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
            primery = 0xFFFFF8B0,       // soft pale yellow
            primeryDark = 0xFF2D2B1E,   // dark olive/mustard grey
            liness = 0xFF81D4FA,        // faded ink blue
            linessDark = 0xFF355E75,    // muted dark blue
            margines = 0xFFE57373,      // muted red
            marginesDark = 0xFF8C3030,  // deep crimson
            textColor = 0xFF212121,     // dark charcoal
            textColorDark = 0xFFFFF5C2  // soft pale yellow text
        )

        ColorsOfNotes.Red -> ColorPaterns( // Rose/Coral Note
            primery = 0xFFFFEBEE,       // warm pink/off-white
            primeryDark = 0xFF362024,   // deep burgundy slate
            liness = 0xFF880E4F,        // deep burgundy
            linessDark = 0xFF632839,    // muted berry
            margines = 0xFFFF8A65,      // soft coral
            marginesDark = 0xFF9E4830,  // burnt coral
            textColor = 0xFF37474F,     // deep blue-gray
            textColorDark = 0xFFFFD9DF  // pale rose text
        )

        ColorsOfNotes.Green -> ColorPaterns( // Mint/Sage Paper
            primery = 0xFFE8F5E9,       // light mint green
            primeryDark = 0xFF1C2E20,   // deep pine green
            liness = 0xFF2E7D32,        // forest green
            linessDark = 0xFF3D6343,    // muted sage
            margines = 0xFFF57C00,      // earthy orange
            marginesDark = 0xFF944B04,  // rust orange
            textColor = 0xFF1B5E20,     // very dark green
            textColorDark = 0xFFC8E6C9  // pale mint text
        )

        ColorsOfNotes.Blue -> ColorPaterns( // Ice Blue Note
            primery = 0xFFE3F2FD,       // soft ice blue
            primeryDark = 0xFF122336,   // deep midnight blue
            liness = 0xFF1565C0,        // deep navy
            linessDark = 0xFF294E75,    // faded slate blue
            margines = 0xFFFBC02D,      // mustard yellow
            marginesDark = 0xFF997314,  // dark gold
            textColor = 0xFF0D47A1,     // dark navy blue
            textColorDark = 0xFFBBDEFB  // pale ice blue text
        )

        ColorsOfNotes.Black -> ColorPaterns( // Dark Mode / Slate
            primery = 0xFF2C2C2C,       // slate gray (light mode version of black note)
            primeryDark = 0xFF121212,   // true dark mode pitch
            liness = 0xFF545454,        // medium gray
            linessDark = 0xFF2A2A2A,    // subtle dark gray
            margines = 0xFFD32F2F,      // bright crimson
            marginesDark = 0xFF8A1C1C,  // dark crimson
            textColor = 0xFFF5F5F5,     // almost white
            textColorDark = 0xFFBDBDBD  // muted gray text
        )

        ColorsOfNotes.White -> ColorPaterns( // Classic Composition Book
            primery = 0xFFFAFAFA,       // paper white
            primeryDark = 0xFF1E1E1E,   // charcoal gray
            liness = 0xFF90CAF9,        // light ruled blue
            linessDark = 0xFF314A5E,    // dim dark blue
            margines = 0xFFEF5350,      // classic standard red
            marginesDark = 0xFF8A2928,  // dark muted red
            textColor = 0xFF121212,     // near black
            textColorDark = 0xFFE0E0E0  // light gray text
        )

        ColorsOfNotes.Kraft -> ColorPaterns( // Recycled Cardboard
            primery = 0xFFD7CCC8,       // light brownish-gray
            primeryDark = 0xFF362C28,   // dark roast coffee
            liness = 0xFF8D6E63,        // medium brown
            linessDark = 0xFF5C4740,    // dim dark brown
            margines = 0xFF5D4037,      // dark espresso brown
            marginesDark = 0xFF261915,  // almost black-brown
            textColor = 0xFF3E2723,     // deep espresso text
            textColorDark = 0xFFD7C9C3  // dusty tan text
        )

        ColorsOfNotes.Lavender -> ColorPaterns( // Pastel Purple
            primery = 0xFFF3E5F5,       // soft lavender
            primeryDark = 0xFF281D30,   // deep plum/eggplant
            liness = 0xFF6A1B9A,        // deep purple
            linessDark = 0xFF503263,    // dusty amethyst
            margines = 0xFFFF4081,      // vivid pink
            marginesDark = 0xFF8A1E43,  // dark magenta
            textColor = 0xFF311B92,     // extremely dark purple
            textColorDark = 0xFFE1D1E8  // soft pale lavender text
        )

        ColorsOfNotes.Blueprint -> ColorPaterns( // Technical Blueprint
            primery = 0xFF1565C0,       // solid architectural blue
            primeryDark = 0xFF081830,   // night-mode blueprint (extremely dark navy)
            liness = 0xFF64B5F6,        // faint light blue
            linessDark = 0xFF1F416E,    // dark grid lines
            margines = 0xFFFFFFFF,      // pure white
            marginesDark = 0xFF8A9FB8,  // muted grey-blue
            textColor = 0xFFF5F5F5,     // soft off-white
            textColorDark = 0xFF4DD0E1  // bright cyan (like a glowing schematic)
        )

        ColorsOfNotes.Ivory -> ColorPaterns( // Premium Moleskine style
            primery = 0xFFFDF6E3,       // warm cream/ivory
            primeryDark = 0xFF262420,   // warm dark grey
            liness = 0xFFE0D8C3,        // very subtle beige line
            linessDark = 0xFF38352F,    // barely visible dark line
            margines = 0xFFD1C7B1,      // tan/khaki margin
            marginesDark = 0xFF1F1D1A,  // edge shadow/dark margin
            textColor = 0xFF2B2A27,     // soft black ink
            textColorDark = 0xFFE8E2D1  // cream text
        )
    }
}
