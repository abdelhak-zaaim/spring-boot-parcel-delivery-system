/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:25
 *  * @modified : 20/05/2024, 21:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class ParcelVolume {
    float height;
    float width;
    float length;

    public float getVolume() {
        return height * width * length;
    }
}
