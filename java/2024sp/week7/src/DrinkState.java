public interface DrinkState {
    void order(Drink d);
    void making(Drink d);
    void maked(Drink d);
    void idle(Drink d);
}
