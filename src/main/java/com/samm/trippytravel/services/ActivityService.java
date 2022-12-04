package com.samm.trippytravel.services;

import com.samm.trippytravel.data.domain.Activity;
import com.samm.trippytravel.payload.request.activity.CreateActivityRequest;
import com.samm.trippytravel.payload.request.activity.UpdateActivityRequest;
import com.samm.trippytravel.payload.response.ActivityResponse;
import com.samm.trippytravel.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityResponse addActivity(CreateActivityRequest createActivityRequest) {
        return toActivityResponse(activityRepository.insert(Activity.builder()
                .trip_id(createActivityRequest.getTrip_id())
                .day_id(createActivityRequest.getDay_id())
                .checked(createActivityRequest.isChecked())
                .name(createActivityRequest.getName())
                .build()));
    }

    public List<ActivityResponse> getActivitiesForDay(String dayIdNumber) {
        return toListActivityResponse(activityRepository.getActivitiesByDayId(dayIdNumber));
    }

    public ActivityResponse deleteActivity(String activityIdNumber) {
        return toActivityResponse(activityRepository.deleteById(activityIdNumber));
    }

    public List<ActivityResponse> deleteActsByDayId(String dayIdNumber) {
        return toListActivityResponse(activityRepository.deleteActsByDayId(dayIdNumber));
    }

    public List<ActivityResponse> deleteActsByTripId(String tripIdNumber) {
        return toListActivityResponse(activityRepository.deleteActsByTripId(tripIdNumber));
    }

    public ActivityResponse updateActivity(String activityIdNumber, UpdateActivityRequest updateActivityRequest) {
        Activity currentActivity = activityRepository.getActivity(activityIdNumber);

        return toActivityResponse(activityRepository.save(Activity.builder()
                ._id(activityIdNumber)
                .trip_id(currentActivity.getTrip_id())
                .day_id(currentActivity.getDay_id())
                .checked(updateActivityRequest.isChecked())
                .name(currentActivity.getName())
                .build()));
    }

    private ActivityResponse toActivityResponse(Activity activity) {
        return ActivityResponse.builder()
                ._id(activity.get_id())
                .trip_id(activity.getTrip_id().toString())
                .day_id(activity.getDay_id().toString())
                .checked(activity.isChecked())
                .name(activity.getName())
                .build();
    }

    private List<ActivityResponse> toListActivityResponse(List<Activity> activityList) {
        return activityList.stream()
                .map(this::toActivityResponse)
                .collect(toList());
    }
}
