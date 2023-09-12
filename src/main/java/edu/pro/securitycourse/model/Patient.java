package edu.pro.securitycourse.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Patient(String name, String phoneNumber, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Patient(String id, String name, String phoneNumber, String description) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return getId().equals(patient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
