
public class OffByN implements CharacterComparator {


    int limitNumber;

    // constructor
    public OffByN(int N) {
        this.limitNumber =  N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.limitNumber;

    }




}
