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
            getFlights: function (departureId, arrivalId) {
                return $http.get(BASE_URL + 'flight/find?departure=' + departureId + '&arrival=' + arrivalId);
            },
            getFilteredAirports: function (filter) {
                return $http.get(BASE_URL + 'airport/all/' + filter);
            },
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
            }

        }
    }

})();
