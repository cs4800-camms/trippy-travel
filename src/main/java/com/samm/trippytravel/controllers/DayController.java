package com.samm.trippytravel.controllers;

import com.samm.trippytravel.payload.request.day.CreateDayRequest;
import com.samm.trippytravel.payload.request.day.UpdateDayRequest;
import com.samm.trippytravel.payload.response.DayResponse;
import com.samm.trippytravel.repository.DayRepository;
import com.samm.trippytravel.services.DayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/days")
public class DayController {
    private final DayService dayService;
    private final DayRepository dayRepository;

    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DayResponse> createDay(
            @RequestBody CreateDayRequest createDayRequest) {
        return new ResponseEntity<>(dayService.addDay(createDayRequest), HttpStatus.OK);
    }

    @GetMapping("{tripIdNumber}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DayResponse>> getDaysForTrip(
            @PathVariable("tripIdNumber") String tripIdNumber) {
        return new ResponseEntity<>(dayService.getDaysForTrip(tripIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("{dayIdNumber}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DayResponse> deleteDay(
            @PathVariable("dayIdNumber") String dayIdNumber) {
        return new ResponseEntity<>(dayService.deleteDay(dayIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("{tripIdNumber}/delete-by-trip")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DayResponse>> deleteDaysByTripId(
            @PathVariable("tripIdNumber") String tripIdNumber) {
        return new ResponseEntity<>(dayService.deleteDaysByTripId(tripIdNumber), HttpStatus.OK);
    }

    @PutMapping("{dayIdNumber}/update")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DayResponse> updateDay(
            @PathVariable("dayIdNumber") String dayIdNumber,
            @RequestBody UpdateDayRequest updateDayRequest) {
        return new ResponseEntity<>(dayService.updateDay(dayIdNumber, updateDayRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete() {
        dayRepository.deleteAll();
    }

//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminAccess() {
//        return "Admin Board.";
//    }
}
