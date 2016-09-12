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

        vm.filterParameters = {};

        vm.seatClass = Constants.SEATCLASS;
        vm.seatType = vm.seatClass[0];
        vm.filterParameters.seatTypeFilter = vm.seatClass[0];

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
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            if (startDate != undefined) {
                if (startDate.length != 0) {
                    vm.startDate = $('#startDate').val();
                }
            }
            if (endDate != undefined) {
                if (endDate.length != 0) {
                    vm.endDate = $('#endDate').val();
                }
            }
        };

        vm.filter = function () {
            getFilteredFlights();
        };

        function getFlight() {
            NetworkService.getFlights($stateParams.departure, $stateParams.arrival).then(function (response) {
                vm.flights = response.data;
            });
        }

        function getFilteredFlights() {
            // console.log("filter");
            // console.log(vm.startDate);
            // console.log(vm.endDate.length);
            if (vm.startDate == undefined) {
                vm.filterParameters.startDate = formatDateToUnix(new Date());
            } else {
                vm.filterParameters.startDate = formatStringDateToUnix(vm.startDate);
            }

            if (vm.endDate == undefined) {
                vm.filterParameters.endDate = formatDateToUnix(new Date(new Date().setFullYear(new Date().getFullYear() + 1)));
            } else {
                vm.filterParameters.endDate = formatStringDateToUnix(vm.endDate);
            }

            if (vm.numberOfSeats == undefined) {
                vm.filterParameters.numberOfSeats = 0;
            } else {
                vm.filterParameters.numberOfSeats = vm.numberOfSeats;
            }

            NetworkService.getFilteredFlights($stateParams.departure, $stateParams.arrival,
                vm.filterParameters.startDate,
                vm.filterParameters.endDate,
                vm.filterParameters.seatTypeFilter,
                vm.filterParameters.numberOfSeats).then(function (response) {
                vm.flights = response.data;
            });
        }

        function formatStringDateToUnix(dateInStringFormat) {
            return Math.floor(new Date(dateInStringFormat).getTime() / 1000);
        }

        function formatDateToUnix(date) {
            return Math.floor(date.getTime() / 1000);
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
        console.log(vm.startDate ? new Date(vm.startDate) : new Date(2016, 1, 1));
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
