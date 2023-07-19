public interface Visitor {

    double visitConcert(Concert concert);
    double visitWorkshop(Workshop workshop);
    double visitGala(Gala gala);
    double visitScreening(Screening screening);


}
