package com.mukhtaryusuf.stacksandqueues;

/**
 * Created by mukhtaryusuf on 2/7/18.
 */

public class AnimalShelter {
    private java.util.LinkedList<Dog> dogQueue;
    private java.util.LinkedList<Cat> catQueue;
    private int timeStamp = 0;

    public AnimalShelter(){
        dogQueue = new java.util.LinkedList<>();
        catQueue = new java.util.LinkedList<>();
    }

    public void enqueue(Animal a){
        a.tStamp = timeStamp;
        if(Dog.class.isInstance(a)){
            Dog dog = (Dog)a;
            dogQueue.addLast(dog);
        }else if(Cat.class.isInstance(a)){
            Cat cat = (Cat)a;
            catQueue.addLast(cat);
        }
        timeStamp++;
    }
    public Animal dequeueAny(){
        int dTS = Integer.MAX_VALUE;
        int cTS = Integer.MAX_VALUE;
        if(!dogQueue.isEmpty())
            dTS = dogQueue.peekFirst().tStamp;
        if(!catQueue.isEmpty())
            cTS = catQueue.peekFirst().tStamp;
        if(dTS < cTS)
            return dogQueue.removeFirst();
        else if(cTS < dTS)
            return catQueue.removeFirst();

        return null;
    }
    public Animal dequeueDog(){
        if(!dogQueue.isEmpty())
            return dogQueue.removeFirst();
        return null;
    }
    public Animal dequeueCat(){
        if(!catQueue.isEmpty())
            return catQueue.removeFirst();
        return null;
    }

    public boolean isEmpty(){
        return (catQueue.isEmpty() && dogQueue.isEmpty());
    }
}
