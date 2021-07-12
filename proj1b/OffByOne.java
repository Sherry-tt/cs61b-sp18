import static org.junit.Assert.assertEquals;

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        if (x == y) {
            return false;
        }
        return true;
        //return Math.abs(x - y) == 1;
    }
}
