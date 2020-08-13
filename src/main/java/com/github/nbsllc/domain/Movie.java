package com.github.nbsllc.domain;

public class Movie {
    private String id;
    private Long rank;
    private String title;
    private String rating;
    private String director;
    private Long releaseYear;

    @Override
    public String toString() {
        return "Movie{" +
            "id='" + id + '\'' +
            ", rank=" + rank +
            ", title='" + title + '\'' +
            ", rating='" + rating + '\'' +
            ", director='" + director + '\'' +
            ", releaseYear=" + releaseYear +
            '}';
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
