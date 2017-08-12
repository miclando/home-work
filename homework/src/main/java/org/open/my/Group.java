package org.open.my;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * pojo class representing the group
 */
public class Group {
    /**
     * represents the sentences collected in thsi group
     * all sentences differ by one word
     */
    private Set<String []> group;
    /**
     * represents the words that differ between the sentences in this group
     */
    private Set<String> changingWord;

    public Group() {
        this.group=new HashSet<>();
        this.changingWord=new HashSet<>();
    }

    public void addSentence(String[] sentence) {
        this.group.add(sentence);
    }

    public void addChangingWord(String changingWord) {
        this.changingWord.add(changingWord);
    }

    public Set<String[]> getGroup() {
        return group;
    }

    public Set<String> getChangingWord() {
        return changingWord;
    }
}
