import com.example.recipesapp.canadian.MealX
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Canadian(
    @SerialName("meals")
    val meals: List<MealX>
)