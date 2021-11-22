package com.example.notesStorage.Note;

import com.example.notesStorage.BaseEntity;
import com.example.notesStorage.auth.User;
import com.example.notesStorage.enums.AccessTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "author")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel
@Table(name = "notes")
public class Note implements BaseEntity<UUID> {

    private static final long serialVersionUID = 4044714827569083806L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ApiModelProperty(notes = "UUID id",name="id",required=true,value="unique name")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Username cannot be empty")
    //@Pattern(regexp = "[a-zA-Z0-9]+")
    @ApiModelProperty(notes = "Name of Note ",name="name",required=true,value="My Note")
    @Size(min = 5, max = 100, message = "The login must be between {min} and {max} characters long")
    private String name;

    @ApiModelProperty(notes = "Body of Note Message",name="message",required=true,value="Write a message")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, max = 10_000, message = "The note's text must be between {min} and {max} characters long")
    private String message;

    @NotNull
    @ApiModelProperty(notes = "AccessTypes of Note",name="accessType",required=true,value="PRIVATE/PUBLIC")
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
    @ApiModelProperty(notes = "Author of Note",name="user",required=true,value=" A.S.Pushkin ")
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Note note = (Note) o;
        return id != null && Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
