package ru.netology.manager;

import ru.netology.domain.*;
import ru.netology.domain.Assignee;
import ru.netology.domain.Author;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;


    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> findOpen() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.OPEN)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    public List<Issue> findClosed() {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getStatus().equals(Status.CLOSED)) {
                temp.add(issue);
            }
        }
        return temp;
    }

    private List<Issue> filterBy(Predicate<Issue> predicate, Comparator<Issue> issueComparator) {
        List<Issue> temp = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                temp.add(issue);
            }
        }
        temp.sort(issueComparator);
        return temp;
    }

    public List<Issue> filterByAuthor(Author author, Comparator<Issue> issueComparator) {
        Predicate<Issue> authorPredicate = issue -> issue.getAuthor().equals(author);

        return filterBy(authorPredicate, issueComparator);
    }

    public List<Issue> filterByAssignee(Assignee assignee, Comparator<Issue> issueComparator) {
        Predicate<Issue> assigneePredicate = issue -> issue.getAssignee().equals(assignee);
        return filterBy(assigneePredicate, issueComparator);
    }

    public List<Issue> filterByLabel(Label label, Comparator<Issue> issueComparator) {
        Predicate<Issue> labelPredicate = issue -> issue.getLabel().equals(label);
        return filterBy(labelPredicate, issueComparator);
    }
}