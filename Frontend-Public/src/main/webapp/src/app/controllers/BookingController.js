(function () {

    angular
        .module('app')
        .controller('BookingController', [
            'NetworkService',
            'BookingService',
            '$scope',
            BookingController
        ]);

    function BookingController(NetworkService, BookingService, $scope) {
        var vm = this;

        vm.numberOfSeats = 1;
        vm.seatClass = [
            "BUSINESS",
            "ECONOMY",
            "FIRST CLASS"
        ];
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


        $scope.$watch('vm.numberOfSeats', function () {
            // console.log('test1');
            // if (vm.numberOfSeats >= vm.seats.length) {
            //     console.log('test2');
            //     for (var i = 0; i < vm.numberOfSeats - vm.seats.length; i++) {
            //         vm.seats.push({});
            //     }
            // } else {
            //     console.log('test3');
            //     for (var i = vm.seats.length; i > vm.numberOfSeats; i--) {
            //         vm.seats.pop();
            //     }
            // }
        });

        $(document).ready(function () {
            $('select').material_select();
        });

    }
})();
