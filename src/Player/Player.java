package Player;

public class Player {
    private boolean _color; // 0 = black, 1 = white
    private int _remainingPaws;

    public Player(boolean color){
        _color = color;
        _remainingPaws = 32;
    }

    public int getRemainingPaws(){
        return  _remainingPaws;
    }
    public void setRemainingPaws(int amount){
        if(amount >= 0 && amount < 33){
            this._remainingPaws = amount;
        }
    }

    public boolean getColor() {
        return _color;
    }
}
