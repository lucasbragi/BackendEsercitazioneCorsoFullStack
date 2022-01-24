package it.Plansoft.esercitazionePlansoft.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.Plansoft.esercitazionePlansoft.dto.ProfessorDto;
import it.Plansoft.esercitazionePlansoft.model.BaseId;

import java.util.Set;

public interface IProfessorController {

    @Operation(summary = "Get a Professor by telephone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    ProfessorDto findProfessorByNumber(String number);

    @Operation(summary = "Get a Professor by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<ProfessorDto> findProfessorByName(String name);

    @Operation(summary = "Get a Professor by surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<ProfessorDto> findProfessorBySurname(String surname);

    @Operation(summary = "Get a Professor by name and surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<ProfessorDto> findProfessorByNameAndSurname(String name, String surname);

    @Operation(summary = "Get a Professor by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<ProfessorDto> findProfessorByCity(String city);

    @Operation(summary = "update Professor by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    ProfessorDto updateById(Long id, ProfessorDto DTO);

    // ProfessorDto addCourse(ProfessorDto professor, CourseDto course);

}
