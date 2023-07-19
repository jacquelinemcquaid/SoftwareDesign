import java.time.LocalDate;
import java.util.Optional;

/*
Representation of a type of Event that can exist
 */
public interface Event {
    public String getName();
    public Location getLocation();
    public LocalDate getDate();
    public double getPrice();
    public int getNumTickets();

    //The event can also accept a visitor
    double acceptVisitor(Visitor visitor);
}
