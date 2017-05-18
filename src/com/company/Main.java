package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException
    {
        int txtLength = -1;
        int size = -1;

        ArrayList<Student> studentListTD = new ArrayList();
        ArrayList<Student> studentListT12D = new ArrayList<>();
        ArrayList<String> options = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/rawDataU6.txt"));
        br.readLine();
        String line;
        while ((line = br.readLine()) != null)
        {
            txtLength++;

            options.add(line);

        }
        for (int i = 0; i<options.size(); i++)
        {
            if ((!options.get(i).contains("+")) || options.get(i).substring(0,1).equalsIgnoreCase("(") || options.get(i).substring(0,1).equalsIgnoreCase("A"))
            {
                options.remove(i);
            }
        }

        options.remove(26);

        String [] secretNum = new String [51];

        for (int i = 0; i < options.size(); i++)
        {
            secretNum[i] = options.get(i).substring(0,2).replaceAll("\t", "");

        }
        for (int i = 0; i<options.size(); i++)
        {
            String q = options.get(i);
            for (int k = 0; k < q.length(); k++)
            {
                int ix = q.indexOf("\"");
                if ((ix + 2) < q.length())
                {
                    if (ix != -1 && !q.substring(ix,ix + 2).contains("+"))
                    {
                        options.set(i,options.get(i).substring(0,ix + 2) + "\t\t" + q.substring(ix + 1));
                        q = q.substring(ix + 1);
                    }
                }

                else
                {
                    q = q.substring(ix + 1);
                }
            }
        }
        for (int i = 0; i < options.size(); i++)
        {
            options.set(i,options.get(i).replaceAll("\t\t","\t"));
            options.set(i,options.get(i).replace("\t\t\t","\t"));
        }



//        for (int i = 0; i < options.size(); i++)
//        {
//            System.out.println(options.get(i));
//        }



        String [] lineOptions;
        double total = 0;
        double [] totals = new double[secretNum.length];


        for (int i= 0; i<options.size(); i++)
        {
            lineOptions = options.get(i).split("\t");
            for (int k = 1; k < lineOptions.length; k++)
            {
                String s = lineOptions[k];
                if (s.contains("+"))
                {
                    for (int j = 0; j<s.length(); j++)
                    {
                        int indx = lineOptions[k].indexOf("+");
                        while (indx !=-1)
                        {
                            if (s.substring(indx + 1, indx + 5).contains("1"))
                            {
                                total += 1;
                            }
                            else if (s.substring(indx + 1, indx + 5).contains("5"))
                            {
                                total += .5;
                            }
                            s = s.substring(indx + 1);
                            indx = s.indexOf("+");
                        }

                        break;

                    }
                }
                else if (!(s.equalsIgnoreCase("")) && s.substring(0,1).matches(".*\\d+.*") && (s.length() <= 3))
                {
                    total = total - (Double.parseDouble(lineOptions[k]) * 0.25);
                }

            }
            totals[i] = total;
            total = 0;
        }

        for (int i = 0; i< totals.length; i++)
        {
            Student s = new Student(secretNum[i], totals[i]);
            studentListTD.add(s);
        }

        ArrayList<Student> StudentTotal = new ArrayList();
        double avg = 0;

        for (int i = 1; i < studentListTD.size(); i++)
        {
            if ((studentListTD.get(i).returnNum()).equalsIgnoreCase(studentListTD.get(i-1).returnNum()))
            {
                avg = (studentListTD.get(i).returnScore() + studentListTD.get(i-1).returnScore())/2;
                Student s = new Student(studentListTD.get(i).returnNum(), avg);
                StudentTotal.add(s);
            }
            else if (studentListTD.get(i).returnNum().equalsIgnoreCase("12")
                    || studentListTD.get(i).returnNum().equalsIgnoreCase("15")
                    ||studentListTD.get(i).returnNum().equalsIgnoreCase("21")
                    ||studentListTD.get(i).returnNum().equalsIgnoreCase("25"))
            {
                avg = studentListTD.get(i).returnScore();
                Student s = new Student(studentListTD.get(i).returnNum(), avg);
                StudentTotal.add(s);
            }
        }


        int count = 0;
        double fr1 = 0;
        double[] fr1Array = new double[secretNum.length];
        double fr2 = 0;
        double[] fr2Array = new double[secretNum.length];

        for (int i = 0; i < options.size(); i++)
        {
            lineOptions = options.get(i).split("\t");
            for (int k = 1; k < lineOptions.length; k++)
            {
                if (k == 0)
                {
                    break;
                }
                String s1 = lineOptions[k];
                if ((s1.matches(".*\\d+.*") && s1.length() <= 1) || (!s1.matches(".*\\d+.*")))
                {
                    count++;
                }
                if (count > 0)
                {
                    for (int j = 1; j < k; j++)
                    {
                        String s = lineOptions[j];
                        if (s.contains("+"))
                        {
                            for (int h = 0; h<s.length(); h++)
                            {
                                int indx = s.indexOf("+");
                                while (indx !=-1)
                                {
                                    if (s.substring(indx + 1, indx + 5).contains("1"))
                                    {
                                        fr1 += 1;
                                    }
                                    else if (s.substring(indx + 1, indx + 5).contains("5"))
                                    {
                                        fr1 += .5;
                                    }
                                    s = s.substring(indx + 1);
                                    indx = s.indexOf("+");
                                }
                                break;
                            }
                        }
                    }
                    fr1Array[i] = fr1;
                    fr1 = 0;
                    for (int a = k; a <lineOptions.length; a++ )
                    {
                        String s = lineOptions[a];
                        if (s.contains("+"))
                        {
                            for (int h = 0; h<s.length(); h++)
                            {
                                int indx = s.indexOf("+");
                                while (indx !=-1)
                                {
                                    if (s.substring(indx + 1, indx + 5).contains("1"))
                                    {
                                        fr2 += 1;
                                    }
                                    else if (s.substring(indx + 1, indx + 5).contains("5"))
                                    {
                                        fr2 += .5;
                                    }
                                    s = s.substring(indx + 1);
                                    indx = s.indexOf("+");
                                }
                                break;
                            }

                        }
                    }
                    fr2Array[i] = fr2;
                    fr2 = 0;
                    k = -1;
                    count = 0;

                }
            }
        }

        for (int i = 0; i < studentListTD.size(); i++)
        {
            Student s = new Student(secretNum[i], fr1Array[i], fr2Array[i], totals[i]);
            studentListT12D.add(s);
        }

        double avgFR1 = 0;
        double avgFR2 = 0;

        ArrayList<Student> StudentAverage = new ArrayList<>();

        for (int i = 1; i < studentListTD.size(); i++)
        {
            if ((studentListT12D.get(i).returnNum()).equalsIgnoreCase(studentListT12D.get(i-1).returnNum()))
            {
                avg = (studentListT12D.get(i).returnScore() + studentListT12D.get(i-1).returnScore())/2;
                avgFR1 = (studentListT12D.get(i).getResponse1() + studentListT12D.get(i-1).getResponse1())/2;
                avgFR2 = (studentListT12D.get(i).getResponse2() + studentListT12D.get(i).getResponse2())/2;
                Student s = new Student(studentListT12D.get(i).returnNum(), avgFR1, avgFR2, avg);
                StudentAverage.add(s);
            }
            else if (studentListT12D.get(i).returnNum().equalsIgnoreCase("12")
                    || studentListT12D.get(i).returnNum().equalsIgnoreCase("15")
                    ||studentListT12D.get(i).returnNum().equalsIgnoreCase("21")
                    ||studentListT12D.get(i).returnNum().equalsIgnoreCase("25"))
            {
                avg = studentListT12D.get(i).returnScore();
                avgFR1 = studentListT12D.get(i).getResponse1();
                avgFR2 = studentListT12D.get(i).getResponse2();
                Student s = new Student(studentListT12D.get(i).returnNum(), avgFR1, avgFR2, avg);
                StudentAverage.add(s);
            }
        }


//        BufferedReader b2 = new BufferedReader(new FileReader("src\\namesTest.txt"));
        BufferedReader b2 = new BufferedReader(new FileReader("src\\names.txt"));
        String name;

        ArrayList<String> nameNumber = new ArrayList<>();

        while ((name = b2.readLine()) != null)
        {
            nameNumber.add(name);
        }

        String [] nn = new String[2];

        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < nameNumber.size(); i++)
        {
            nn = nameNumber.get(i).split("\t");
            names.add(nn[0]);
        }

        System.out.printf("%-25s %-25s %-25s %-25s %-25s\n", "Name", "Number", "Free Response 1", "Free Response 2", "Total Score (including syntax errors)");
        for (int i = 0; i < StudentTotal.size(); i++)
        {
//            System.out.println(StudentAverage.get(i).returnNum() + "\t\t\t\t\t\t" + StudentAverage.get(i).getResponse1() + "\t\t\t\t\t\t\t\t"
//                    + StudentAverage.get(i).getResponse2() + "\t\t\t\t\t\t\t\t" + StudentTotal.get(i).returnScore());
            System.out.printf("%-25s %-25s %-25s %-25s %-25s\n", names.get(i), StudentAverage.get(i).returnNum(), StudentAverage.get(i).getResponse1(),
                    StudentAverage.get(i).getResponse2(), StudentTotal.get(i).returnScore());
            System.out.println("");
        }



    }
}
