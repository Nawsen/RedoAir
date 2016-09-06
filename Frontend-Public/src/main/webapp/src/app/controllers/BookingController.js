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


        // Set default values
        vm.birthDate = new Date("6 September, 2016");
        vm.payment = 'CC';
        vm.numberOfSeats = 1;
        vm.seatClass = [
            "BUSINESS",
            "ECONOMY",
            "FIRST CLASS"
        ];
        vm.seatType = vm.seatClass[0];
        vm.seats = [{}];


        vm.getNumber = function (num) {
            return new Array(num);
        };

        vm.register = function () {
            NetworkService.postRegister(vm.user).then(function () {
                Materialize.toast('Successfully registered!', 1000);
            });
        };


        vm.addSeat = function () {
            vm.seats.push({});
        };
        vm.removeSeat = function (index) {
            console.log(index);
            console.log(vm.seats.splice(index, 1));
        };
        vm.book = function () {
            console.log($('.datepicker').val());
        };


        $scope.$watch("$('.datepicker').val()", function () {
            console.log(vm.birthDate);
        });


        vm.openDatePicker = function () {
            $('.datepicker').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 15 // Creates a dropdown of 15 years to control year
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
