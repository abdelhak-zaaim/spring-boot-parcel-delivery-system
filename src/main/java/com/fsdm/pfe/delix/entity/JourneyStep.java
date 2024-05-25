/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 17:25
 *  * @modified : 20/05/2024, 17:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.StepStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StepStatus stepStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Area area;

}
