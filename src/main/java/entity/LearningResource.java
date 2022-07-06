package entity;

import java.time.LocalDate;

public class LearningResource {
    private Integer id;
    private String name;
    private Double cost_price;
    private Double selling_price;
    private LearningResourcesStatus learningResourcesStatus;
    private LocalDate created_date;
    private LocalDate published_date;
    private LocalDate retired_date;

    public LearningResource(){

    }

    public LearningResource(Integer id, String name, Double cost_price, Double selling_price, LearningResourcesStatus learningResourcesStatus, LocalDate created_date, LocalDate published_date, LocalDate retired_date) {
        this.id = id;
        this.name = name;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.learningResourcesStatus = learningResourcesStatus;
        this.created_date = created_date;
        this.published_date = published_date;
        this.retired_date = retired_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost_price() {
        return cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(Double selling_price) {
        this.selling_price = selling_price;
    }

    public LearningResourcesStatus getLearningResourcesStatus() {
        return learningResourcesStatus;
    }

    public void setLearningResourcesStatus(LearningResourcesStatus learningResourcesStatus) {
        this.learningResourcesStatus = learningResourcesStatus;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public LocalDate getPublished_date() {
        return published_date;
    }

    public void setPublished_date(LocalDate published_date) {
        this.published_date = published_date;
    }

    public LocalDate getRetired_date() {
        return retired_date;
    }

    public void setRetired_date(LocalDate retired_date) {
        this.retired_date = retired_date;
    }
}
