package it.Plansoft.esercitazionePlansoft.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.Plansoft.esercitazionePlansoft.dto.StudentDto;
import it.Plansoft.esercitazionePlansoft.model.BaseId;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface IStudentController {

    @Operation(summary = "Get a Student by telephone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    StudentDto findStudentByNumber(String number);

    @Operation(summary = "Get a Student by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<StudentDto> findStudentByName(String name);

    @Operation(summary = "Get a Student by his surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<StudentDto> findStudentBySurname(String surname);

    @Operation(summary = "Get a Student by name and surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<StudentDto> findStudentByNameAndSurname(String name, String surname);

    @Operation(summary = "Get a Student by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    Set<StudentDto> findStudentByCity(String city);

    @Operation(summary = "update student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    StudentDto updateById(Long id, StudentDto DTO);


}
