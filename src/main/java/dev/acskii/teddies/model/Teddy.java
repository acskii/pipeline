package dev.acskii.teddies.model;

import dev.acskii.teddies.model.common.TeddyColor;
import jakarta.persistence.*;

/* Andrew :) */

@Entity
@Table( name = "teddies" )
public class Teddy {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Column( name = "name", nullable = false, unique = true )
    private String name;

    @Enumerated( EnumType.STRING )
    @Column( name = "color" )
    private TeddyColor color;

    /* Constructors */
    public Teddy() {}

    public Teddy(String name, TeddyColor color) {
        this.name = name;
        this.color = color;
    }

    /* Getters & Setters */
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public TeddyColor getColor() {return color;}
    public void setColor(TeddyColor color) {this.color = color;}
}
