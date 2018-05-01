package com.vote2go.votingsystem.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Mamytov Khamidullo
 * 14.03.2018
 */
@Getter
@Setter
@Entity
@SuppressWarnings("JpaQlInspections")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedEntityGraph(name = Restaurant.GRAPH_WITH_VOTES_FOODS, attributeNodes =
        {
                @NamedAttributeNode("votes"),
                @NamedAttributeNode("foods")
        })
@Table(name = "restaurants")
public class Restaurant extends NamedEntity{
    public static final String GRAPH_WITH_VOTES_FOODS = "Restaurant.withVotesFoods";

    @NotBlank
    @Column(name = "description", nullable = false)
    @Size(min = 5, max = 255)
    @SafeHtml
    private String description;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    private Set<Vote> votes;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @JsonManagedReference(value = "restaurant-foods")
    private Set<Food> foods;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
