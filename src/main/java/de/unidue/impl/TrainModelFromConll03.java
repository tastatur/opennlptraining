package de.unidue.impl;

import opennlp.tools.formats.Conll02NameSampleStream;
import opennlp.tools.formats.Conll03NameSampleStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TrainModelFromConll03 extends AbstractTrainModel {

    @Override
    public void train(String trainModelFilename, String outModelFileName) {
        int types = getTypesToExtract();
        TokenNameFinderModel model = populateModel(trainModelFilename, types);
        if (model != null) {
            saveModelIntoFile(model, outModelFileName);
        }
    }

    private TokenNameFinderModel populateModel(String trainModelFilename, int types) {
        TokenNameFinderModel model = null;
        try (InputStream trainFile = new FileInputStream(trainModelFilename)) {
            ObjectStream<NameSample> trainData = new Conll03NameSampleStream(Conll03NameSampleStream.LANGUAGE.DE, trainFile,
                    types);

            model = NameFinderME.train("de", null, trainData, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    private int getTypesToExtract() {
        return Conll02NameSampleStream.GENERATE_PERSON_ENTITIES | Conll02NameSampleStream.GENERATE_ORGANIZATION_ENTITIES |
                Conll02NameSampleStream.GENERATE_MISC_ENTITIES | Conll02NameSampleStream.GENERATE_LOCATION_ENTITIES;
    }
}
