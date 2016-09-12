(function () {

    angular
        .module('app')
        .controller('BookingController', [
            'NetworkService',
            'BookingService',
            '$scope',
            '$timeout',
            '$state',
            'Constants',
            BookingController
        ]);

    function BookingController(NetworkService, BookingService, $scope, $timeout, $state, Constants) {
        // console.log(BookingService.selectedFlight);
        if (BookingService.selectedFlight) {
            var vm = this;

            vm.selectedFlight = BookingService.selectedFlight;
            console.log(BookingService.selectedFlight);
            // Set default values
            vm.birthDate = new Date("6 September, 2016");
            vm.numberOfSeats = 1;
            vm.seatClass = Constants.SEATCLASS;
            vm.booking = {};
            vm.booking.payment = 'CC';
            vm.booking.seatType = vm.seatClass[0];
            vm.booking.seats = [{}];


            // NetworkService.getFlightDetails(vm.selectedFlight.flightId).then(function (response) {
            //     vm.price = response.data.
            // });
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
            vm.book = function (form) {
                if (form.$valid) {
                    NetworkService.postBooking(mapBookingToBackend(vm.booking)).then(function success(response) {
                        $state.go('booking-overview');
                        Materialize.toast('Booking successful!', 2000);
                    }, function fail(response) {
                        Materialize.toast('Something broke', 2000);
                    });
                }
            };

            function mapBookingToBackend(booking) {
                var mappedBooking = {};


                mappedBooking.flightNumber = vm.selectedFlight.flightNumber;
                mappedBooking.creditCardNumber = booking.creditcard;
                mappedBooking.cvc = booking.cardcheck;
                mappedBooking.expiryMonth = booking.cardmonth;
                mappedBooking.expiryYear = booking.cardyear;
                mappedBooking.tickets = [];
                for (var i = 0; i < booking.seats.length; i++) {
                    var ticket = booking.seats[i];
                    ticket.seatType = booking.seatType;
                    mappedBooking.tickets.push(ticket);
                }

                console.log(mappedBooking);
                return mappedBooking;
            }

            /*
             @Transient
             private Long creditCardNumber;
             @Transient
             private Integer cvc;
             @Transient
             private Integer expiryMonth;
             @Transient
             private Integer expiryYear;
             */

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

        } else {
            $state.go('overview');
        }
    }

})();
