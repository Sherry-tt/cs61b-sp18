import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("cac"));
        assertTrue(palindrome.isPalindrome("trart"));
    }

    @Test
    public void testisPalindromeOverloading() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", cc));
        assertTrue(palindrome.isPalindrome("fa", cc));
        assertFalse(palindrome.isPalindrome("fh", cc));

        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("bing", cc));
        assertFalse(palindrome.isPalindrome("aabaa", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
    }

}
