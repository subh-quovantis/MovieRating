package com.example.movieratingpoc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateMovieDTO {

    @NotNull
    @Min(value = 0, message = "Rate above 0")
    @Max(value = 5, message = "Rate below 5")
    private float rating;

}
