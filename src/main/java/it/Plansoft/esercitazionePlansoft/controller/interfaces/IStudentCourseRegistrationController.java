package it.Plansoft.esercitazionePlansoft.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.Plansoft.esercitazionePlansoft.dto.StudentCourseRegistrationDto;
import it.Plansoft.esercitazionePlansoft.model.BaseId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IStudentCourseRegistrationController {

    @Operation(summary = "Registration of student to courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    StudentCourseRegistrationDto addRegistration(StudentCourseRegistrationDto studentCourseRegistrationDto);

    @Operation(summary = "Get Student registration course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    StudentCourseRegistrationDto getRegistration(Long id);

    @Operation(summary = "Remove courses from a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found items",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseId.class))}),
            @ApiResponse(responseCode = "400", description = "invalid items",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "items not found",
                    content = @Content)})
    StudentCourseRegistrationDto removeRegistration(Long studentId, Long courseId);

}
