package org.open.my;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroppingLogic {

    private File outputFile;
    private File inputFile;
    private List<String[]> inputSentences;
    private Map<String[],Map<Integer,Group>> mapping= new HashMap<>();

    public GroppingLogic(File inputFile, File outputFile){
        this.inputFile=inputFile;
        this.outputFile=outputFile;
    }

    public void startGropping(){
        try {
            readInput();
            createGroups();
            writeOutput();
        } catch (IOException e) {
            System.out.println("exception:"+e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;

        }
    }

    private void createGroups() {
        String[] sentence1;
        String[] sentence2;
        for(int sentence1Index =0 ;sentence1Index<inputSentences.size(); sentence1Index++){
            sentence1 = inputSentences.get(sentence1Index);
            for(int sentence2Index=sentence1Index+1; sentence2Index<inputSentences.size(); sentence2Index++){
                sentence2 = inputSentences.get(sentence2Index);
                evaluate(sentence1,sentence2);
            }
        }


    }

    private void evaluate(String[] sentence1, String[] sentence2) {
        boolean matcheFound=false;
        int matchedWordIndex=0;
        List<String> changingWord = new ArrayList<>();
        boolean equel=false;
        if(sentence1.length == sentence2.length){
            for (int i =2;i<sentence1.length;i++) {
                equel=sentence1[i].equals(sentence2[i]);
                if(!equel && !matcheFound){
                    matcheFound=true;
                    matchedWordIndex=i;
                    changingWord.add(sentence1[i]);
                    changingWord.add(sentence2[i]);
                }
                else if(!equel && matcheFound){
                    return;
                }
            }
            if(!matcheFound) {
                return;
            }
            storeMatch(sentence1, sentence2, matchedWordIndex, changingWord);
        }
    }

    private void storeMatch(String[] sentence1, String[] sentence2, int matchedWordIndex, List<String> changingWord) {

        Map<Integer, Group> map = this.mapping.get(sentence1);
        if(map==null){
            map = new HashMap<>();
            this.mapping.put(sentence1,map);
        }

        Group group = map.get(matchedWordIndex);
        if(group==null){
            group=new Group();
            map.put(matchedWordIndex,group);
            group.addSentence(sentence1);
            group.addChangingWord(sentence1[matchedWordIndex]);
        }
        group.addChangingWord(sentence2[matchedWordIndex]);
        group.addSentence(sentence2);

    }

    private void readInput() throws IOException {
            Stream<String> lines = Files.lines(inputFile.toPath());
            inputSentences = lines.map(e -> e.split("\\s")).collect(Collectors.toList());

    }

    private void writeOutput() throws IOException {
        String prefix="The changing word was: ";
        Files.deleteIfExists(outputFile.toPath());
        Files.write(outputFile.toPath(), "=====".concat(System.lineSeparator()).getBytes(),StandardOpenOption.CREATE);
        for(Map<Integer,Group> map : mapping.values()){
            for(Group group: map.values()){
                for(String[] sentence : group.getGroup()) {
                    Files.write(outputFile.toPath(), String.join(" ", sentence).concat(System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                }
                Files.write(outputFile.toPath(), prefix.concat(String.join(", ", group.getChangingWord())).concat(System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
            }
        }
        Files.write(outputFile.toPath(), "=====".concat(System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);


    }

    private void print() {
        this.mapping.forEach( (e,k) -> {
            System.out.println("sentence : "+ Arrays.toString(e));
            k.forEach( (o,p) -> {
                System.out.println("index:"+o);
                System.out.println("matching words:"+k.get(o).getChangingWord());
                System.out.println("groups:");
                k.get(o).getGroup().forEach( w-> System.out.println(Arrays.toString(w)));

            });
        });
    }

}
