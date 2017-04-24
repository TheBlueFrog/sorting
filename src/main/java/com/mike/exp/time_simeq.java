package com.mike.exp;

/**
 * Created by mike on 4/23/2017.
 */
// time_simeq.java  not a great benchmark
//                  probably no zeros on diagonal, not near signgular

import java.text.*;

class time_simeq
{
    DecimalFormat f1 = new DecimalFormat("0.000E00");

    time_simeq()
    {
        int N = 4097;
        double A[] = new double[N*N];
        double X[] = new double[N];
        double Y[] = new double[N];
        double t, total;
        double start, now, next;

        System.out.println("time_simeq.java running");
        start = System.currentTimeMillis();
        for(int n=4; n<N; n=n*2)
        {
            init_matrix(n, A, Y);
            now = System.currentTimeMillis();
            System.out.println("matrix initialized at "+((now-start)/1000));
            new simeq(n, A, Y, X);
            next = System.currentTimeMillis();
            total = 0.0;
            for(int i=0; i<n; i++)
            {
                t = 0.0;
                for(int j=0; j<n; j++) t = t + A[i*n+j]*X[j];
                total = total + Math.abs(t-Y[i]);
            }
            System.out.println("n="+n+", total error="+total+
                    ", simeq took "+((next-now)/1000)+" seconds");
        }
    } // end time_simeq

    void init_matrix(int n, double A[], double Y[])
    {
        for(int i=0; i<n; i++)
        {
            Y[i] = Math.random();
            for(int j=0; j<n; j++)
                A[i*n+j] = Math.random();
        }
    } // end init_matrix

} // end class time_simeq.java
