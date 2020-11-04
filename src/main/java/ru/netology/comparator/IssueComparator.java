package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
