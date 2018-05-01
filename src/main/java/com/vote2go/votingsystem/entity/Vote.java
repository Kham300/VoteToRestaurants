package com.vote2go.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vote2go.votingsystem.Utils.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_date_unique_idx")})
public class Vote extends BaseEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date) {
        this(null, date);
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                ", date=" + date +
                '}';
    }
}
