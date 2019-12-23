package com.mipo.common.design.test;

public class Demo1 {

    public static void main(String[] args) {
        DogAdapter dogAdapter = DogAdapter.getInstance();
        dogAdapter.run();
        dogAdapter.wang();
        dogAdapter.cry();
        dogAdapter.move();
    }

    static class DogAdapter extends Dog implements Robot {
        private static DogAdapter dogAdapter = null;

        private DogAdapter() {
        }

        @Override
        public void cry() {
            System.out.println("cry");
        }

        @Override
        public void move() {
            System.out.println("move");
        }

        public static DogAdapter getInstance() {
            if(dogAdapter == null) {
                dogAdapter = new DogAdapter();
            }
            return dogAdapter;
        }
    }

    static class Dog{
        public void wang() {
            System.out.println("wang");
        }

        public void run() {
            System.out.println("run");
        }
    }


    interface Robot {
        void cry();
        void move();
    }
}


