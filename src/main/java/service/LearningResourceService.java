package service;

import entity.LearningResource;
import entity.LearningResourcesStatus;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LearningResourceService {
    public List<LearningResource> getLearningResource() {
        File learningResourcesFile = new File("learningResources.csv");
        List<LearningResource> learningResources = loadLearningResourceFromCsv(learningResourcesFile);
        return learningResources;
    }
    private List<LearningResource> loadLearningResourceFromCsv(File csvFile){
        List<LearningResource> learningResources = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(csvFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            line = bufferedReader.readLine();
            while(line!= null){
                String[] attributes = line.split(",");
                LearningResource learningResource = createLearningResource(attributes);
                learningResources.add(learningResource);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return learningResources;
    }
    public List<Double> getProfitMargin(){
        List<LearningResource> learningResources = getLearningResource();

        return learningResources.stream()
                .map(lr -> ((lr.getSelling_price() - lr.getCost_price())/lr.getSelling_price()))
                .collect(toList());
    }
    public List<LearningResource> sortLearningResourcesByProfitMargin(){
        List<LearningResource> learningResources = getLearningResource();

        learningResources.sort((lr1, lr2) -> {
            Double profitMargin1 = (lr1.getSelling_price() - lr1.getCost_price())/lr1.getSelling_price();
            Double profitMargin2 = (lr2.getSelling_price() - lr2.getCost_price())/lr2.getSelling_price();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        return learningResources;
    }
    private void populateLearningResourcesToCsv(List<LearningResource> learningResources){
        final String csvDelimiter = ",";

        try {
            File learningResourcesFile = new File("LearningResources.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(learningResourcesFile.getName(), true));
            for (LearningResource learningResource : learningResources) {
                bufferedWriter.newLine();
                StringBuffer singleLine = new StringBuffer();
                singleLine.append(learningResource.getId());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getName());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getCost_price());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getSelling_price());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getLearningResourcesStatus());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getCreated_date());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getPublished_date());
                singleLine.append(csvDelimiter);
                singleLine.append(learningResource.getRetired_date());
                bufferedWriter.write(singleLine.toString());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private LearningResource createLearningResource(String[] attributes){
        Integer learningResourceId = Integer.parseInt(attributes[0].replaceAll("\\D+", ""));
        String learningResourceName = attributes[1];
        Double costPrice = Double.parseDouble(attributes[2]);
        Double sellingPrice = Double.parseDouble(attributes[3]);
        LearningResourcesStatus learningResourceStatus = LearningResourcesStatus.valueOf(attributes[4]);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate createdDate = LocalDate.parse(attributes[5], dateFormatter);
        LocalDate publishedDate = LocalDate.parse(attributes[6], dateFormatter);
        LocalDate retiredDate = LocalDate.parse(attributes[7], dateFormatter);

        LearningResource learningResource = new LearningResource(
                learningResourceId, learningResourceName, costPrice, sellingPrice, learningResourceStatus, createdDate, publishedDate, retiredDate
        );
        return learningResource;
    }
}
