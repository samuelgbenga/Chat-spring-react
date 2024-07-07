package ng.samuel.spring_chat.repository;

import ng.samuel.spring_chat.model.Message;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface MessageRepository extends JpaRepository <Message, Long> {

    // retrieves the element by the pages: pagination
    Page<Message> findTopByOrderByIdDesc(Pageable pageable);

    // find the first 20 message in descending order
    List<Message> findFirst20ByOrderByIdDesc();

    // specify the number of message to be retrieved.
    @Query(value = "SELECT * FROM message e ORDER BY e.id DESC LIMIT ?1", nativeQuery = true)
    List<Message> findLastNRecords(int n);
}
