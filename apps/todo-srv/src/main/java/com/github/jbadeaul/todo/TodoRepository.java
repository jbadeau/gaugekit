package com.github.jbadeaul.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository that handles all database access for the todo list items
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
