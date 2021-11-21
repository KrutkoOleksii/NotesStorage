package com.example.notesStorage.Note;

import com.example.notesStorage.BaseEntity;
import com.example.notesStorage.auth.User;
import com.example.notesStorage.enums.AccessTypes;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@ToString
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

    @NotNull
    @Size(min = 5, max = 100, message = "should be more than 5 and not more than 100")
    private String name;

    @NotNull
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
