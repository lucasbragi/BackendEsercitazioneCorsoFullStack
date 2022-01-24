package it.Plansoft.esercitazionePlansoft.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.Plansoft.esercitazionePlansoft.dto.CourseDto;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.model.BaseId;

import java.util.Set;

public interface ICourseController {

    @Operation(summary = "Get a Course by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<CourseDto> findByName(String name);

    @Operation(summary = "Get a Course by his Professor name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<CourseDto> findByProfessorName(String name);

    @Operation(summary = "update course by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    CourseDto updateById(Long id, CourseDto DTO);

}
