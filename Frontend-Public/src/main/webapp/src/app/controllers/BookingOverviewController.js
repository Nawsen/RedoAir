(function () {

    angular
        .module('app')
        .controller('BookingOverviewController', [
            'NetworkService',
            'BookingService',
            '$scope',
            '$timeout',
            '$state',
            'AuthenticationService',
            BookingOverviewController
        ]);

    function BookingOverviewController(NetworkService, BookingService, $scope, $timeout, $state, AuthenticationService) {
        if (AuthenticationService.isLoggedIn()) {
            var vm = this;

            vm.bookings = NetworkService.getAllBookings().then(function (response) {
                console.log(response.data);
                return response.data;
            }, function () {
                Materialize.toast("Couldn't get bookings", 1000);
            })
        } else {
            $state.go('overview');
        }
    }

})();
