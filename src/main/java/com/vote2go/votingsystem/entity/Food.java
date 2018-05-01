package com.vote2go.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vote2go.votingsystem.Utils.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * Mamytov Khamidullo
 * 14.03.2018
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "foods", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date", "name", "price"}, name = "foods_unique_idx")})
public class Food extends NamedEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @Column(name = "price", nullable = false, columnDefinition = "int default 0")
    @NotNull
    private Integer price;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "restaurant-foods")
    private Restaurant restaurant;

    public Food() {
    }

    public Food(LocalDate date, String name, Integer price) {
        this(null, date, name, price);
    }

    public Food(Integer id, LocalDate date, String name, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }
}
