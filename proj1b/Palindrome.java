public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        while (!d.isEmpty()) {
            if (d.size() == 1) {
                return true;
            } else if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }

    /*return true, if the word is a palindrome according to the character comparison */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d = wordToDeque(word);
        while (!d.isEmpty()) {
            if (d.size() == 1) {
                return true;
            } else if (!cc.equalChars((char) d.removeFirst(), (char) d.removeLast())) {
                return false;
            }
        }
        return true;

    }
}
