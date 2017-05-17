package com.company;

/**
 * Created by Superuser on 5/17/2017.
 */
public class Student
{
    private String num;
    private double score;
    private double response1;
    private double response2;
    private String name;
    public Student(String n, double fr1, double fr2, double s)
    {
        num = n;
        score = s;
        response1 = fr1;
        response2 = fr2;
    }
    public Student(String n,double s)
    {
        num = n;
        score = s;
    }
    public Student(String n, String id)
    {
        name = n;
        num = id;
    }
    public String returnNum()
    {
        return num;
    }
    public double returnScore()
    {
        return score;
    }
    public double getResponse1()
    {
        return response1;
    }
    public double getResponse2()
    {
        return response2;
    }
    public String returnName()
    {
        return name;
    }
}
