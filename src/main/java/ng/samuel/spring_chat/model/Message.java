package ng.samuel.spring_chat.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {
    //  a public message application
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="sender", nullable = false)
    private String sender;

    @Column(name="message", length = 1000)
    private String message;

    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
}
