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
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="sender_id")
    private User sender;

    @Column(name="content", length = 1000)
    private String content;

    @Column(name="created_at")
    @CreationTimestamp
    private String createdAt;
}
