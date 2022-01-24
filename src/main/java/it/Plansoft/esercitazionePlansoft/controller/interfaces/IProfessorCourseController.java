package it.Plansoft.esercitazionePlansoft.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorCourseDto;
import it.Plansoft.esercitazionePlansoft.model.BaseId;

public interface IProfessorCourseController {

    @Operation(summary = "Add courses to a Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    ProfessorCourseDto addCourse(ProfessorCourseDto professorCourseDto);

    @Operation(summary = "Remove Courses from a Professor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    ProfessorCourseDto removeCourse(ProfessorCourseDto professorCourseDto);

    @Operation(summary = "Get Professor courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    ProfessorCourseDto getCourses(Long id);

}
