package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException
    {
        int txtLength = -1;
        int size = -1;

        ArrayList<Student> studentListTD = new ArrayList();
        ArrayList<String> studentListT12D = new ArrayList<>();
        ArrayList<String> options = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/rawDataU6.txt"));
        br .readLine();
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
                if (!q.substring(ix,ix + 2).contains("+"))
                {
                    options.set(i,options.get(i).substring(0,ix + 1) + "\t\t" + q.substring(ix + 1));
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

        for (int i=0; i<options.size(); i++)
        {
            lineOptions = options.get(i).split("\t");
            for (int k = 1; k < lineOptions.length; k++)
            {
                if (lineOptions[k].contains("+"))
                {
                    for (int j = 0; j<lineOptions[k].length(); j++)
                    {
                        int indx = lineOptions[k].indexOf("+");
                        while (indx !=-1)
                        {
                            if (lineOptions[k].substring(indx + 2, indx + 4).contains("1"))
                            {
                                total += 1;
                            }
                            else if (lineOptions[k].substring(indx + 2, indx + 4).contains("5"))
                            {
                                total += .5;
                            }
                            lineOptions[k] = lineOptions[k].substring(indx + 1);
                            indx = lineOptions[k].indexOf("+");
                        }

                    }
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

        System.out.println("Number\t\t\t" + "Total Score");
        for (int i = 0; i < StudentTotal.size(); i++)
        {
            System.out.println(StudentTotal.get(i).returnNum() + "\t\t\t\t" + StudentTotal.get(i).returnScore());
        }



    }
}
