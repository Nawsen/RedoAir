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
            'Constants',
            FlightController
        ]);

    function FlightController($scope, NetworkService, $stateParams, $state, BookingService, AuthenticationService, Constants) {
        var vm = this;

        vm.seatClass = Constants.SEATCLASS;
        vm.seatType = vm.seatClass[0];
        vm.go = function (flight) {
            console.log(flight);
            BookingService.selectedFlight = flight;
            if (AuthenticationService.isLoggedIn()) {
                $state.go('booking');
            } else {
                $state.go('account', {fromFlight: true});
            }
        };


        vm.getSeatsByClass = function (flight, seatClass) {
            console.log("test");
            if (seatClass == "FIRST CLASS") {
                seatClass = "FIRST_CLASS";
            }
            for (var i = 0; i < flight.availableSeats.length; i++) {
                if (flight.availableSeats[i].type == seatClass) {
                    return flight.availableSeats[i].amount;
                }
            }
        };

        vm.test = function () {
            // console.log($('.datepicker').val());
            console.log($('#startDate').val());
            console.log(new Date($('#startDate').val()));;
            console.log($('#endDate').val());
        };

        function getFlight() {
            NetworkService.getFlights($stateParams.departure, $stateParams.arrival).then(function (response) {
                vm.flights = response.data;
                // console.log(response.data);
            });
        }

        vm.flights = getFlight();

        vm.openStartDatePicker = function () {
            $('#startDate').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 200,
                onClose: vm.test,
                min: new Date()
                // selectYears: 15 // Creates a dropdown of 15 years to control year
            });
        };
        console.log(vm.startDate ? new Date(vm.startDate) : new Date(2016,1,1));
        vm.openEndDatePicker = function () {
            $('#endDate').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 200,
                onClose: vm.test,
                min: new Date()
                // min: vm.startDate ? new Date(vm.startDate) : new Date(2016,1,1),
                // selectYears: 15 // Creates a dropdown of 15 years to control year
            });
            console.log($('#endDate').attr('min'));
        };
        $(document).ready(function () {
            $('select').material_select();
        });
    }

})();
