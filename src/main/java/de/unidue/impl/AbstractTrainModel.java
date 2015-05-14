package de.unidue.impl;

import de.unidue.TrainModel;
import opennlp.tools.namefind.TokenNameFinderModel;

import java.io.*;

public abstract class AbstractTrainModel implements TrainModel {
    protected void saveModelIntoFile(TokenNameFinderModel model, String outModelFileName) {
        try(OutputStream outModelFile = new BufferedOutputStream(new FileOutputStream(outModelFileName))) {
            model.serialize(outModelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
