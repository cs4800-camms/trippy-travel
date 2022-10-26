import "bootstrap/dist/css/bootstrap.min.css";
import "./ItineraryPage.css"

export default function ItineraryPage() {
    return (
        <body>
            <h1>Plan Your Trip</h1>
            <br></br><br></br>
            <div class="row">
                <div class="col">
                    <div class="list-group">
                        <h2> Your itinerary </h2>
                        <a href="#" class="list-group-item" data-toggle="collapse">Day 1</a>
                        <div class="list-group">
                            <a href="#" class="list-group-item">Breakfast at cafe</a>
                            <a href="#" class="list-group-item">Visit the garden</a>
                            <a href="#" class="list-group-item">Go to club</a>
                        </div>

                        <a href="#" class="list-group-item" data-toggle="collapse">Day 2</a>
                        <div class="list-group">
                            <a href="#" class="list-group-item">Lunch at Mcdonalds</a>
                            <a href="#" class="list-group-item">Go to Lake ArrowHead</a>
                        </div>

                        <a href="#" class="list-group-item" data-toggle="collapse">Day 3</a>
                        <div class="list-group">
                            <a href="#" class="list-group-item">Hiking at a trail</a>
                            <a href="#" class="list-group-item">Lunch at Chipotle</a>
                            <a href="#" class="list-group-item">Shopping at century city</a>
                            <a href="#" class="list-group-item">Dinner at Shake Shack</a>
                            <a href="#" class="list-group-item">Go the club</a>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <h2>Activity Suggestions</h2>
                </div>
            </div>
        </body>
    );
}