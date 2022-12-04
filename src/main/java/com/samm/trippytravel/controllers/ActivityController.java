package com.samm.trippytravel.controllers;

import com.samm.trippytravel.payload.request.activity.CreateActivityRequest;
import com.samm.trippytravel.payload.request.activity.UpdateActivityRequest;
import com.samm.trippytravel.payload.response.ActivityResponse;
import com.samm.trippytravel.repository.ActivityRepository;
import com.samm.trippytravel.services.ActivityService;
import lombok.RequiredArgsConstructor;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;
    private final ActivityRepository activityRepository;

    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ActivityResponse> createActivity(
            @RequestBody CreateActivityRequest createActivityRequest) {
        return new ResponseEntity<>(activityService.addActivity(createActivityRequest), HttpStatus.OK);
    }

    @GetMapping("{dayIdNumber}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ActivityResponse>> getActivitiesForDay(
            @PathVariable("dayIdNumber") String dayIdNumber) {
        return new ResponseEntity<>(activityService.getActivitiesForDay(dayIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("{activityIdNumber}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ActivityResponse> deleteActivity(
            @PathVariable("activityIdNumber") String activityIdNumber) {
        return new ResponseEntity<>(activityService.deleteActivity(activityIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("{dayIdNumber}/delete-by-day")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ActivityResponse>> deleteActsByDayId(
            @PathVariable("dayIdNumber") String dayIdNumber) {
        return new ResponseEntity<>(activityService.deleteActsByDayId(dayIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("{tripIdNumber}/delete-by-trip")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ActivityResponse>> deleteActsByTripId(
            @PathVariable("tripIdNumber") String tripIdNumber) {
        return new ResponseEntity<>(activityService.deleteActsByTripId(tripIdNumber), HttpStatus.OK);
    }

    @PutMapping("{activityIdNumber}/update")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ActivityResponse> updateActivity(
            @PathVariable("activityIdNumber") String activityIdNumber,
            @RequestBody UpdateActivityRequest updateActivityRequest) {
        return new ResponseEntity<>(activityService.updateActivity(activityIdNumber, updateActivityRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete() {
        activityRepository.deleteAll();
    }
}
