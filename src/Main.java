import com.cources.ships.Place;
import com.cources.user.User;

public class Main {
    public static void main(String[] args) {
        Place seaField = new Place();
        seaField.drawSeaField();
        seaField.fillShipUser();
        seaField.placeOpponentShips();
        User.stepUser(Place.opponentBoard);
    }
}