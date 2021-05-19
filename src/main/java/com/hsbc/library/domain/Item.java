package com.hsbc.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Enumerated
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Item(Book book) {
        this.book = book;
        this.status = Status.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return new EqualsBuilder().append(id, item.id).append(status, item.status).append(book, item.book).isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book);
    }
}
