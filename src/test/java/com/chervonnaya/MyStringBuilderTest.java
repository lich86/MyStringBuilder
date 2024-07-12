package com.chervonnaya;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStringBuilderTest {
    @Test
    void appendTestWithEmptyConstructor() {
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append("a").append("b").append("c");

        assertEquals("abc", myStringBuilder.toString());
    }

    @Test
    void appendTestWithNotEmptyConstructor() {
        MyStringBuilder myStringBuilder = new MyStringBuilder("a");
        myStringBuilder.append("b").append("c");

        assertEquals("abc", myStringBuilder.toString());
    }

    @Test
    void appendTestNull() {
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append(null);

        assertEquals("null", myStringBuilder.toString());
    }

    @Test
    void undoTest() {
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append("a").append("b").append("c");

        assertEquals("a", myStringBuilder.undo().undo().toString());
    }


}