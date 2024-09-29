/*
# 432. All O`one Data Structure


Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

 
Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"
 

Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
*/






class Node {
    public int count;
    public Set<String> keys = new HashSet<>();
    public Node prev = null;
    public Node next = null;

    public Node(int count) {
        this.count = count;
    }

    public Node(int count, String key) {
        this.count = count;
        keys.add(key);
    }
}

class AllOne {
    public AllOne() {
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (keyToNode.containsKey(key))
            incrementExistingKey(key);
        else
            addNewKey(key);
    }

    public void dec(String key) {
        // It is guaranteed that key exists in the data structure before the
        // decrement.
        decrementExistingKey(key);
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private Map<String, Node> keyToNode = new HashMap<>();
    private Node head = new Node(0);
    private Node tail = new Node(0);

    // Adds a new node with frequency 1.
    private void addNewKey(final String key) {
        if (head.next.count == 1)
            head.next.keys.add(key);
        else
            insertAfter(head, new Node(1, key));
        keyToNode.put(key, head.next);
    }

    // Increments the frequency of the key by 1.
    private void incrementExistingKey(final String key) {
        Node node = keyToNode.get(key);
        node.keys.remove(key);

        if (node.next == tail || node.next.count > node.count + 1)
            insertAfter(node, new Node(node.count + 1));

        node.next.keys.add(key);
        keyToNode.put(key, node.next);

        if (node.keys.isEmpty())
            remove(node);
    }

    // Decrements the count of the key by 1.
    private void decrementExistingKey(final String key) {
        Node node = keyToNode.get(key);
        node.keys.remove(key);

        if (node.count > 1) {
            if (node.prev == head || node.prev.count != node.count - 1)
                insertAfter(node.prev, new Node(node.count - 1));
            node.prev.keys.add(key);
            keyToNode.put(key, node.prev);
        } else {
            keyToNode.remove(key);
        }

        if (node.keys.isEmpty())
            remove(node);
    }

    private void insertAfter(Node node, Node newNode) {
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */