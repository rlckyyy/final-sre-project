package postingapp.finalsreproject.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import postingapp.finalsreproject.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private LocalDateTime registeredAt;
    @Enumerated(EnumType.STRING)
    private List<Role> roleList;
}
