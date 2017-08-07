package org.open.my;

import java.io.File;

public class Executor {


    public static void main(String [] args){

        if(args.length==2) {

            String inputFile = args[0];
            String outputFile = args[1];

            GroppingLogic groppingLogic = new GroppingLogic(new File(inputFile), new File(outputFile));

            groppingLogic.startGropping();
        }
        else{
            System.out.println("invalide number of arguments was provided.");
            System.out.println("usege: < input file location > < output file location >");
        }

    }
}
