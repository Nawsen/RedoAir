/**
 * Created by MBTAZ48 on 1/09/2016.
 */
(function () {

    angular
        .module('app')
        .factory('NetworkService', [
            '$http',
            'Constants',
            NetworkService
        ]);

    function NetworkService($http, Constants) {
        var BASE_URL = Constants.BASE_URL;
        return {
            getAllAirports: function () {
                return $http.get(BASE_URL + 'airport/all');
            },
            getFilteredAirports: function (filter) {
                return $http.get(BASE_URL + 'airport/all/' + filter);
            },
            getFlights: function (departureId, arrivalId) {
                return $http.get(BASE_URL + 'flight/find?departure=' + departureId + '&arrival=' + arrivalId);
            },
            getFilteredFlights: function (departure, arrival, startDate, endDate, typeSeat, placesLeft) {
                return $http.get(BASE_URL + 'flight/findFiltered?departure=' + departure + '&arrival=' + arrival + '&startDate=' + startDate + '&endDate=' + endDate
                    + '&typeSeat=' + typeSeat + '&placesLeft=' + placesLeft);
            },
            /*
             @QueryParam("departure") String departureCode,
             @QueryParam("arrival") String arrivalCode,
             @QueryParam("startDate") Double startDate,
             @QueryParam("endDate") Double endDate,
             @QueryParam("typeSeat")SeatType type,
             @QueryParam("placesLeft") Integer free
             */
            postRegister: function (user) {
                return $http.post(BASE_URL + 'customer/create', {
                    email: user.email,
                    firstName: user.firstName,
                    lastName: user.lastName,
                    password: user.password
                })
            },
            postBooking: function (booking) {
                return $http.post(BASE_URL + 'booking/order', booking);
            },
            getAllBookings: function () {
                return $http.get(BASE_URL + 'booking/all');
            },
            getFlightDetails: function (flightID) {
                return $http.get(BASE_URL + 'order/details/' + flightID);
            },


        }
    }

})();
