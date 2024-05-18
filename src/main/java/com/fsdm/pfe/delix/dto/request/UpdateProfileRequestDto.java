/*
 *
 *  * @project : DeliX
 *  * @created : 18/05/2024, 16:20
 *  * @modified : 18/05/2024, 16:20
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import com.fsdm.pfe.delix.util.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Value
@Builder
public class UpdateProfileRequestDto implements Serializable {
    @NotNull
    @NotEmpty
    String firstName;
    @NotNull
    @NotEmpty
    String lastName;
    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String phoneNumber;


}
