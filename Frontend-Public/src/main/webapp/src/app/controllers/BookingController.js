(function () {

    angular
        .module('app')
        .controller('BookingController', [
            'NetworkService',
            'BookingService',
            '$scope',
            '$timeout',
            BookingController
        ]);

    function BookingController(NetworkService, BookingService, $scope, $timeout) {
        var vm = this;

        vm.selectedFlight = BookingService.selectedFlight;
        console.log(BookingService.selectedFlight);
        // Set default values
        vm.birthDate = new Date("6 September, 2016");
        vm.numberOfSeats = 1;
        vm.seatClass = [
            "BUSINESS",
            "ECONOMY",
            "FIRST CLASS"
        ];
        vm.booking = {};
        vm.booking.payment = 'CC';
        vm.booking.seatType = vm.seatClass[0];
        vm.booking.seats = [{}];


        vm.getNumber = function (num) {
            return new Array(num);
        };

        vm.register = function () {
            NetworkService.postRegister(vm.user).then(function () {
                Materialize.toast('Successfully registered!', 1000);
            });
        };


        vm.addSeat = function () {
            vm.booking.seats.push({});
        };
        vm.removeSeat = function (index) {
            vm.booking.seats.splice(index, 1);
        };
        vm.book = function () {
            vm.booking.birthDate = $('.datepicker').val();

            console.log(vm.booking);
        };


        // $scope.$watch("$('.datepicker').val()", function () {
        //     console.log(vm.birthDate);
        // });


        vm.openDatePicker = function () {
            $('.datepicker').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 200
                // selectYears: 15 // Creates a dropdown of 15 years to control year
            });
        };
        // vm.initializeDatePicker();
        // function initializeDatePicker() {
        //     $('.datepicker').pickadate({
        //         selectMonths: true, // Creates a dropdown to control month
        //         selectYears: 15 // Creates a dropdown of 15 years to control year
        //     });
        // }


        $(document).ready(function () {
            $('select').material_select();
        });
        // $('.datepicker').pickadate({
        //     selectMonths: true, // Creates a dropdown to control month
        //     selectYears: 15 // Creates a dropdown of 15 years to control year
        // });
    }
})();
