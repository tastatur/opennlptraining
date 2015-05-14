package de.unidue.de.unidue.runners;

import de.unidue.TrainModel;
import de.unidue.impl.TrainModelFromConll03;

public class Conll03Runner {

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Bitte, alle Parameter spezifizieren!");
        }

        String trainFrom = args[0];
        String saveTo = args[1];

        TrainModel trainModel = new TrainModelFromConll03();
        trainModel.train(trainFrom, saveTo);
    }
}
