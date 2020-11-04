package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueRepositoryTest {
    private IssueRepository issueRepository = new IssueRepository();

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, new Author(1, "name1", "surname1"), Label.BUG, new Assignee(2, "name2", "surname2"), LocalDate.of(2020, 2, 14), new Tag(1, "tag1"));
    private Issue issue2 = new Issue(2, "name1", Status.OPEN, new Author(3, "name3", "surname3"), Label.BUG, new Assignee(4, "name4", "surname4"), LocalDate.of(2020, 2, 14), new Tag(2, "tag2"));
    private Issue issue3 = new Issue(3, "name1", Status.CLOSED, new Author(5, "name5", "name5"), Label.ENHANCEMENT, new Assignee(6, "name6", "surname6"), LocalDate.of(2020, 2, 14), new Tag(3, "tag3"));
    private Issue issue4 = new Issue(4, "name1", Status.CLOSED, new Author(7, "name7", "name7"), Label.QUESTION, new Assignee(5, "name5", "surname5"), LocalDate.of(2020, 2, 14), new Tag(4, "tag4"));
    private Issue issue5 = new Issue(5, "name1", Status.OPEN, new Author(8, "name8", "name8"), Label.BUG, new Assignee(1, "name1", "surname1"), LocalDate.of(2020, 2, 14), new Tag(5, "tag5"));
    private Issue issue6 = new Issue(6, "name1", Status.OPEN, new Author(2, "name2", "surname2"), Label.BUG, new Assignee(8, "name8", "name8"), LocalDate.of(2020, 2, 14), new Tag(6, "tag6"));

    @BeforeEach()
    void setUp() {
        issueRepository.add(issue1);
        issueRepository.add(issue2);
        issueRepository.add(issue3);
        issueRepository.add(issue4);
        issueRepository.add(issue5);
        issueRepository.add(issue6);
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = issueRepository.findAll();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4, issue5, issue6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = issueRepository.findById(5);
        assertEquals(issue5, actual);
    }

    @Test
    void shouldThrowByIdException() {
        assertThrows(NotFoundException.class, () -> issueRepository.findById(10));
    }

    @Test
    void shouldOpenById() {
        issueRepository.openById(1);
        Issue byId = issueRepository.findById(1);
        assertSame(byId.getStatus(), Status.OPEN);
    }

    @Test
    void shouldCloseById() {
        issueRepository.closeById(6);
        Issue byId = issueRepository.findById(6);
        assertSame(byId.getStatus(), Status.CLOSED);
    }
}





