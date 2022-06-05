class StreamChecker {
    
    private static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean withValue = false;
        public TrieNode node(char c) {
            return children[c - 'a'];
        }
        public TrieNode createNode(char c) {
            TrieNode node = new TrieNode();
            children[c - 'a'] = node;
            return node;
        }
    }
    
    private TrieNode root = new TrieNode();
    private char[] chars;
    private int n = 0;
    private int head = 0;
    private int tail = 0;
    private boolean empty = true;

    public StreamChecker(String[] words) {
        int length = 0;
        for (String word : words) {
            addToTrie(word);
            length = Math.max(length, word.length());
        }
        chars = new char[length];
        n = length;
    }
    
    private void addToTrie(String word) {
        TrieNode current = root;
        for(int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            TrieNode node = current.node(c);
            if (node == null) {
                node = current.createNode(c);
            }
            current = node;
        }
        current.withValue = true;
    }
    
    public boolean query(char letter) {
        if ((tail + 1) % n == head) {
            head = (head + 1) % n;
        }
        if (empty) {
            chars[head] = letter;
            empty = false;
        } else {
            tail = (tail + 1) % n;
            chars[tail] = letter;
        }
        int t = tail < head ? (tail + n) : tail;
        TrieNode current = root;
        for(int i = t; i >= head; i--) {
            char c = chars[i % n];
            TrieNode node = current.node(c);
            if (node == null) {
                return false;
            }
            if (node.withValue) {
                return true;
            }
            current = node;
        }
        return current.withValue;
    }
}