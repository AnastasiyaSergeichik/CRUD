package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private Author author;
    private Label label;
    private Status status;
    private Assignee assignee;
    private Set<Tag> tagsSet = new HashSet<>();
    private LocalDate date;

    public Issue(int id, String name, Status status, Author author, Label label, Assignee assignee, LocalDate date, Tag tag) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
        this.date = date;
        this.tagsSet.add(tag);
    }
}