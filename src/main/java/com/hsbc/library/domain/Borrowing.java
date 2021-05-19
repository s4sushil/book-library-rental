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
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BORROWINGS")
public class Borrowing {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "BORROWED_FROM")
    private LocalDate borrowedFrom;

    @Column(name = "BORROWED_TO")
    private LocalDate borrowedTo;

    @Column(name = "PAID_FOR_DAMAGED")
    private boolean isPaidForDamaged;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    public Borrowing(Item item, Reader reader) {
        this.item = item;
        this.reader = reader;
        this.borrowedFrom = LocalDate.now();
        this.borrowedTo = null;
        this.isPaidForDamaged = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Borrowing)) return false;

        Borrowing borrowing = (Borrowing) o;

        return new EqualsBuilder().append(isPaidForDamaged, borrowing.isPaidForDamaged).append(id, borrowing.id)
                .append(borrowedFrom, borrowing.borrowedFrom).append(borrowedTo, borrowing.borrowedTo)
                .append(item, borrowing.item).append(reader, borrowing.reader).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(borrowedFrom).append(borrowedTo)
                .append(isPaidForDamaged).append(item)
                .append(reader).toHashCode();
    }

}
