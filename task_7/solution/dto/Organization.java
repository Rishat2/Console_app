package dto;

import java.util.Date;
import java.util.Objects;

public class Organization{

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле может быть null

    public Organization(String name, Coordinates coordinates, Double annualTurnover, OrganizationType type, Address officialAddress) {
        id = IdGenerator.nextId();
        setName(name);
        setCoordinates(coordinates);
        creationDate = new Date();
        setAnnualTurnover(annualTurnover);
        setType(type);
        setOfficialAddress(officialAddress);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название организации не должно быть пустой строкой!");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates == null) {
            throw new IllegalArgumentException("Координаты организации должны быть определены!");
        }
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Double annualTurnover) {
        if(annualTurnover != null && annualTurnover <= 0) {
            throw new IllegalArgumentException("Ежегодный оборот организации должен быть больше нуля!");
        }
        this.annualTurnover = annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        if(type == null) {
            throw new IllegalArgumentException("Тип организации должен быть определен!");
        }
        this.type = type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

}
