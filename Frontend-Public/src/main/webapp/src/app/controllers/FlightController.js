(function () {

    angular
        .module('app')
        .controller('FlightController', [
            '$scope',
            'NetworkService',
            '$stateParams',
            '$state',
            'BookingService',
            FlightController
        ]);

    function FlightController($scope, NetworkService, $stateParams, $state, BookingService) {
        var vm = this;


        vm.go = function (flight) {
            BookingService.selectedFlight = flight;
            $state.go('account');
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
