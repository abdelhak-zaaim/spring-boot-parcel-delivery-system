/*
 *
 *  * @project : DeliX
 *  * @created : 25/05/2024, 18:57
 *  * @modified : 16/05/2024, 20:56
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

/*
 *
 *  * @project : DeliX
 *  * @created : 11/05/2024, 13:48
 *  * @modified : 11/05/2024, 13:48
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Area {

    Date createdAt;
    Date updatedAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code", referencedColumnName = "code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private City cityCode;
    private String postCode;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}

