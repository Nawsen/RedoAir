(function () {

    angular
        .module('app')
        .controller('FlightController', [
            '$scope',
            'NetworkService',
            '$stateParams',
            '$state',
            'BookingService',
            'AuthenticationService',
            FlightController
        ]);

    function FlightController($scope, NetworkService, $stateParams, $state, BookingService, AuthenticationService) {
        var vm = this;


        vm.go = function (flight) {
            BookingService.selectedFlight = flight;
            if (AuthenticationService.isLoggedIn()) {
                $state.go('booking');
            } else {
                $state.go('account', {fromFlight: true});
            }
        };


        function getFlight() {
            NetworkService.getFlights($stateParams.departure, $stateParams.arrival).then(function (response) {
                vm.flights = response.data;
                // console.log(response.data);
            });
        }

        vm.flights = getFlight();
    }

})();
