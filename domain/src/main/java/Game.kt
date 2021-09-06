import java.time.LocalDateTime

data class Game (
    val id: Int,
    val title: String,
    val platforms: List<Platform>,
    val cover: Image,
    val isFavourite: Boolean
)

data class GameDetail (
    val id: Int,
    val title: String,
    val description: String,
    val released: LocalDateTime,
    val category: String,
    val genres: List<Genre>,
    val platforms: List<Platform>,
    val gameModes: List<Gamemode>,
    val cover: Image,
    val screenshots: List<Image>,
    val ageRatings: List<AgeRating>,
    val isFavourite: Boolean
)