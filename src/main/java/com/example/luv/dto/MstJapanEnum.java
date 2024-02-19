package com.example.luv.dto;

public class MstJapanEnum {

    enum NameLevel {
        N1(1),
        N2(2),
        N3(3),
        N4(4),
        N5(5);


        private int value;

        NameLevel(int value) {
            this.value = value;
        }
    }
}
