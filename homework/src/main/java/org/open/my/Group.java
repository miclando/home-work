package org.open.my;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private List<String []> group;
    private Set<String> changingWord;

    public Group() {
        this.group=new ArrayList<>();
        this.changingWord=new HashSet<>();
    }

    public void addSentence(String[] sentence) {
        this.group.add(sentence);
    }

    public void addChangingWord(String changingWord) {
        this.changingWord.add(changingWord);
    }

    public List<String[]> getGroup() {
        return group;
    }

    public Set<String> getChangingWord() {
        return changingWord;
    }
}
