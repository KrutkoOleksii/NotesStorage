package com.example.notesStorage.Note;

import com.example.notesStorage.BaseEntity;
import com.example.notesStorage.auth.User;
import com.example.notesStorage.enums.AccessTypes;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Data
@Getter
@Setter
@ToString(exclude = "author")
@EqualsAndHashCode(exclude = "author")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notes")
public class Note implements BaseEntity<UUID> {

    private static final long serialVersionUID = 4044714827569083806L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Note name cannot be empty")
    //@Pattern(regexp = "[a-zA-Z0-9]+")
    @Size(min = 5, max = 100, message = "Nte name must be between {min} and {max} characters long and contains only numbers and english letters")
    private String name;

    @NotBlank(message = "Note text cannot be empty")
    @Size(min = 5, max = 10_000, message = "The note text must be between {min} and {max} characters long")
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessTypes accessType;

    public String getAuthorName(){
        return author != null ? author.getUsername() : "";
    }

    public Note(String id,String name, String message, AccessTypes accessType) {
        this.name = name;
        this.message = message;
        this.accessType = accessType;
        this.id = UUID.fromString(id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
