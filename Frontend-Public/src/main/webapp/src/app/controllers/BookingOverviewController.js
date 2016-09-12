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

            vm.bookings = [];

            try {
                NetworkService.getAllBookings().then(function (response) {
                    vm.bookings = response.data;
                    console.log(response.data);
                    // return response.data;
                }, function () {
                    // Materialize.toast("Couldn't get bookings", 1000);
                });
            } catch (error) {
                return null;
                //No need to do anything with the error, this means the user has no bookings.
            }
            $(document).ready(function () {
                $('.collapsible').collapsible({
                    accordion: false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
                });
            });

        } else {
            $state.go('overview');
        }
    }
})();
