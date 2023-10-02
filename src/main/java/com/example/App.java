package com.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        ClientStr client = new ClientStr();
        client.connetti();
        client.comunica();
    }
}
