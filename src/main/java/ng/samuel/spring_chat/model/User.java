package ng.samuel.spring_chat.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

//    @Column(name="password")
//    private String password;

    @Column(name="full_name")
    private String fullName;

    @Column(name="status")
    private Status status;
}
