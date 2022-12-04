package com.samm.trippytravel.repository;

import com.samm.trippytravel.data.domain.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, Long> {
    @Query(value="{ 'day_id' : ObjectId( ?0 )}")
    List<Activity> getActivitiesByDayId(String dayId);

    @Query(value="{'_id' : ?0}", delete = true)
    Activity deleteById(String id);

    @Query(value="{'_id' : ?0}")
    Activity getActivity(String id);

    @Query(value="{'day_id' : ObjectId( ?0 )}", delete = true)
    List<Activity> deleteActsByDayId(String dayId);

    @Query(value="{'trip_id' : ObjectId( ?0 )}", delete = true)
    List<Activity> deleteActsByTripId(String tripId);
}
