(function () {

    angular
        .module('app')
        .controller('IndexController', [
            'NetworkService',
            'Constants',
            'AuthenticationService',
            '$state',
            '$scope',
            '$window',
            IndexController
        ]);

    function IndexController(NetworkService, Constants, AuthenticationService, $state, $scope, $window) {
        console.log("works");
        var vm = this;


        vm.bookings = function () {
            $state.go('booking-overview');
        };

        vm.login = function () {
          $state.go('account')
        };
        vm.logout = function () {
            AuthenticationService.logOut();
            $window.location.reload();
            // $state.reload();
        };

        $scope.$watch(AuthenticationService.isLoggedIn(), function () {
            vm.isLoggedIn = AuthenticationService.isLoggedIn();
        })
    }

})();

