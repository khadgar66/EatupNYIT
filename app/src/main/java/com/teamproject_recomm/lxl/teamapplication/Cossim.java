package com.teamproject_recomm.lxl.teamapplication;

/**
 * Created by lxl on 5/2/2017.
 */

public class Cossim {

    public double dot(double[] a, double[] b){
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public double norm(double[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i] * a [i];
        }

        return (int)Math.sqrt(sum);

    }

    public double cossim(double[] a, double[] b){

        double dotProduct = dot(a,b);
        double normA = norm(a);
        double normB = norm(b);
        //double sum = dotProduct/(normA * normB);
        //System.out.println("dot :" + dotProduct);
        //System.out.println("normA: " + normA + " normB: " + normB);
        //System.out.println("cossim :" + sum);

        return dotProduct/(normA * normB);
    }
}