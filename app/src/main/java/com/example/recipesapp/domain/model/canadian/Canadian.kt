import com.example.recipesapp.domain.model.canadian.MealX
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Canadian(
    @SerialName("meals")
    val meals: List<MealX>
)