package com.chervonnaya;

import java.util.Arrays;
import java.util.Stack;

public class MyStringBuilder {

    private static final int DEFAULT_CAPACITY = 16;
    private char[] buffer;
    private int currentLength;
    private Stack<Snapshot> snapshots;

    public MyStringBuilder() {
        buffer = new char[DEFAULT_CAPACITY];
        currentLength = 0;
        snapshots = new Stack<>();
    }

    public MyStringBuilder(int capacity) {
        buffer = new char[capacity];
        currentLength = 0;
        snapshots = new Stack<>();
    }

    public MyStringBuilder(String string) {
        buffer = string.toCharArray();
        currentLength = string.length();
        snapshots = new Stack<>();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - buffer.length > 0) {
            int newCapacity = buffer.length * 2;
            if (minCapacity - newCapacity > 0)
                newCapacity = minCapacity;
            buffer = Arrays.copyOf(buffer, newCapacity);
        }
    }

    public MyStringBuilder append(String s) {
        createSnapshot();
        if (s == null) s = "null";
        ensureCapacity(currentLength + s.length());
        System.arraycopy(s.toCharArray(), 0, buffer, currentLength, s.length());
        currentLength += s.length();
        return this;
    }

    private void createSnapshot() {
        char[] bufferCopy = Arrays.copyOf(buffer, buffer.length);
        snapshots.push(new Snapshot(bufferCopy, currentLength));
    }

    public MyStringBuilder undo() {
        if (!snapshots.isEmpty()) {
            Snapshot snapshot = snapshots.pop();
            this.buffer = snapshot.buffer;
            this.currentLength = snapshot.currentLength;
        }
        return this;
    }

    @Override
    public String toString() {
        return new String(buffer, 0, currentLength);
    }

    private record Snapshot(char[] buffer, int currentLength) {
    }

}
