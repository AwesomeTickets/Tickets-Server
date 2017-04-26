package com.awesome_tickets.business.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    private Integer movieId;
    private Country country;
    private MovieStatus movieStatus;
    private MovieType movieType;
    private Set<MovieStyle> movieStyleSet;
    private Date pubDate;
    private String title;
    private float rating;
    private int length;
    private String posterSmall;
    private String posterLarge;

    public Movie() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_status_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_type_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "movie_has_style",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_style_id", referencedColumnName ="movie_style_id")})
    public Set<MovieStyle> getMovieStyleSet() {
        return movieStyleSet;
    }

    public void setMovieStyleSet(Set<MovieStyle> movieStyleSet) {
        this.movieStyleSet = movieStyleSet;
    }

    @Column(name = "pub_date", nullable = false)
    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Column(name = "title", nullable = false, length=10)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "rating", columnDefinition="float(2,1) default 0.0")
    @DecimalMin("0.1")
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Column(name = "length")
    @Min(1)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Column(name = "poster_small", length=128)
    public String getPosterSmall() {
        return posterSmall;
    }

    public void setPosterSmall(String posterSmall) {
        this.posterSmall = posterSmall;
    }

    @Column(name = "poster_large", length=128)
    public String getPosterLarge() {
        return posterLarge;
    }

    public void setPosterLarge(String posterLarge) {
        this.posterLarge = posterLarge;
    }
}
