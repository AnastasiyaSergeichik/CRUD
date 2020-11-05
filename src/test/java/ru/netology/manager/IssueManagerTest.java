package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssueComparator;
import ru.netology.domain.Author;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;
import ru.netology.domain.Tag;
import ru.netology.repository.IssueRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IssueManagerTest {
  private IssueRepository repository = new IssueRepository();
  private IssueManager issueManager = new IssueManager(repository);
  private IssueComparator comparator = new IssueComparator();

  private Issue issue1 = new Issue(1, "name1", Status.OPEN, new Author(1, "name1", "surname1"), Label.BUG, new Assignee(2, "name2", "surname2"), LocalDate.of(2020, 2, 14), new Tag(1, "tag1"));
  private Issue issue2 = new Issue(2, "name1", Status.OPEN, new Author(3, "name2", "surname2"), Label.BUG, new Assignee(4, "name4", "surname4"), LocalDate.of(2020, 2, 14), new Tag(2, "tag2"));
  private Issue issue3 = new Issue(3, "name1", Status.CLOSED, new Author(5, "name5", "name5"), Label.ENHANCEMENT, new Assignee(6, "name6", "surname6"), LocalDate.of(2020, 2, 14), new Tag(3, "tag3"));
  private Issue issue4 = new Issue(4, "name1", Status.CLOSED, new Author(7, "name7", "name7"), Label.QUESTION, new Assignee(5, "name5", "surname5"), LocalDate.of(2020, 2, 14), new Tag(4, "tag4"));
  private Issue issue5 = new Issue(5, "name1", Status.OPEN, new Author(8, "name8", "name8"), Label.BUG, new Assignee(1, "name1", "surname1"), LocalDate.of(2020, 2, 14), new Tag(5, "tag5"));
  private Issue issue6 = new Issue(6, "name1", Status.OPEN, new Author(4, "name4", "surname4"), Label.BUG, new Assignee(2, "name2", "surname2"), LocalDate.of(2020, 2, 14), new Tag(6, "tag6"));

  @BeforeEach()
  void setUp() {
    issueManager.add(issue1);
    issueManager.add(issue2);
    issueManager.add(issue3);
    issueManager.add(issue4);
    issueManager.add(issue5);
    issueManager.add(issue6);
  }

  @Test
  void shouldFindAllOpen() {
    List<Issue> actual = issueManager.findOpen();
    List<Issue> expected = Arrays.asList(issue1, issue2, issue5, issue6);
    assertEquals(expected, actual);
  }

  @Test
  void shouldFindAllClosed() {
    List<Issue> actual = issueManager.findClosed();
    List<Issue> expected = Arrays.asList(issue3, issue4);
    assertEquals(expected, actual);
  }

  @Test
  void shouldFilterByAuthor() {
    List<Issue> actual = issueManager.filterByAuthor(new Author(4, "name4", "surname4"), comparator);
    List<Issue> expected = Arrays.asList(issue6);
    assertEquals(expected, actual);
  }

  @Test
  void shouldNotFilterByAuthor() {
    List<Issue> actual = issueManager.filterByAuthor(new Author(10, "name10", "surname10"), comparator);
    assertEquals(0, actual.size());
  }

  @Test
  void shouldFilterByAssignee() {
    List<Issue> actual = issueManager.filterByAssignee(new Assignee(2, "name2", "surname2"), comparator);
    List<Issue> expected = Arrays.asList(issue1, issue6);
    assertEquals(expected, actual);
  }

  @Test
  void shouldNotFilterByAssignee() {
    List<Issue> actual = issueManager.filterByAssignee(new Assignee(9, "name9", "surname9"), comparator);
    assertEquals(0, actual.size());
  }

  @Test
  void shouldFilterByLabel() {
    List<Issue> actual = issueManager.filterByLabel(Label.BUG, comparator);
    List<Issue> expected = Arrays.asList(issue1, issue2, issue5, issue6);
    assertEquals(expected, actual);
  }

  @Test
  void shouldNotFilterByLabel() {
    List<Issue> actual = issueManager.filterByLabel(Label.DOC, comparator);
    assertEquals(0, actual.size());
  }
}





