package com.hsbc.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "READERS")
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ACCOUNT_CREATED")
    private LocalDate accountCreated;

    @OneToMany(
            targetEntity = Borrowing.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "reader")
    private List<Borrowing> readersBorrowings = new ArrayList<>();

    public Reader(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Reader)) return false;

        Reader reader = (Reader) o;

        return new EqualsBuilder().append(id, reader.id).append(name, reader.name)
                .append(lastName, reader.lastName)
                .append(accountCreated, reader.accountCreated).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id)
                .append(name).append(lastName).toHashCode();
    }
}
