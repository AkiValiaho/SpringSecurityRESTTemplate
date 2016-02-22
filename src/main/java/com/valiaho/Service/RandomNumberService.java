package com.valiaho.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by akivv on 18.2.2016.
 */
@Service
public class RandomNumberService {

    private Random random = new Random();

    public int generateRandomNumber(int i) {
        return random.nextInt(i);
    }
}
